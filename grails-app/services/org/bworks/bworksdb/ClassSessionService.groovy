package org.bworks.bworksdb

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJValueFormatter;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.output.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.JasperViewer;

class ClassSessionService {

    boolean transactional = true

    def getBlankAttendanceSheet(classSessionId) {

        def classSessionInstance = ClassSession.get(classSessionId)
        def reportData = attendanceMapForSession(classSessionInstance)

        // Create thin borders for columns
        Style columnStyle = new Style();
        columnStyle.setBorder(Border.THIN);

        Style subtitleStyle = new Style();
        subtitleStyle.setHorizontalAlign(HorizontalAlign.CENTER)

        FastReportBuilder drb = new FastReportBuilder();
        drb = drb.addColumn("Student","studentName",String.class.getName(),30, columnStyle);
        classSessionInstance.lessonDates.each { ld ->
            drb = drb.addColumn(ld.lesson.name.toString() + 
                                ", " + ld.lessonDate.format('MMM. d').toString(),
                                "attended_${ld.id}",String.class.getName(),15,
                                columnStyle);
        }
        drb.setPageSizeAndOrientation(Page.Page_A4_Landscape())
        drb.setUseFullPageWidth(true)

        DynamicReport dr = 
            drb.setTitle("Attendance : ${classSessionInstance.name}")
               .setSubtitle(classSessionInstance.startDate.format('MMMM d, yyyy'))
               .setSubtitleStyle(subtitleStyle)
               .setUseFullPageWidth(true).build();
 
        JRDataSource ds = new JRBeanCollectionDataSource(reportData);   
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, 
                new ClassicLayoutManager(), ds);
        def reportFormat = 'PDF';
        ReportWriter reportWriter = 
            ReportWriterFactory
            .getInstance()
            .getReportWriter(jp, reportFormat, 
                [(JRHtmlExporterParameter.IMAGES_URI): "".toString()]);

        return reportWriter
    }

    def attendanceMapForSession(classSessionInstance) {
        def enrolledStudents = []

        def students = classSessionInstance.enrollments.each { enr ->
            def row = [:]
            row.studentName = enr.student.toString()
            classSessionInstance.lessonDates.each { ld ->
               row["attended_${ld.id}"] = " "
            }
            enrolledStudents << row
        }
        return enrolledStudents
    }

    // returns a map like this:
    // <studentId> : [ attendanceCount:6, totalLessons:6, 
    //                         missed : [lessonDate1, lessonDate2] ]
    def attendancesForSession(classSessionInstance) {
        def lessonDates = classSessionInstance.lessonDates.collect {
            it
        }

        def results = [:]
        
        // initialize a map for each student enrolled in this session.
        // map will contain # of classes attended, and a list of classes missed,
        // along with totalLessons in this session
        classSessionInstance.enrollments.each { enr ->
            results[enr.student.id] = [ attendanceCount : 0 , 
                                        totalLessons : lessonDates.size(),
                                        missed : lessonDates.clone() ]
        }

        classSessionInstance.lessonDates.each {
            it.attendees.each { att ->
                if (att.status == 'present') {
                    results[att.student.id].attendanceCount += 1
                    // remove this attendance's lessonDate from the student's "missed" list.
                    results[att.student.id].missed.remove(att.lessonDate)
                }
            }
        }
        return results
    }

    // enrolls a student, or changes their status from dropped out
    // to "in progress"
    def enrollStudent(studentInstance, classSessionInstance) {
        def msgs = [:]
        def enr = classSessionInstance.enrollments.find {
            it.student == studentInstance
        }

        if (enr) {
            enr.status = EnrollmentStatus.IN_PROGRESS
            enr.save()
        }
        else {
            def e = new Enrollment(student:studentInstance, classSession: classSessionInstance)
            classSessionInstance.addToEnrollments(e)
            if (classSessionInstance.save(flush:true)) {
            }
        }
        
        msgs['enrolledStudents'] = activeEnrollments(classSessionInstance).size()
        return msgs
    }

    // Removes an enrollment, unless there's already attendances in 
    // this class session for this student.  If there's already attendences,
    // then mark the enrollment as dropout.
    def disrollStudent(studentInstance, classSessionInstance) {
        def msgs = [:]
        def enr = classSessionInstance.enrollments.find {
            it.student == studentInstance
        }

        if (enr) {
            def attendance = classSessionInstance.lessonDates*.attendees*.find { attendee ->
                attendee.student == studentInstance && attendee.status == 'present'
            }
            
            if (attendance) {
                enr.status = EnrollmentStatus.DROPPED_OUT
                enr.save()
            }
            else {
                classSessionInstance.removeFromEnrollments(enr)
                enr.delete(flush : true)
            }

        }

        msgs['enrolledStudents'] = activeEnrollments(classSessionInstance).size()

        return msgs
    }

    def activeEnrollments(classSessionInstance) {
        classSessionInstance.enrollments.findAll {
            it.status != EnrollmentStatus.DROPPED_OUT
        }
    }

    def welcomeLetterReportData(classSessionInstance) {
        def reportData = [ forwardParams : [:] ]
        reportData['forwardParams']['PROGRAM_NAME'] = classSessionInstance.course.name
        reportData['forwardParams']['START_DATE'] = classSessionInstance.startDate.toString()

        println "Enrollments are: " + classSessionInstance.enrollments.size()
        def contacts = classSessionInstance.enrollments.collect {
            println "Boo"
            it.student.contact
        }.unique()

        reportData['contacts'] = contacts.collect {
            buildContactData(it)
        }

        return reportData

    }

    // returns an array of contact information, used in Welcome Letter
    // TODO: Refactor this method and similar method in CourseSessionService
    // into perhaps the ContactService or something.
    // (After writing tests, of course)
    def buildContactData(contactInstance) {
        
        def addr = [ contactInstance.address1 , contactInstance.address2 ?: '' ]
        def csz = [ contactInstance.city ?: '' , contactInstance.state ?: '', contactInstance.zipCode ?: '']
        
        def reportData = [
            CONTACT_NAME:contactInstance,
            CONTACT_ADDRESS:addr.join('<BR />'),
            // TODO Contact Notes, mmmk?
            CONTACT_NOTES:'',
            CONTACT_CITY_STATE_ZIP:csz.join(', '),
            CONTACT_EMAIL:contactInstance.emailAddress,
            CONTACT_PHONE:contactInstance.phoneNumbers.join('<br />'),
            CONTACT_ID:contactInstance.id
        ]        

        return reportData
    }
}

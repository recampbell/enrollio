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
    // studentId : [ total:6, attended : 5, missed : [lessonDate1, lessonDate2] ]
    // where (3) above is the ID(s) of the lessonDates that were missed.
    //     summary: '4 of 5']
    def attendancesForSession(classSessionInstance) {
        // get lesson dates for this class session
        // each lesson date -> get attendences
        // go through each attendance and add 1 to attended
        // get enrollments for this session
        // for each enrollment
        // --> find attendences for this student
        //
        def results = [:]
        classSessionInstance.lessonDates.each {
            println "class session insance: " + it
            it.attendees.each { att ->
                if (!results[att.student.id]) {
                    results[att.student.id] = [ attendanceCount : 0 ]
                }
                println "Status meow ${att.status}"
                if (att.status == 'present') {
                    println "Adding meow ${att.student.id}"
                    results[att.student.id].attendanceCount += 1
                }
            }
        }
        return results
    }
}








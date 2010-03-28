package org.bworks.bworksdb

import org.bworks.bworksdb.util.TestKeys
import org.bworks.bworksdb.auth.*
import org.grails.comments.*
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.util.*

class DataLoadingService {

    boolean transactional = true

    // load data from hard-coded filenames
    def loadFromFiles() {

      def importDir = ApplicationHolder.application.parentContext.getResource("/WEB-INF/importData").getFile()

      def classFile = new File(importDir, 'Class.zml')
      if (classFile.exists()) {
          log.info("adding class session data from ${classFile.name}")
          def xml = classFile.getText()
          loadClassSessions(xml)
          log.info("Done adding student data from ${classFile.name}")
      }

      def contactFile = new File(importDir, 'Contact.xml')
      if (contactFile.exists()) {
          log.info("Begin adding contact data from ${contactFile.name}")
          def xml = contactFile.getText()
          loadContacts(xml)
          log.info("Done adding contact data from ${contactFile.name}")
      }

      def studentFile = new File(importDir, 'Student.xml')
      if (studentFile.exists()) {
          log.info("Begin adding student data from ${studentFile.name}")
          def xml = studentFile.getText()
          loadStudents(xml)
          log.info("Done adding student data from ${studentFile.name}")
      }

    }

    def loadClassSessions(xmlString, course = null) {
        if (!course) {
            course = getDefaultCourse()
        }
        def xml = new XmlSlurper().parseText(xmlString)
		def sessions = xml.children()
        sessions.each { xmlSess ->
            // Take first class date and put it into the name of the session
            // Date format looks like this:  
            //     <Class1Date>2006-03-11T00:00:00</Class1Date>
            def xmlStartDate = xmlSess.Class1Date.text().split('T')[0]
            def cs = new ClassSession(course:course, 
                name:xmlStartDate, 
                startDate: Date.parse('yyyy-MM-dd', xmlStartDate))
            if (cs.validate() && cs.save()) {
                log.info("Imported class session ${cs.name} id: ${cs.id}")
                addCommentAboutId(cs, xmlSess.ClassID.text())
                // Now, load some awesome lesson dates for this session
                // from our import data.
                loadLessonDates(cs, xmlSess)
            }
            else {
                log.error("Couldn't import class session.  Errors are: ")
                cs.errors.allErrors.each {
                    log.error("Error: ${it}")
                }
            }
        }
    }

    // Load lesson dates from Class Session XML.
    // XML looks like this:
    // <Class>
    //     <Class1Date>2006-03-11T00:00:00</Class1Date>
    //     <Class2Date>2006-03-18T00:00:00</Class1Date>
    def loadLessonDates(cs, xmlSess) {
        def lessons = getDefaultCourse().lessons

        def eacLessons = [ 
           Lesson.findByName(TestKeys.LESSON_KIDS_AEC_INTRO),
           Lesson.findByName('Scratch Programming'),
           Lesson.findByName('Word Processing'),
           Lesson.findByName('Presentations'),
           Lesson.findByName('Email and WWW'),
           Lesson.findByName('Graduation')
        ]

        (1..6).each {
            def dt = xmlSess.getProperty("Class${it}Date").text().split("T")[0]

            def lessonDate = new LessonDate(
                 lesson:eacLessons[it - 1],
                 lessonDate:Date.parse('yyyy-MM-dd', dt))
            cs.addToLessonDates(lessonDate)
        }
    }

    def loadStudents(xmlString) {
        def xml = new XmlSlurper().parseText(xmlString)
		def students = xml.children()
        students.each { xmlStu ->
            def stu = new Student(firstName : xmlStu.FirstName.text(),
                                  lastName  : xmlStu.LastName.text())

            stu.emailAddress = xmlStu.email.text()
            if(xmlStu.Grade.text()) {
                stu.grade = xmlStu.Grade.text().toInteger()
            }
            stu.gender = xmlStu.Gender.text()
            if (xmlStu.BirthDate.text()) {
                stu.birthDate = Date.parse("yyyy-MM-dd",xmlStu.BirthDate.text() )
            }
            
            def con = findContactByOldId(xmlStu.ParentID.text())
            if (con) {
                con.addToStudents(stu)
            }

            if (stu.validate() && stu.save()) {
                if (xmlStu.Notes.text()) {
                    addComment(stu, xmlStu.Notes.text())
                }

                if (xmlStu.ClassID.text() != '') {
                    loadEnrollment(stu, xmlStu)
                }
                else {
                    loadInterest(stu)
                }
            
                log.info("Student ${stu} imported.")
            }
            else {
                log.error("Couldn't import student.  Errors are: ")
                stu.errors.allErrors.each {
                    log.error("Error: ${it}")
                }
            }
        }
    }

    def loadEnrollment(stu, xmlStu) {
        if (xmlStu.ClassID.text() != '') {
            log.info("Trying to find class session '" + xmlStu.ClassID.text() + "'")
            def cs = findClassSessionByOldId(xmlStu.ClassID.text())
            if (cs) {
                log.info("Found class session " + cs)
                log.info("Trying to add enrollment for student" + stu)
                log.info("Deciding enrollment status:")
                def enr = new Enrollment(student:stu)
                if (xmlStu.GraduateDate.text() != '') {
                    // then student graduated.
                    // Graduation date is tracked with the Class Session, so just
                    // mark enrollment as GRADUATED and throw away GraudateDate
                    enr.status = EnrollmentStatus.GRADUATED
                }
                else if (xmlStu.DropOut.text() == "1") { 
                    enr.status = EnrollmentStatus.DROPPED_OUT
                }
                cs.addToEnrollments(enr)
            }
        }
    }

        
    def loadInterest(stu) {
        // create an interest in the default course for this student
        // use the signup date for the student's parent as the signupDate for the Interest
        def crs = getDefaultCourse()
        def interest = new Interest(active:true, student:stu, 
                                    course: crs,
                                    signupDate:stu.contact.signupDate).save()        
        crs.addToInterests(interest) 
        stu.addToInterests(interest) 
    }

    def loadContacts(xmlString) {
        def xml = new XmlSlurper().parseText(xmlString)
		def contacts = xml.children()
        contacts.each { xmlCon ->
            def con = new Contact(lastName:xmlCon.LastName.text(),
                                  firstName:xmlCon.FirstName.text())

            con.address1 = xmlCon.Address1.text()
            con.address2 = xmlCon.Address2.text()
            con.city = xmlCon.City.text()
            con.zipCode = xmlCon.Zip.text()
            con.state = xmlCon.State.text()

            if (xmlCon.PrimaryPhone != '') {
                con.addToPhoneNumbers(new PhoneNumber(label:"Home", phoneNumber:xmlCon.PrimaryPhone.text()))
            }

            if (xmlCon.SecondPhone != '') {
                log.error("found second phone for ${con.lastName} ${xmlCon.SecondPhone}")
                con.addToPhoneNumbers(new PhoneNumber(label:"Other", phoneNumber:xmlCon.SecondPhone.text()))
            }

            if (xmlCon.ParentEmail != '') {
                con.emailAddress = xmlCon.ParentEmail.text()
            }

            con.cannotReach = xmlCon.CouldNotReach.text() == '1' ? true : false
    
            def signupDateString = xmlCon.DateOfSignUp.text().split('T')[0]
            // Record this date to the SignupDate, also
            con.signupDate = Date.parse('yyyy-MM-dd', signupDateString)

            if (con.validate() && con.save()) {
                addCommentAboutId(con, xmlCon.ParentID.text())

                if (xmlCon.Note != '') {
                    addComment(con, xmlCon.Note?.text())
                }
                if (xmlCon.InfoTakenBy != '') {
                    log.error("Adding infotakenby: ${xmlCon.InfoTakenBy?.text()}")
                    addComment(con, 'Info taken by: ' + xmlCon.InfoTakenBy.text())
                }

                // Add comment about the date that contact signed up.
                // Duplicate functionality of signupDate, but what the heck
                addComment(con, 'Signup Date:' + signupDateString)

                log.info("Imported contact ${con} id: ${con.id}")
            }
            else {
                log.error("Couldn't import contact.  Errors are: ")
                con.errors.allErrors.each {
                    log.error("Error: ${it}")
                }
            }

        }
    }


    // utility method to find a course to load data into.
    // prev. version of dB did not have the concept of "Courses"
    // Default to children's EAC course.
    def getDefaultCourse() {
        def c = Course.findByNameIlike("chil") ?: Course.get(1)
        return c
    }

    def addCommentAboutId(thingy, id) {
        addComment(thingy,  "Imported.  Original ID was :${id}:")
    }

    def addComment(thingy, comment) {
        thingy.addComment(ShiroUser.findByUsername("admin"), comment) 
    }

    def findContactByOldId(oldContactId) {
        // go through contacts' notes, and find this oldContactId
        def c = CommentLink.createCriteria()
        def links = c.list {
            eq('type', GrailsNameUtils.getPropertyName(Contact.class))
            comment {
                like('body', "%:${oldContactId}:%")
            }
        }

        if (links) {
            return Contact.findById(links[0].commentRef)
        }
        else {
            return null
        }
        
    }

    def findClassSessionByOldId(oldId) {
        // go through sessions' notes, and find this oldId
        def c = CommentLink.createCriteria()
        def links = c.list {
            eq('type', GrailsNameUtils.getPropertyName(ClassSession.class))
            comment {
                like('body', "%:${oldId}:%")
            }
        }

        if (links) {
            def cs =ClassSession.findById(links[0].commentRef) 
            log.info("Found Class session with: " + oldId)
            log.info("Class Session name is: " + cs)
            log.info("Class Session start date is: " + cs.startDate)
            return cs
        }
        else {
            log.error("Couldn't find Class session having oldId: '${ oldId }'")
            return null
        }
    }
}

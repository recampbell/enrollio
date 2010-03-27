package org.bworks.bworksdb
import org.bworks.bworksdb.auth.*
import org.grails.comments.*
import grails.util.*

class DataLoadingService {

    boolean transactional = true

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
            def sessionName = xmlSess.Class1Date.text().split('T')[0]
            def cs = new ClassSession(course:course, name:sessionName, startDate:new Date())
            if (cs.validate() && cs.save()) {
                log.info("Imported class session ${cs.name} id: ${cs.id}")
                addCommentAboutId(cs, xmlSess.ClassID.text())
            }
            else {
                log.error("Couldn't import class session.  Errors are: ")
                cs.errors.allErrors.each {
                    log.error("Error: ${it}")
                }
            }
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

            if (con.validate() && con.save()) {
                addCommentAboutId(con, xmlCon.ParentID.text())

                if (xmlCon.Note != '') {
                    addComment(con, xmlCon.Note?.text())
                }
                if (xmlCon.InfoTakenBy != '') {
                    log.error("Adding infotakenby: ${xmlCon.InfoTakenBy?.text()}")
                    addComment(con, 'Info taken by: ' + xmlCon.InfoTakenBy.text())
                }

                addComment(con, 'Signup Date:' + xmlCon.DateOfSignUp.text().split('T')[0])
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

}

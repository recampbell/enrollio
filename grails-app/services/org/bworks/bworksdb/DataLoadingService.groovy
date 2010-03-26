package org.bworks.bworksdb
import org.bworks.bworksdb.auth.*

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

    def loadContacts(xmlString) {
        def xml = new XmlSlurper().parseText(xmlString)
		def contacts = xml.children()
        contacts.each { xmlCon ->
            def con = new Contact(lastName:xmlCon.LastName.text(),
                                  firstName:xmlCon.FirstName.text())
            if (con.validate() && con.save()) {
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
        thingy.addComment(ShiroUser.findByUsername("admin"), "Imported.  Original ID was :${id}:")
    }

}

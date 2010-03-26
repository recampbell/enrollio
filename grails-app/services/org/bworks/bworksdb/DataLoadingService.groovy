package org.bworks.bworksdb

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
            println "xml Session Name is: " + xmlSess.Class1Date.text()
            println "Session Name is: " + sessionName
            def cs = new ClassSession(course:course, name:sessionName, startDate:new Date())
            if (cs.validate() && cs.save()) {
                log.info("Imported class session ${cs.name} id: ${cs.id}")
            }
            else {
                log.error("Couldn't import class session.  Errors are: ")
                cs.errors.allErrors.each {
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

}
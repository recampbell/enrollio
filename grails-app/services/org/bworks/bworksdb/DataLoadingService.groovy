package org.bworks.bworksdb

class DataLoadingService {

    boolean transactional = true

    def loadClassSessions(xmlString, course = null) {
        if (!course) {
            course = getDefaultCourse()
        }
        def xml = new XmlSlurper().parseText(xmlString)
		def sessions = xml.children()
        sessions.each {
            new ClassSession(course:course, name:'Imported.', startDate:new Date()).save()
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

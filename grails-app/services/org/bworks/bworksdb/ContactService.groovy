package org.bworks.bworksdb

class ContactService {

    boolean transactional = true
    def configSettingService

    def createStudentStub(contactInstance) {

        // Create a stub new student, in case user wants to create one.
        def newStudentInstance = new Student(lastName:contactInstance.lastName,
                                          contact:contactInstance)

        // create interest in default prog.
        def defCourse = configSettingService.getSetting(ConfigSetting.DEFAULT_COURSE)
        if (defCourse) {
            def interest = new Interest(student:newStudentInstance,
                                        course:Course.get(defCourse.value.toLong()))
            newStudentInstance.addToInterests(interest)
        }

        return newStudentInstance
    }
}

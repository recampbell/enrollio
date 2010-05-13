package org.bworks.bworksdb

class StudentService {

    boolean transactional = true

    protected def buildSelectedIds(def interests) {
        def selected = []
        
        if (interests) {
            // turn 1 or N into a single list and iterate
            def tmpInterests = []
            tmpInterests << interests
            tmpInterests.flatten().each { selected << it.toString() }
        }
        
        selected
    }
        
    // Loop through interests, either:
    // create a new one
    // or set active to 'selected'
    // Interests is an array of goofy Grails checkboxes
    // signupDates is a map of dates, using the Course's ID as keys
    // Example:  1 : December 12, 2009        2: January 11, 2010
    // This allows updating the interest dates for the various courses.
    // i.e. bob signed up for EAC program in Dec, but signed up for Bicycle in May
    def saveInterests(student, interests, signupDates = null) {

        def selectedInterests = buildSelectedIds(interests)
        
        Course.findAll().each { course ->
            def interest = Interest.findWhere(student:student, course:course)
            def courseId = course.id.toString()
            def isInterested = selectedInterests.contains(courseId)
                println "Interest signupDates" + signupDates

            if (interest) {
                // Interest exists for this course -- set its active
                // status according to whether the student's interested or not
                interest.active = isInterested            
            }
            else if (isInterested) {
                // If student is interested, then create a new Interest
                def note = new Note().save()
                interest = new Interest(active:true, student:student, course:course,
                                                     note:note)
                course.addToInterests(interest)
                student.addToInterests(interest)
            }

            // Apply the signup date, if any
            if (interest && signupDates && signupDates[courseId]) {
                println "Interest signupDates" + signupDates[courseId]
                println "before" + interest.signupDate
                interest.signupDate = signupDates[courseId]
                interest.save(flush:true)
                println "after" + interest.signupDate
            }
        }            
    }

    // return signup date for student's interest in a course,
    // or today's date, if no interests already exist
    def signupDateForInterest(student, course) {
        def interest = Interest.findWhere(student:student, course:course)
        if (interest) {
            return interest.signupDate
        }
        else {
            return new Date()
        }
    }
}

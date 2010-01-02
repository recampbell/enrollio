package org.bworks.bworksdb
class StudentAnonymizer {

    def anon = new Anonymizer()
    def xml = null
	def students

    StudentAnonymizer(String s) {
        xml = new XmlSlurper().parseText(s)
		students = xml.children()
    }

    def anonymize() {
        // First, sweep all first names, main!
        students.FirstName.each {
            it.replaceBody anon.firstName()
        }

        // Next, sweep all last names, main!
        students.LastName.each {
            it.replaceBody anon.lastName()
        }
    }
}

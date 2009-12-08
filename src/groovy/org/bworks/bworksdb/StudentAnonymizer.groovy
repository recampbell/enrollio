package org.bworks.bworksdb
class StudentAnonymizer {

    def anon = new Anonymizer()
    def xml = null
    def students

    StudentAnonymizer(String s) {
        def xml = new XmlParser().parseText(s)
        students = xml.dataroot.children()
    }
}

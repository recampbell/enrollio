package org.bworks.bworksdb
import grails.test.*

import org.bworks.bworksdb.Student

class StudentTests extends GroovyTestCase {

    void testNotNullLastName() {
        def s = new Student(firstName:"Groovy in Action", lastName:"Foo")
        assertFalse s.validate()
    }

    // This is just to see what it takes to find a Student's Interests
    void testInterestStuff() {
       def c = new Contact(firstName:'Jack',
                           lastName:'Sprat',
                           address1:'103 Smith Street',
                           address2:'Apartment 1B',
                           state:'MO',
                           zipCode:'63153',
                           emailAddress:'jack.sprat@crazynate.com').save()
        def s = new Student(firstName:"Groovy in Action", lastName:"Foo", contact:c)
        if (!s.validate())  s.errors.allErrors.each { println it }
        
        assert s.validate()
        c.addToStudents(s)
        s.save()

        def p = new Program(description:"Byteworks Children's Earn-A-Computer Program",
                             name:"Children's EAC").save()
        def i = new Interest(active:true, program:p, student:s).save()
        s.addToInterests(i)

        assertNotNull Interest.findByStudent(s)
    }

}

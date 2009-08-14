import org.bworks.bworksdb.*
class BootStrap {

   def init = { servletContext ->
         if (grails.util.GrailsUtil.environment == "development") {
             loadDevData()
         }
   }
   def destroy = {
   }

   // Git some test data in these here parts
   def loadDevData() {
       def c0 = new Contact(firstName:'Jack',
                           lastName:'Sprat',
                           address1:'103 Smith Street',
                           address2:'Apartment 1B',
                           state:'MO',
                           zipCode:'63153',
                           emailAddress:'jack.sprat@crazynate.com').save()

       c0.addToPhoneNumbers(new PhoneNumber(label:'Home', 
                                            phoneNumber:'(314)-123-4567'))

       c0.addToPhoneNumbers(new PhoneNumber(label:'Work', 
                                            phoneNumber:'(314)-444-5555'))
       def c1 = new Contact(firstName:'Jill',
                           lastName:'Pill',
                           address1:'304 Zonkers Ave.',
                           state:'MO',
                           zipCode:'63123')

       if (!c1.validate()) {
           c1.errors.allErrors.each { println it }
       }
       c1.save()
       c1.addToPhoneNumbers(new PhoneNumber(label:'Cell', 
                                            phoneNumber:'(314)-777-1111'))
       c1.addToStudents(new Student(lastName:'Pill',
                                    firstName:'Blue'))
       c1.addToStudents(new Student(lastName:'Pill',
                                    firstName:'Jagged',
                                    middleName:'Little'))


       loadDevPrograms()
   } 

   def loadDevPrograms() {
       def p0 = new Program(description:"Byteworks Children's Earn-A-Computer Program",
                             name:"Children's EAC").save()
       def eacLessons = ['Intro to Computers', 'Scratch Programming',
                         'Word Processing', 'Presentations', 'Email and WWW', 'Graduation']
       eacLessons.eachWithIndex { it, i ->
           p0.addToLessons(new Lesson(description:it,
                                      name:it,
                                      sequence:i))
       }
           
       def p1 = new Program(description:"Byteworks Adult Earn-A-Computer Program",
                             name:"Adult EAC").save()
       def p2 = new Program(description:"Byteworks Mentorship Program",
                             name:"Mentorship Program").save()
   }

}

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
       def s0 = new ConfigSetting(key:'defaultInterestProgram',
                                  value:1,
                                  isDefault: true,
                                  description:'When entering Students, this program will be the default program they\'re interested in').save()
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
       c0.addToStudents(new Student(lastName:'Sprat',
                                    firstName:'Snarfo'))
       c0.save(flush:true)
       def c1 = new Contact(firstName:'Jill',
                           lastName:'Pill',
                           address1:'304 Zonkers Ave.',
                           state:'MO',
                           zipCode:'63123')

       if (!c1.validate()) {
           c1.errors.allErrors.each { println it }
       }
       c1.save(flush:true)
       c1.addToPhoneNumbers(new PhoneNumber(label:'Cell', 
                                            phoneNumber:'(314)-777-1111'))
       c1.addToStudents(new Student(lastName:'Pill',
                                    firstName:'Blue'))
       c1.addToStudents(new Student(lastName:'Pill',
                                    firstName:'Jagged',
                                    middleName:'Little'))


       // You have to flush, otherwise hibernate doesn't save this doodie
       // and the findByFirstNameAndLastName flips out in the loadDevPrograms method.
       // TODO: Find mobetta way of doing this, like setting some kind of "politeToilet:true"
       // method so that Grails flushes after every use.
       c1.save(flush:true)
       loadDevPrograms()

       100.times {
           loadDummyContactAndStudents()
       }
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

       def s = Student.findByFirstNameAndLastName('Jagged', 'Pill')
       assert s != null
       s.addToInterests(new Interest(program:p0, active:true))
   }

   // Method used to create a dummy contact, student and an interest
   // in a program
   def loadDummyContactAndStudents() {
       def seed = new Random()
       def randAddress = seed.nextInt(1000) 
       def zip = '63' + seed.nextInt(100).toString().padLeft(3, "0")
       def lastName = randomLastName()
       def firstName = randomFirstName()
       // Use an email address for 50% of the people
       def emailAddress = seed.nextInt(2) == 1 ? '' : "${firstName}.${lastName}@" + randomEmailServer() + ".com"
       def c0 = new Contact(firstName:firstName,
                           lastName:lastName,
                           address1:randAddress.toString() + ' ' + randomStreetName(),
                           state:'MO',
                           zipCode:zip,
                           emailAddress:emailAddress).save()

       def randPhone = seed.nextInt(10000) - 1
       randPhone = randPhone.toString().padLeft(4, "0")
       c0.addToPhoneNumbers(new PhoneNumber(label:'Home', 
                                            phoneNumber:"(314)-123-$randPhone"))

       randPhone = seed.nextInt(10000) - 1
       randPhone = randPhone.toString().padLeft(4, "0")
       c0.addToPhoneNumbers(new PhoneNumber(label:'Work', 
                                            phoneNumber:"(314)-444-${randPhone}"))

       def numStudents = seed.nextInt(3) + 1
       numStudents.times {
           def stud = new Student(lastName:lastName,
                                   firstName:randomFirstName())

           c0.addToStudents(stud)
           c0.save(flush:true)
          
           // Add a random amount of interests to random programs :-)
           def programs = Program.findAll().collect { it.id }
           def availProgs = programs.clone()
           // Get 0 .. numPrograms -- some students might not have interests
           def numProgsForStudent = seed.nextInt(programs.size() + 1)
           numProgsForStudent.times {
               println availProgs
               def randomProg = seed.nextInt(availProgs.size())
               println randomProg
               def prog = availProgs.remove(randomProg)
               stud.addToInterests(new Interest(program:Program.get(prog), active:true))
           }
       }

   }

   def randomFirstName() {
       def seed = new Random()
       def names = ['Bob', 'Jane', 'Dooge', 'Patty', 'Charlie', 'Snoopy', 'Schizoid',
                    'Nate', 'Dan', 'Mary', 'Adam', 'Chris', 'Theresa', 'Snarf', 'Peter',
                    'Missie', 'Julie', 'Wong', 'Debbie']
       return names[seed.nextInt(names.size() - 1)]
   }
   
   def randomEmailServer() {
       def seed = new Random()
       def names = ['yahoo', 'aol', 'gmail', 'grails', 'fiddlesticks']
       return names[seed.nextInt(names.size() - 1)]
   }

   def randomLastName() {
       def seed = new Random()
       def names = ['Neff', 'James', 'Jones', 'Ryholight', 'Klein', 'Jackson',
                    'Birdcage', 'Lionheart', 'Braveheart', 'Gibson', 'Snippet',
                    'Black', 'Caldwell', 'Spacely', 'Jetson', 'Flintstone', 'Fudd',
                    'Zapata', 'Wolfenstein', 'Davis', 'Washington', 'Jefferson', 'Madison',
                    'Pujols', 'La Russa', 'Dempster', 'Ryan', 'Ramirez', 'Gonzalez',
                    'Manson', 'Dahmer', 'Link', 'Stephanopolis', 'Rather', 'Walters',
                    'Klez', 'Klockenhammer', 'Gosling', 'Torvalds', 'Stallman']
       return names[seed.nextInt(names.size() - 1)]
   }

   def randomStreetName() {
       def seed = new Random()
       def names = ['Neff', 'James', 'Jones', 'Ryholight', 'Klein', 'Jackson', 'Main',
                    'Oklahoma', 'Virginia', 'Royal', 'Arsenal Boulevard', 'Zipenstein', 'McCausland Avenue']
       return names[seed.nextInt(names.size() - 1)]
   }

}

package org.bworks.bworksdb

import org.apache.shiro.crypto.hash.Sha1Hash
import org.bworks.bworksdb.util.TestKeys

class TestDataService {

    boolean transactional = true

    def programService
        
    // we don't want random data for integration tests
    def loadIntegrationTestData() {
        // get program
        loadDefaultPrograms()
        
        def program = Program.findByName(TestKeys.PROGRAM)
        
        // build contact, student
        def contact = new Contact(firstName:'first',
                            lastName:'last',
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()

        def student = new Student(lastName:TestKeys.STUDENT, contact:contact)

        contact.addToStudents(student)
        contact.save(flush:true)
        
        // add interest to program and student
        def note = new Note(text:TestKeys.NOTE).save()
        def interest = new Interest(active:true, student:student, program:program, note:note).save()        
        program.addToInterests(interest)
        student.addToInterests(interest)

        student.save(flush:true)
        program.save(flush:true)
    }
    
    // Git some test data in these here parts
    def loadDevData(numContacts = 100) {
        loadDefaultPrograms()
 
        numContacts.times {
            loadDummyContactAndStudents()
        }
        loadDummyClassSessions()
        loadDummyUsers()
    } 

    def loadDummyClassSessions() {
        def progs = Program.list()
        progs.each { prog ->
            def cs = new ClassSession(name:"${prog.name} ${new Date().format('MM/dd/yyyy')}.",
                                      program:prog,
                                      startDate: new Date()).save()
            def nac = programService.nextAvailableLessonDates(cs.program, new Date())
            nac.each { lessonDate ->
                cs.addToLessonDates(lessonDate)
            }

            cs.save()

            prog.interests.eachWithIndex  { interest, i ->
                if (i < 5) {
                    cs.addToEnrollments(new Enrollment(student:interest.student))
                }
                else {
                    return
                }
            }
            cs.save()
        }

   }

    def loadDummyUsers(numUsers = 3) {
        numUsers.times {
            def userRole = ShiroRole.findByName("User")
            def lastName = randomLastName()
            def firstName = randomFirstName()
            def userName = firstName.substring(0,1) + lastName 
            def dummyUser = new ShiroUser(username: userName,
                    firstName : firstName,
                    lastName : lastName,
                    passwordHash: new Sha1Hash(userName).toHex()
                    )
            if (!dummyUser.validate()) {
                println "username : ${userName}"
                println "User didn't validate!"
                println adminUser.errors.allErrors
            }
            else {
                dummyUser.save()
                new ShiroUserRoleRel(user: dummyUser, role: userRole).save()
            }
        }
    }

    def loadDefaultPrograms() {
        def p0 = new Program(description:"Byteworks Children's Earn-A-Computer Program",
                              name:"Children's EAC").save()
        def eacLessons = ['Intro to Computers', 'Scratch Programming',
                          'Word Processing', 'Presentations', 'Email and WWW', 'Graduation']
        eacLessons.eachWithIndex { it, i ->
            p0.addToLessons(new Lesson(description:it,
                                       name:it,
                                       sequence:i))
        }
            
        new Program(description:"Byteworks Adult Earn-A-Computer Program", name:"Adult EAC").save()
        new Program(description:"Byteworks Mentorship Program", name:"Mentorship Program").save()
 
        def s0 = new ConfigSetting(configKey:'defaultInterestProgram',
                                   value:1,
                                   isDefault: true,
                                   description:'When entering Students, this program will be the default program they\'re interested in').save()
    }

    def loadDummyContacts(numContacts = 100) {

        numContacts.times {
            loadDummyContactAndStudents()
        }
    }
 
    // Method used to create a dummy contact, student and an interest
    // in a program
    def loadDummyContactAndStudents() {
        def seed = new Random()
        def randAddress = seed.nextInt(1000) 
        def address2 = ''
 
        if (randAddress.mod(3) == 0) {
            address2 = 'Apt. A'
        }
        def zip = '63' + seed.nextInt(100).toString().padLeft(3, "0") + '-1234'
        def lastName = randomLastName()
        def firstName = randomFirstName()
        // Use an email address for 50% of the people
        def emailAddress = seed.nextInt(2) == 1 ? '' : "${firstName}.${lastName}@" + randomEmailServer() + ".com"
        def c0 = new Contact(firstName:firstName,
                            lastName:lastName,
                            address1:randAddress.toString() + ' ' + randomStreetName(),
                            address2:address2,
                            city:'Saint Louis',
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
                
                def randomProg = seed.nextInt(availProgs.size())
               
                def prog = availProgs.remove(randomProg)
                stud.addToInterests(new Interest(program:Program.get(prog), active:true))
            }
        }
 
    }
 
    def randomFirstName() {
        def seed = new Random()
        def names = ['Bob', 'Jane', 'Dooge', 'Patty', 'Charlie', 'Snoopy', 'Woodstock', 
                     'Schizoid', 'Nate', 'Dan', 'Mary', 'Adam', 'Chris', 'Theresa', 
                     'Snarf', 'Peter', 'Missie', 'Julie', 'Wong', 'Debbie']
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

package org.bworks.bworksdb
class Anonymizer {

    def random = new Random()
    def firstNames = [
        'Bob', 'Jane', 'Dooge', 'Patty', 'Charlie', 'Snoopy', 'Woodstock', 
        'Schizoid', 'Nate', 'Dan', 'Mary', 'Adam', 'Chris', 'Theresa', 
        'Snarf', 'Peter', 'Missie', 'Julie', 'Wong', 'Debbie', 'Spud', 'Larry',
        'Geoff', 'Marcos', 'Amelda', 'Gene', 'Suzie', 'Lionel', 'Ru'
    ]

    def emailServers = [
        'changeme.com', 'fakeemail.com', 'gmail.com', 'grails.org', 'fiddlesticks.com'
    ]

    def lastNames = [
        'Neff', 'James', 'Jones', 'Ryholight', 'Klein', 'Jackson',
        'Birdcage', 'Lionheart', 'Braveheart', 'Gibson', 'Snippet',
        'Black', 'Caldwell', 'Spacely', 'Jetson', 'Flintstone', 'Fudd',
        'Zapata', 'Wolfenstein', 'Davis', 'Washington', 'Jefferson', 'Madison',
        'Pujols', 'La Russa', 'Dempster', 'Ryan', 'Ramirez', 'Gonzalez',
        'Manson', 'Link', 'Stephanopolis', 'Rather', 'Walters',
        'Klez', 'Klockenhammer', 'Gosling', 'Torvalds', 'Stallman'
    ]

    def phoneNumber() {
        def randPhone = random.nextInt(10000) - 1
        randPhone = randPhone.toString().padLeft(4, "0")
        return "(314)-123-$randPhone"
    }

    def emailServer() {
        return names[seed.nextInt(emailServers.size() - 1)]
    }

    def firstName() {
        return firstNames[random.nextInt(firstNames.size() - 1)]
    }

    def lastName() {
        return lastNames[random.nextInt(lastNames.size() - 1)]
    }

    def zipCode() {
        return '63' + seed.nextInt(100).toString().padLeft(3, "0") + '-1234'
    }

    def emailAddress(String username) {
        if (!username) {
            username = String.join(",", firstName() , lastName())
        }
        return username . "@" . randomEmailServer()
    }
            
}

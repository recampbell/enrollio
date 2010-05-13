package org.bworks.bworksdb.util

// Class to provide static dummy data for tests
// TODO: this shouldn't really be in the app code

class TestData {
    // Note: the indentation here is on purpose, otherwise the 
    // xml parser barfs
    static String fixtureSingleClassSession(classId = 13) {
        def xml = """<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  xsi:noNamespaceSchemaLocation="Class.xsd">
<Class>
<ClassID>${classId}</ClassID>
<Class1Date>2006-03-11T00:00:00</Class1Date>
<Class2Date>2006-03-18T00:00:00</Class2Date>
<Class3Date>2006-03-25T00:00:00</Class3Date>
<Class4Date>2006-04-01T00:00:00</Class4Date>
<Class5Date>2006-07-22T00:00:00</Class5Date>
<Class6Date>2006-08-26T00:00:00</Class6Date>
<ClassStartTime>11:00 am</ClassStartTime>
<IsActive>0</IsActive>
</Class>
</dataroot>
"""
    return xml
}
    static String fixtureMultipleStudents(overrides = [:]) {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  
xsi:noNamespaceSchemaLocation="Student.xsd">
'''

        // Totoro Tortenweasel, Class ID 13, Graduated, Parent Tortenweasel
        xml = xml + """<Student> <StudentID>123</StudentID>
<LastName>Tortenweasel</LastName>
<FirstName>Totoro</FirstName>
<ParentID>${overrides['parentID'] ?: '1010101'}</ParentID>
<Gender>M</Gender> <BirthDate>1997-01-07T00:00:00</BirthDate> <Grade>10</Grade>
<ClassID>${overrides['classID'] ?: '13'}</ClassID>
<GraduateDate>1997-01-07T00:00:00</GraduateDate>
<DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID>
<email>totoro@alum.bworks.org</email>
</Student>
"""

        // Smokey Bandit, dropped out, Parent is Tortenweasel, ClassID 13
        xml = xml + """<Student> <StudentID>188</StudentID>
<ParentID>${overrides['parentID'] ?: '1010101'}</ParentID>
<LastName>Bandit</LastName>
<FirstName>Smokey</FirstName> <Notes>Mentorship Interested</Notes>
<BirthDate>1997-01-07T00:00:00</BirthDate> <Grade>10</Grade>
<ClassID>${overrides['classID'] ?: '13'}</ClassID> <DropOut>1</DropOut>
<SystemReceivedID>0</SystemReceivedID>
<email>smokeybandit@reynolds.com</email>
</Student>
"""

xml = xml + '</dataroot>'

        return xml

    }

}

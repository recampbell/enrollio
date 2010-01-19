package org.bworks.bworksdb.util

// useful for asserting in testing
// TODO: this shouldn't really be in the app code

class TestKeys {
    static public final String STUDENT = 'Jim Lahey, Park Supervisor'
    static public final String STUDENT2 = 'J-Rock'
    static public final String NOTE = 'this is a note'
    
    static public final String PROGRAM_ADULT_AEC = 'Adult EAC'
    static public final String PROGRAM_KIDS_AEC = "Children's EAC" 
    static public final String PROGRAM_MENTORSHIP = "Mentorship Program"
    
    static public final String CONTACT_EMAIL = 'barb.lahey@gmail.com'
    static public final String CONTACT1_FIRST_NAME = 'Barb'
    static public final String CONTACT1_LAST_NAME = 'Lahey'

    static public final String SESSION_KIDS_DATE_FORMATTED = 'April 20, 2010' 
    static public final Date SESSION_KIDS_DATE = Date.parse('MMMM d, yyyy', SESSION_KIDS_DATE_FORMATTED)
    static public final String SESSION_KIDS_NAME = "Children's EAC Session" 

    static public final String SESSION_MENTORSHIP_DATE_FORMATTED = 'May 21, 2010' 
    static public final Date SESSION_MENTORSHIP_DATE = Date.parse('MMMM d, yyyy', SESSION_MENTORSHIP_DATE_FORMATTED)
    static public final String SESSION_MENTORSHIP_NAME = "Mentorship Session" 
    
    static public final String SESSION_ADULT_DATE_FORMATTED = 'June 22, 2010' 
    static public final Date SESSION_ADULT_DATE = Date.parse('MMMM d, yyyy', SESSION_ADULT_DATE_FORMATTED)
    static public final String SESSION_ADULT_NAME = "Adult EAC Session" 

    static public final String LESSON_KIDS_AEC_INTRO = "Intro to Computers" 
    static public final String LESSON_KIDS_AEC_INTRO_DESCRIPTION = 
        "This lesson teaches the basics of computer hardware and software," + 
        "\nincluding how computers store information."
}

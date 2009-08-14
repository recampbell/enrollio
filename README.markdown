Enrollio is used to enroll students in the programs offered by Bworks.

Bworks is the umbrella organization for Byteworks and Bicycleworks located
in Saint Louis, Missouri

Byteworks and Bicycleworks are volunteer organizations that strive to
help inner city children reach their full potential.

Byteworks offers the "Earn A Computer" program where students take six
computer-related classes, and are awarded a free computer upon completion
of the six classes.  Bicycleworks offers a similar "Earn-A-Bike" class.

Please see http://bworks.org for more information

Enrollio's purpose is to help Bworks maintain its student database.


h1. Grailsy Voodoo Features

h2. Tag Libraries
Enrollio has a slick Tag Library which loops through all available Programs,
and returns a checkbox for the program which is checked/unchecked depending
on whether the student is already interested in the Program.

Data structure:

Program ->
  Describes one of the volunteer programs that are available at Bworks.
  Example: Earn-A-Computer Program
           Earn-A-Bike Program
           Byteworks Mentorship Program

ClassSession ->
  A group of dates/lessons that pertain to a Program.
  Example: Earn-A-Computer Program, July 2009 Session
           Earn-A-Bike Program, February 2009 Session

   belongs to a Program
   Has Many LessonDates (Example: LessonDate #1 is July 1st, 2009
                                  LessonDate #2 is July 8th, 2009
                                  LessonDate #3 is July 15th, 2009)

Student ->
  Has many Enrollments


Enrollment ->
  Joins Students and ClassSessions
  Example: Student "Barney" is enrolled in the following Class Sessions:
                   1) Earn-A-Computer Program, July 2009 Session
                   2) Earn-A-Bike Program, February 2009 Session

Attendance.groovy
ClassSession.groovy
Contact.groovy
Enrollment.groovy
Interest.groovy
Lesson.groovy
LessonDate.groovy
Note.groovy
PhoneNumber.groovy
Program.groovy

= Contact Phone Numbers =
Addition of dynamic contact phone numbers was provided by:
http://lxisoft.com/web/guest/grails

# Problems

The "Problems" section can be used to point out things in Grails or Enrollio that were
confusing, time-draining or otherwise not really fun to develop.  We can use this section
to identify things we'd like to improve as we continue our mastery of Grails and subsequently
the rest of the Universe.

## Data not persisted immediately in BootStrap

I had problems in the bootStrap when saving test data, and then trying to retrieve it immediately,
due to the flush:true not being specified when saving DOs

## Obscure errors when quotes missing

When calling the interestCheckBoxes tag library, I had a quote missing from the 2nd param, and
received a less than helpful message.  Something like this will reproduce the problem:

  <g:interestCheckBoxes student="${student}" idx="${idx} />

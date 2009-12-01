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

# Installed Plugins:

## PDF Plugin

# JQuery Voodoo Features

## Date Picker

  * Creating Class Sessions:
    * Date Picker when creating Class Sessions will update all the LessonDates with
      newly selected date, in weekly increments
    * If user selects a different Program for a new Class Session, 
      LessonDates Ajaxily downloaded for the new Program, and given
      dates that adhere to any startDate the user already selected

# Grailsy Voodoo Features

## Tag Libraries

Enrollio has a slick Tag Library which loops through all available Programs,
and returns a checkbox for the program which is checked/unchecked depending
on whether the student is already interested in the Program.

# Attributions

Thanks to the following sources for providing code examples, etc

## Contact Phone Numbers

Addition of dynamic contact phone numbers was provided by:
http://lxisoft.com/web/guest/grails

## General Layout of Application

http://moongrails.blogspot.com/2008/12/grails-templates.html

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

## findAll using Booleans

I was unable to easily write a method/getter on the Program DO which would show
me the Active Interests in that Program.

I tried the following:

  getActiveInterests() {
      return interests.findByActive(true)
  }

# Program Documentation

We might move this documentation to the wiki, since it could become rather large
to fit in a README file, but for now......

## Data structure:

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

Oren was here.



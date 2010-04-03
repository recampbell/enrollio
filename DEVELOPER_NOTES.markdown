# Developer Notes

Enrollio requires Grails 1.2.

Some steps need to be taken before running Enrollio:

# Configuration

You will want to change some of the things in your Config.groovy before running
Enrollio.

grails.serverURL is currently hard-coded to "http://enrollio.org".  You will want
to change this.

# Compile App

> grails compile

# Compile Error

If you receive the following error when running grails compile:

  Error executing script TestApp: : java.lang.NoClassDefFoundError: 
  Lcom/gargoylesoftware/htmlunit/html/HTMLParser$HtmlUnitDOMBuilder;

Then, just run the compile again!

## You might want to increase permgen size on your JVM

This will avoid permgen out-of-memory errors when running the tests.

Put the following in your .bashrc or .profile

    export JAVA_OPTS=-XX:MaxPermSize=128m

## Create the src/java and src/groovy directories

CD to the directory where you downloaded Enrollio, and run the following commands:
  
    $ mkdir -p src/groovy
    $ mkdir -p src/java

## Run the tests / fix DOMBuilder Error

We try to write a comprehensive set of tests for Enrollio.
All tests should pass before you use Enrollio.

CD to the directory where you downloaded Enrollio, and run the following commands:

    $ grails test-app

If you receive the following error:

  Error executing script TestApp: : java.lang.NoClassDefFoundError: 
  Lcom/gargoylesoftware/htmlunit/html/HTMLParser$HtmlUnitDOMBuilder;

You merely need to run the tests again:

    $ grails test-app

## Remove jasperreports-2.0.5

After Grails installs the necessary plugins, it's a wise move to remove the troublesome
jasperreports-2.0.5.jar file from your ~/.grails directory.  See below for details.

# Building War File

Run the tests and the application itself before building the .war file.

Also, see the jasperreports-2.0.5 kludge below.

## Remove outdated jasperreports-2.0.5.jar when building War file

The jasper reports plugin (0.9.7) is shipped with two jasperreports-X-X-X.jar files.

Grails puts both .jar files into the .war, and the outdated .jar file might be used
when generating the Call List report or the Graduation Report.  This will produce an error
like this one:

    Parse Error at line 2 column 412: Document root element "jasperReport", must match DOCTYPE root "null".
    org.xml.sax.SAXParseException: Document root element "jasperReport", must match DOCTYPE root "null".
    <snip>
    Caused by: net.sf.jasperreports.engine.JRException: org.xml.sax.SAXParseException: Document root element "jasperReport", must match DOCTYPE root "null".
	at net.sf.jasperreports.engine.xml.JRXmlLoader.loadXML(JRXmlLoader.java:243)
	at net.sf.jasperreports.engine.xml.JRXmlLoader.loadXML(JRXmlLoader.java:226)

The solution is to DELETE the older jasperreports-X-X-X file from your ~/.grails directory
before building the war file.  Example:

  cd ~/.grails
  find . -name 'jasperreports*jar'
  # Zap the one with the older version number
	

# Upgrading Enrollio

If you've downloaded Enrollio before the Grails 1.2 upgrade, you should
only need to install Grails 1.2 to run enrollio.

# Functional Tests fail if run w/unit & integration

As of 2010/04/02, you need to run functional tests separately from integration and
unit tests.  If you just run grails test-app, there is a failure in the UserFunctionalTests:

  No signature of method: java.util.Collections$UnmodifiableRandomAccessList.click() is 
  applicable for argument types: () values: []

Any solutions to this problem are welcome.

# Developer Notes

Enrollio requires Grails 1.2.

Some steps need to be taken before running Enrollio:

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

# Upgrading Enrollio

If you've downloaded Enrollio before the Grails 1.2 upgrade, you should
only need to install Grails 1.2 to run enrollio.

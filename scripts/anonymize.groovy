#!/usr/bin/env groovy

/* 
 *  Script: anonymize.groovy
 *   Usage: anonymize.groovy
 * Purpose: Anonymize data brought over from orig. database, 
 *          for the purpose of getting a large amount of test data
 *  Author: Crazy Nate
 * History: 
 */

// Parse command line arguments
def cli = new CliBuilder(usage: "groovy anonymize.groovy [-h|--help]\n" + 
                                '--format <student|contact> --file <filename>',
                         parser: new org.apache.commons.cli.GnuParser ())
cli.h(argName:'help', longOpt:'help', 'show usage information and quit')
cli.t(argName:'format', longOpt:'format', args:1, required:true, 'Format of input file')
cli.f(argName:'file', longOpt:'file', args:1, required:true, 'Input file name')

// To actually parse the command line options, use the following command.
def opt = cli.parse(args)

// cli.parse will have already printed the usage if a required param is missing.
// All we have to do is exit if !opt
if (!opt) return
if (opt.h) {
    cli.usage() 
    return 0
}

if (opt.t != 'student' && opt.t != 'contact') {
    cli.usage()
    return 0
}



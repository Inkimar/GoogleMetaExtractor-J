#!/usr/bin/groovy
def name='World'; println "change file names"
String oldFilename = "old.txt"  
String newFilename = "new.txt"  
  
new File(oldFilename).renameTo(new File(newFilename))



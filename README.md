[![Build Status](https://api.travis-ci.org/Inkimar/GoogleMetaExtractor-J.svg?branch=master)](https://travis-ci.org/Inkimar/GoogleMetaExtractor-J)

=====================
# MetaExtractor
work-in-progress <p>

Java:

1. fetches Image-files with format xxxx-yyyy-nnnnnnnnn_'view'.jpg.
2. pattern : where yyyy-nnnnnnnnn is the catalog-number, _'view'_ is a view such as one of the following {'abdo','face','vent','late','pron','dors', and soforth ... }
3. parses the name, saves it in a map key = catalog-number and values is a list with views
4. fetches exif-data from images, if asked for..

## The filename
ex. NHRS-GULI000000001_dors.jpg


## run it like this:
prereq: 
java 

1. path = ~/Pictures
2. java -jar target/MetaExtractor-jar-with-dependencies.jar ~/Pictures



## probz: using a fixed path to the images

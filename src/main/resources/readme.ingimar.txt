About the image-files:

information stored in.CR2 - not  in .tif ( that has been processed )
one morphbank-post owns an array of images ( one or more )
the naming is as follows -> 
-> NHRS-GULI000006860_face.tif
-> NHRS-GULI000006860_geni.tif
-> NHRS-GULI000006860_labe.CR2
-> NHRS-GULI000006860_late.tif
-- Where 'NHRS-GULI000006860' is the catalog-number (cat.nr) [ use that for dina-webservice(rs) ]
-- and 'face','geni','labe' and 'late' are the views
- - - I save every image in the class 'MapWrapper' - Map<String, List<String>> map;
- - - where the key is the cat.nr and the list contains the views.
- 
But we could use information from.CR2 and attach it to a 'morphbank'-post that
describes all the images linked to that mb-post.

Test ?
ExifExtractTest
- Right now reading from a path,  see config-properties.
- - filepath.images.test

Mail sent on the 9th of July ( 2013 )
Question about the views and soforth.
Some other files - with an emptyspace, should be dealt with from
the terminal, given a proper name ..... OBS <-

Writing these to a txt-file now ( datumstämplat )
strange-view is : . Palp1.
strange-view is : . 1rst leg1.
strange-view is : . vent.
strange-view is : .first legs.
strange-view is : . palp.
strange-view is : .000001975.
strange-view is : . Palp2.
strange-view is : .000001957.
strange-view is : .000001949.
strange-view is : .000001974.
strange-view is : .000001948.
strange-view is : .000001947.
strange-view is : .000001950.
strange-view is : .000001963.
strange-view is : .000001959.
strange-view is : . cheli.

2Frågor - 10Juli
Could I locate the files with two underscore and 'mend' them with a groovy-script ?
The faulty-file-name is -> NHRS-GULI_000001959_dors.CR2
and should be -> NHRS-GULI000001959_dors.CR2 ?
-  should we make some more 'checks' on the file according to how it should be ?
- should we try to pinpoint which cat.nr has 'raw'-file ( Another map - cat.nr + cr2? )

~/tmp/test-images$ cat change-file-name.groovy 
#!/usr/bin/groovy
def name='World'; println "change file names"
String oldFilename = "old.txt"  
String newFilename = "new.txt"  
  
new File(oldFilename).renameTo(new File(newFilename))


Techniques new to me:
- hamcrest is, must read more about that.
- - used in JUnit to compare 2 unorderd lists.

2013-07-19, 15:30  : Möte; Johannes + Kevin + Ingimar
Either jpg or raw can have all the metadata.
tif has never the full amount of metadata.

Stacking :
- adding a scalebar to the image ( saved as tif ? )
- - jpg , you save as tif ...


Johannes lista : 1-4

spara metadata och bild ....

1)
identicalfilename.cr2 ( raw )
identicalfilename.tif
identicalfilename.jpg
-> use cr2 for metadata, use tif for image

2)
identicalfilename.jpg
identicalfilename.tif 
-> use jpg for metadata, use tif for image

3)
identicalfilename.cr2
identicalfilename.jpg
-> use cr2 for metadata and image

4)
identicalfilename.cr2
identicalfilename.tiff

--> cr2 for metadata, and tif for image



Egen fråga, till mig :


Java:
titta närmare på exif-extraction.
får exception, beror det på filnamnet ?

MB:
hur sparar vi vyn från xml-filen !?


Check this :
Is morphbank able to handle cr2
- if not, can we transform cr2 to tif withoug loosing metadata ...
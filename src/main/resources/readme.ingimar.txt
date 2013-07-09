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


Techniques new to me:
- hamcrest is, must read more about that.
- - used in JUnit to compare 2 unorderd lists.
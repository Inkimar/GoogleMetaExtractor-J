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
Techniques new to me:
- hamcrest is, must read more about that.
- - used in JUnit to compare 2 unorderd lists.
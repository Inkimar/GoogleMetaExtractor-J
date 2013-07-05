How to send a request to the dina-service:
- where 'NHRS-GULI000004112' is the catalognumber, the catalogenumber is a part of the image-name.

curl -H "Accept:application/json" http://dina-web-client.nrm.se/dina-service/Collectionobject/catalogNumber/NHRS-GULI000004112
curl -H "Accept:application/xml"s http://dina-web-client.nrm.se/dina-service/Collectionobject/catalogNumber/NHRS-GULI000004112 

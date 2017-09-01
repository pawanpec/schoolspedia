# schoolspedia
mvn install:install-file -Dfile=feed4j.jar -DgroupId=com.sp.feed4j -DartifactId=feed4j -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=json-tj_1.0.jar -DgroupId=org.json -DartifactId=json -Dversion=tj_1.0 -Dpackaging=jar
mvn eclipse:clean
mvn eclipse:eclipse -Dwtpversion=2.0
mvn clean install -Dmaven.test.skip=true
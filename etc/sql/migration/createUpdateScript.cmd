--schema migration template
java -jar apgdiff-1.4.jar --ignore-start-with schema_3.sql schema_4.sql > target.sql

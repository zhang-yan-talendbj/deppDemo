<<<<<<< HEAD
mvn archetype:create-from-project
=======
call mvn clean
call mvn archetype:create-from-project

cd target/generated-sources/archetype/
call mvn install
pause
exit
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def

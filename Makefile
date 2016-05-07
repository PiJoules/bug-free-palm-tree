default: run

JAVA_CLASSPATH=".:json-20160212.jar"

clean:
	rm *.class

build:
	javac -cp $(JAVA_CLASSPATH) *.java

dryrunsurvey: build
	java -cp $(JAVA_CLASSPATH) MainDriver test.survey.txt

dryrun: dryrunsurvey

test: dryrun

run: build
	java -cp $(JAVA_CLASSPATH) MainDriver


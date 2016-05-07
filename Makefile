default: run

JAVA_CLASSPATH=".:json-20160212.jar"

clean:
	rm *.class

build:
	javac -cp $(JAVA_CLASSPATH) *.java

dryrunsurvey: build
	java -cp $(JAVA_CLASSPATH) MainDriver survey.txt

dryruntest: build
	java -cp $(JAVA_CLASSPATH) MainDriver test.txt

dryrun: dryrunsurvey dryruntest

test: dryrun

run: build
	java -cp $(JAVA_CLASSPATH) MainDriver


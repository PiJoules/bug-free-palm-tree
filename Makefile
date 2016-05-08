default: run

JAVA_CLASSPATH=".:json-20160212.jar"

clean:
	rm *.class

build:
	javac -cp $(JAVA_CLASSPATH) *.java

dryrunsurvey: build
	rm -f something.survey.json
	rm -f new.survey.json
	java -cp $(JAVA_CLASSPATH) MainDriver survey.txt
	@echo ""
	@echo -n "Checking if something.survey.json exists..."
	@[ -f something.survey.json ] && echo "Success!" || echo "Failed"
	@echo -n "Checking if new.survey.json exists..."
	@[ -f new.survey.json ] && echo "Success!" || echo "Failed"
	@echo -n "Comparing new.survey.json against expected.survey.json..."
	@diff new.survey.json expected.survey.json && echo "Success!" || echo "Failed"

dryruntest: build
	rm -f something.test.json
	rm -f new.test.json
	java -cp $(JAVA_CLASSPATH) MainDriver test.txt
	@echo ""
	@echo -n "Checking if something.test.json exists..."
	@[ -f something.test.json ] && echo "Success!" || echo "Failed"
	@echo -n "Checking if new.test.json exists..."
	@[ -f new.test.json ] && echo "Success!" || echo "Failed"
	@echo -n "Comparing new.test.json against expected.test.json..."
	@diff new.test.json expected.test.json && echo "Success!" || echo "Failed"

dryrun: dryrunsurvey dryruntest

test: dryrun

run: build
	java -cp $(JAVA_CLASSPATH) MainDriver


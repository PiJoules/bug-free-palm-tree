# Survey/Test Taking System Using Voice Output (Assignment 4)
Creating, Displaying, and Storing a Survey/Test to a File


## Voice Features
Below are a few extra instructions and information about the text-to-speech FreeTTS software.
I ran into a bit of obstacles that I was able to find solutions to.

### FreeTTS and Ubuntu
This project does not actually use the jars provided as part of the assignment. Instead, this
project uses a jar of of freetts that was made from scratch. The provided pre-built jars did
not actually work for me because I was using Ubuntu.

When I was trying to compile and run the Hello World example, I would always freeze
ont the line where voice.speak(String) is called. I found the reason and solution to this problem
thanks to this: http://stackoverflow.com/a/11514789/2775471

Essentially, on the default JavaSE that comes with Ubuntu, the default class used for managing
audio events is PulseAudioSourceDataLine. This class is subclassed from Line, which is in charge
of sending out some notification to all its listeners if the Line is open. The problem is that
PulseAudioSourceDataLine is implemented in such a way that it requires receiving some response
from its Listeners before actually opening, but the Listeners can't send out any responses until
they receive a notification from the PulseAudioSourceDataLine, which can't send out notifications
if it's not open. This causes the program to deadlock whenever speaking.

I don't know if this problem was addressed in the latest release of FreeTTS, but the changes
necessary to actually use this package in Ubuntu was made in this version of FreeTTS:
https://github.com/timabell/FreeTTS

I built this from source by just calling `ant` in the root directory of the repo and am able to
successfully use it in this project by just appending including the `lib/freetts.jar` file
onto my classpath. This fork of FreeTTS works with the default kevin16 voice and I am able to 
compile and run the provided example and this assignment with this jar.

The location of the jar in this repo is `Hello_World_FreeTTS/src/FreeTTS/lib/freetts.jar`.

An alternative solution to this could be to use the Oracle/Sun JDK unstead of the Ubuntu 14.04/OpenJDK,
but I have not experimented with this solution.

### Testing the voice
Just a warning: becaue a lot of text is used in the menu and instructions, it takes a while for the translator
to actually speak all of it, and I have not implemented a way to skip the voice on user key entry
or speed up the speaking.

The integration tests can still be run with `make test`, which still tests the whole functionality of
the project, but it will take a long time for it to complete through speech.

### Troubleshooting Errors
I know that Windows distributions of FreeTTS should not have this problem because they are not using
Ubuntu 14.04/OpenJDK. So if there are any errors that arise while running this that may be caused
by this pre-built jar file compiled on my Ubuntu computer, try instead runnning `make run_windows`
which will compile everything using the provided jars with the assignment instead of my pre-built jar.
I haven't tested if this works on Windows, but I have not been able to find anyone else having this problem
on anything that isn't Ubuntu.


## Usage
Everything is handled in a Makefile. This program was meant to run and compile on Tux
since I don't want to use an IDE.

To run normally:
```sh
$ make
```

To run the tests/dry runs:
```sh
$ make test
```


### Features
All the required features in Assignment 3 should be implemented such that everything
on both menus does something.

One question and one test can be loaded/handled at a time.

You cannot save a test/survey if one is not created or previously loaded.

A new survey/test will not be saved unless you choose to do so on menu 1.


## Save file format
The save file for a questionnaire will just be json. The questionnaire
will be a list of dictionaries with the keys "questionText", "answerType",
and "expectedAnswers". Both the questionText and answerType are strings
and the expectedAnswers are lists of varying types depending on the answerType.

A question with only one answer is represented by an expectedAnswers list
of length 1.

The expectedAnswers key is required in a Test, but not required in a Survey.
Both the questionText and answerType are required in both a Survey and Test.

Response files are also saved in json format. 3 example responses are provided
in responses.json, responses2.json, and responses3.json for tabulation purposes.

### Possible answerTypes
If an incorrect expectedAnswers format is provided for a given answerType,
an Exception is thrown.

(For the examples below with multilne strings for questionText, json does not accept
this type of multiline string, only newlines. These multiline strings are only here
as an example of the field usages.)

#### TrueFalse
expectedAnswer is a boolean
```json
{
    "questionText": "Is 5 greater than 3?",
    "answerType": "TrueFalse",
    "expectedAnswers": [True]
}
```

#### MultipleChoice
expectedAnswer is a list of strings
```json
{
    "questionText": "
        Which of the following are fruit?
        A: Apple
        B: Banana
        C: Water Mellon
        D: Potato
    ",
    "answerType": "MultipleChoice",
    "expectedAnswers": [["A", "B", "C"]]
}
```

#### Short
expectedAnswers can be null or a string. If it is null, it will not be graded.
If it is a string, it will be graded.
A ShortAnswer is marked as correct if the expected answers are equal (ignoring case).
```json
{
    "questionText": "What is the square?",
    "answerType": "Short",
    "expectedAnswers": ["4 angles", "A rectangle"]
}
```

#### Essay
expectedAnswers is null and cannot be graded.
```json
{
    "questionText": "How complex is the square?",
    "answerType": "Essay",
    "expectedAnswers": [null]
}
```

#### RankChoices
expectedAnswers is a list of strings.
```json
{
    "questionText": "
        Please rank the value of the following numbers from high to low
        A: 1
        B: 5
        C: 3 
        D: 2 
    ",
    "answerType": "RankChoices",
    "expectedAnswers": [["B", "C", "D", "A"]]
}
```

#### Matching
expectedAnswers is a list of strings where each string is in the format "number-letter".
```json
{
    "questionText": "
        Question: Please match the following two columns
        Column1    Column2
        (1): 1+1      A: 7       
        (2): 5+2      B: 6 
        (3): 3+3      C: 2
        (4): 2+2      D: 4
    ",
    "answerType": "RankChoices",
    "expectedAnswers": [["1-C", "2-A", "3-B", "4-D"]]
}
```

### Example files
An example survey json file can be found in example.survey.json.
An example test json file can be found in example.test.json.


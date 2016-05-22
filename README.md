# Survey/Test Taking System (Assignment 2)
Creating, Displaying, and Storing a Survey/Test to a File

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


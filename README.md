# Survey/Test Taking System (Assignment 2)
Creating, Displaying, and Storing a Survey/Test to a File


## Save file format
The save file for a questionnaire will just be json. The questionnaire
will be a list of dictionaries with the keys "questionText", "answerType",
and "expectedAnswer". Both the questionText and answerType are strings
and the expectedAnswer varies depending on the answerType.

### Possible answerTypes
If an incorrect expectedAnswer format is provided for a given answerType,
an Exception is thrown.

#### TrueFalse
expectedAnswer is a boolean
```json
{
    "questionText": "Is 5 greater than 3?",
    "answerType": "TrueFalse",
    "expectedAnswer": True
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
    "expectedAnswer": ["A", "B", "C"]
}
```

#### Short
expectedAnswer can be null or a string. If it is null, it will not be graded.
If it is a string, it will be graded.
```json
{
    "questionText": "What is the square?",
    "answerType": "Short",
    "expectedAnswer": "4 angles"
}
```

#### Essay
expectedAnswer is null and cannot be graded.
```json
{
    "questionText": "How complex is the square?",
    "answerType": "Essay",
    "expectedAnswer": null
}
```

#### RankChoices
expectedAnswer is a list of strings.
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
    "expectedAnswer": ["B", "C", "D", "A"]
}
```

#### Matching
expectedAnswer is a list of strings where each string is in the format "number-letter".
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
    "expectedAnswer": ["1-C", "2-A", "3-B", "4-D"]
}
```

### Example file
```json
[
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
        "expectedAnswer": ["1-C", "2-A", "3-B", "4-D"]
    },
    {
        "questionText": "Is 5 greater than 3?",
        "answerType": "True",
        "expectedAnswer": True
    },
    ...
]
```


# jsonParserUtils [![Build Status](https://api.travis-ci.org/cristib89/jsonParserUtils.svg?branch=master)](https://travis-ci.org/cristib89/jsonParserUtils)

### Description: 

A simple class which attempts to simplify parsing and extraction of data from JSON documents by going to the requested element directly.
The hierarchy in this case describes from top to bottom how the parser should extract the requested object from the json document.

###  Kudos to:

- https://github.com/fangyidong/json-simple
- https://code.google.com/archive/p/json-simple/

### Usage:

```
example json:

{
  "foo":"bar",
  "boolval":false,
  "numberval":1,
  "tags":[
    {
      "baz":"vaz",
      "tag":"random1"
    },
    {
      "baz":"vad",
      "tag":"random2"
    }
  ]
}

example code:

JSONParserUtils utils = new JSONParserUtils();
utils.extract(json, "foo"); // returns bar [String]
utils.extract(json, "boolval"); // returns Boolean.FALSE
utils.extract(json, "numberval"); // returns Long value
utils.cardinality(json, "tags"); // returns 2 
utils.extract(json, "tags.2.tag"); // returns random2
```

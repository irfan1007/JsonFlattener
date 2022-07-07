# JsonFlattener

Pre-requisite 
  - Java 18
  - Maven
  
Build Command
  - mvn clean compile package
  
Files
  - run.sh to run Main class after maven build is successful and jar is created using build command
  - data.json to give input using stdin
  - JsonFlattenerTest has TestNG unit test for positive and negative cases
  
Sample runs
  - ./run.sh "{"a":3,"b":{"c":3}}"

      ========= Input JSON ===========
      
      {a:3,b:{c:3}}

      ========= Flattened JSON ===========
      
      {"a":3,"b.c":3}
  
  -    cat data.json | ./run.sh 
        
        ========= Input JSON ===========
         
         {      "a": 1,         "b": {          "c": 2,                 "d": 3,                 "e": {                  "f": 4          }       } }

        
        ========= Flattened JSON ===========
        
        {"a":1,"b.c":2,"b.d":3,"b.e.f":4}


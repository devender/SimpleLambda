# SimpleLambda

Simple AWS Lambda written in Java

#### To create the lambda function run

```Bash
aws lambda create-function \     
--region us-east-1 \          
--function-name SimpleLambda \                         
--zip-file fileb://target/SimpleLambda-1.0-SNAPSHOT.jar \
--role arn:aws:iam::111111111111:role/executeLambda \
--handler example.Hello \
--runtime java8 \
--timeout 15 \
--memory-size 512
```

*NOTE*: The arn refers to the Role, you have to create a role that has lambda access.

#### To update the code run

```Bash
aws lambda update-function-code \
--function-name SimpleLambda \
--zip-file fileb://target/SimpleLambda-1.0-SNAPSHOT.jar
```

#### To invoke the function run

```Bash
aws lambda invoke-async \
--function-name SimpleLambda \
--invoke-args input.js \
--debug
```

You can find input.js in the src/test/rescources directory.

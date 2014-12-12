    ooooooooo.                       o8o  oooo   o8o           
    `888   `Y88.                     `"'  `888   `"'           
     888   .d88'  .oooo.   oooo d8b oooo   888  oooo   .oooo.o 
     888ooo88P'  `P  )88b  `888""8P `888   888  `888  d88(  "8 
     888          .oP"888   888      888   888   888  `"Y88b.  
     888         d8(  888   888      888   888   888  o.  )88b 
    o888o        `Y888""8o d888b    o888o o888o o888o 8""888P' 
    
# Testing JSON-RPC 2.0 APIs made easy

## Basic usage
Create a configuration file, e.g. `sample.conf` that contains the `URL`, `method` and `parameters` and the expected `response` like this:

```hocon
url = "https://api.example.com/json-rpc"
method = echo
id = "abcd1234"

params = """{
  "msg": "Hello world"
}"""

response = """{
 "jsonrpc": "2.0",
 "result": {
  "msg": "Hello world"
 },
 "id": "abcd1234"
}"""
```

And then execute it with

```shell
java -Dconfig.file=/path/to/sample.conf -jar dist/parilis-assembly-1.0.0.jar
```

If the test is successful the tool will exit with status "0" like this:

``` shell
$ java -Dconfig.file=/path/to/sample.conf -jar dist/parilis-assembly-1.0.0.jar
Testing method "echo" at https://api.example.com/json-rpc
API responded correctly.
```

If the test fails, you will see an exit status of "1" and output like this:

``` shell
$ java -Dconfig.file=/path/to/sample.conf -jar dist/parilis-assembly-1.0.0.jar
Testing method "echo" at https://api.example.com/json-rpc
Error: Got {
 "jsonrpc": "2.0",
 "result": {
  "msg": "Hello world"
 },
 "id": "abcd1234"
} where {
 "jsonrpc": "2.0",
 "result": {
  "msg": "Hello world!"
 },
 "id": "abcd1234"
} was expected.
```

Voila!
    ooooooooo.                       o8o  oooo   o8o           
    `888   `Y88.                     `"'  `888   `"'           
     888   .d88'  .oooo.   oooo d8b oooo   888  oooo   .oooo.o 
     888ooo88P'  `P  )88b  `888""8P `888   888  `888  d88(  "8 
     888          .oP"888   888      888   888   888  `"Y88b.  
     888         d8(  888   888      888   888   888  o.  )88b 
    o888o        `Y888""8o d888b    o888o o888o o888o 8""888P' 
    
# Testing JSON-RPC 2.0 APIs made easy

## Basic usage
Create a configuration file that contains the `URL`, `method` and `parameters` and the expected response like this:

```hocon
url = "https://api.example.com/json-rpc"
headers.x-custom = my header
method = echo
id = "abcd1234"
params.msg = Hello world
response = """{
 "jsonrpc": "2.0",
 "result": {
  "msg": "hi"
 },
 "id": "abcd1234"
}"""
```

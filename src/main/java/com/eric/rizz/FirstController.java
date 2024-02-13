package com.eric.rizz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


// passwod = password
@RestController
public class FirstController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from first controller";
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String post(
            @RequestBody String message
    ) {
        return "Request accepted and message is : " + message;
    }



}

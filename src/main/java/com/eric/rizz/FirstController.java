package com.eric.rizz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Hello from first controller";
//    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String post(
            @RequestBody String message
    ) {
        return "Request accepted and message is : " + message;
    }

    @PostMapping("/post-order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String post(
            @RequestBody Order order
    ) {
        return "Request accepted and message is : " + order.toString();
    }

    @PostMapping("/post-order-record")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String postRecord(
            @RequestBody OrderRecord order
    ) {
        return "Request accepted and message is : " + order.toString();
    }

    // http://localhost:8080/hello/eric
    @GetMapping("/hello/{user-name}")
    public String pathVar(
                @PathVariable("user-name") String userName
            )
    {
        return "my value= " + userName.toString();
    }

    // http://localhost:8080/hello?param=paramValue&param_name_2=value_2
    @GetMapping("/hello")
    public String paramVar(
                @RequestParam("user-name") String userName,
                @RequestParam("user-lastname") String userLastname
            )
    {
        return "my value= " + userName + "   " + userLastname ;
    }

}

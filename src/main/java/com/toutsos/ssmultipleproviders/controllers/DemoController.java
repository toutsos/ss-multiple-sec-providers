package com.toutsos.ssmultipleproviders.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @GetMapping(path = "/demo")
    public String demo(){
        return "demo";
    }

}

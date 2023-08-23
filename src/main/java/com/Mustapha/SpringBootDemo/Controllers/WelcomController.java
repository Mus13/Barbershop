package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/Welcome")
    public String welcome(){
        return welcomeService.print();
    }

}

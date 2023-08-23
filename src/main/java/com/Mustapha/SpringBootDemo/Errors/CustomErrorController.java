package com.Mustapha.SpringBootDemo.Errors;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

@RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        String errorMessage = "Oops! Something went wrong.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getErrorPath() {
        return "/error";
    }
}

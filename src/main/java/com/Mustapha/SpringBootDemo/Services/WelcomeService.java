package com.Mustapha.SpringBootDemo.Services;

import com.Mustapha.SpringBootDemo.Models.WelcomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    @Autowired
    private WelcomeModel welcomeModel;

    public String print() {
        String output = "";
        output=welcomeModel.getNameSalon()+"<br>";
        for (String name:welcomeModel.names())
            output += name.toUpperCase() + " & ";
        output = output.substring(0, output.length() - 3);
        output+="<br>";
        for (String job:welcomeModel.getJobs())
            output += job.toUpperCase() + "<br>";
        for (String description:welcomeModel.getDescriptions())
            output += description.toUpperCase() + "<br>";
        output+="<br>";
        return output;
    }
}

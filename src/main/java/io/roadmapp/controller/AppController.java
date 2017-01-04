package io.roadmapp.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@ComponentScan("io.roadmapp")
public class AppController {

    // Serve index.jsp which describe the API
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    // Serve testing json hello world
    @RequestMapping("/me")
    @ResponseBody public String me() { return "hello world";}

}

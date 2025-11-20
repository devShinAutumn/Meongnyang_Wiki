package com.example.meongnyang_wiki.controller;



import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping("")
    public String main(Model model, @RequestParam HashMap<String, Object> map) throws Exception {

        return "";
    }

}

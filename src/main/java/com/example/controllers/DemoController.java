package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.daos.DemoDao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DemoController {

    @Autowired
    private DemoDao demoDao;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    @ResponseBody // This anotation is used to return just the text.
                  // If you want to return a template you don't need that.
    public String index() {
        return "Hello Spring";
    }

    // @RequestMapping(value = "/home", method = RequestMethod.GET)
    // public String home() {
    // return "home";
    // }
    @GetMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("name", demoDao.getFirstName());
        return "home";
    }

    @GetMapping("/")
    @ResponseBody
    public String sathindu() {
        return "Hellow World";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam int id, @RequestParam String name) {
        int rowsInsterd = demoDao.insertUser(id, name);

        return rowsInsterd > 0 ? "User inserted Succesfully" : "Failed Successfully";
    }

}

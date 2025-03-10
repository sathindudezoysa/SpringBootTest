package com.example.controllers;

import com.example.daos.DemoDao;
import com.example.daos.dto.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrudController {

    @Autowired
    private DemoDao demoDao;

    @GetMapping("/crud")
    public String crud(ModelMap modelMap){
        modelMap.addAttribute("items", demoDao.getNames());
        return "crud";
    }

    @GetMapping("/new")
    public String newItem(){
        return "new";
    }
    @GetMapping("/update")
    public String updateItem(@RequestParam Integer id, ModelMap modelMap){
        Profile item = demoDao.getSearchItem(id);
        modelMap.addAttribute("id", item.getId());
        modelMap.addAttribute("name", item.getName());
        return "new";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam int id, @RequestParam String name) {
        int rowsInsterd = demoDao.insertUser(id, name);

        return rowsInsterd > 0 ? "User inserted Succesfully" : "Failed Successfully";
    }

    @PostMapping("/change")
    @ResponseBody
    public String changeUser(@RequestParam int id, @RequestParam String name){
        int update = demoDao.updateUser(id, name);
        return  update > 0 ? "User Updated Successfully" : "Failed Successfullu";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam int id){
        int delete = demoDao.deleteUser(id);

        return delete > 0 ? "User deleted Succesfully" : "Failed Successfully";
    }
}

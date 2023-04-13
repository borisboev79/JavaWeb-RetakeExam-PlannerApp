package bg.softuni.examretake.controller;

import bg.softuni.examretake.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("assign/{id}")
    public String assignTask(@PathVariable Long id){
        this.userService.assignTaskToUser(id);

        return "redirect:/home";
    }

    @GetMapping("return/{id}")
    public String returnTask(@PathVariable Long id){
        this.userService.returnTaskFromUser(id);

        return "redirect:/home";
    }
}

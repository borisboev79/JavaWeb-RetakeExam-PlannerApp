package com.example.plannerapp.controller;

import com.example.plannerapp.helper.LoggedUser;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final TaskService taskService;

    @Autowired
    public HomeController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        if(loggedUser.getId() == null) {
            model.setViewName("index");
            return model;
        }

        Set<TaskViewModel> tasks = this.taskService.getAllTasks();
        Set<TaskViewModel> assignedTasks = this.taskService.getAssignedTasks();

        model.addObject("tasks", tasks);
        model.addObject("assignedTasks", assignedTasks);

        model.setViewName("home");

        return model;
    }
}

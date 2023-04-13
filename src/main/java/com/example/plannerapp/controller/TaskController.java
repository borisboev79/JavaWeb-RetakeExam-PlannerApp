package com.example.plannerapp.controller;

import com.example.plannerapp.helper.LoggedUser;
import com.example.plannerapp.model.binding.TaskAddModel;
import com.example.plannerapp.model.enums.PriorityLevel;
import com.example.plannerapp.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final LoggedUser loggedUser;

    @Autowired
    public TaskController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute(name = "taskAddModel")
    private TaskAddModel taskAddModel(){
        return new TaskAddModel();
    }



    @GetMapping("/add")
    public String getAddTask(Model model){

        if(loggedUser.getId() == null) {
            return "index";
        }


        model.addAttribute("priorities", PriorityLevel.values());
        return "task-add";
    }

    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute(name = "taskAddModel") TaskAddModel taskAddModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("taskAddModel", taskAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.taskAddModel", bindingResult);

            return "redirect:/tasks/add";
        }

        this.taskService.addTask(taskAddModel);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable Long id){
        this.taskService.removeTask(id);

        return "redirect:/home";
    }

}

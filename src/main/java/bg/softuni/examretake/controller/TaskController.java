package bg.softuni.examretake.controller;

import bg.softuni.examretake.model.enums.PriorityLevel;
import bg.softuni.examretake.service.task.TaskService;
import bg.softuni.examretake.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }



    @GetMapping("/add")
    public String getAddSong(Model model){
        model.addAttribute("priorities", PriorityLevel.values());
        return "task-add";
    }

}

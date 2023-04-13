package bg.softuni.examretake.controller;

import bg.softuni.examretake.helper.LoggedUser;
import bg.softuni.examretake.model.TaskViewModel;
import bg.softuni.examretake.service.task.TaskService;
import bg.softuni.examretake.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public HomeController(LoggedUser loggedUser, UserService userService, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        if(loggedUser.getId() == null) {
            model.setViewName("index");
            return model;
        }

        Set<TaskViewModel> tasks = this.taskService.getAllTasks();
      //  Set<Song> playlist = this.userService.getPlaylist(this.loggedUser.getId());

        model.addObject("tasks", tasks);
       // model.addObject("playlist", playlist);
        //model.addObject("totalMin", playlist.stream().mapToInt(Song::getDuration).sum());

        model.setViewName("home");

        return model;
    }
}

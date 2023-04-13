package bg.softuni.examretake.service.task;

import bg.softuni.examretake.helper.LoggedUser;
import bg.softuni.examretake.model.TaskViewModel;
import bg.softuni.examretake.model.binding.TaskAddModel;
import bg.softuni.examretake.model.entity.Task;
import bg.softuni.examretake.model.entity.User;
import bg.softuni.examretake.repository.PriorityRepository;
import bg.softuni.examretake.repository.TaskRepository;
import bg.softuni.examretake.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final ModelMapper mapper;
    private final LoggedUser loggedUser;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, PriorityRepository priorityRepository, ModelMapper mapper, LoggedUser loggedUser) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
        this.mapper = mapper;
        this.loggedUser = loggedUser;
    }

    public void addTask(TaskAddModel taskAddModel) {

        Task task = this.mapper.map(taskAddModel, Task.class);
        task.setPriority(this.priorityRepository.findByName(taskAddModel.getPriority()).orElse(null));

        this.taskRepository.saveAndFlush(task);

    }

    public Set<TaskViewModel> getAllTasks() {
        Set<TaskViewModel> tasks = new LinkedHashSet<>();
        List<Task> taskList = this.taskRepository.findAll().stream().filter(task -> task.getUser() == null).toList();

        for (Task task : taskList) {
            TaskViewModel taskModel = new TaskViewModel();
            this.mapper.map(task, taskModel);
            taskModel.setPriority(task.getPriority().getName());

            tasks.add(taskModel);
        }

        return tasks;
    }

    @Transactional
    public Set<TaskViewModel> getAssignedTasks() {
        User user = this.userRepository.findById(this.loggedUser.getId()).orElse(new User());
        Set<TaskViewModel> tasks = new LinkedHashSet<>();
        List<Task> taskList = user.getTasks().stream().filter(Objects::nonNull).toList();

        for (Task task : taskList) {
            TaskViewModel taskModel = new TaskViewModel();
            this.mapper.map(task, taskModel);
            taskModel.setPriority(task.getPriority().getName());

            tasks.add(taskModel);
        }

        return tasks;
    }
}

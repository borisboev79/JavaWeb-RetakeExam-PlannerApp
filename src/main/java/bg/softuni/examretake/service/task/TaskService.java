package bg.softuni.examretake.service.task;

import bg.softuni.examretake.model.TaskViewModel;
import bg.softuni.examretake.model.binding.TaskAddModel;
import bg.softuni.examretake.model.entity.Priority;
import bg.softuni.examretake.model.entity.Task;
import bg.softuni.examretake.repository.PriorityRepository;
import bg.softuni.examretake.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, PriorityRepository priorityRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.mapper = mapper;
    }

    public void addTask(TaskAddModel taskAddModel) {

        Task task = this.mapper.map(taskAddModel, Task.class);
        task.setPriority(this.priorityRepository.findByName(taskAddModel.getPriority()).orElse(null));

        this.taskRepository.saveAndFlush(task);

    }

    public Set<TaskViewModel> getAllTasks() {
        Set<TaskViewModel> tasks = new LinkedHashSet<>();
        List<Task> taskList = this.taskRepository.findAll();

        for (Task task : taskList) {
            TaskViewModel taskModel = new TaskViewModel();
            this.mapper.map(task, taskModel);
            taskModel.setPriority(task.getPriority().getName());

            tasks.add(taskModel);
        }

        return tasks;
    }
}

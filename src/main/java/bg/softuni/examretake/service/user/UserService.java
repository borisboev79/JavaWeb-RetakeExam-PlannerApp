package bg.softuni.examretake.service.user;


import bg.softuni.examretake.helper.LoggedUser;
import bg.softuni.examretake.model.entity.Task;
import bg.softuni.examretake.model.entity.User;
import bg.softuni.examretake.repository.TaskRepository;
import bg.softuni.examretake.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.loggedUser = loggedUser;
    }


    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


    @Transactional
    public void assignTaskToUser(Long id) {
        User user = this.userRepository.findById(this.loggedUser.getId()).orElse(new User());
        Task task = this.taskRepository.findById(id).orElse(null);

        user.getTasks().add(task);

        assert task != null;
        task.setUser(user);

        this.userRepository.saveAndFlush(user);
        this.taskRepository.saveAndFlush(task);

    }
}

package com.example.plannerapp.service.user;


import com.example.plannerapp.helper.LoggedUser;
import com.example.plannerapp.model.entity.Task;
import com.example.plannerapp.model.entity.User;
import com.example.plannerapp.repository.TaskRepository;
import com.example.plannerapp.repository.UserRepository;
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

    @Transactional
    public void returnTaskFromUser(Long id) {
        User user = this.userRepository.findById(this.loggedUser.getId()).orElse(new User());
        Task task = this.taskRepository.findById(id).orElse(null);

        user.getTasks().remove(task);

        assert task != null;
        task.setUser(null);

        this.userRepository.saveAndFlush(user);
        this.taskRepository.saveAndFlush(task);

    }
}

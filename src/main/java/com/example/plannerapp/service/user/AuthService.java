package com.example.plannerapp.service.user;


import com.example.plannerapp.helper.LoggedUser;
import com.example.plannerapp.model.binding.UserLoginModel;
import com.example.plannerapp.model.binding.UserRegisterModel;
import com.example.plannerapp.model.entity.User;
import com.example.plannerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.encoder = encoder;
    }


    public void registerUser(UserRegisterModel userRegisterModel) {
        this.userRepository.saveAndFlush(User.builder()
                .username(userRegisterModel.getUsername())
                .password(encoder.encode(userRegisterModel.getPassword()))
                .email(userRegisterModel.getEmail())
           //     .offers(new ArrayList<>())
           //     .boughtOffers(new ArrayList<>())
                .build());
    }


    public void loginUser(UserLoginModel userLoginModel) {
        User user = this.userRepository.findByUsername(userLoginModel.getUsername()).orElse(new User());

        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }


    public void logoutUser() {
        this.loggedUser.clearUser();
    }


    public boolean isAuthentic(String username, String password) {
        User user = this.userRepository.findByUsername(username).orElse(new User());
        String encodedPassword = user.getPassword();

        return encoder.matches(password, encodedPassword);
    }
}


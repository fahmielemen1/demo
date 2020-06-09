package com.example.demo.service;

import com.example.demo.UserRequest;
import com.example.demo.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    String username, password;
    int id;

    public String register (UserRequest userRequest) {

        User existUser = userRepository.findByUserName(userRequest.getUsername());

        if (existUser != null) {
            return "gagal, username sudah ada";
        }
        User user = new User();
        user.setUserName(userRequest.getUsername());
        user.setPassWord(userRequest.getPassword());
        userRepository.save(user);

        return "register sukses";
    }

    public UserResponse login(UserRequest userRequest) {

        User existUser = userRepository.findByUserUsernamePassword(userRequest.getUsername(),
                userRequest.getPassword());

        if (existUser == null) {
            return null;
        } else {

            this.id = existUser.getId();
            this.username = existUser.getUserName();
            this.password = existUser.getPassWord();
        }
        UserResponse userResponse = new UserResponse(this.id, this.username, this.password);

        return userResponse;

    }
}

package io.roadmapp.service;

import io.roadmapp.model.CurrentUserDetails;
import io.roadmapp.model.User;

import java.util.List;

public interface UserService {

    User getUser(int id);

    User getUser(String email);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    List<User> getUsers();

}
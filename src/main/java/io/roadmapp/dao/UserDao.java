package io.roadmapp.dao;

import io.roadmapp.model.User;

import java.util.List;

public interface UserDao {

    User getUser(int id);

    void saveUser(User user);

    void deleteUser(String email);

    List<User> getUsers();

    User getUser(String email);

}
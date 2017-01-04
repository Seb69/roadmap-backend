package io.roadmapp.controller;

import io.roadmapp.model.User;
import io.roadmapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService service;

    /*
     * This method will return the current user.
     */
    @RequestMapping(value = { "/current" }, method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public @ResponseBody Object getCurrentUser(Authentication authentication) {
        System.out.println("Fetching Current User...");
        return authentication.getPrincipal();
    }


    /*
     * This method will list all existing users.
     */
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public @ResponseBody List<User> getUsers() {
        System.out.println("Fetching Users...");
        List<User> users = service.getUsers();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }


    /*
     * This method will return user via the param id.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User " + id + "...");
        User user = service.getUser(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return null;
        }
        return user;
    }

    /*
     * This method will provide the medium to add a new user.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = { "" }, method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User...");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println(user);
        service.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return user;
    }

    /*
     * This method will update user via id.
     */
    @RequestMapping(value = { "{id}" }, method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public @ResponseBody User updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println("Updating User " + id + "...");
        User currentUser = service.getUser(id);

        if (currentUser == null) {
            System.out.println("User with id " + id + " not found");
            return null;
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        service.updateUser(currentUser);
        return currentUser;
    }

}
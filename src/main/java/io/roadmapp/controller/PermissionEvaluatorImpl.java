package io.roadmapp.controller;

import io.roadmapp.model.CurrentUserDetails;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component(value="permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    public PermissionEvaluatorImpl() {}

    public boolean hasPermission(Authentication authentication, Object idToCheck, Object principal) {
        CurrentUserDetails currentUser = (CurrentUserDetails) principal;
        Integer id = (Integer) idToCheck;
        System.out.println("CONTROL ACCESS:: currentUserId =" + currentUser.getId() + " idToCheck=" + id);
        return currentUser != null && currentUser.getId() == id;
    }

    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }

}
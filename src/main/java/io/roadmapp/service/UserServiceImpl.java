package io.roadmapp.service;

import java.util.List;

import io.roadmapp.dao.UserDao;
import io.roadmapp.model.CurrentUserDetails;
import io.roadmapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User getUser(int id) {
        return dao.getUser(id);
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.getUser(user.getId());
        if(entity!=null){
            entity.setUsername(user.getUsername());
            entity.setEmail(user.getEmail());
            entity.setPassword(user.getPassword());
        }
    }

    public void deleteUser(int id) {

    }

    public List<User> getList() {
        return null;
    }

    public void deleteUser(String email) {
        dao.deleteUser(email);
    }

    public List<User> getUsers() {
        return dao.getUsers();
    }

    public User getUser(String email) {
        return dao.getUser(email);
    }

    public boolean isUserEmailUnique(Integer id, String email) {
        User user = getUser(email);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }

}

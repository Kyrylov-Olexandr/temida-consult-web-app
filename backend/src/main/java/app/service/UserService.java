package app.service;


import app.model.User;
import app.vo.SignUpForm;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findOne(String email);

    Collection<User> findByRole(String role);

    User registerNewUser(SignUpForm signUpForm);

    User updateProfile(User user);

    List<User> findAll();

    List<User> findAll(String filterText);

    void deleteById(Long id);

}
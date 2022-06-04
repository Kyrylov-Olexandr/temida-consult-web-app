package app.service.impl;


import app.enums.Role;
import app.exception.UserNotValidException;
import app.model.User;
import app.repository.UserRepository;
import app.service.UserService;
import app.vo.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<User> findOne(String email) {
        return Optional.of(userRepository.findByEmail(email));
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public User registerNewUser(SignUpForm signUpForm) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        user.setEmail(signUpForm.getEmail());
        user.setFirstName(signUpForm.getFirstName());
        user.setLastName(signUpForm.getLastName());
        user.setRole(Role.ADMIN.name());
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserNotValidException();
        }

    }

    @Override
    @Transactional
    public User updateProfile(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setZip(user.getZip());
        oldUser.setPhone(user.getPhone());
        oldUser.setLocality(user.getLocality());
        oldUser.setRegion(user.getRegion());
        oldUser.setStreet(user.getEmail());
        return userRepository.save(oldUser);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(String filterText) {
        return userRepository.findAllByFilter(filterText);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}


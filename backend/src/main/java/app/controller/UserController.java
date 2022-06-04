package app.controller;

import app.model.User;
import app.security.JWT.JwtProvider;
import app.service.UserService;
import app.vo.JwtResponse;
import app.vo.LoginForm;
import app.vo.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    private final UserService userService;

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity save(@RequestBody SignUpForm signUpForm) {
        try {
            userService.registerNewUser(signUpForm);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOne(userDetails.getUsername()).orElseThrow();
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.fullName(), user.getRole()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity updateProfile(@RequestBody User user, Principal principal) {
        try {
            if (!principal.getName().equals(user.getEmail())) throw new IllegalArgumentException();
            userService.updateProfile(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam() String email) {
        try {
            User user = userService.findOne(email).orElseThrow();
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String filterText) {
        if (filterText.equals("")) {
            return ResponseEntity.ok(userService.findAll());
        } else {
            return ResponseEntity.ok(userService.findAll(filterText));
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity getAll(@RequestParam Long id) {
       userService.deleteById(id);
       return ResponseEntity.ok().build();
    }
}
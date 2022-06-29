package com.liga.homework.controller;

import com.liga.homework.model.User;
import com.liga.homework.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.OK)
  public void saveUser(@RequestBody User user){
    userService.save(user);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable("id") Long id){
    userService.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getOneUser(@PathVariable("id") Long id){
    return userService.getOneUserById(id);
  }

  @DeleteMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllUsers(){
    userService.deleteAll();
  }

}

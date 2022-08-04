package com.liga.homework.controller;

import com.liga.homework.model.User;
import com.liga.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v1")
public class UserController {

  private final UserService userService;

  @PostMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public void addUser(@RequestBody (required=false) User user){
    if(user == null) user = User.builder().name("Нет имени").build();
    userService.save(user);
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
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
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllTasks(){
    userService.deleteAll();
  }

  @PutMapping("/{id}")
  public void editTask(@PathVariable("id") Long id, @RequestParam String params){
    //   Вариант 1
    userService.edit(id, params);
    //   Вариант 2
//   Можно вообще передать json и сразу сохранить в репозиторий
  }
}

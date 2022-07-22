package com.liga.homework.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.liga.homework.model.User;
import com.liga.homework.repo.UserRepo;
import com.liga.homework.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private UserService userService;

  @Mock
  UserRepo userRepo;

  @Test
  void Should_ReturnUserList() {
    List<User> userList = getUserList();

    when(userRepo.findAll()).thenReturn(userList);

    List<User> result = userService.getAllUsers();

    assertEquals(userList.size(), result.size());
    assertEquals(userList.get(2).getName(), result.get(2).getName());
  }

  @Test
  void Should_ReturnEmptyUserList_When_RepoIsEmpty() {
    List<User> userList = new ArrayList<>();

    when(userRepo.findAll()).thenReturn(userList);

    List<User> result = userService.getAllUsers();

    assertEquals(userList.size(), result.size());
    assertTrue(result.isEmpty());
  }

  @Test
  void Should_GetOneUserById_When_IdIsValid() {
    User user = getNewUser();

    when(userRepo.findById(any())).thenReturn(Optional.ofNullable(user));

    User result = userService.getOneUserById(user.getId());

    assertEquals(user.getName(), result.getName());

  }

  @ParameterizedTest
  @NullSource
  @ValueSource(longs = {-1, -13})
  void Should_TrowsException_When_IdIsInvalid(Long param) {
    assertThrows(EntityNotFoundException.class, ()->userService.getOneUserById(param));
  }


  @ParameterizedTest
  @NullSource
  @ValueSource(longs = {-1, -13})
  void editException(Long param) {
    assertThrows(EntityNotFoundException.class, ()->userService.edit(param, "test"));
  }

  List<User> getUserList() {
    User user1 = User.builder().name("Tom Marvolo Riddle").build();
    User user2 = User.builder().name("Harry James Potter").build();
    User user3 = User.builder().name("Dobby").build();
    return List.of(user1,user2,user3);
  }

  User getNewUser(){
    return User.builder().name("Albus Dumbldore").build();
  }

  @BeforeEach
  void setup(){
    userService = Mockito.spy(new UserServiceImpl(userRepo));
  }

}
package com.liga.homework.service.impl;

import com.liga.homework.model.User;
import com.liga.homework.repo.UserRepo;
import com.liga.homework.service.UserService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public void edit(Long id, String newValue) {
    User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    user.setName(newValue);
    userRepo.save(user);
  }

  @Override
  public User getOneUserById(Long id) {
    return userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void save(User user) {
    userRepo.save(user);
  }

  @Override
  public void deleteById(Long id) {
    userRepo.deleteById(id);
  }

  @Override
  public void deleteAll() {
    userRepo.deleteAll();
  }
}

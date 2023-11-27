package com.rentacar.service;

import com.rentacar.model.User;
import com.rentacar.request.CarRequest;
import com.rentacar.request.ChangePasswordRequest;
import com.rentacar.request.UserRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    List<User> getAllUsers();
    User getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    User createUser(UserRequest request);
    User updateUser(Long id, CarRequest request);
    void deleteUser(Long id);
}

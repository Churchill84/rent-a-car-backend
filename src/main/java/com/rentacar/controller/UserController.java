package com.rentacar.controller;

import com.rentacar.assembler.UserModelAssembler;
import com.rentacar.model.User;
import com.rentacar.request.ChangePasswordRequest;
import com.rentacar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Qualifier("userService")
    private final UserService service;
    private final UserModelAssembler userModelAssembler;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{email}")
    public EntityModel<User> findUserByEmail(@PathVariable String email) {
        Optional<User> user = service.getUserByEmail(email);
        return user.map(userModelAssembler::toModel).orElse(null);
    }
}

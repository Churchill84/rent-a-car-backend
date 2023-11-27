package com.rentacar.controller;

import com.rentacar.request.AuthenticationRequest;
import com.rentacar.response.AuthenticationResponse;
import com.rentacar.service.AuthenticationService;
import com.rentacar.request.RegisterRequest;
import com.rentacar.util.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @PostMapping("/validate")
  public ResponseEntity<Boolean> validateToken(@RequestBody Map<String, String> tokenMap) {
    String token = tokenMap.get("token");
    Optional<UserDetails> userDetails = UserUtils.getCurrentUserDetails();

    if (userDetails.isPresent()) {
      boolean isValid = service.isTokenValid(token, userDetails.get());
      return ResponseEntity.ok(isValid);
    }

    return ResponseEntity.noContent().build();
  }

}

package com.rentacar.util;

import com.rentacar.exception.UserNotFoundException;
import com.rentacar.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserUtils implements AuditorAware<UserDetails> {

    private UserUtils() {
    }

    @Override
    public Optional<UserDetails> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal);
    }

    public static User getUser() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;

        if (authentication != null && authentication.isAuthenticated()) {
            user = (User) authentication.getPrincipal();
        }
        return user;
    }

    public static Optional<UserDetails> getCurrentUserDetails() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal);
    }
}

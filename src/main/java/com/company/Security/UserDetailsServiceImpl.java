package com.company.Security;

import com.company.Models.Users.Users;
import com.company.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable ((userService.findByLogin (login)));
        Users user = usersOptional.orElseThrow(() -> new UsernameNotFoundException ("User doesn't exist"));
        return UserSecurity.fromUser (user);
    }
}
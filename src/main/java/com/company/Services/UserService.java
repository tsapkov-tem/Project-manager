package com.company.Services;

import com.company.Models.Users.Users;
import com.company.Repos.UsersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepos usersRepos;

    @Autowired
    public UserService(UsersRepos usersRepos) {
        this.usersRepos = usersRepos;
    }

    public Users findByLogin(String login) {
        return usersRepos.findByLogin(login).orElse(null);
    }
}

package com.company.Services;

import com.company.Repos.UsersRepos;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepos usersRepos;

    public UserService(UsersRepos usersRepos) {
        this.usersRepos = usersRepos;
    }
}

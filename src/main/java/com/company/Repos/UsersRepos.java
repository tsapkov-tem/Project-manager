package com.company.Repos;

import com.company.Models.Users.Users;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ComponentScan("com.company.Repos")
public interface UsersRepos extends CrudRepository<Users, Integer> {
    Optional<Users> findByLogin(String login);
}

package com.company.Repos;

import com.company.Models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepos extends CrudRepository<Users, Integer> {
}

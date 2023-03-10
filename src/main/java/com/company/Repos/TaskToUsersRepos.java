package com.company.Repos;

import com.company.Models.TasksToUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskToUsersRepos extends CrudRepository<TasksToUsers, Integer> {
}

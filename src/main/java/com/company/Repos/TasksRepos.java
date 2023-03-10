package com.company.Repos;

import com.company.Models.Tasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepos extends CrudRepository<Tasks, Integer> {
}

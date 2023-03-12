package com.company.Repos;

import com.company.Models.Tasks.Tasks;
import com.company.Models.Tasks.TasksToUsers;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskToUsersRepos extends CrudRepository<TasksToUsers, Integer> {
    void deleteAllByIdTaskIn(List<Integer> idTask);

    void removeByIdTask(Integer idTask);
    Optional<TasksToUsers> findByIdTask(Integer idTask);
}

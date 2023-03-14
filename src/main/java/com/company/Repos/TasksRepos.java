package com.company.Repos;

import com.company.Models.Tasks.Tasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepos extends CrudRepository<Tasks, Integer> {
    List<Tasks> findAllByIdIn(List<Integer> ids);
    void deleteAllByIdIn(List<Integer> ids);

    @Override
    void deleteById(Integer idTask);
}

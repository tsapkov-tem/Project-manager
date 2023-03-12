package com.company.Repos;


import com.company.Models.Projects.HierarchyTasks;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HierarchyTasksRepos extends CrudRepository<HierarchyTasks, Integer> {
    List<HierarchyTasks> findAllByIdProject(Integer id);
    void removeAllByIdProject(Integer idProject);

    Optional<HierarchyTasks> findByIdTask(Integer idTask);
    void deleteByIdTask(Integer idTask);

    void deleteAllByIdTaskIn(List<Integer> ids);
}

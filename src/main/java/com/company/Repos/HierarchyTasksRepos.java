package com.company.Repos;

import com.company.Models.HierarchyTasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierarchyTasksRepos extends CrudRepository<HierarchyTasks, Integer> {
}

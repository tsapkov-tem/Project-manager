package com.company.Repos;

import com.company.Models.HierarchyProjects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierarchyProjectsRepos extends CrudRepository <HierarchyProjects, Integer> {
}

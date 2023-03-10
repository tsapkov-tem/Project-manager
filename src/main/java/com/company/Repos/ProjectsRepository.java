package com.company.Repos;

import com.company.Models.Projects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository<Projects, Integer> {
}

package com.company.Repos;

import com.company.Models.Projects.Projects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsRepos extends CrudRepository<Projects, Integer> {
    @Override
    Optional<Projects> findById(Integer id);
    List<Projects> findAllByParent(boolean parent);

    List<Projects> findAllByIdIn(List<Integer> ids);
}

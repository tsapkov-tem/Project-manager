package com.company.Repos;


import com.company.Models.Projects.HierarchyProjects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HierarchyProjectsRepos extends CrudRepository<HierarchyProjects, Integer> {
    List<HierarchyProjects> findAllByIdParent(Integer id);
    void deleteByIdChild(Integer idChild);

    List<HierarchyProjects> findByIdChild(Integer idChild);

}

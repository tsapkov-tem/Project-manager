package com.company.Models.Projects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "HIERARCHY_TASKS", schema = "PUBLIC", catalog = "MANAGERDB")
public class HierarchyTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idProject;
    private Integer idTask;
}

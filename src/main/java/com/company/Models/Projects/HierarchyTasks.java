package com.company.Models.Projects;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "HIERARCHY_TASKS")
public class HierarchyTasks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ID_PROJECT")
    private Integer idProject;
    @Basic
    @Column(name = "ID_TASK")
    private Integer idTask;

}

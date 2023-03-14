package com.company.Models.Projects;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "HIERARCHY_PROJECTS")
public class HierarchyProjects {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ID_PARENT", nullable = false)
    private Integer idParent;
    @Basic
    @Column(name = "ID_CHILD")
    private Integer idChild;
}

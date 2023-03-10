package com.company.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "HIERARCHY_PROJECTS", schema = "PUBLIC", catalog = "MANAGERDB")
public class HierarchyProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idParent;
    private Integer idChild;
}

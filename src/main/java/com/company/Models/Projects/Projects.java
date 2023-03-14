package com.company.Models.Projects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Projects {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "NAME", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "PARENT", nullable = false)
    private Boolean parent;
    @Basic
    @Column(name = "CHILD", nullable = false)
    private Boolean child;

}

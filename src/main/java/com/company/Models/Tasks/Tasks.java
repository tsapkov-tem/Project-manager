package com.company.Models.Tasks;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tasks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "DATE_CREATE", nullable = false, length = 30)
    private String date_create;
    @Basic
    @Column(name = "DATE_CHANGE", nullable = false, length = 30)
    private String date_change;
    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "TEXT", nullable = false)
    private String text;
    @Basic
    @Column(name = "TYPE", nullable = false, length = 20)
    private String type;
    @Basic
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

}

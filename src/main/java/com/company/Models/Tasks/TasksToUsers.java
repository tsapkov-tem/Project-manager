package com.company.Models.Tasks;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TASKS_TO_USERS")
public class TasksToUsers {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ID_TASK")
    private Integer idTask;
    @Basic
    @Column(name = "ID_USER")
    private Integer idUser;
}

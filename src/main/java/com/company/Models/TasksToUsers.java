package com.company.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TASKS_TO_USERS", schema = "PUBLIC", catalog = "MANAGERDB")
public class TasksToUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idTask;
    private Integer idUser;

}

package com.company.Models.Tasks;

import com.company.Models.Users.Users;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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

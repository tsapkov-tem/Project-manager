package com.company.Models.Users;

import com.company.Models.Tasks.TasksToUsers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    private Integer id;
    private String login;
    private String password;
    private Role role;

    @OneToOne
    public TasksToUsers tasksToUsers;
}

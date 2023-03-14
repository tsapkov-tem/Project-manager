package com.company.Models.Users;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "LOGIN", nullable = false, length = 20)
    private String login;
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "ROLE", nullable = false, length = 20)
    private String role;

    public Role getRole(){
        if(role.equals("ADMIN")){
            return Role.ADMIN;
        }
        return Role.USER;
    }
}

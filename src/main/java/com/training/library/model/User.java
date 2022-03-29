package com.training.library.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    @Column(name = "USER_NAME", nullable = false)
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    private final UserRole userRole = UserRole.USER;
    
    public User(String username, String password) {
        this.name = username;
        this.password = password;
    }
}

package com.vivek.practice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Appuser")
@Data // Create getters and setters
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, nullable = false,name="username")
    private String username;

    @Column(unique = true, nullable = false,name="email")
    private String email;

    @Column(name="password")
    private String password;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "appUserRole", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "appuserroles")
    //List<AppUserRole> appUserRoles;
    private String appuserroles;

}
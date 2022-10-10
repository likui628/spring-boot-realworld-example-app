package com.example.realworld.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**

 create table users (
 id varchar(255) primary key,
 username varchar(255) UNIQUE,
 password varchar(255),
 email varchar(255) UNIQUE,
 bio text,
 image varchar(511)
 );

 */
@Entity(name = "Users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity extends BaseEntity{

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable =false,unique = true)
    private String email;

    @Column(name = "bio",columnDefinition = "text")
    private String bio;

    @Column(name = "image")
    private String image;
}

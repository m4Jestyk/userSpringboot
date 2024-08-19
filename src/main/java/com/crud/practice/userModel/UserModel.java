package com.crud.practice.userModel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_subjects", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "subject")
    private List<String> subjects = new ArrayList<>();


    //Getters

    public Long getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public List<String> getSubjects()
    {
        return this.subjects;
    }

    //Setters

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setSubjects(List<String> subjects){
        this.subjects = subjects;
    }

    public void addSubject(String subject)
    {
        this.subjects.add(subject);
    }

    public void removeSubject(String subject)
    {
        this.subjects.remove(subject);
    }

}

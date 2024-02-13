package com.FSPj1.Spingboot.model;


import jakarta.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "role")
    private String role;
    @Column(name = "email_id")
    private String emailID;

    public Employee()   //Whenever u create parameterized constructor u need default constructor since hibernate uses proxies to create proxy objects
    {


    }
    public Employee(String firstName, String role, String emailID) //parameterized constructor
    {
        this.firstName = firstName;
        this.role = role;
        this.emailID = emailID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}

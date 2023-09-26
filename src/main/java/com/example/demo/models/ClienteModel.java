package com.example.demo.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String fullName;
    private String email;
    private Date dataOfBirth;
    private Date dateOfInscription;
    private Long age;
    private String phone;
    private String emergencyNumber;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setDataOfBirth(Date dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public Date getDataOfBirth() {
        return this.dataOfBirth;
    }

    public void setDateOfInscription(Date dataOfInscription) {
        this.dateOfInscription = dataOfInscription;
    }

    public Date getDateOfInscription() {
        return this.dateOfInscription;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getAge() {
        return this.age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setId(Long id2) {
    }
}
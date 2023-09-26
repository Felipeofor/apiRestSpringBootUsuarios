package com.example.demo.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
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
    private Date dateOfBirth;
    private Date dateOfInscription;
    private Long age;
    private String phone;
    private String emergencyNumber;
    private Date nextDueDate;
    private Boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.calculateNextDueDate();
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfInscription(Date dataOfInscription) {
        this.dateOfInscription = dataOfInscription;
    }

    public Date getDateOfInscription() {
        return this.dateOfInscription;
    }

    public void setAge(Integer age) {
        this.age = age != null ? age.longValue() : null;
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

    public Date getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(Date nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if (isActive) {
            this.updateNextDueDate();
        }
    }

    // Método para calcular la fecha de vencimiento automáticamente al crear un
    // nuevo cliente
    @PrePersist
    public void calculateNextDueDate() {
        if (dateOfInscription != null) {
            LocalDate inscriptionDate = dateOfInscription.toLocalDate();
            LocalDate nextDueDate = inscriptionDate.plusMonths(1);
            this.nextDueDate = Date.valueOf(nextDueDate);
        }
    }

    // Método para actualizar la fecha de vencimiento basada en la fecha actual
    public void updateNextDueDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDueDate = currentDate.plusMonths(1);
        this.nextDueDate = Date.valueOf(nextDueDate);
    }
}
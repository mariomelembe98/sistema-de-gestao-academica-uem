package com.uem.sgnfx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "patients", schema = "medlink")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "invitation_letter")
    private String invitationLetter;

    @Column(name = "visa_undertaking_form")
    private String visaUndertakingForm;

    @Column(name = "visa_application_form")
    private String visaApplicationForm;

    @Column(name = "passport_copy")
    private String passportCopy;

    @Column(name = "bi_copy")
    private String biCopy;

    @Column(name = "visa_photos")
    private String visaPhotos;

    @Column(name = "bank_statement")
    private String bankStatement;

    @Column(name = "medical_report")
    private String medicalReport;

    @Column(name = "flight_tickets")
    private String flightTickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Patient(Long id, String name, LocalDate dateOfBirth, String email, String phone, String address, String invitationLetter, String visaUndertakingForm, String visaApplicationForm, String passportCopy, String biCopy, String visaPhotos, String bankStatement, String medicalReport, String flightTickets, User user, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.invitationLetter = invitationLetter;
        this.visaUndertakingForm = visaUndertakingForm;
        this.visaApplicationForm = visaApplicationForm;
        this.passportCopy = passportCopy;
        this.biCopy = biCopy;
        this.visaPhotos = visaPhotos;
        this.bankStatement = bankStatement;
        this.medicalReport = medicalReport;
        this.flightTickets = flightTickets;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvitationLetter() {
        return invitationLetter;
    }

    public void setInvitationLetter(String invitationLetter) {
        this.invitationLetter = invitationLetter;
    }

    public String getVisaUndertakingForm() {
        return visaUndertakingForm;
    }

    public void setVisaUndertakingForm(String visaUndertakingForm) {
        this.visaUndertakingForm = visaUndertakingForm;
    }

    public String getVisaApplicationForm() {
        return visaApplicationForm;
    }

    public void setVisaApplicationForm(String visaApplicationForm) {
        this.visaApplicationForm = visaApplicationForm;
    }

    public String getPassportCopy() {
        return passportCopy;
    }

    public void setPassportCopy(String passportCopy) {
        this.passportCopy = passportCopy;
    }

    public String getBiCopy() {
        return biCopy;
    }

    public void setBiCopy(String biCopy) {
        this.biCopy = biCopy;
    }

    public String getVisaPhotos() {
        return visaPhotos;
    }

    public void setVisaPhotos(String visaPhotos) {
        this.visaPhotos = visaPhotos;
    }

    public String getBankStatement() {
        return bankStatement;
    }

    public void setBankStatement(String bankStatement) {
        this.bankStatement = bankStatement;
    }

    public String getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(String medicalReport) {
        this.medicalReport = medicalReport;
    }

    public String getFlightTickets() {
        return flightTickets;
    }

    public void setFlightTickets(String flightTickets) {
        this.flightTickets = flightTickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
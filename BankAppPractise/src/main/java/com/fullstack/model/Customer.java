package com.fullstack.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custId;
    @Setter
    @Size(min = 2, message = "Employee Name should be at-least 2 characters")
    private String custName;
    @Setter
    @Column(unique = true)
    @Email(message = "Email must e valid")
    private String custEmail;
    @Setter
    @Size(min = 4, message = "Employee password should be at-least 4 characters")
    private String custPass;
    private double custAccBalanace;
    @Setter
    @Range(min = 1000000000L, max = 9999999999L, message = "Employee Contact Number Must be 10 Digits")
    @Column(unique = true)
    private double custContactNumber;
    @Setter
    private String custAddress;
    @Setter
    private int otp;

    public int getCustId() {
        return custId;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public String getCustPass() {
        return custPass;
    }

    public double getCustAccBalanace() {
        return custAccBalanace;
    }

    public void setCustAccBalanace(double custAccBalanace) {
        this.custAccBalanace = custAccBalanace;
    }

    public double getCustContactNumber() {
        return custContactNumber;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public int getOtp() {
        return otp;
    }

}

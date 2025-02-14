package br.com.devsdofuturobr.customer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customers")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id", nullable = false)
    private Integer id;

    @Column(name = "cust_name", nullable = false, length = 100)
    private String name;

    @Column(name = "cust_address", length = 255)
    private String address;

    @Column(name = "cust_city", length = 100)
    private String city;

    @Column(name = "cust_state", length = 50)
    private String state;

    @Column(name = "cust_zip", length = 20)
    private String zip;

    @Column(name = "cust_country", length = 50)
    private String country;

    @Column(name = "cust_contact", length = 100)
    private String contact;

    @Column(name = "cust_email", length = 150)
    private String email;
}



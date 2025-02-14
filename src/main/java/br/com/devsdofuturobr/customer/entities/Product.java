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

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id", nullable = false)
    private Integer id;
    
    @Column(name = "prod_name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "prod_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "prod_desc", columnDefinition = "TEXT")
    private String description;
}
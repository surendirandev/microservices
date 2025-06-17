package com.surendiran.ecom.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "user_table")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private UserRole userRole = UserRole.CUSTOMER;

    /* Cascade Type - ALL -> Address will be removed if User is removed - Cascaded Type*/
    /* orphanRemoval - If the address reference is removed from the User and no other use referenced this address, then its removed*/
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    /* referencedColumnName - id of Address Table*/
    /* name - constraint name*/
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

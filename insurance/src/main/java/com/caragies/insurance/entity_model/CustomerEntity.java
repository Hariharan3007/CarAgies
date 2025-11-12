package com.caragies.insurance.entity_model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customer_id")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;

    private String customer_name;

    private Integer customer_age;

    private String customer_gender;

    private String customer_emailId;

    private String customer_userName;

    private String customer_password;

    private Long customer_mobileNumber;

    private String role="Customer";

    private Boolean isSubscribed;

    @OneToMany(mappedBy = "vehicle_id", cascade = CascadeType.ALL)
    private List<VehicleInfoEntity> vehicle;

    @OneToMany(mappedBy = "request_id")
    private List<CustomerRequest> requestList;

}

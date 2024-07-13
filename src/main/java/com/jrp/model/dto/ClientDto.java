package com.jrp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class ClientDto implements Serializable {

    private Integer idClient;
    private String name;
    private String lastName;
    private String email;
    private Date registerDate;

}

package com.javatechie.multiple.ds.api.model.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ROLE_TB")
public class Role {
    @Id
    private Long id;
    private String name;
}

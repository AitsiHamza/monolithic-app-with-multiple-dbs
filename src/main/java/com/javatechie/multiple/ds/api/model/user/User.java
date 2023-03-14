package com.javatechie.multiple.ds.api.model.user;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER_TB")
public class User {
	@Id
	private Long id;
	private String userName;
	@ElementCollection
	private List<UserRole> userRoles;
}

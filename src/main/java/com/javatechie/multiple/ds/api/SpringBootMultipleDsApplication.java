package com.javatechie.multiple.ds.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import com.javatechie.multiple.ds.api.model.role.Role;
import com.javatechie.multiple.ds.api.model.user.UserRole;
import com.javatechie.multiple.ds.api.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.multiple.ds.api.book.repository.BookRepository;
import com.javatechie.multiple.ds.api.model.book.Book;
import com.javatechie.multiple.ds.api.model.user.User;
import com.javatechie.multiple.ds.api.user.repository.UserRepository;

@SpringBootApplication
@RestController
public class SpringBootMultipleDsApplication {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@PostConstruct
	public void addData2DB() {
		List<Role> roles = roleRepository.saveAll(Stream.of(new Role(990l, "USER_ROLE"), new Role(998l, "ADMIN_ROLE")).collect(Collectors.toList()));
		List<UserRole> userRoles = roles.stream().map(role -> new UserRole(role.getId(),role.getName())).collect(Collectors.toList());

		userRepository.saveAll(
				Stream.of(
						new User(744l, "John",userRoles),
						new User(455l, "Pitter", userRoles.subList(0,1))
						).collect(Collectors.toList()));

		bookRepository.saveAll(
				Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/getRoles")
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDsApplication.class, args);
	}
}

package com.tcessin.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcessin.workshopmongo.domain.User;
import com.tcessin.workshopmongo.dto.UserDTO;
import com.tcessin.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		
		List<UserDTO> list = userService.findAll()
			.stream()
			.map(obj -> new UserDTO(obj))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		
		User user = userService.findById(id);
		
		return ResponseEntity.ok(new UserDTO(user));
	}

}

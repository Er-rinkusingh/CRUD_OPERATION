package In.sp.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import In.sp.main.Dto.UserDto;
import In.sp.main.entity.User;
import In.sp.main.service.Userservice;

@RestController
@RequestMapping("/Api")
public class Usercontroller {
	
	    @Autowired
	    private Userservice userservice;

	    @PostMapping("/save")
	    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
	        UserDto savedUserDto = userservice.saveuser(userDto);
	        return ResponseEntity.ok(savedUserDto);
	    }

	    @GetMapping("/get")
	    public ResponseEntity<List<UserDto>> getAllUsers() {
	        List<UserDto> users = userservice.getAllUsers();
	        return ResponseEntity.ok(users);
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
	        UserDto deletedUser = userservice.deleteUser(id);
	        return ResponseEntity.ok(deletedUser);
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
	        User updatedUser = userservice.updateUser(id, userDto);
	        return ResponseEntity.ok(updatedUser);
	    }
	}



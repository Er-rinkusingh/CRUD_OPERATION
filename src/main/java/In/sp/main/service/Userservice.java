package In.sp.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import In.sp.main.Dto.UserDto;
import In.sp.main.entity.User;
import In.sp.main.repository.Userrepository;

@Service
public class Userservice {
	
	@Autowired
	private Userrepository userrepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto saveuser(UserDto userDto) {
		
//		First convert the Dto to entity using modelmapper
		User user=modelMapper.map(userDto, User.class);
		
		User savedUser = userrepository.save(user);
		
		return modelMapper.map(savedUser, UserDto.class);
		
	}
	
	public List<UserDto> getAllUsers() {
	    //Getting all user from the database..
	    List<User> users = userrepository.findAll();
	    
	    if (users.isEmpty()) {
	        throw new RuntimeException("Users not found.");
	    }
	    
	    return users.stream()
	                .map(user -> modelMapper.map(user, UserDto.class))
	                .collect(Collectors.toList());
	}
	
	public UserDto deleteUser(Long id) {
	   
	    User user = userrepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

	    userrepository.delete(user);
	    
	    return modelMapper.map(user, UserDto.class);
	}

	
	public User updateUser(Long id, UserDto updatedUser) {
        Optional<User> existingUserOptional = userrepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            
            // Update fields
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setCity(updatedUser.getCity());

            // Save updated user
            return userrepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

	
	

}



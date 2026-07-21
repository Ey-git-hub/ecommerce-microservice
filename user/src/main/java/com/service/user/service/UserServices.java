package com.app.ecommerce.service;

import com.app.ecommerce.dto.AddressDTO;
import com.app.ecommerce.dto.UserRequests;
import com.app.ecommerce.dto.UserResponse;
import com.app.ecommerce.model.Address;
import com.app.ecommerce.repository.UserRepository;
import com.app.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    //    private List<User> userList = new ArrayList<>();
//    private Long nextid=1L;
    //adding the user
    public void addUsers(UserRequests userRequests) {
    User user = new User();
    updateUserFromRequest(user,userRequests);
        userRepository.save(user);
    }


    //update the user
    public boolean UpdateUsers(UserRequests updatedUserRequests, Long id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser,updatedUserRequests);
                    userRepository.save(existingUser);
                    return true;

                }).orElse(false);

    }

    //fetching the users
    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }
    private void updateUserFromRequest(User user, UserRequests userRequests) {
   user.setFirstName(userRequests.getFirstName());
   user.setLastName(userRequests.getLastName());
   user.setEmail(userRequests.getEmail());
   user.setPhone(userRequests.getPhone());
   if(userRequests.getAddress() != null){
       Address address=new Address();
       address.setCity(userRequests.getAddress().getCity());
       address.setStreet(userRequests.getAddress().getStreet());
       address.setCountry(userRequests.getAddress().getCountry());
       address.setZipcode(userRequests.getAddress().getZipcode());
       user.setAddress(address);
   }
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response= new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        if(user.getAddress()!=null){
            AddressDTO addressDTO=new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());

        }
     return response;


    }
}
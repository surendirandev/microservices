package com.surendiran.ecom.app.service;

import com.surendiran.ecom.app.dto.AddressDTO;
import com.surendiran.ecom.app.dto.UserRequest;
import com.surendiran.ecom.app.dto.UserResponse;
import com.surendiran.ecom.app.model.Address;
import com.surendiran.ecom.app.model.User;
import com.surendiran.ecom.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private List<User> userList = new ArrayList<>();

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream().map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest) {
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public User updateUser(Long id, UserRequest userRequest) {

        return userRepository.findById(id)
                .map(user -> {
                    updateUserFromRequest(user, userRequest);
                    userRepository.save(user);
                    return user;
                }).orElse(null);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (userRequest.getAddressDTO() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddressDTO().getStreet());
            address.setCity(userRequest.getAddressDTO().getCity());
            address.setState(userRequest.getAddressDTO().getState());
            address.setCountry(userRequest.getAddressDTO().getCountry());
            address.setZipcode(userRequest.getAddressDTO().getZipcode());
            user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setUserRole(user.getUserRole());
        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddressDTO(addressDTO);
        }
        return userResponse;
    }
}

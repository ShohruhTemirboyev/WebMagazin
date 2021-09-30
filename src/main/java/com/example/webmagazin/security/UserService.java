package com.example.webmagazin.security;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.entity.enam.RoleName;
import com.example.webmagazin.payloat.*;
import com.example.webmagazin.repository.RoleRepository;
import com.example.webmagazin.repository.UserRepository;
import com.example.webmagazin.service.AddresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AddresService addresService;


    public ApiJwtRespons saveUser(ReqUser reqUser){

        ApiJwtRespons response=new ApiJwtRespons();
        try {
            if (!userRepository.existsByPhoneNumber(reqUser.getPhoneNumber())){
                User user=new User();
                user.setPhoneNumber(reqUser.getPhoneNumber());
                user.setPassword(passwordEncoder.encode(reqUser.getPassword()));
                user.setFirstName(reqUser.getFirstName());
                user.setLastName(reqUser.getLastName());
                user.setMiddleName(reqUser.getMiddleName());
                user.setEmail(reqUser.getEmail());
                user.setNumber(reqUser.getNumber());
                user.setLocation(reqUser.getLocation());
               user.setAddress((addresService.saveAddres(reqUser.getResAddres())));
                user.setBirthDate(reqUser.getBirthDate());
                user.setRoles((Collections.singletonList(roleRepository.findByRoleName(RoleName.ROLE_USER))));
                userRepository.save(user);
                response.setMessage("User ro'yhatdan o'tkazildi");
                response.setCode(200);
                response.setUserId(user.getId());

        }
            else {
                response.setMessage("Bunday User mavjud");
                response.setCode(202);
            }
        }
        catch (Exception ex){
         response.setCode(500);
         response.setMessage("Userni Saqlashda xatolik!!!");
        }
        return response;
    }
    public ResUser getUser(User user){
        return new ResUser(
                user.getPhoneNumber(),
                user.getPassword(),
                user.getLastName(),
                user.getBirthDate()
        );
    }


    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(number);
    }

    public UserDetails loadUserByUserId(UUID id) {
    return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User id no validate"));
    }


}

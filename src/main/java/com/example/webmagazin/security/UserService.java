package com.example.webmagazin.security;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.entity.enam.RoleName;
import com.example.webmagazin.payloat.ApiJwtRespons;
import com.example.webmagazin.payloat.ReqUser;
import com.example.webmagazin.payloat.ResUser;
import com.example.webmagazin.repository.RoleRepository;
import com.example.webmagazin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public ApiJwtRespons saveUser(ReqUser reqUser){

        ApiJwtRespons response=new ApiJwtRespons();
        try {
            if (!userRepository.existsByPhoneNumber(reqUser.getPhoneNumber())){
                User user=new User();
                user.setPhoneNumber(reqUser.getPhoneNumber());
                user.setPassword(passwordEncoder.encode(reqUser.getPassword()));

                user.setBirthDate(reqUser.getBirthDate());
                user.setRoles((Collections.singletonList(roleRepository.findByRoleName(RoleName.ROLE_USER))));
                userRepository.save(user);
                response.setMessage("User ro'yhatdan o'tkazildi");
                response.setSuccess(true);
                response.setUserId(user.getId());

        }
            else {
                response.setMessage("Bunday User mavjud");
                response.setSuccess(false);
            }
        }
        catch (Exception ex){
         response.setSuccess(false);
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

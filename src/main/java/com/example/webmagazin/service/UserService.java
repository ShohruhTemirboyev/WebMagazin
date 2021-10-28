package com.example.webmagazin.service;

import com.example.webmagazin.entity.Product;
import com.example.webmagazin.entity.User;
import com.example.webmagazin.entity.enam.RoleName;
import com.example.webmagazin.payloat.*;
import com.example.webmagazin.repository.ProductRepository;
import com.example.webmagazin.repository.RoleRepository;
import com.example.webmagazin.repository.UserRepository;
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

    @Autowired
    ProductRepository productRepository;


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
    public ApiResponse editUser(ReqUser reqUser,User user){
        ApiResponse response=new ApiResponse();
        try {
            Optional<User>userOptional=Optional.ofNullable(userRepository.findByPhoneNumber(reqUser.getPhoneNumber()));
            if (!userOptional.isPresent()){
             user.setId(user.getId());
             user.setPhoneNumber(reqUser.getPhoneNumber());
             userRepository.save(user);
             response.setMessage("Success");
             response.setCode(200);
            }
            else {
                response.setCode(202);
                response.setMessage("Bunday raqamli user mavjud");
            }

        }
        catch (Exception ex){
          response.setMessage("Error");
          response.setCode(500);
        }
        return response;
    }
    public ApiResponse addLikedProduct(User user,UUID productId){
        ApiResponse response=new ApiResponse();
        try {
          Optional<Product> optionalProduct=productRepository.findById(productId);
          if (optionalProduct.isPresent()){
              long count = user.getLikedProduct().stream().filter(person -> person.getDescription().equals(optionalProduct.get().getDescription())).count();
            if (count==0){
               user.getLikedProduct().add(optionalProduct.get());
                if(optionalProduct.get().getInterests()!=null){
                    optionalProduct.get().setInterests(optionalProduct.get().getInterests()+1);
                }else{
                    optionalProduct.get().setInterests(1L);
                }
               productRepository.save(optionalProduct.get());
               userRepository.save(user);
               response.setCode(200);
               response.setMessage("Success");

            }
            else {
                response.setMessage("Bunday product sizda mavjud");
                response.setCode(208);
            }
          }
          else {
              response.setMessage("Bunday Id lik product topilmadi!!!");
              response.setCode(207);
          }

        }
        catch (Exception exception){
            response.setMessage("Error");
            response.setCode(500);
        }
        return response;
    }
    public ApiResponse remoweLikedProduct(User user, UUID productId){
        ApiResponse response=new ApiResponse();
        try{
            Optional<Product> optional = productRepository.findById(productId);
            if(optional.isPresent()) {
                user.getLikedProduct().removeIf(book1 -> book1.getDescription().equals(optional.get().getDescription()));
                userRepository.save(user);
                response.setCode(200);
                response.setMessage("success");
            }else{
                response.setCode(207);
                response.setMessage("buynaqa idlik mahsulot mavjud emas !");
            }
        }catch(Exception e){
            response.setCode(500);
            response.setMessage("Error");
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

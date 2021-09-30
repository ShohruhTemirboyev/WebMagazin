package com.example.webmagazin.service;

import com.example.webmagazin.entity.Comentarya;
import com.example.webmagazin.entity.Product;
import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqComentarya;
import com.example.webmagazin.repository.ComentaryaRepository;
import com.example.webmagazin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ComentaryaService {

    @Autowired
    ComentaryaRepository comentaryaRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse addComentarya(User user, UUID productId,String text){
        ApiResponse response=new ApiResponse();
        try {
            Comentarya comentarya=new Comentarya();
            comentarya.setText(text);
            comentarya.setProductId(productId);
            comentarya.setUser(user);
            comentaryaRepository.save(comentarya);
            response.setCode(200);
            response.setMessage("Saqlandi");

        }
        catch (Exception exception){
            response.setMessage("Saqlashda hatolik");
            response.setCode(500);
        }
        return response;
    }
}

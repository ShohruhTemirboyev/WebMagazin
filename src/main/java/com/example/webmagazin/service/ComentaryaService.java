package com.example.webmagazin.service;

import com.example.webmagazin.entity.Comentarya;
import com.example.webmagazin.entity.Product;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqComentarya;
import com.example.webmagazin.repository.ComentaryaRepository;
import com.example.webmagazin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComentaryaService {

    @Autowired
    ComentaryaRepository comentaryaRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse addComentarya(ReqComentarya reqComentarya){
        ApiResponse response=new ApiResponse();
        try {
            Comentarya comentarya=new Comentarya();
            comentarya.setPersonId(reqComentarya.getPersonId());
            comentarya.setText(reqComentarya.getText());
            comentarya.setProductId(reqComentarya.getProductId());
            comentaryaRepository.save(comentarya);
            Optional<Product> product=productRepository.findById(reqComentarya.getProductId());
            if (product.isPresent()){
            }



        }
        catch (Exception exception){
            response.setMessage("Saqlashda hatolik");
            response.setSuccess(false);
        }
        return response;
    }
}

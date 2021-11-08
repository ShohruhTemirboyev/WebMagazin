package com.example.webmagazin.service;

import com.example.webmagazin.entity.Comentarya;
import com.example.webmagazin.entity.Product;
import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ApiResponseModel;
import com.example.webmagazin.payloat.ReqComentarya;
import com.example.webmagazin.payloat.ResComent;
import com.example.webmagazin.repository.ComentaryaRepository;
import com.example.webmagazin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ComentaryaService {

    @Autowired
    ComentaryaRepository comentaryaRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse addComentarya(User user, UUID productId,String text){
        ApiResponse response=new ApiResponse();
        try {Optional<Product> optionalProduct=productRepository.findById(productId);
            if (optionalProduct.isPresent()){
            Comentarya comentarya=new Comentarya();
            comentarya.setText(text);
            comentarya.setProductId(productId);
            comentarya.setUser(user);
            comentaryaRepository.save(comentarya);
            response.setCode(200);
            response.setMessage("Saqlandi");

          }
            else {
                response.setCode(207);
                response.setMessage("Bunday Id lik product topilmadi");
            }
        }
        catch (Exception exception){
            response.setMessage("Saqlashda hatolik");
            response.setCode(500);
        }
        return response;
    }

    public ApiResponse deleteComent(Long productId){
        ApiResponse response=new ApiResponse();
        try {
            Optional<Comentarya> optionalComentarya=comentaryaRepository.findById(productId);
            if (optionalComentarya.isPresent()){
                comentaryaRepository.delete(optionalComentarya.get());
                response.setMessage("Success");
                response.setCode(200);

            }
            else {
                response.setCode(207);
                response.setMessage("Bunday Id lik Comentarya topilmadi");
            }

        }
        catch (Exception ex){
            response.setMessage("Error");
            response.setCode(500);
        }
        return response;
    }

    public ApiResponseModel getComent(Long comentId){
        ApiResponseModel responseModel=new ApiResponseModel();
        try {
            Optional<Comentarya> comentaryaOptional=comentaryaRepository.findById(comentId);
            if (comentaryaOptional.isPresent()){
                ResComent resComent=new ResComent();
                responseModel.setCode(200);
                responseModel.setMessage("Success");
                resComent.setName(comentaryaOptional.get().getUser().getFirstName()+" "+comentaryaOptional.get().getUser().getLastName());
                resComent.setText(comentaryaOptional.get().getText());
                resComent.setProductId(comentaryaOptional.get().getProductId());
                resComent.setId(comentId);
                resComent.setDate(comentaryaOptional.get().getCreatedAt());
                responseModel.setObject(resComent);
            }
            else {
                responseModel.setMessage("Bunday Coment topilmadi");
                responseModel.setCode(207);
            }

        }
        catch (Exception exception){
            responseModel.setCode(500);
            responseModel.setMessage("Error");
        }
        return responseModel;
    }
    public ApiResponseModel getAllProductComent(UUID productId){
        ApiResponseModel responseModel=new ApiResponseModel();
        try {
             Optional<Product> optionalProduct=productRepository.findById(productId);
             if (optionalProduct.isPresent()){
                List<ResComent> resComents=comentaryaRepository.findAllByProductId(productId).stream().map(comentarya -> getComentList(comentarya)).collect(Collectors.toList());
                 responseModel.setCode(200);
                 responseModel.setMessage("Success");
                responseModel.setObject(resComents);
             }


             else {
                 responseModel.setMessage("Bunday mahsulot topilmadi");
                 responseModel.setCode(207);
             }
        }
        catch (Exception e){
            responseModel.setCode(500);
            responseModel.setMessage("Error");
        }
        return responseModel;
    }
    public ResComent getComentList(Comentarya comentarya){

      ResComent resComent=new ResComent();
      resComent.setText(comentarya.getText());
      resComent.setId(comentarya.getId());
      resComent.setProductId(comentarya.getProductId());
      resComent.setName(comentarya.getUser().getFirstName()+" "+comentarya.getUser().getLastName());
      resComent.setDate(comentarya.getCreatedAt());
        return resComent;
    }
}

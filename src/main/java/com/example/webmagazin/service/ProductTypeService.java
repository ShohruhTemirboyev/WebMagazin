package com.example.webmagazin.service;

import com.example.webmagazin.entity.ProductType;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqProductType;
import com.example.webmagazin.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    public ApiResponse saveType(ReqProductType reqProductType){

        ApiResponse response=new ApiResponse();

        try {
        if (!productTypeRepository.existsByName(reqProductType.getName())){
            ProductType productType=new ProductType();
            productType.setName(reqProductType.getName());
            productTypeRepository.save(productType);
            response.setMessage("Malumot saqlandi");
            response.setCode(200);
           }
        else {
            response.setCode(202);
            response.setMessage("Bunday malumot mavjud");
        }
        }
        catch (Exception exception){
            response.setCode(500);
            response.setMessage("Saqlashda hatolik");
        }
        return  response;
    }

    public ApiResponse delete(Integer productTypeId){
        ApiResponse response=new ApiResponse();
        try {
            Optional<ProductType> productTypeOptional=productTypeRepository.findById(productTypeId);
            if (productTypeOptional.isPresent()){
                productTypeRepository.deleteById(productTypeId);
                response.setCode(200);
                response.setMessage("Success");
            }
            else {
                response.setMessage("Bunday Idlik type topilmadi");
                response.setCode(207);
            }

        }
        catch (Exception exception){
            response.setCode(500);
            response.setMessage("Error");
        }
        return response;

    }

}

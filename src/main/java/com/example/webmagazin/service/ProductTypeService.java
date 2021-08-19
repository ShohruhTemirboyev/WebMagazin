package com.example.webmagazin.service;

import com.example.webmagazin.entity.ProductType;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqProductType;
import com.example.webmagazin.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            response.setSuccess(true);
           }
        else {
            response.setSuccess(false);
            response.setMessage("Bunday malumot mavjud");
        }
        }
        catch (Exception exception){
            response.setSuccess(false);
            response.setMessage("Saqlashda hatolik");
        }
        return  response;
    }

}

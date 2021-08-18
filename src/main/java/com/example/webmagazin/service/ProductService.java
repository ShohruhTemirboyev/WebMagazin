package com.example.webmagazin.service;

import com.example.webmagazin.entity.Product;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqProduct;
import com.example.webmagazin.repository.AttachmentRepository;
import com.example.webmagazin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    ProductRepository productRepository;
    public ApiResponse saveProduct(ReqProduct reqProduct){
        ApiResponse response=new ApiResponse();
        try {
            Product product=new Product();
            product.setDescription(reqProduct.getDescription());
            product.setPrice(reqProduct.getPrice());
            product.setProductType(reqProduct.getProductType());
            product.setProductTypeName(reqProduct.getProductTypeName());
            product.setAttachments(attachmentRepository.findAllById(reqProduct.getAttachmentId()));
            product.setCount(reqProduct.getCount());
            product.setBestseller(reqProduct.getBestseller());
            product.setSuperPrice(reqProduct.getSuperPrice());
            product.setNewProduct(reqProduct.getNewProduct());
            product.setVendor(reqProduct.getVendor());
            productRepository.save(product);
            response.setMessage("Maxsulot bazaga saqlandi");
            response.setSuccess(true);

        }
        catch (Exception ex){
            response.setSuccess(false);
            response.setMessage("Product saqlashda xatolik");
        }
        return response;
    }

    public ApiResponse deleteProduct(UUID id){
        ApiResponse apiResponse=new ApiResponse();
        try {
            if (productRepository.existsById(id)){
                productRepository.deleteById(id);
                apiResponse.setSuccess(true);
                apiResponse.setMessage("Maxsulot o'chirildi");
            }
            else {
                apiResponse.setMessage("Bunday Idlik maxsulot topilmadi");
                apiResponse.setSuccess(false);
            }

        }
        catch (Exception ex){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Maxsulot o'chirishda xatolik");
        }
        return apiResponse;
    }

}
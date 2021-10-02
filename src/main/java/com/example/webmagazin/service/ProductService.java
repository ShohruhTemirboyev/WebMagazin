package com.example.webmagazin.service;

import com.example.webmagazin.entity.Product;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqProduct;
import com.example.webmagazin.repository.AttachmentRepository;
import com.example.webmagazin.repository.ProductRepository;
import com.example.webmagazin.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
            product.setProductType(reqProduct.getProductType());
            product.setProductTypeName(reqProduct.getProductTypeName());
            product.setAttachments(attachmentRepository.findAllById(reqProduct.getAttachmentId()));
            product.setCount(reqProduct.getCount());
           product.setNewPrice(reqProduct.getNewPrice());
           product.setOldPrice(reqProduct.getOldPrice());
            product.setVendor(reqProduct.getVendor());
            productRepository.save(product);
            response.setMessage("Maxsulot bazaga saqlandi");
            response.setCode(200);

        }
        catch (Exception ex){
            response.setCode(500);
            response.setMessage("Product saqlashda xatolik");
        }
        return response;
    }

    public ApiResponse deleteProduct(UUID id){
        ApiResponse apiResponse=new ApiResponse();
        try {
            if (productRepository.existsById(id)){
                productRepository.deleteById(id);
                apiResponse.setCode(200);
                apiResponse.setMessage("Maxsulot o'chirildi");
            }
            else {
                apiResponse.setMessage("Bunday Idlik maxsulot topilmadi");
                apiResponse.setCode(207);
            }

        }
        catch (Exception ex){
            apiResponse.setCode(500);
            apiResponse.setMessage("Maxsulot o'chirishda xatolik");
        }
        return apiResponse;
    }
    public List<Product> getListProductType(Long productType,int page,int size){
        Page<Product> products=productRepository.findAllByProductType(productType, CommonUtils.getPageable(page,size));
         return products.getContent();
    }

}

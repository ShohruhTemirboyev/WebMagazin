package com.example.webmagazin.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CommonUtils {
    public static void validatePageAndSize(int page,int size){
        if (page<0 || size>AppConstants.MAX_PAGE_SIZE){
            throw new RuntimeException("Page manfiy va size"+AppConstants.MAX_PAGE_SIZE+"bo'lishi mumkin emas");
        }
    }


    public static Pageable getPageable(int page,int size){
        Pageable pageable= PageRequest.of(page, size, Sort.Direction.DESC,"createdAt");
        validatePageAndSize(page,size);
        return pageable;
    }
}

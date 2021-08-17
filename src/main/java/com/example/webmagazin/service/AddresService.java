package com.example.webmagazin.service;

import com.example.webmagazin.entity.Address;
import com.example.webmagazin.payloat.ResAddres;
import com.example.webmagazin.repository.AddresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddresService {
    @Autowired
    AddresRepository addresRepository;
    public Address saveAddres(ResAddres resAddres)
    {
        Address address1=new Address();
        address1.setCountry(resAddres.getCountry());
        address1.setRegion(resAddres.getRegion());
        address1.setDistrict(resAddres.getDistrict());
        addresRepository.save(address1);
        return address1;


    }


}

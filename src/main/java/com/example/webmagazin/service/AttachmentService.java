package com.example.webmagazin.service;

import com.example.webmagazin.entity.Attachment;
import com.example.webmagazin.entity.AttachmentContent;
import com.example.webmagazin.payloat.ApiResponseModel;
import com.example.webmagazin.payloat.ResUploadFile;
import com.example.webmagazin.repository.AttachmentContentRepository;
import com.example.webmagazin.repository.AttachmentRepository;
import com.example.webmagazin.repository.AttachmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentTypeRepository attachmentTypeRepository;



    public ApiResponseModel saveFile(MultipartHttpServletRequest request) throws IOException {
        List<ResUploadFile> resUploadFiles=new ArrayList<>();
        Iterator<String> itr=request.getFileNames();
        MultipartFile file;
        while (itr.hasNext()){
            file=request.getFile(itr.next());


            Attachment attachment=attachmentRepository.save(new Attachment(file.getOriginalFilename(),file.getContentType(), file.getSize()));

            attachmentContentRepository.save(new AttachmentContent(file.getBytes(),attachment));

            resUploadFiles.add(new ResUploadFile(attachment.getId(),
                    attachment.getName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/").path(attachment.getId().toString()).toUriString(),
                    attachment.getContentType(),
                    attachment.getSize()));
        }
        return new ApiResponseModel("Saqlandi",true,resUploadFiles);
    }
    public HttpEntity<?> getAttachmentContent(UUID attachmentId){
        Attachment attachment=attachmentRepository.findById(attachmentId).orElseThrow(() -> new ResourceNotFoundException("getAttachment"));
        AttachmentContent attachmentContent=attachmentContentRepository.findByAttachment(attachment).orElseThrow(() -> new ResourceNotFoundException("Attachment content"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+attachment.getName()+"\"")
                .body(attachmentContent.getBytes());
    }
}

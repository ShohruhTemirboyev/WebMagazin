package com.example.webmagazin.repository;

import com.example.webmagazin.entity.AttachmentType;
import com.example.webmagazin.entity.enam.AttachmentTypeEnam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentTypeRepository extends JpaRepository<AttachmentType, UUID> {
AttachmentType findByAttachmentTypeEnam(AttachmentTypeEnam type);
}

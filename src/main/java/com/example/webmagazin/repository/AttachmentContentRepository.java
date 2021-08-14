package com.example.webmagazin.repository;

import com.example.webmagazin.entity.Attachment;
import com.example.webmagazin.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    Optional<AttachmentContent> findByAttachment(Attachment attachment);
}

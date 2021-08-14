package com.example.webmagazin.payloat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResUploadFile {
private UUID fileId;
private String  fileName;
private String fileDownloadUri;
private String fileType;
private Long size;
}

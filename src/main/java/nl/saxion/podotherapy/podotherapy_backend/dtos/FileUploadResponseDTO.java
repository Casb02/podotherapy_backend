package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class FileUploadResponseDTO {
    private String message;
    private String fileName;
    private Date uploadDate;
}

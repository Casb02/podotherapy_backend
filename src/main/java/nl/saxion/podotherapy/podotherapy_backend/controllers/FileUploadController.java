package nl.saxion.podotherapy.podotherapy_backend.controllers;

import nl.saxion.podotherapy.podotherapy_backend.dtos.FileUploadResponseDTO;
import nl.saxion.podotherapy.podotherapy_backend.entities.Gamefile;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.services.PersonService;
import nl.saxion.podotherapy.podotherapy_backend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/gamesave")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private PersonService personService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDTO> upload(@RequestParam("file")MultipartFile file, Authentication authentication) {
        //get person from security context
        Person person = personService.getPersonFromAuthentication(authentication);

        Gamefile storedFile = storageService.storeFile(file, person);

        FileUploadResponseDTO response = new FileUploadResponseDTO("Upload success",storedFile.getFileName(), storedFile.getDate());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restore")
    public File restore(Authentication authentication) {
        //get person from security context
        Person person = personService.getPersonFromAuthentication(authentication);

        return storageService.getLatestGamesave(person);
    }
}

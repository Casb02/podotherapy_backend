package nl.saxion.podotherapy.podotherapy_backend.services;

import nl.saxion.podotherapy.podotherapy_backend.entities.Gamefile;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.repositories.GamefileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class StorageService {

    @Value("${storage.location}")
    private String storageLocation;

    @Autowired
    private GamefileRepository gamefileRepository;

    public Gamefile storeFile(MultipartFile file, Person person) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = "gamefile_" + timeStamp +  "_" + person.getId() + "." + extension;

        String personalStorageLocation = storageLocation + "/" + person.getUuid();

        File directory = new File(personalStorageLocation);
        if (! directory.exists()){
            directory.mkdir();
        }

        try {
            Path targetLocation = Paths.get(personalStorageLocation).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Gamefile gamefile = new Gamefile(targetLocation.toString(), person);
            return gamefileRepository.save(gamefile);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + fileName, e);

        }
    }

    /**
     * Loads the data from a file.
     *
     * @return the loaded file
     */
    public File load() {

        return null;
    }

}

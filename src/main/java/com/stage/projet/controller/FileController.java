package com.stage.projet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping(value="/file",headers="Content-Type= multipart/form-data")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class FileController {



    //definir l'emplacement pour stocker des fichiers
    public static final String DIRECTORY = System.getProperty("user.home") + "/Desktop/stage/logiciel/fichier";
    Path cheminVersUploads= Paths.get("C:\\Users\\Fiacre\\Desktop\\stage\\logiciel\\fichier");


    //definir une methode pour uploader
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("file") MultipartFile file) throws IOException {
        List<String> filenames = new ArrayList<>();
        log.info(String.valueOf(file));

            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            //Si le dossier uploads n'existe pas, je le créée
            if(!Files.exists(cheminVersUploads)){
                try {
                    Files.createDirectories(cheminVersUploads);
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }

            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        return ResponseEntity.ok().body(filenames);
    }




    //definir une methode pour telecharger des fichiers
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> dowloadFichiers(@PathVariable("filename") String filename) throws IOException {
        log.info(filename);
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " pas trouvé sur le serveur");
        }
        MultipartFile file;
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("File-name",filename);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,Files.probeContentType(filePath));
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION,"inline;File-Name=" +  resource.getFilename());
        return ResponseEntity.ok().headers(httpHeaders).body(resource);
    }
}

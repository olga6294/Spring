package com.example.demo.file;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.example.demo.dropbox.DropboxConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class FileController {

    private DropboxConfiguration dropboxConfiguration;

    public void uploadFile(String filename, String directory){
        try (InputStream in = new FileInputStream(directory+"/"+filename)) {

            FileMetadata metadata = dropboxConfiguration.dropboxClient().files().uploadBuilder("/"+filename)
                    .uploadAndFinish(in);

            System.out.println(metadata.toStringMultiline());
        } catch (IOException | DbxException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
        }
    }

}
package com.example.demo.file;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.example.demo.dropboxdirectory.DropboxConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FileController {

    @Autowired
    private DropboxConfiguration dropboxConfiguration;

    public void uploadFile(String filename, String directory){
        try (InputStream in = new FileInputStream(directory+"/"+filename)) {

            FileMetadata metadata = dropboxConfiguration.dropboxClient().files().uploadBuilder("/"+filename)
                    .uploadAndFinish(in);

            System.out.println(metadata.toStringMultiline());
        } catch (UploadErrorException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
        } catch (DbxException ex) {
            System.err.println("DBXError uploading to Dropbox: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
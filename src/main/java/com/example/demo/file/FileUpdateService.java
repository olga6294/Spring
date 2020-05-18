package com.example.demo.file;

import com.example.demo.directory.DirectoryContentProvider;
import com.example.demo.dropbox.DropboxDirectoryContentProvider;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUpdateService {

    private Environment environment;
    private FileController fileController;
    private DirectoryContentProvider directoryContentProvider;
    private DropboxDirectoryContentProvider dropboxDirectoryContentProvider;
    private String directory;

    public FileUpdateService(
            Environment environment,
            FileController fileController,
            DirectoryContentProvider directoryContentProvider,
            DropboxDirectoryContentProvider dropboxDirectoryContentProvider
        )
    {
        this.environment = environment;
        this.fileController = fileController;
        this.directoryContentProvider = directoryContentProvider;
        this.dropboxDirectoryContentProvider = dropboxDirectoryContentProvider;
        this.directory = environment.getProperty("listening.directory");
    }

    public void updateFiles(){
        List<String> localDirectoryContent = directoryContentProvider.provideDirectoryContent(directory);
        List<String> dropboxDirectoryContent = dropboxDirectoryContentProvider.getDropboxContent();
        if(localDirectoryContent.remove(dropboxDirectoryContent)){
            localDirectoryContent.forEach(file  ->  fileController.uploadFile(file, directory));
        }
    }
}
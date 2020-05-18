package com.example.demo.file;

import com.example.demo.config.Keys;
import com.example.demo.directory.DirectoryContentProvider;
import com.example.demo.dropbox.DropboxDirectoryContentProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUpdateService {

    private Keys keys;
    private FileController fileController;
    private DirectoryContentProvider directoryContentProvider;
    private DropboxDirectoryContentProvider dropboxDirectoryContentProvider;
    private String directory;

    public FileUpdateService(
            Keys keys,
            FileController fileController,
            DirectoryContentProvider directoryContentProvider,
            DropboxDirectoryContentProvider dropboxDirectoryContentProvider
        )
    {
        this.keys = keys;
        this.fileController = fileController;
        this.directoryContentProvider = directoryContentProvider;
        this.dropboxDirectoryContentProvider = dropboxDirectoryContentProvider;
        this.directory = keys.getListeningDirectory();
    }

    public void updateFiles(){
        List<String> localDirectoryContent = directoryContentProvider.provideDirectoryContent(directory);
        List<String> dropboxDirectoryContent = dropboxDirectoryContentProvider.getDropboxContent();
        if(localDirectoryContent.removeAll(dropboxDirectoryContent)){
            localDirectoryContent.forEach(file  ->  fileController.uploadFile(file, directory));
        }
    }
}
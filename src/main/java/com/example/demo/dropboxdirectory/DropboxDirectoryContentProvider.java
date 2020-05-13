package com.example.demo.dropboxdirectory;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DropboxDirectoryContentProvider {

    @Autowired
    private DropboxConfiguration dropboxConfiguration;

    public List<String> getDropboxContent(){
        try {
            ListFolderBuilder listFolderBuilder = dropboxConfiguration.dropboxClient().files().listFolderBuilder("");
            return listFolderBuilder.start().getEntries().stream().map(element -> element.getName()).collect(Collectors.toList());
        }catch(DbxException dbxException){
            System.out.println(dbxException.getMessage());
        }
        return null;
    }
}

package com.example.demo.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DropboxDirectoryContentProvider {

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

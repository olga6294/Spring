package com.example.demo.directory;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DirectoryContentProvider {

    public List<String> provideDirectoryContent(String directory){
        try(Stream<Path> pathWalker = Files.walk(Paths.get(directory))){
            return pathWalker.map(Path::toFile).map(file -> file.getName()).skip(1).collect(Collectors.toList());
        }catch(IOException ioException){
            ioException.printStackTrace();
            throw new RuntimeException();
        }
    }


}

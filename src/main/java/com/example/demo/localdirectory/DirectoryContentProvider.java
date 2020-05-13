package com.example.demo.localdirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryContentProvider {

    public static List<String> provideDirectoryContent(String directory){
        try(Stream<Path> pathWalker = Files.walk(Paths.get(directory))){
            return pathWalker.map(Path::toFile).map(file -> file.getName()).collect(Collectors.toList());
        }catch(IOException ioException){
            ioException.printStackTrace();
            throw new RuntimeException();
        }
    }


}

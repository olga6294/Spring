package com.example.demo;

import com.example.demo.directory.DirectoryListener;
import com.example.demo.file.FileUpdateService;
import com.example.demo.metadata.MetaDataProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class);

        MetaDataProvider metaDataProvider = applicationContext.getBean(MetaDataProvider.class);
        Thread thread = new Thread(metaDataProvider);
        thread.start();

        FileUpdateService fileUpdateService = applicationContext.getBean(FileUpdateService.class);
        fileUpdateService.updateFiles();

        DirectoryListener directoryListener = applicationContext.getBean(DirectoryListener.class);
        directoryListener.listen();

    }

}

package com.example.demo.directory;

import com.example.demo.config.Keys;
import com.example.demo.file.FileController;
import com.example.demo.metadata.MetaDataCollector;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

@Service
public class DirectoryListener {

    private Environment environment;
    private FileController fileController;
    private MetaDataCollector metaDataCollector;
    private String directory;

    public DirectoryListener(
            Environment environment,
            FileController fileController,
            MetaDataCollector metaDataCollector
            )
    {
        this.environment = environment;
        this.fileController = fileController;
        this.metaDataCollector = metaDataCollector;
        this.directory = environment.getProperty(Keys.DIRECTORY);
    }

    public void listen() {

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directoryPath = Paths.get(directory);

            directoryPath.register(watchService, ENTRY_CREATE);

            WatchKey watchKey = watchService.take();

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
                    executorService.execute(() -> takeActionOnEvent(watchEvent));
                }
            }

        } catch (InterruptedException | IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void takeActionOnEvent(WatchEvent<?> event) {
        WatchEvent.Kind<?> kind = event.kind();

        if (kind.equals(ENTRY_CREATE)) {
            fileController.uploadFile(event.context().toString(), directory);
            System.out.println("created " + event.context());

            metaDataCollector.incrementFilesSent();
        }
    }
}

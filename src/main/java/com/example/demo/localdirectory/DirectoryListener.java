package com.example.demo.localdirectory;

import com.example.demo.file.FileController;
import com.example.demo.metadata.MetaDataCollector;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

@AllArgsConstructor
@Service
public class DirectoryListener {

    @Autowired
    private FileController fileController;
    @Autowired
    private Environment environment;

    public void listen() {
        System.out.println("TADAA!!");
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directoryPath = Paths.get(environment.getProperty("directory"));

            directoryPath.register(watchService, ENTRY_CREATE);

            WatchKey watchKey = watchService.take();

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
                    executorService.execute(() -> takeActionOnEvent(watchEvent));
                }
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void takeActionOnEvent(WatchEvent<?> event) {
        WatchEvent.Kind<?> kind = event.kind();

        if (kind.equals(ENTRY_CREATE)) {
            fileController.uploadFile(event.context().toString(), environment.getProperty("directory"));
            System.out.println("created " + event.context());

            MetaDataCollector.getInstance().incrementFilesSent();
        }
    }
}

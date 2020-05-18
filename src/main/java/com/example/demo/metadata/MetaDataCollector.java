package com.example.demo.metadata;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MetaDataCollector {

    private AtomicInteger filesSent = new AtomicInteger(0);

    public void incrementFilesSent() {
        filesSent.addAndGet(1);
    }

    public int getFilesSent() {
        return filesSent.get();
    }
}
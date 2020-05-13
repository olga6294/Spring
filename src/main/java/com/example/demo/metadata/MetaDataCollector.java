package com.example.demo.metadata;

import java.util.concurrent.atomic.AtomicInteger;

public class MetaDataCollector {

    private static MetaDataCollector metaDataCollector;

    private AtomicInteger filesSent = new AtomicInteger(0);

    public static synchronized MetaDataCollector getInstance(){
        if(metaDataCollector == null)
            metaDataCollector = new MetaDataCollector();
        return metaDataCollector;
    }

    public void incrementFilesSent() {
        filesSent.addAndGet(1);
    }

    public int getFilesSent() {
        return filesSent.get();
    }
}
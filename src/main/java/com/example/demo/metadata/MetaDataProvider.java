package com.example.demo.metadata;

import org.springframework.stereotype.Service;

@Service
public class MetaDataProvider implements Runnable{

    private MetaDataCollector metaDataCollector;

    public MetaDataProvider(MetaDataCollector metaDataCollector){
        this.metaDataCollector = metaDataCollector;
    }

    @Override
    public void run() {
        while(true){
            try{
                System.out.println("Files sent: "+metaDataCollector.getFilesSent());
                Thread.sleep(10000);
            }catch (InterruptedException interruptedException){
                System.out.println(interruptedException.getMessage());
                throw new RuntimeException();
            }
        }
    }
}
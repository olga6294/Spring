package com.example.demo.metadata;

public class MetaDataProvider implements Runnable{

    @Override
    public void run() {
        while(true){
            try{
                System.out.println("Files sent: "+MetaDataCollector.getInstance().getFilesSent());
                Thread.sleep(10000);
            }catch (InterruptedException interruptedException){
                System.out.println(interruptedException.getMessage());
                throw new RuntimeException();
            }
        }
    }
}
package com.fruitfactory.infrastructure;

import com.fruitfactory.interfaces.IOFDataRepositoryPipe;
import com.fruitfactory.models.OFItemsContainer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Yariki on 1/29/2017.
 */
public class OFDateRepositoryPipe implements IOFDataRepositoryPipe {

    private LinkedBlockingQueue<OFItemsContainer> dataContainer;
    private final Object syncObject = new Object();
    private volatile boolean available = false;

    public OFDateRepositoryPipe() {
        dataContainer = new LinkedBlockingQueue<>();
    }

    @Override
    public synchronized void pushData(OFItemsContainer container)  {
        try{
            dataContainer.put(container);
            available = true;
            syncObject.notifyAll();
        }catch(InterruptedException ie){

        }catch (Exception ex){

        }
    }

    @Override
    public synchronized void stopNotify() {
        syncObject.notifyAll();
    }

    @Override
    public synchronized OFItemsContainer popData() {
        if(!available){
            try {
                syncObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        OFItemsContainer container = dataContainer.poll();
        available = !dataContainer.isEmpty();
        return container;
    }
}

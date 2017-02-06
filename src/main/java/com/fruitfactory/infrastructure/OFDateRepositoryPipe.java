package com.fruitfactory.infrastructure;

import com.fruitfactory.interfaces.IOFDataRepositoryPipe;
import com.fruitfactory.models.OFItemsContainer;
import org.apache.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Yariki on 1/29/2017.
 */
public class OFDateRepositoryPipe implements IOFDataRepositoryPipe {

    private LinkedBlockingQueue<OFItemsContainer> dataContainer;
    private final Object syncObject = new Object();
    private volatile boolean available = false;

    private final Logger logger = Logger.getLogger(OFDateRepositoryPipe.class);


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
            logger.error(ie.getMessage());
        }catch (Exception ex){
            logger.error(ex.getMessage());
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
                logger.error(e.getMessage());
            }
        }
        OFItemsContainer container = dataContainer.poll();
        available = !dataContainer.isEmpty();
        return container;
    }
}

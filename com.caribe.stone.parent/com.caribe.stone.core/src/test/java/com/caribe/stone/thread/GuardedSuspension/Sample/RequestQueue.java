package com.caribe.stone.thread.GuardedSuspension.Sample;

import java.util.LinkedList;

public class RequestQueue {
    private final LinkedList queue = new LinkedList();
    public synchronized Request getRequest() {
        while (queue.size() <= 0) {
            try {                                   
                wait(1000);
            } catch (InterruptedException e) {      
            	e.printStackTrace();
            }                                       
        }                                           
        Request removeFirst = (Request)queue.removeFirst();
		return removeFirst;
    }
    public synchronized void putRequest(Request request) {
        queue.addLast(request);
        notifyAll();
    }
}

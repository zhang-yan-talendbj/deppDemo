package com.caribe.stone.thread.SingleThreadedExecution.Q5;

public class SecurityGate {
    private int counter;
    public void enter() {
        counter++;
    }
    public void exit() {
        counter--;
    }
    public int getCounter() {
        return counter;
    }
}

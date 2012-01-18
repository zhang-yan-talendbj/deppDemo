package com.caribe.stone.thread.balk.Init1;

public class Something {
    private boolean initialized = false;
    public synchronized void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }
    private void doInit() {
        // ʵ�ʵĴ�ʼ�������@
    }
}

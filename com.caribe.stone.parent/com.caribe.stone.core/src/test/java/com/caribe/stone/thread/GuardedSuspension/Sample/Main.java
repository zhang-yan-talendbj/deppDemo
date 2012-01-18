package com.caribe.stone.thread.GuardedSuspension.Sample;

public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
        new ServerThread(requestQueue, "b2", 6535897L).start();
        new ServerThread(requestQueue, "b3", 6535897L).start();
        new ServerThread(requestQueue, "b4", 6535897L).start();
        new ServerThread(requestQueue, "b5", 6535897L).start();
    }
}

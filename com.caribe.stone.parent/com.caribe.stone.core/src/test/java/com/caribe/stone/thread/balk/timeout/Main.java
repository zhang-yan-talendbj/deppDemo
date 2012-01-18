package com.caribe.stone.thread.balk.timeout;

public class Main {
    public static void main(String[] args) {
        Host host = new Host(3000);
        try {
            System.out.println("execute BEGIN");
            host.execute();
        } catch (TimeoutExeception e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

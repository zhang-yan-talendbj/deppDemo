package com.caribe.stone.j2se.thread.balk.timeout;

public class Host {
    private final long timeout; // timeoutֵ��
    private boolean ready = false; //������ִ�еĻ�Ϊtrue

    public Host(long timeout) {
        this.timeout = timeout;
    }

    // ���״̬
    public synchronized void setExecutable(boolean on) {
        ready = on;
        notifyAll();
    }                                                           

    // ����״̬��ִ��
    public synchronized void execute() throws InterruptedException, TimeoutExeception {
        long start = System.currentTimeMillis(); //��ʼʱ��
        while (!ready) {
            long now = System.currentTimeMillis(); //����ʱ��
            long rest = timeout - (now - start); //���ڵȴ��ʱ��
            if (rest <= 0) {
                throw new TimeoutExeception("now - start = " + (now - start) + ", timeout = " + timeout);
            }
            wait(rest);
        }
        doExecute();
    }

    // ʵ�ʵĴ��?���@
    private void doExecute() {
        System.out.println(Thread.currentThread().getName() + " calls doExecute");
    }
}

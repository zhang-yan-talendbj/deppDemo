package com.caribe.stone.thread.GuardedSuspension.Q5;

public class TalkThread extends Thread {
    private RequestQueue input;
    private RequestQueue output;
    public TalkThread(RequestQueue input, RequestQueue output, String name) {
        super(name);
        this.input = input;
        this.output = output;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":BEGIN");
        for (int i = 0; i < 20; i++) {
            // ��ȡ�öԷ�������
            Request request1 = input.getRequest();
            System.out.println(Thread.currentThread().getName() + " gets  " + request1);

            // �[�W�@�Ӽ���һ����̾��(!)����Է�
            Request request2 = new Request(request1.getName() + "!");
            System.out.println(Thread.currentThread().getName() + " puts  " + request2);
            output.putRequest(request2);
        }
        System.out.println(Thread.currentThread().getName() + ":END");
    }
}

package com.caribe.stone.j2se.thread.Immutable.A4;

public class Main {
    public static void main(String[] args) {
        // ����ʵ��
        UserInfo userinfo = new UserInfo("Alice", "Alaska");

        // ��ʾ
        System.out.println("userinfo = " + userinfo);

        // ״̬����
        StringBuffer info = userinfo.getInfo();
        info.replace(12, 17, "Bobby");  // "Alice"����m�O12..16

        //�ٶ���ʾ
        System.out.println("userinfo = " + userinfo);
    }
}

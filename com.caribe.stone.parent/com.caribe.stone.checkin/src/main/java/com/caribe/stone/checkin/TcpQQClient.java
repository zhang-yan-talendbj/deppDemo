package com.caribe.stone.checkin;
import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TcpQQClient extends JFrame{
        JButton btnConnection=new JButton("连接");//连接按钮
        JButton btnSend=new JButton("发送");//发送按钮
        
        JTextField txtServer=new JTextField(10);
        JTextField txtUser=new JTextField(10);
        JTextField txtContext=new JTextField(30);
        
        JLabel lblServer=new JLabel("服务器：");
        JLabel lblUser=new JLabel("用户名：");
        JLabel lblContext=new JLabel("内容：");
        
        JComboBox cmbUsers=new JComboBox();
        

        List lstMsg=new List(10,true);
        
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        JPanel p4=new JPanel();
        
        Socket s;//创建Socket对象
        
        public TcpQQClient(){
                super("QQ聊天");
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setLayout(new BorderLayout());
                
                p1.setLayout(new BorderLayout());
                
                p1.add(lstMsg);
                p1.add(p3,BorderLayout.SOUTH);
                
                p2.add(lblUser);
                p2.add(txtUser);
                p2.add(lblServer);
                p2.add(txtServer);
                p2.add(btnConnection);
                
                cmbUsers.addItem("所有人");
                p3.add(cmbUsers);
                p3.add(lblContext);
                p3.add(txtContext);
                p3.add(btnSend);
                btnSend.setEnabled(false);
                
                btnSend.addActionListener(new ButtonMonitor());
                btnConnection.addActionListener(new ButtonMonitor());
                
                this.add(p2,BorderLayout.SOUTH);
                this.add(p1,BorderLayout.CENTER);
                this.pack();
                this.setVisible(true);
        }
        
        class ButtonMonitor implements ActionListener{

                public void actionPerformed(ActionEvent e) {
                        if(e.getSource()==btnConnection){ //连接按钮
                                try {
                                        //连接服务器
                                        s=new Socket(txtServer.getText(),8888);
                                        //获得输入流
                                        InputStream is=s.getInputStream();
                                        //创建输出流 获取返回的套接字的输出流
                                        PrintStream ps=new PrintStream(s.getOutputStream(),true);
                                        ps.println("register-"+txtUser.getText());
                                        //启动ClientRehread类的线程--将信息将到启动ClientRehread类
                                        new Thread(new ClientRehread(lstMsg,is,cmbUsers,txtUser.getText())).start();
                                        TcpQQClient.this.btnSend.setEnabled(true);
                                } catch (Exception e2) {
                                        System.out.println("客户端连接出错");
                                }
                        }
                        if(e.getSource()==btnSend){//发送按钮
                                try {
                                        PrintStream ps=new PrintStream(s.getOutputStream(),true);
                                        if("所有人".equals(cmbUsers.getSelectedItem())){
                                                ps.println("all-"+txtUser.getText()+"-"+txtContext.getText());
                                        }else{
                                                ps.println(cmbUsers.getSelectedItem()+"-"+txtUser.getText()+"-"+txtContext.getText());
                                        }
                                        lstMsg.add("你 对 "+cmbUsers.getSelectedItem()+" 说："+txtContext.getText());
                                        txtContext.setText("");
                                } catch (Exception e2) {
                                        System.out.println("客户端发送信息出错");
                                }
                        }
                }
                
        }

        
        public static void main(String[] args) {
                new TcpQQClient();
        }

}

class ClientRehread implements Runnable{//线程-接收消息类
        List lstMsg;
        InputStream is;
        JComboBox cmbUsers;
        String user;
        
        public ClientRehread(List lstMsg, InputStream is,JComboBox cmbUsers,String user) {
                this.lstMsg = lstMsg;
                this.is = is;
                this.cmbUsers=cmbUsers;
                this.user=user;
        }

        public void run() {
                try {
                        BufferedReader br=new BufferedReader(new InputStreamReader(is));
                        while(true){
                                String reStr=br.readLine();
                                String[] str=reStr.split("-");
                                if("register".equals(str[0])){
                                        this.cmbUsers.removeAllItems();
                                        this.cmbUsers.addItem("所有人");
                                        for(int i=1;i<str.length;i++){
                                                this.cmbUsers.addItem(str[i]);
                                        }
                                }else if("all".equals(str[0])){
                                        if(!user.equals(str[1])){
                                                lstMsg.add(str[1]+" 对所有人说："+str[2]);
                                        }
                                }else if(user.equals(str[0])){
                                        lstMsg.add(str[1]+" 对你说："+str[2]);
                                }
                                lstMsg.makeVisible(lstMsg.getItemCount()-1);  //设置最后一项可见
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("客户端接受信息出错");
                }
        }
        
}

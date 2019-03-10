package com.qqcwathlient.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;

public class ClientLogin extends JFrame{
	
	JLabel jlbl1;
	
	
	
	JTabbedPane zhongbuJtP;
	JPanel zbjp1,zbJP2,zbJP3,zbJP4;
	
	JLabel zbJL1,zbJL2,zbJL3,zbJL4;
	JTextField JTx;
	JPasswordField JPass;
	JButton zbAN;
	JCheckBox zbXZ1,zbXZ2/*,zbXZ3,zbXZ4,zbXZ5*/;
	
	JLabel jb1,jb2,jb3;
	
	public ClientLogin() {
		jlbl1=new JLabel(new ImageIcon("images/tou.gif"));
		this.add(jlbl1,"North");
		
		zhongbuJtP=new JTabbedPane();
		zbjp1=new JPanel(new GridLayout(3,3));
		zbJP2=new JPanel();
		zbJP3=new JPanel();
		zbJP4=new JPanel();
		
		zbJL1=new JLabel("vas账号",JLabel.CENTER);zbJL2=new JLabel("vas密码",JLabel.CENTER);
		zbJL3=new JLabel("忘记密码",JLabel.CENTER);
		zbJL3.setForeground(Color.blue);
		zbJL4=new JLabel("申请账号密保",JLabel.CENTER);
		zbJL4.setForeground(Color.blue);
		JTx=new JTextField();
		JPass=new JPasswordField();
		zbAN=new JButton(new ImageIcon("images/clear.gif"));
		zbXZ1=new JCheckBox("记住密码");zbXZ2=new JCheckBox("隐身登陆");
		//zbXZ3=new JCheckBox("隐身");zbXZ4=new JCheckBox("隐身");zbXZ5=new JCheckBox("隐身");
		
		zbjp1.add(zbJL1);zbjp1.add(JTx);zbjp1.add(zbAN);
		zbjp1.add(zbJL2);zbjp1.add(JPass);zbjp1.add(zbJL3);
		zbjp1.add(zbXZ1);zbjp1.add(zbXZ2);zbjp1.add(zbJL4);
		//zbjp1.add(zbXZ3);zbjp1.add(zbXZ4);zbjp1.add(zbXZ5);
		
		
	
		zhongbuJtP.add(zbjp1,"vas");
		zhongbuJtP.add(zbJP2,"qq");
		jb1=new JLabel("施工中",JLabel.CENTER);jb1.setForeground(Color.red);zbJP2.add(jb1);
		zhongbuJtP.add(zbJP3,"wx");
		jb2=new JLabel("施工中",JLabel.CENTER);jb2.setForeground(Color.red);zbJP3.add(jb2);
		zhongbuJtP.add(zbJP4,"yx");
		jb3=new JLabel("施工中",JLabel.CENTER);jb3.setForeground(Color.red);zbJP4.add(jb3);
		this.add(zhongbuJtP);
		
		
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	

	public static void main(String[] args) {
		ClientLogin clientLogin=new ClientLogin();

	}

}

package com.yychatserver.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yychat.model.Message;

public class YychatDbUtil {
	public static final String MYSQLDRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://127.0.0.1:3306/yychat";
	public static final String DBUSER= "root";
	public static final String DBPASS= "";
	
	public static void loadDriver(){
		
		try {
			Class.forName(MYSQLDRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		loadDriver();
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static boolean loginValidate(String userName,String passWord) {
		boolean loginSuccess=false;
		Connection conn=getConnection();
		
		String user_Login_Sql="select * from user where username=? and password=?";
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		 try {
			ptmt=conn.prepareStatement(user_Login_Sql);
			ptmt.setString(1, userName);
			ptmt.setString(2, passWord);
			rs=ptmt.executeQuery();
			loginSuccess=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB(conn,ptmt,rs);
		}
		return loginSuccess;
	}
	public static String getFriendString(String userName) {
		Connection conn=getConnection();
		String friend_Relation_Sql="select slaveuser from relation where majoruser=? and relationtype=1";
		PreparedStatement ptmt=null;
		String friendString="";
		ResultSet rs=null;
		
		try {
			ptmt=conn.prepareStatement(friend_Relation_Sql);
			ptmt.setString(1,userName);
			rs=ptmt.executeQuery();
			
			while(rs.next()) {
				
				friendString=friendString+rs.getString("slaveuser")+" ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB(conn,ptmt,rs);
		}
		return friendString;
	}
	public static void closeDB(Connection conn,PreparedStatement ptmt,ResultSet rs) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ptmt!=null) {
			try {
				ptmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
//	Class.forName(MYSQLDRIVER);
//	System.out.println("已经加载了数据库驱动！");
//	//2、连接数据库
//	System.out.println(URL);
//	//中文用户名必须用下面的url
//	//String url="jdbc:mysql://127.0.0.1:3306/yychat?useUnicode=true&characterEncoding=UTF-8";
//	String dbUser="root";
//	String dbPass="";				
//	Connection conn=DriverManager.getConnection(url,dbUser,dbPass);
//	
//	//3、创建PreparedStatement对象，用来执行SQL语句
//	String user_Login_Sql="select * from user where username=? and password=?";
//	PreparedStatement ptmt=conn.prepareStatement(user_Login_Sql);
//	ptmt.setString(1, userName);
//	ptmt.setString(2, passWord);
//	
//	//4、执行查询，返回结果集
//	ResultSet rs=ptmt.executeQuery();
//	
//	//5、根据结果集来判断是否能登录
//	boolean loginSuccess=rs.next();
//	//实现密码验证功能
//	
//
////6、在yychatserver项目中添加数据库驱动：Build Path->Add External JARs->mysql-connector-java-5.1.6-bin
////创建不了				
//	mess=new Message();//验证操作
//	mess.setSender("Server");
//	mess.setReceiver(userName);
}

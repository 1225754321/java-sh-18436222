1、数据库代码的封装：放到一个类（YychatDbUtil）,可以使得代码逻辑更清晰。

2、实验名称：注册新用户
       实验内容：从客户端输入用户名和密码并发送到服务器端，服务器接收到后写入到数据库user表中。
       实验目的：掌握按钮的事件响应代码的设计，掌握客户端向服务器端发送新用户名和密码，掌握如何把新用户名和密码写入到数据库表中。
      问题分析与算法或操作要点：
 	  步骤1、为注册按钮添加事件响应代码
 	  步骤2：响应动作代码
	 步骤3：在User类中添加新的成员变量
 	步骤4：显示注册成功或失败的提示信息
	 步骤5：创建registerUserIntoDB方法来发送user对象到服务器端，并且接收服务器返回的message
	 步骤6：添加Message对象的新类型
	 步骤7：在服务端完成新用户的注册
	 步骤8：对注册用户名进行查询				
	步骤9：如果没有同名用户，把新用户的名字和密码写入到user表中
 
 3、添加新好友
        实验内容：从客户端输入新好友的名字，发送到服务器端验证通过后,把新好友加入到relation表中，
        	（验证用户是否存在，验证该好友是否已经是好友，不做：对方同意添加好友），
        	服务器发送消息到客户端，客户端利用该消息完成好友的添加。
   	添加新好友实验步骤：1、增加添加好友的按钮，添加监听器
         2、增加处理事件的代码
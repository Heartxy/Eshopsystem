package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	//eshopΪ���ݿ����ƣ�db_urlΪ���ݿ��url��db_userΪ���ݿ��û��� db_pansswordΪ���ݿ�����
	public static String db_url = "jdbc:mysql://localhost:3306/eshop?useUnicode=true&characterEncoding=UTF-8"; //����ͳһ���ַ���
	public static String db_user="root";
	public static String db_password="happy";
	
	public static Connection getConn(){
		//�÷������������ݿ⽨������
		Connection conn=null;  //����һ�����Ӷ���
		try{
			//����try�ﲶ׽�쳣����ֹ���Ӵ���
			Class.forName("com.mysql.jdbc.Driver");//����mysql������
			conn=DriverManager.getConnection(db_url,db_user,db_password);
			//ͨ��DriveManager���getConnection���������ݿ��������			
		}catch(Exception e){
			e.printStackTrace(); //��������쳣�Ͱ��쳣·��������
		}
		return conn;
	}
	public static void close(Statement state,Connection conn){
		//���մ򿪵ķ���˳��ر�state��conn
		if(state!=null){
			//ֻҪstate��Ϊ�վ͹ر�
			try{
				state.close();
			}
			catch(SQLException e){
				//��������ݿ��쳣�Ͱ�·�����
				e.printStackTrace();
			}
		}
		if(conn!=null){
			//ֻҪconn��Ϊ�վ͹ر�
			try{
				conn.close();
			}
			catch(SQLException e){
				//��������ݿ��쳣�Ͱ�·�����
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs,Statement state,Connection conn){
		//���մ򿪵ķ���˳��ر�rs,state��conn
		if(rs!=null){
			//ֻҪrs��Ϊ�վ͹ر�
			try{
				rs.close();
			}
			catch(SQLException e){
				//��������ݿ��쳣�Ͱ�·�����
				e.printStackTrace();
			}
		}
		if(state!=null){
			//ֻҪstate��Ϊ�վ͹ر�
			try{
				state.close();
			}
			catch(SQLException e){
				//��������ݿ��쳣�Ͱ�·�����
				e.printStackTrace();
			}
		}
		if(conn!=null){
			//ֻҪconn��Ϊ�վ͹ر�
			try{
				conn.close();
			}
			catch(SQLException e){
				//��������ݿ��쳣�Ͱ�·�����
				e.printStackTrace();
			}
		}
	}
}

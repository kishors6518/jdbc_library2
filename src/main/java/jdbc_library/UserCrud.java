package jdbc_library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc_user.User;

public class UserCrud {
	
	public Connection getConnection()throws Exception
	{
		String className="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/librarydb";
		String user="root";
		String pass="root";
		
		Class.forName(className);
		Connection connection=DriverManager.getConnection(url, user, pass);
		return connection;
	}
	public int singUp(User user)throws Exception
	{
		Connection connection=getConnection();
		String sql="INSERT INTO USER(ID,NAME,PHONE,EMAIL,PASSWORD) VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setLong(3, user.getPhone());
		preparedStatement.setString(4,user.getEmail());
		preparedStatement.setString(5, user.getPassword());
		
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;
	}
	public String getPassword(String email)throws Exception
	{
		Connection  connection=getConnection();
		String pass="SELECT PASSWORD FROM USER WHERE EMAIL=?";
		PreparedStatement preparedStatement=connection.prepareStatement(pass);
		preparedStatement.setString(1, email);
		ResultSet set=preparedStatement.executeQuery();
		String password=null;
		while (set.next()) {
			password=set.getString("password");
		}
		connection.close();
		return password;		
	}
	public int updatePassword(String email,String password)throws Exception
	{
		Connection connection=getConnection();
		String updatePass="UPDATE USER SET PASSWORD=? WHERE EMAIL=?";
		PreparedStatement preparedStatement=connection.prepareStatement(updatePass);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;	
	}
	public User getInfo(String email)throws Exception {
		String info="SELECT * FROM USER WHERE EMAIL=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(info);
		preparedStatement.setString(1, email);
		ResultSet set=preparedStatement.executeQuery();
		User user=new User();
		while(set.next())
		{
			user.setId(set.getInt("id"));
			user.setName(set.getString("name"));
			user.setPhone(set.getLong("phone"));
			user.setEmail(set.getString("email"));
			user.setPassword(set.getString("password"));
		}
		return user;		
	}
	public int updateEmail(String email,String password)throws Exception
	{
		Connection connection=getConnection();
		String updatePass="UPDATE USER SET EMAIL=? WHERE PASSWORD=?";
		PreparedStatement preparedStatement=connection.prepareStatement(updatePass);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;	
	}

}

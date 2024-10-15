package jdbc_library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibraryCrud {
	
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
	public int addBook(Library library)throws Exception
	{
		Connection connection=getConnection();
		String sql="INSERT INTO BOOKS(ID,NAME,AUTHOR,GENRE) VALUES(?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,library.getId());
		preparedStatement.setString(2, library.getName());
		preparedStatement.setString(3, library.getAuthor());
		preparedStatement.setString(4,library.getGenre());
		
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;
	}
	public Library getBook(int id)throws Exception {
		String info="SELECT * FROM BOOKS WHERE ID=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(info);
		preparedStatement.setInt(1, id);
		ResultSet set=preparedStatement.executeQuery();
		Library library=new Library();
		while(set.next())
		{
			library.setId(set.getInt("id"));
			library.setName(set.getString("name"));
			library.setAuthor(set.getString("author"));
			library.setGenre(set.getString("genre"));
		}
		return library;		
	}
	public Library getBook(String name)throws Exception {
		String info="SELECT * FROM BOOKS WHERE NAME=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(info);
		preparedStatement.setString(1, name);
		ResultSet set=preparedStatement.executeQuery();
		Library library=new Library();
		while(set.next())
		{
			library.setId(set.getInt("id"));
			library.setName(set.getString("name"));
			library.setAuthor(set.getString("author"));
			library.setGenre(set.getString("genre"));
		}
		return library;		
	}
	public int updateBook(String name,int id) throws Exception
	{
		String update="UPDATE BOOKS SET NAME=? WHERE ID=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(update);
		preparedStatement.setString(1,name);
		preparedStatement.setInt(2, id);
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;	
	}
	public int deleteBook(int id) throws Exception
	{
		String delete="DELETE FROM BOOKS WHERE ID=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(delete);
		preparedStatement.setInt(1, id);
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;	
	}
	public int deleteBook(String name) throws Exception
	{
		String delete="DELETE FROM BOOKS WHERE NAME=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(delete);
		preparedStatement.setString(1, name);
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;	
	}

}

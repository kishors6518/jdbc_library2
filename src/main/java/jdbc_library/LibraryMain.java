package jdbc_library;

import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc_user.User;

public class LibraryMain {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		UserCrud ucrud = new UserCrud();
		LibraryCrud lcrud=new LibraryCrud();
		Library library=new Library();
		boolean flag = true;
		while (flag) {
			try {
				System.out.println("Welcome in Kishor's Library \n1.Signup \n2.Login \n3.Exit");
				System.out.println("Enter your choice");
				int op = scanner.nextInt();
				switch (op) {
				case 1: {
					System.out.println("Enter the Id");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter the Name");
					String name = scanner.nextLine();
					System.out.println("Enter the Phone no");
					long phone = scanner.nextLong();
					scanner.nextLine();
					System.out.println("Enter the Email");
					String email = scanner.nextLine();
					System.out.println("Enter the Password");
					String password = scanner.nextLine();

					User user = new User();
					user.setId(id);
					user.setName(name);
					user.setPhone(phone);
					user.setEmail(email);
					user.setPassword(password);

					int result = ucrud.singUp(user);
					if (result != 0) {
						System.out.println("SignUp Successful");
						break;
					} else {
						System.out.println("SignUp failed plese re-enter data");
					}
				}
					break;
				case 2: {
					scanner.nextLine();
					System.out.println("Enter the email");
					String email = scanner.nextLine();
					System.out.println("Enter the password");
					String password = scanner.nextLine();
					String dbPass = ucrud.getPassword(email);
					boolean flag1 = true;
					while (flag1) {
						if (dbPass != null) {
							if (dbPass.equals(password)) {
								System.out.println();
								System.out.println("1.Add Book \n2.Find Book \n3.Update Book  \n4.Delete Book\n5.Display Info \n6.Upddate Email \n7.Update Password \n8.Forgot Password \n9.Logout");
								System.out.println("Enter your choice");
								int op2 = scanner.nextInt();
								switch (op2) {
								case 1:{
									System.out.println("Enter the Id");
									int id = scanner.nextInt();
									scanner.nextLine();
									System.out.println("Enter the Book Name");
									String bname = scanner.nextLine();
									System.out.println("Enter the Author ");
									String author=scanner.nextLine();
									System.out.println("Enter the Genre");
									String genre = scanner.nextLine();


									library=new Library();
									library.setId(id);
									library.setName(bname);
									library.setAuthor(author);
									library.setGenre(genre);

									int result = lcrud.addBook(library);
									if (result != 0) {
										System.out.println("Book added Successful");
										break;
									} else {
										System.out.println("Book not added");
									}
								}break;
								case 2:{
									System.out.println();
									System.out.println("Search by \n1.ID \n2.Name \n3.Author \n4.Type");
									System.out.println("Enter your choice");
									int op1=scanner.nextInt();
									switch (op1) {
									case 1:{
										int id=scanner.nextInt();
										library=lcrud.getBook(id);
										if(id==library.getId())
										{
											System.out.println("ID:"+library.getId()+", Name:"+library.getName()+", Author:"+library.getAuthor()+", Genre:"+library.getGenre());
										}
										else
										{
											System.out.println(id+" is not found");
										}
									}break;
									case 2:{
										scanner.nextLine();
										String name=scanner.nextLine();
										library=lcrud.getBook(name);
										if(name.toLowerCase().contains(library.getName().toLowerCase()))
										{
											System.out.println("ID:"+library.getId()+", Name:"+library.getName()+", Author:"+library.getAuthor()+", Genre:"+library.getGenre());
										}
										else if(library.getName()==null)
										{
											System.out.println(name+" not found");
										}
									}
										break;
									case 3:{
										scanner.nextLine();
										String author=scanner.nextLine();
										library=lcrud.getBook(author);
										if(author.equalsIgnoreCase(library.getAuthor()))
										{
											System.out.println("ID:"+library.getId()+", Name:"+library.getName()+", Author:"+library.getAuthor()+", Genre:"+library.getGenre());
										}
										else
										{
											System.out.println(author+" not found");
										}
									}
										break;
									case 4:{
										scanner.nextLine();
										String genre=scanner.nextLine();
										library=lcrud.getBook(genre);
										if(genre.equalsIgnoreCase(library.getGenre()))
										{
											System.out.println("ID:"+library.getId()+", Name:"+library.getName()+", Author:"+library.getAuthor()+", Genre:"+library.getGenre());
										}
										else
										{
											System.out.println(genre+" not found");
										}
									}
										break;
									}
									
								}break;
								case 3:{
									scanner.nextLine();
									System.out.println("Enter update book name");
									String name=scanner.nextLine();
									System.out.println("Enter id for update book");
									int id=scanner.nextInt();
									LibraryCrud crud=new LibraryCrud();
									int result=crud.updateBook(name, id);
									if(result!=0)
									{
										System.out.println("Book Updated");
									}
									else
									{
										System.out.println("Book not update");
									}
									
								}break;
								case 4:{
									System.out.println();
									System.out.println("Delete by \n1.ID \n2.Name");
									System.out.println("Enter your choice");
									int op1=scanner.nextInt();
									switch (op1) {
									case 1:{
										int id=scanner.nextInt();
										int result=lcrud.deleteBook(id);
										if(result!=0)
										{
											System.out.println("Book Deleted");
										}
										else
										{
											System.out.println(id+" is not deleted");
										}
									}break;
									case 2:{
										scanner.nextLine();
										String name=scanner.nextLine();
										int result=lcrud.deleteBook(name);
										if(result!=0)
										{
											System.out.println("Book Deleted");
										}
										else
										{
											System.out.println(name+" is not deleted");
										}
									}
									}
								}break;								
								case 5: {
									User user = ucrud.getInfo(email);
									System.out.println("ID: " + user.getId()+", Name:"+user.getName()+", Phone:"+user.getPhone()+", Email:"+user.getEmail()+", Password:"+user.getPassword());
								
								}
									break;
								case 6:{
									scanner.nextLine();
									System.out.println("Enter new email for update");
									String uemail=scanner.nextLine();
									int result = ucrud.updateEmail(uemail,password);
									if (result != 0) {
										System.out.println("Email Update");
										break;
									} else {
										System.out.println("Failed to update password");
									}
								}
								

								case 7: {
									int i = 0;
									while (i < 3) {
										scanner.nextLine();
										System.out.println("Enter the new password");
										String updatePass1 = scanner.nextLine();
										System.out.println("Please re-enter password");
										String updatePass2 = scanner.nextLine();
										if (updatePass1.equals(updatePass2)) {
											int result = ucrud.updatePassword(email, updatePass2);
											if (result != 0) {
												System.out.println("Password Update");
												break;
											} else {
												System.out.println("Failed to update password");
											}
										} else {
											System.out.println("Please enter update password and re-enter password as same");
											i++;
										}
									}
									if (i >= 3) {
										System.out.println("Your limit is reached");
									}

								}
									break;
								case 8: {
									scanner.nextLine();
									System.out.println("Enter the new password");
									String pass = scanner.nextLine();
									int result = ucrud.updatePassword(email, pass);
									if (result != 0) {
										System.out.println("Password Updated");
									} else {
										System.out.println("Password not update");
									}
								}
									break;
								case 9: {
									System.out.println("Logout Successful");
									flag1 = false;
									break;
								}
								}
							} else {
								System.out.println("Please check credentials");
								break;
							}
						} else {
							System.out.println(email + " this mail is not registered");
							break;
						}
					}
				}
					break;
				case 3: {
					System.out.println("Exit Successful");
					flag = false;
				}
				}
			} catch (InputMismatchException i) {
				System.out.println("Please enter valid data");
				break;
			}
		}

	}

}

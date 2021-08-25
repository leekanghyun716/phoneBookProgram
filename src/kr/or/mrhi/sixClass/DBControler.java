package kr.or.mrhi.sixClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBControler {


	public static int phoneBookInsert(PhoneBook phoneBook) {
		Connection con = DBUtillity.getConnection();
		PreparedStatement preparedStatement = null;
		int count =0;
		String insertQuery = "insert into phonebookdb.phonebooktbl values(?,?,?,?,?,?)";
		try {
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, phoneBook.getPhoneNumber());
			preparedStatement.setString(2, phoneBook.getName());
			preparedStatement.setString(3, phoneBook.getGender());
			preparedStatement.setString(4, phoneBook.getJop());
			preparedStatement.setString(5, phoneBook.getBirthday());
			preparedStatement.setInt(6, phoneBook.getAge());

			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return count;
	}
	//전화번호부 검색하기
	public static List<PhoneBook> phoneBookSearchTBL(String inputSearchData, int searchNumber) {
		List<PhoneBook> list = new ArrayList<PhoneBook>();
		Connection con = DBUtillity.getConnection();
		String searchQuery = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
			
			switch (searchNumber) {
			case 1:
				
				searchQuery = "select * from phonebooktbl where phoneNumber like ?";
				break;
			case 2:
				
				searchQuery = "select * from phonebooktbl where name like ?";
				break;
			case 3:
				searchQuery = "select * from phonebooktbl where gender like ?";
				break;
			default:System.out.println("검색번호범위에 벗어났습니다. (다시입력)");return list;
			}
			
			
		
		
		
		
		try {
			preparedStatement = con.prepareStatement(searchQuery);
			inputSearchData = "%"+inputSearchData+"%";
			preparedStatement.setString(1, inputSearchData);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.isBeforeFirst()) {
				System.out.println("찾을수없어");
				return list;
			}
			while (resultSet.next()) {
				String phoneNumber = resultSet.getString(1);
				String name = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String jop = resultSet.getString(4);
				String birthday = resultSet.getString(5);
				int age = resultSet.getInt(6);

				PhoneBook phoneBook = new PhoneBook(phoneNumber, name, gender, jop, birthday, age);
				list.add(phoneBook);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return list;
	}
	public static int phoneBookDeleteTBL(String name) {
		Connection con = DBUtillity.getConnection();
		String strQuery = "delete from phonebooktbl where name like ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		boolean flag =false;
		try {
			preparedStatement=con.prepareStatement(strQuery);
			preparedStatement.setString(1, name);
			count=preparedStatement.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return count;
	}
	public static int phoneBookUpdateTBL(String phoneNumber,String name) {
		Connection con = DBUtillity.getConnection();
		String strQuery = "update phonebooktbl set name =? where phoneNumber = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		boolean flag =false;
		try {
			preparedStatement=con.prepareStatement(strQuery);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phoneNumber);
			count=preparedStatement.executeUpdate();
			if(count ==1) {
				flag =true;
				System.out.println(phoneNumber+"님이 수정되었습니다.");
			}else {
				System.out.println(phoneNumber+"님이 수정 실패되었습니다.");
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return count;
	}
	public static List<PhoneBook> phoneBookSelectTBL() {
		List<PhoneBook> list = new ArrayList<PhoneBook>();
		Connection con = DBUtillity.getConnection();
		String strQuery = "select * from phoneBookDB.phonebooktbl";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement(strQuery);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.isBeforeFirst()) {
				System.out.println("데이터가 없습니다.");
				
			}
			while (resultSet.next()) {
				String phoneNumber = resultSet.getString(1);
				String name = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String jop = resultSet.getString(4);
				String birthday = resultSet.getString(5);
				int age = resultSet.getInt(6);

				PhoneBook phoneBook = new PhoneBook(phoneNumber, name, gender, jop, birthday, age);
				list.add(phoneBook);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return list;
	}

}

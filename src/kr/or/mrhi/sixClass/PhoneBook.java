package kr.or.mrhi.sixClass;

import java.util.Objects;

public class PhoneBook {
	private String phoneNumber;//전화번호
	private String name;//이름
	private String gender;//성별
	private String jop;//직업
	private String birthday;//생일
	private int age;//나이
	
	public PhoneBook(String phoneNumber, String name, String gender, String jop, String birthday, int age) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.gender = gender;
		this.jop = jop;
		this.birthday = birthday;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PhoneBook) {
			PhoneBook book = (PhoneBook)obj;
			return this.phoneNumber.equals(book.getPhoneNumber());
		}
			
		return false;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJop() {
		return jop;
	}

	public void setJop(String jop) {
		this.jop = jop;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		String year = this.birthday.substring(0,4);
		String month = this.birthday.substring(4,6);
		String day = this.birthday.substring(6);
		String strBirthday =year+"년"+month+"월"+day+"일";
		return  phoneNumber + "\t" + name + "\t" + gender + "\t" + jop+ "\t   " + strBirthday + "\t   " + age+"세";
	}
	
	
}

package Survey;

public class InfoVo {
	private long number;
	private int age;
	private String religion;
	
	public InfoVo(long number, int age, String religion) {
		super();
		this.number = number;
		this.age = age;
		this.religion = religion;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}

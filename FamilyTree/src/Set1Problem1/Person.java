package Set1Problem1;

import java.util.List;

public class Person {
	private String name;
	private String gender;
	List<Person> childs;
	Person partner;
	Person parent;

	public Person(String name, String gender) {
		this.name = name;
		this.gender = gender;
		childs = null;
		partner = null;
		parent = null;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public List<Person> getChild() {
		return childs;
	}
}

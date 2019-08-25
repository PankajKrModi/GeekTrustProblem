package Set1Problem1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Family {

	private  Person king,queen;
	public Family() {
		king = new Person("King Shan","M");
		queen = new Person("Queen Anga","F");
		king.partner =queen;
		queen.partner = king;
		addChild("Queen Anga","Chit","M");
		addChild("Queen Anga","Ish","M");
		addChild("Queen Anga","Vich","M");
		addChild("Queen Anga","Aras","M");
		addChild("Queen Anga","Satya","F");


		addPartner("Satya", "Vyan","M");
		addPartner("Aras", "Chitra","F");
		addPartner("Vich", "Lika","F");
		addPartner("Chit", "Amba","F");

		addChild("Amba","Dritha","F");
		addChild("Amba","Tritha","F");
		addChild("Amba","Vritha","M");
		addChild("Lika","Villa","F");
		addChild("Lika","Chika","F");
		addChild("Chitra","Jinki","F");
		addChild("Chitra","Ahit","M");
		addChild("Satya","Asva","M");
		addChild("Satya","Vyas","M");
		addChild("Satya","Atya","F");

		addPartner("Dritha", "Jaya", "M");
		addPartner("Jinki", "Arit","M");
		addPartner("Asva", "Satvy","F");
		addPartner("Vyas", "Krpi","F");

		addChild("Dritha","Yodhan","M");
		addChild("Jinki","Lavnya","F");
		addChild("Jinki","Laki","M");
		addChild("Satvy","Vasa","M");
		addChild("Krpi","Kriya","M");
		addChild("Krpi","Krithi","F");


	}

	private void addPartner(String personName,String newPartnerName,String partnerGender) {
		Person person = findPersonByName(personName);
		if(person!=null) {
			Person partner= new Person(newPartnerName,partnerGender);
			person.partner = partner;
			partner.partner = person;
		}

	}

	public  String addChild(String motherName,String childName,String childGender) {
		Person mother = findPersonByName(motherName);
		
		if(mother==null) {
			return "PERSON_NOT_FOUND";
		}
		if(mother.getGender().equalsIgnoreCase("F")) {
			Person newPerson = new Person(childName,childGender);
			if(mother.childs ==null) {
				mother.childs =  new ArrayList<Person>();
			}
			mother.childs.add(newPerson);
			newPerson.parent = mother;
			return "CHILD_ADDITION_SUCCEEDED";
		}
		return "CHILD_ADDITION_FAILED";
	}

	public String get_RelationShip(String name, String relationship) {
		Person person = findPersonByName(name);
		if(person==null) {
			return "PERSON_NOT_FOUND";
		}
		relationship = relationship.toLowerCase();
		switch (relationship) {
		case  "paternal-uncle":
			return getPaternalUncle(person);

		case "maternal-uncle":
			return getMaternalUncle(person);

		case "paternal-aunt":
			return getPaternalAunt(person);

		case "maternal-aunt":
			return getMaternalAunt(person);
			
		case "sister-in-law":
			return getSisterinLaw(person);
			
		case "brother-in-law":
			return getBrotherinLaw(person);
			
		case "son":
			return getSon(person);
			
		case "daughter":
			return getDaughter(person);
			
		case "Siblings":
			return getSiblins(person);
		}
		return "NONE";
	}

	private String getSiblins(Person person) {
		List<Person> brothers = getBrother(person);
		List<Person> sisters = getSister(person);
		
		String sibling ="";
		if(brothers!=null) {
			for(Person p:brothers) {
				sibling = sibling+ ", "+p.getName();
			}
		}
		if(sisters!=null) {
			for(Person p:sisters) {
				sibling = sibling+ ", "+p.getName();
			}
		}
		if(sibling.length()>0) {
			return trim(sibling);
		}
		return "NONE";
	}
	private String getSon(Person person) {
		Person mother=null;
		
		if(person.partner==null)
			return "NONE";
		
		if(person.getGender().equals("M")) {
			mother = person.partner;
		}else mother = person;
		
		List<Person> childs = mother.getChild();
		String sons="";
		if(childs!=null) {
			for(Person p:childs) {
				if(p.getGender().equals("M"))
				sons = sons+", "+p.getName();
			}
			if(sons.length()>0) {
				return trim(sons);
			}
		}
		return "NONE";
	}
	
	private String getDaughter(Person person) {
		
		if(person.partner==null)
			return "NONE";
		
		Person mother=null;
		
		if(person.getGender().equals("M")) {
			mother = person.partner;
		}else mother = person;
		
		List<Person> childs = mother.getChild();
		String daughter="";
		if(childs!=null) {
			for(Person p:childs) {
				if(p.getGender().equals("F"))
					daughter = daughter+", "+p.getName();
			}
			if(daughter.length()>0) {
				return trim(daughter);
			}
		}
		return "NONE";
	}
	
	private String getBrotherinLaw(Person person) {
		String broInLaw = "";
		
		List<Person> sisters = getSister(person);
		if(sisters!=null) {
			for(Person sis:sisters) {
				if(sis.partner!=null) {
					broInLaw = broInLaw + ", " + sis.partner.getName();
				}
			}
		}
		if(person.getGender().equals("M")) {
			Person wife = person.partner;
			List<Person> wivesBrother = getBrother(wife);
			if(wivesBrother!=null) {
				for(Person bro:wivesBrother) {
					broInLaw = broInLaw + ", "+bro.getName();
				}
			}
		}else if(person.getGender().equals("F")) {
			Person husband = person.partner;
			List<Person> husbandBrother = getBrother(husband);
			if(husbandBrother!=null) {
				for(Person bro:husbandBrother) {
					broInLaw = broInLaw + ", "+bro.getName();
				}
			}
		}
		if(broInLaw.length()>0) {
			return trim(broInLaw);
		}
		return "NONE";

	}
	
	private String getSisterinLaw(Person person) {
		String sisInLaw = "";
		
		List<Person> brothers = getBrother(person);
		if(brothers!=null) {
			for(Person bro:brothers) {
				if(bro.partner!=null) {
					sisInLaw = sisInLaw + ", " + bro.partner.getName();
				}
			}
		}
		if(person.getGender().equals("M")) {
			Person wife = person.partner;
			List<Person> wivesSister = getSister(wife);
			if(wivesSister!=null) {
				for(Person sis:wivesSister) {
					sisInLaw = sisInLaw + ", "+sis.getName();
				}
			}
			
		}else if(person.getGender().equals("F")) {
			Person husband = person.partner;
			List<Person> husbandSister = getSister(husband);
			if(husbandSister!=null) {
				for(Person sis:husbandSister) {
					sisInLaw = sisInLaw + ", "+sis.getName();
				}
			}
		}
		if(sisInLaw.length()>0) {
			return trim(sisInLaw);
		}
		return "NONE";
	}
	
	private List<Person> getSister(Person person) {
		if(person==null || person.parent==null)
			return null;
		
		List<Person> sis=new ArrayList<Person>();
		
		Person parent = person.parent;
		if(parent.getGender().equals("M")) {
			parent = parent.partner;
		}
		
		List<Person> childs = parent.getChild();
		for(Person child:childs) {
			if(child.getGender().equals("F")&& !child.getName().equals(person.getName())) {
				sis.add(child);
			}
		}
		if(sis.size()>0)
			return sis;
		
		return null;	
	}
	
	
	private List<Person> getBrother(Person person) {
	
		if(person==null || person.parent==null)
			return null;
		
		List<Person> bro=new ArrayList<Person>();
		
		Person parent = person.parent;
		if(parent.getGender().equals("M")) {
			parent = parent.partner;
		}
		
		List<Person> childs = parent.getChild();
		for(Person child:childs) {
			if(child.getGender().equals("M")&& !child.getName().equals(person.getName())) {
				bro.add(child);
			}
		}
		if(bro.size()>0)
			return bro;
		return null;
	}
	
	private String getPaternalUncle(Person person) {

		if(person.parent==null) {
			return "NONE";
		}
		Person father = person.parent.partner;

		if(father.parent!=null) {
			Person grandMother = father.parent;
			List<Person> fatherSibling = grandMother.getChild();
			String uncles = "";
			for(Person p:fatherSibling) {
				if(p.getGender().equals("M")&& !p.getName().equals(father.getName())) {
					uncles = uncles+", "+p.getName();
				}
			}
			if(uncles.length()>0) {
				return trim(uncles);
			}
		}

		return "NONE";
	}

	private String getMaternalUncle(Person person) {

		if(person.parent==null) {
			return "NONE";
		}
		Person mother = person.parent;

		if(mother.parent!=null) {
			Person grandMother = mother.parent;
			List<Person> motherSibling = grandMother.getChild();
			String uncles = "";
			for(Person p:motherSibling) {
				if(p.getGender().equals("M")) {
					uncles = uncles+", "+p.getName();
				}
			}
			if(uncles.length()>0) {
				return trim(uncles);
			}
		}

		return "NONE";
	}

	private String getMaternalAunt(Person person) {

		if(person.parent==null) {
			return "NONE";
		}
		Person mother = person.parent;

		if(mother.parent!=null) {
			Person grandMother = mother.parent;
			List<Person> motherSibling = grandMother.getChild();
			String aunt = "";
			for(Person p:motherSibling) {
				if(p.getGender().equals("F")&& !p.getName().equalsIgnoreCase(mother.getName())) {
					aunt = aunt+", "+p.getName();
				}
			}
			if(aunt.length()>0) {
				return trim(aunt);
			}
		}

		return "NONE";
	}

	private String getPaternalAunt(Person person) {

		if(person.parent==null) {
			return "NONE";
		}
		Person father = person.parent.partner;

		if(father.parent!=null) {
			Person grandMother = father.parent;
			List<Person> fatherSibling = grandMother.getChild();
			String uncles = "";
			for(Person p:fatherSibling) {
				if(p.getGender().equals("F")) {
					uncles = uncles+", "+p.getName();
				}
			}
			if(uncles.length()>0) {
				return trim(uncles);
			}
		}

		return "NONE";
	}	

	private  Person findPersonByName(String name) {
		if(queen.getName().equalsIgnoreCase(name)) {
			return queen;
		}else {
			Queue<Person> queue = new LinkedList<Person>();

			queue.add(queen);

			while(!queue.isEmpty()) {

				Person person = queue.remove();

				List<Person> childs = person.getChild();
				if(childs==null) {
					continue;
				}
				for(Person child:childs) {
					if(child.getName().equalsIgnoreCase(name)) {
						queue = null;
						return child;
					}
					queue.add(child);
					if(child.partner!=null) {
						if(child.partner.getName().equalsIgnoreCase(name)) {
							queue = null;
							return child.partner;
						}
						queue.add(child.partner);
					}
				}
			}
		}
		return null;
	}
	private String trim(String names) {
		int i=0;
		for(i=0;i<names.length();i++) {
			if((names.charAt(i)<='Z'&&names.charAt(i)>='A')||(names.charAt(i)<='z'&&names.charAt(i)>='a')) {
				break;
			}
		}
		return names.substring(i);
	}
	public Person getQueen() {
		return queen;
	}
	public Person getKing() {
		return king;
	}
}

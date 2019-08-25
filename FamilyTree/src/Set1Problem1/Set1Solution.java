package Set1Problem1;

public class Set1Solution {

	
	public static void main(String[] args) {
		Family family = new Family();
		
		System.out.println(family.addChild("Satya", "Ketu", "M"));
		
		
		System.out.println(family.get_RelationShip("Ish", "Son"));
		
		System.out.println(family.get_RelationShip("Satvy", "Sister-In-Law"));
		System.out.println(family.get_RelationShip("MIsha", "daughter"));
		
	}	

}

package model;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FamilyTree {
	
	private Set<Person> familyMembers;
	
	public FamilyTree() {
		familyMembers = new HashSet<Person>();
	}
	
	public void add(Person person) {
		assert(person!=null);		
		familyMembers.add(person);
	}
	
	public Person find(String name, int age) {
		for(Person p : familyMembers){
			if(p.getName().equals(name) && p.getAge()==age) {
				return p;
			}
		}
		return null;
	}
	
	

	/**
	 * prints on the console all given names on a new line
	 * @param person
	 */
	public static void printNames(Person person) {
		System.out.println(person.getName());
	}
	public static void printNames(List<Person> list) {
		int i;
		for (i = 0; i < list.size(); i++) {
			printNames(list.get(i));
		}
		System.out.println("-----------------------");
	}
}
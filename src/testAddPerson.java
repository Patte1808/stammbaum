import static org.junit.Assert.*;

import org.junit.Test;

import model.FamilyTree;
import model.Person;
import model.Person.Gender;

public class testAddPerson {

	@Test
	public void addOnePerson() {
		Person firstPerson = new Person("Test", 0, Gender.Male);
		FamilyTree famTree = new FamilyTree();
		
		famTree.add(firstPerson);
		
		assertEquals(famTree.find("Test", 0), firstPerson);
	}
	
	@Test
	public void addTwoPersonsInRelationship() {
		Person firstPerson = new Person("Test", 0, Gender.Male);
		Person secondPerson = new Person("Test2", 10, Gender.Female);
		
		firstPerson.setSpouse(secondPerson);
		
		FamilyTree famTree = new FamilyTree();
		
		famTree.add(firstPerson);
		famTree.add(secondPerson);
		
		assertEquals(true, firstPerson.isMarried() && secondPerson.isMarried());
		assertEquals(secondPerson, firstPerson.getSpouse());
		assertEquals(firstPerson, secondPerson.getSpouse());
	}

}

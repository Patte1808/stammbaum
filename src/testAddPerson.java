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
	
	// Todo: Zyklen testen, Baum aufbauen und zusammenhangslose Person
	
	@Test
	public void addTwoPersonsAsSiblings() {
		Person firstPerson = new Person("Test", 0, Gender.Male);
		Person secondPerson = new Person("Test2", 10, Gender.Female);
		Person thirdPerson = new Person("Parent 1", 20, Gender.Male);
		
		firstPerson.setParent(thirdPerson);
		secondPerson.setParent(thirdPerson);
		
		thirdPerson.setChildren(firstPerson);
		thirdPerson.setChildren(secondPerson);
		
		FamilyTree famTree = new FamilyTree();
		
		famTree.add(firstPerson);
		famTree.add(secondPerson);
		famTree.add(thirdPerson);

		assertEquals(firstPerson, secondPerson.siblings().get(0));
		assertEquals(secondPerson, firstPerson.siblings().get(0));
		
		assertEquals(thirdPerson, firstPerson.getParent());
		assertEquals(thirdPerson, secondPerson.getParent());
		
		assertEquals(firstPerson, thirdPerson.getChildren().get(0));
		assertEquals(secondPerson, thirdPerson.getChildren().get(1));
	}
}

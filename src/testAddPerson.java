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
	
	@Test
	public void testRelationshipsFromGrandmotherOverAuntToFather() {
		Person grandmother1 = new Person("Grandmother1", 40, Gender.Female);
		Person grandfather1 = new Person("Grandfather1", 44, Gender.Male);
		
		Person grandmother2 = new Person("Grandmother2", 30, Gender.Female);
		Person grandfather2 = new Person("Grandfather2", 40, Gender.Male);
		
		Person aunt1 = new Person("Aunt1", 10, Gender.Female);
		Person aunt2 = new Person("Aunt2", 12, Gender.Female);
		
		Person uncle1 = new Person("Uncle1", 12, Gender.Male);
		Person uncle2 = new Person("Uncle2", 20, Gender.Male);
		
		Person mother = new Person("Mother", 20, Gender.Female);
		Person father = new Person("Father", 30, Gender.Male);
		
		Person child = new Person("Child", 1, Gender.Male);
		
		Person cousin1 = new Person("Cousin1", 2, Gender.Male);
		Person cousin2 = new Person("Cousin2", 3, Gender.Female);
		
		grandmother1.setSpouse(grandfather1);
		grandmother1.setChildren(mother);
		grandmother1.setChildren(aunt1);
		grandmother1.setChildren(uncle1);
		
		grandmother2.setSpouse(grandfather2);
		grandmother2.setChildren(father);
		grandmother2.setChildren(aunt2);
		grandmother2.setChildren(uncle2);
		
		mother.setSpouse(father);
		mother.setChildren(child);
		
		uncle1.setChildren(cousin1);
		uncle2.setChildren(cousin2);
		
		assertEquals(aunt1, child.aunts(Gender.Female).get(0));
		assertEquals(uncle1, child.uncles(Gender.Female).get(0));
		
		assertEquals(aunt2, child.aunts(Gender.Male).get(0));
		assertEquals(uncle2, child.uncles(Gender.Male).get(0));
		
		// IndexOutOfBoundsException. Scheinbar kann man nicht direkt den Vater abfragen, wenn
		// man die Mutter als "Parent" angibt?
		assertEquals(father, child.getFather().get(0));
		assertEquals(mother, child.getMother().get(0));
		
		// Könnte man hier nicht auch so was wie bei den aunts und uncles machen?
		// Das ich direkt am Kinderobjekt abfragen kann, wer Großmutter mütterlicherseits ist etc?
		assertEquals(child, grandmother1.grandchildren().get(0));
		assertEquals(child, grandmother2.grandchildren().get(0));
		assertEquals(child, grandfather1.grandchildren().get(0));
		assertEquals(child, grandfather2.grandchildren().get(0));
		
		// Hier das gleiche wie bei den Großeltern. Ich weiß nicht, wie ich das sonst 
		// besser testen kann.Hast Du eine Idee?
		assertEquals(cousin1, child.cousins().get(0));
		assertEquals(cousin2, child.cousins().get(1));
	}
	
	@Test
	public void testForCycles() {
		Person person1 = new Person("Person1", 2, Gender.Female);
		Person person2 = new Person("Person2", 2, Gender.Male);
		
		person1.setParent(person2);
		
		try {
			person2.setParent(person1);
			fail();
		} catch(Exception e) {
			// TODO ERROR MESSAGE ANPASSEN
			final String expected = "Error bla";
			assertEquals(expected, e.getMessage());
		}
	}
	
	@Test
	public void testParentSpouseRelationship() {
		Person person1 = new Person("Person1", 2, Gender.Female);
		Person person2 = new Person("Person2", 2, Gender.Male);
		
		person1.setChildren(person2);
		
		/* 
		 * Idee: Ein Elternteil sollte nicht mit seinem eigenen Kind verheiratet sein
		 * Ist das Objekt bereits Kind von dem Objekt, mit dem man es verheiraten soll,
		 * sollte ein Fehler geschmissen werden
		 */
		try {
			person2.setSpouse(person1);
			fail();
		} catch(Exception e) {
			// TODO ERROR MESSAGE ANPASSEN
			final String expected = "Error bla";
			assertEquals(expected, e.getMessage());
		}
	}
	
	@Test
	public void testDistinctionOfTwoObjectsWithSameAttributes() {
		Person person1 = new Person("Person", 2, Gender.Male);
		Person person2 = new Person("Person", 2, Gender.Male);
		
		assertEquals(false, person1.equals(person2));
	}
}

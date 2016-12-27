package model;
import model.Person.Gender;

public class Main {

	public static void main(String[] args) {
		FamilyTree famTree = new FamilyTree();
		
		try {
			Person max = new Person("Max", 24, Gender.Male);
			Person silvio = new Person("Silvio", 53, Gender.Male);
			Person marina = new Person("Marina", 56, Gender.Female);
			Person rita = new Person("Rita", 78, Gender.Female);
			Person dummii = new Person("Dummii", 80, Gender.Male);
			Person annette = new Person("Annette", 51, Gender.Female);
			Person tahnee = new Person("Tahnee", 18, Gender.Female);
			Person uta = new Person("Uta", 75, Gender.Female);
			Person dummi = new Person("Dummi", 76, Gender.Male);
			Person hanko = new Person("Hanko", 49, Gender.Male);
			Person lilly = new Person("Lilly", 8, Gender.Female);
			Person thiemo = new Person("Thiemo", 26, Gender.Male);
			Person nico = new Person("Nico", 35, Gender.Male);
			Person david = new Person("David", 32, Gender.Female);
			
			famTree.add(silvio);
			
			rita.setSpouse(dummii);
			rita.setChildren(silvio);
			rita.setChildren(marina);
			annette.setSpouse(silvio);
			max.setParent(annette);
			tahnee.setParent(silvio); 
			dummi.setSpouse(uta);
			uta.setChildren(annette);
			uta.setChildren(hanko);
			hanko.setChildren(lilly);
			marina.setChildren(nico);
			marina.setChildren(david);
			hanko.setChildren(thiemo);
			
			
			famTree.printNames(uta.getChildren());	
			famTree.printNames(annette.getChildren());
			
			famTree.printNames(max.siblings());
			famTree.printNames(max.aunts(Gender.Male));
			famTree.printNames(max.uncles(Gender.Male));
			famTree.printNames(max.uncles(Gender.Female));
			famTree.printNames(max.aunts(Gender.Female));
			famTree.printNames(max.cousins());
			famTree.printNames(max.grandparents());
			famTree.printNames(uta.grandchildren());
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

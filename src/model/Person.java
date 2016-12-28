package model;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
	
	public enum Gender{Male, Female};
	
	private final String name;
	private int age;
	private Gender gender;
	private Person parent;
	private List<Person> children;
	private Person spouse;
	
	
	
	public Person(String name, int age, Gender gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		children = new ArrayList<Person>();
	}
	
	
	
	
	/**
	 * overwrites equals method to compare identifying attribute
	 * @param person
	 * @return if two objects of type person are equal
	 */
	/*public boolean equals(Person person) {
		if(this.name == person.name) {
			if(this.age == person.age) {
				return true;
			}
		}
		return false;
	}*/
	
	
	
	
	public String getName() {
		return name;
	}
	
	
	public int getAge() {
		return age;
	}
	/**
	 * incAge increases the age of a person
	 */
	public void incAge() {
		age++;
	}
	
	
	public Gender getGender() {
		return gender;
	}
	
	
	public Person getSpouse() {
		return spouse;
	}
	/**
	 * if a spouse is set the Person has to be married. That is done, if it isn't set yet.
	 * afterwards the spouse's spouse is set if it is not yet.
	 * @param spouse
	 */
	public void setSpouse(Person spouse) {
		if(spouse==null) throw new IllegalArgumentException("can not devorce people");
		else if(spouse.isMarried()) throw new IllegalArgumentException("person is already married");
		this.spouse = spouse;
		spouse.spouse = this;
	}
	/**
	 * @return if a person has a spouse or not
	 */
	public boolean isMarried() {
		return (spouse != null);
	}
	
	
	/**
	 * getMother returns all female parents
	 * @return a list of all mothers
	 */
	public List<Person> getMother() {
		List<Person> returnList = new ArrayList<Person>();
		if(parent.gender == Gender.Female) {
			returnList.add(parent);
			if(parent.spouse.gender == Gender.Female) {
				returnList.add(parent.spouse);
			}
		}
		if(returnList.equals(null)) throw new IllegalArgumentException("no mother(s)");
		return returnList;
	}
	/**
	 * getFather returns all male parents
	 * @return a list of all fathers
	 */
	public List<Person> getFather() {
		List<Person> returnList = new ArrayList<Person>();
		if(parent.gender == Gender.Male) {
			returnList.add(parent);
			if(parent.spouse.gender == Gender.Male) {
				returnList.add(parent.spouse);
			}
		}
		if(returnList.equals(null)) throw new IllegalArgumentException("no father(s)");
		return returnList;
	}
	public Person getParent() {
		return parent;
	}

	
	/**
	 * the given person is added, if the variable parents does not equals another person
	 * at least the children relation is added to the given person
	 * @param parent
	 */
	public void setParent(Person parent) {
		if(parent==null) throw new IllegalArgumentException("not an existing person");
		else if(this.parent == null) { 
			this.parent = parent;
			parent.setChildren(this);
		}
		else if(!this.parent.equals(parent)) throw new IllegalArgumentException("a parent is already set");
	}
	
	
	
	
	public List<Person> getChildren() {
		List<Person> returnList = new ArrayList<Person>();
		returnList.addAll(children);
		returnList.addAll(spouse.children);
		return returnList;
	}
	/**
	 * adds the given person to the list of children the person has
	 * the given person is not added if the list already contains it
	 * @param child adding to list
	 */
	public void setChildren(Person child) {
		if(child==null) throw new IllegalArgumentException("not an existing person");
		if(spouse!=null) {
			if(spouse.getChildren().contains(child)) return; //throw new IllegalArgumentException("children already exists");
		}
		if(this.children.contains(child)) return; //throw new IllegalArgumentException("children already exists");
		children.add(child);
		child.setParent(this);
	}	
	
	
	
	/**
	 * sisters and brothers of the given person excluding the given person
	 * @return a list of all siblings 
	 */
	public List<Person> siblings() {
		if(parent==null) throw new IllegalArgumentException("no parent set");
		List<Person> siblings = parent.getChildren();
		for (int i = 0; i < siblings.size(); i++) {
			if(siblings.get(i).equals(this)) {
				siblings.remove(i);
			}
		}
		return siblings;
	}
	
	
	/**
	 * returns a list of the children of all siblings of the parents of the given name
	 * @param person
	 * @return list of cousins
	 */
	public List<Person> cousins() {
		List<Person> cousins = new ArrayList<Person>();
		List<Person> parentSiblings = parent.siblings();
		parentSiblings.addAll(parent.spouse.siblings());
		for (int i = 0; i < parentSiblings.size(); i++) {
			cousins.addAll(parentSiblings.get(i).children);
			if(parentSiblings.get(i).spouse!=null) cousins.addAll(parentSiblings.get(i).spouse.children);
		}
		return cousins;
	}
	
	
	/**
	 * returns a list of the brother(s) of a given person's parents depending on a given relation
	 * @param person
	 * @param relationGender: uncle on male or female side of parents
	 * @return list of all uncles on relationGender side
	 */
	public List<Person> uncles(Gender relationGender) {
		if((this.parent.spouse.gender != relationGender) && (this.parent.gender != relationGender)) throw new IllegalArgumentException("person does not have uncles on this gender side");
		List<Person> uncle = new ArrayList<Person>();
		if(this.parent.gender == relationGender) {
			uncle.addAll(parent.siblings());
		} else if(this.parent.spouse.gender == relationGender) {
			uncle.addAll(parent.spouse.siblings());
		}
		for(int i = 0; i < uncle.size(); i++) {
			if(uncle.get(i).gender == Gender.Female) {
				uncle.remove(i);
			}
		}
		return uncle;
	}
	/**
	 * returns a list of the sister(s) of a given person's parents depending on a given relation
	 * @param person
	 * @param relationGender: aunt on male or female side of parents
	 * @return list of all aunts on relationGender side
	 */
	public List<Person> aunts(Gender relationGender) {
		ArrayList<Person> aunt = new ArrayList<Person>();
		if(parent.gender == relationGender) {
			aunt.addAll(parent.siblings());
		} else if(parent.spouse.gender == relationGender) {
			aunt.addAll(parent.spouse.siblings());
		}
		for (int i = 0; i < aunt.size(); i++) {
			if(aunt.get(i).gender == Gender.Male) {
				aunt.remove(i);
			}
		}
		return aunt;
	}
	
	
	/**
	 * returns a list of the parent's parents of the given person
	 * @param person
	 * @return list of grandparents
	 */
	public List<Person> grandparents() {
		if(this.parent==null) throw new IllegalArgumentException("person does not have a parent");
		else if(this.parent.spouse!=null) {
			if((this.parent.parent==null) && (this.parent.spouse.parent==null)) throw new IllegalArgumentException("person does not have grandparents");
		}
		List<Person> grandparents = new ArrayList<Person>();
		grandparents.add(this.parent.parent);
		grandparents.add(this.parent.parent.spouse);
		if(this.parent.spouse!=null) {
			grandparents.add(this.parent.spouse.parent);
			grandparents.add(this.parent.spouse.parent.spouse);
		}
		return grandparents;
	}
	
	/**
	 * returns a list of the children's children of a given person
	 * @param person
	 * @return list of grandchildren
	 */
	public List<Person> grandchildren() {
		if(this.spouse!=null) {
			if((this.children==null) && (this.spouse.children==null)) return null;
		}
		List<Person> grandchildren = new ArrayList<Person>();
		for(int i = 0; i < children.size(); i++) {
			if(children.get(i).children!=null) grandchildren.addAll(children.get(i).children);
			if(children.get(i).spouse!=null) grandchildren.addAll(children.get(i).spouse.children);
		}
		return grandchildren;
	}
}

package thread;

import java.util.concurrent.atomic.AtomicReference;

public class TeastAtomicReference {

	public static void main(String[] args) {
		AtomicReference<Person> person = new AtomicReference<Person>();
		Person p = new Person("1", 1);
		person.set(p);
		Person p2 = new Person("2", 2);
		person.compareAndSet(p, p2);
		System.out.println(person.get().getName());
		
		Person p22 = new Person("2", 2);
		Person p4 = new Person("4", 4);
		person.compareAndSet(p22, p4);
		System.out.println(person.get().getName());
	}
}
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj){
    	Person person = (Person) obj;
        return (this.getAge() == person.getAge());
    }
}
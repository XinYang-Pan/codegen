package example.gen;

import io.github.xinyangpan.models.person.Person;
import org.springframework.core.convert.converter.Converter;

public class PersonToPersonConverter implements Converter<Person, Person> {

	public Person convert(Person person) {
		if (person == null) {
			return null;
		}
		Person person1 = new Person();
		person1.setName(person.getName());
		person1.setAge(person.getAge());
		person1.setAddress(person.getAddress());
		person1.setNickNames(person.getNickNames());
		person1.setPhoneNumberMap(person.getPhoneNumberMap());
		return person1;
	}

}
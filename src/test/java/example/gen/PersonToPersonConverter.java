package example.gen;

import io.github.xinyangpan.models.person.Person;
import org.springframework.core.convert.converter.Converter;

public class PersonToPersonConverter implements Converter<Person, Person> {

	public Person convert(Person arg0) {
		if (arg0 == null) {
			return null;
		}
		Person person = new Person();
		person.setName(arg0.getName());
		person.setPhoneNumberMap(arg0.getPhoneNumberMap());
		person.setAddress(arg0.getAddress());
		person.setNickNames(arg0.getNickNames());
		person.setAge(arg0.getAge());
		return person;
	}

}
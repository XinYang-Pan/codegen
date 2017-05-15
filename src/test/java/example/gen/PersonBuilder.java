package example.gen;

import org.apache.commons.lang3.builder.Builder;
import io.github.xinyangpan.models.person.Person;

public class PersonBuilder implements Builder<Person> {

	@Override
	public Person build() {
		Person person = new Person();
		person.setName(null);
		person.setAge(0);
		person.setAddress(null);
		person.setNickNames(null);
		person.setPhoneNumberMap(null);
		return person;
	}

}
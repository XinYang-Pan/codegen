package example.gen;

import io.github.xinyangpan.models.person.Person;
import org.apache.commons.lang3.builder.Builder;

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
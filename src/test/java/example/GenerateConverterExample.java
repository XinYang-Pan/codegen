package example;

import java.io.OutputStreamWriter;

import io.github.xinyangpan.codegen.Tools;
import io.github.xinyangpan.models.person.Person;

public class GenerateConverterExample {

	public static void main(String[] args) {
		Tools.generateConverter(Person.class, Person.class, "example.gen", "person", new OutputStreamWriter(System.out));
	}

}

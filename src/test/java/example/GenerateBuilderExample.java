package example;

import java.io.OutputStreamWriter;

import io.github.xinyangpan.codegen.Tools;
import io.github.xinyangpan.models.person.Person;

public class GenerateBuilderExample {

	public static void main(String[] args) {
		Tools.generateBuilder(Person.class, "example.gen", new OutputStreamWriter(System.out));
	}

}

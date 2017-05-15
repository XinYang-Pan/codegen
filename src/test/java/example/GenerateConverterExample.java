package example;

import io.github.xinyangpan.codegen.Tools;
import io.github.xinyangpan.codegen.classfile.type.ClassType;
import io.github.xinyangpan.models.person.Person;

public class GenerateConverterExample {

	public static void main(String[] args) {
		ClassType converterGen = Tools.generateConverter(Person.class, Person.class, "example.gen", "person");
		converterGen.printToConsole();
	}

}

package example;

import io.github.xinyangpan.codegen.Tools;
import io.github.xinyangpan.codegen.classfile.type.ClassType;
import io.github.xinyangpan.models.person.Person;

public class GenerateBuilderExample {

	public static void main(String[] args) {
		ClassType builderGen = Tools.generateBuilder(Person.class, "example.gen");
		builderGen.processToConsole();
	}

}

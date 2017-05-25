package example;

import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.tool.Tools;
import io.github.xinyangpan.models.person.Person;

public class GenerateToStringExample {

	public static void main(String[] args) {
		MethodPart methodPart = Tools.generateToString(Person.class);
		methodPart.processToConsole();
	}

}

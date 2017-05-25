package example;

import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.tool.Tools;
import io.github.xinyangpan.commons.PropertyCollectingType;
import io.github.xinyangpan.models.person.Person;

public class GenerateCopyExample {

	public static void main(String[] args) {
		MethodPart methodPart = Tools.generateCopy(Person.class, PropertyCollectingType.DECLARED_FIELD);
		methodPart.processToConsole();
	}

}

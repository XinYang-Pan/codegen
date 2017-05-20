package example;

import io.github.xinyangpan.codegen.Tools;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class GenerateAddExample {

	public static void main(String[] args) {
		String fieldName = "interfaces";
		String singular = "interface";
		Class<?> type = ClassWrapper.class;
		Tools.generateAddForListField(type, fieldName, singular).processToConsole();
		System.out.println();
		Tools.generateAddsForListField(type, fieldName, singular).processToConsole();
	}

}

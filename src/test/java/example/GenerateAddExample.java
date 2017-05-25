package example;

import io.github.xinyangpan.codegen.tool.Tools;

public class GenerateAddExample {

	public static void main(String[] args) {
		String fieldName = "annotations";
		String singular = "annotation";
		Class<?> type = Class.class;
		Tools.generateAddForListField(type, fieldName, singular).processToConsole();
		System.out.println();
		Tools.generateAddsForListField(type, fieldName, singular).processToConsole();
	}

}

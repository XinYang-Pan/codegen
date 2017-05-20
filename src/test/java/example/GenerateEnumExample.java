package example;

import java.io.Serializable;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.type.EnumType;

public class GenerateEnumExample {

	public static void main(String[] args) throws Exception {
		//
		EnumType enumType = new EnumType();
		enumType.setPackageName("io.github.xinyangpan.test");
		enumType.setName("TestType");
		enumType.setValues(Lists.newArrayList("T1", "T2"));
		enumType.addInterface(Serializable.class);
		enumType.processToConsole();
		enumType.processToFile("./src/test/java");
	}

}

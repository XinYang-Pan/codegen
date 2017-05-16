package example;

import java.io.Serializable;
import java.util.LinkedHashSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.classfile.type.EnumType;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class GenerateEnumExample {

	public static void main(String[] args) throws Exception {
		//
		EnumType enumType = new EnumType();
		enumType.setPackageName("io.github.xinyangpan.test");
		enumType.setName("TestType");
		enumType.setValues(Lists.newArrayList("T1", "T2"));
		LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();
		interfaces.add(ClassWrapper.of(Serializable.class));
		enumType.setInterfaces(interfaces);
		enumType.processToConsole();
		enumType.processToFile("./src/test/java");
	}

}

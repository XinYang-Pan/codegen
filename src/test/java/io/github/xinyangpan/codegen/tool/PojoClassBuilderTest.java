package io.github.xinyangpan.codegen.tool;

import org.junit.Test;

import io.github.xinyangpan.codegen.classfile.type.ClassType;

public class PojoClassBuilderTest {

	@Test
	public void test() {
		ClassType classType = PojoClassBuilder.create()
			.name("example.gen.pojo", "Staff")
			.property("department", String.class)
			.property("depId", int.class)
			.hasToString(true)
			.hasCopy(true)
			.hasPropertyMeta(true)
			.build();
		classType.processToConsole();
	}

}

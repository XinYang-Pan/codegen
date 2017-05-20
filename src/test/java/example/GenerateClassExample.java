package example;

import org.springframework.core.convert.converter.Converter;

import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.type.ClassType;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class GenerateClassExample {

	public static void main(String[] args) throws Exception {
		//
		MethodPart methodPart = new MethodPart("convert", Integer.class, new ParameterPart(String.class, "string"));
		FieldPart fieldPart = new FieldPart(int.class, "id");
		fieldPart.addAnnotationWrapper(new AnnotationWrapper(SuppressWarnings.class, "(\"unused\")"));
		//
		ClassType classType = new ClassType();
		classType.setPackageName("io.github.xinyangpan.test");
		classType.setName("Test");
		classType.addMethodPart(methodPart);
		classType.addFieldPart(fieldPart);
		classType.addInterface(ClassWrapper.of(Converter.class, String.class, Integer.class));
		classType.processToConsole();
		classType.processToFile("./src/test/java");
	}

}

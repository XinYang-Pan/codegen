package example;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.Tools;
import io.github.xinyangpan.codegen.classfile.ClassFile;
import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class GenerateClassFileExample {

	public static void main(String[] args) throws Exception {

		//
		MethodPart methodPart = new MethodPart("convert", ClassWrapper.of(Integer.class), new ParameterPart(String.class, "string"));
		FieldPart fieldPart = new FieldPart(ClassWrapper.of(int.class), "id");
		AnnotationWrapper annotationWrapper = new AnnotationWrapper(SuppressWarnings.class, "(\"unused\")");
		ArrayList<AnnotationWrapper> newArrayList = Lists.newArrayList(annotationWrapper);
		fieldPart.setAnnotationWrapper(newArrayList);
		//
		ClassFile classFile = new ClassFile();
		classFile.setPackageName("io.github.xinyangpan.test");
		classFile.setName("Test");
		classFile.setMethodParts(Lists.newArrayList(methodPart));
		classFile.setFieldParts(Lists.newArrayList(fieldPart));
		LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();
		interfaces.add(ClassWrapper.of(Converter.class, String.class, Integer.class));
		classFile.setInterfaces(interfaces);
		Tools.generateClassFile(classFile, new OutputStreamWriter(System.out));
	}

}
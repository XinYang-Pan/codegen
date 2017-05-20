package io.github.xinyangpan.codegen;

import static org.apache.commons.lang3.StringUtils.capitalize;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.builder.BuilderGenerator;
import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.type.ClassType;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.converter.ConverterGenerator;
import io.github.xinyangpan.codegen.core.CodeGenUtils;
import io.github.xinyangpan.commons.PropertyCollectingType;

public class Tools {

	public static MethodPart generateToString(Class<?> clazz) {
		return generateToString(clazz, PropertyCollectingType.DECLARED_FIELD);
	}

	public static MethodPart generateToString(Class<?> clazz, PropertyCollectingType propertyCollectingType) {
		List<FieldPart> fieldParts = CodeGenUtils.getFieldParts(clazz, propertyCollectingType);
		return generateToString(fieldParts, clazz.getSimpleName());
	}

	public static MethodPart generateToString(List<FieldPart> fieldParts, String classSimpleName) {
		List<String> contents = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(fieldParts)) {
			contents.add("StringBuilder builder = new StringBuilder();");
			contents.add(String.format("builder.append(\"%s [%s=\");", classSimpleName, fieldParts.get(0).getName()));
			contents.add(String.format("builder.append(%s);", fieldParts.get(0).getName()));
			for (int i = 1; i < fieldParts.size(); i++) {
				contents.add(String.format("builder.append(\", %s=\");", fieldParts.get(i).getName()));
				contents.add(String.format("builder.append(%s);", fieldParts.get(i).getName()));
			}
			contents.add("builder.append(\"]\");");
			contents.add("return builder.toString();");
		} else {
			contents.add("return super.toString();");
		}
		//
		MethodPart methodPart = new MethodPart("toString", String.class);
		methodPart.setAnnotationWrappers(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		return methodPart;
	}

	public static MethodPart generateCopy(Class<?> clazz, PropertyCollectingType propertyCollectingType) {
		List<FieldPart> fieldParts = CodeGenUtils.getFieldParts(clazz, propertyCollectingType);
		//
		List<String> contents = Lists.newArrayList();
		String className = clazz.getSimpleName();
		contents.add(String.format("%s copy = new %s();", className, className));
		if (CollectionUtils.isNotEmpty(fieldParts)) {
			for (FieldPart fieldPart : fieldParts) {
				String name = fieldPart.getName();
				contents.add(String.format("copy.%s = this.%s;", name, name));
			}
		}
		contents.add("return copy;");
		//
		MethodPart methodPart = new MethodPart("copy", clazz);
		methodPart.setAnnotationWrappers(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		return methodPart;
	}

	public static MethodPart generateAddForListField(Class<?> type, String fieldName, String singular) {
		String methodName = String.format("add%s", StringUtils.capitalize(singular));
		String content = String.format("this.%s.add(%s);", fieldName, singular);
		MethodPart methodPart = new MethodPart(methodName, void.class, new ParameterPart(type, singular));
		methodPart.setContents(Lists.newArrayList(content));
		return methodPart;
	}

	public static MethodPart generateAddsForListField(Class<?> type, String fieldName, String singular) {
		String methodName = String.format("add%s", StringUtils.capitalize(fieldName));
		//
		List<String> contents = Lists.newArrayList();
		contents.add(CodeGenUtils.format(0, "if (%s == null) {", fieldName));
		contents.add(CodeGenUtils.format(1, "return;"));
		contents.add(CodeGenUtils.format(0, "}"));
		contents.add(CodeGenUtils.format(0, "for (%s %s : %s) {", type.getSimpleName(), singular, fieldName));
		contents.add(CodeGenUtils.format(1, "this.%s.add(%s);", fieldName, singular));
		contents.add(CodeGenUtils.format(0, "}"));
		//
		ParameterPart parameterPart = new ParameterPart(type, fieldName);
		parameterPart.setVarargs(true);
		MethodPart methodPart = new MethodPart(methodName, void.class, parameterPart);
		methodPart.setContents(contents);
		return methodPart;
	}

	public static ClassType generateConverter(Class<?> source, Class<?> target, String packageName, String paramName) {
		//
		ConverterGenerator converterGenerator = new ConverterGenerator(target, source, paramName, PropertyCollectingType.DECLARED_FIELD);
		List<String> contents = converterGenerator.contents();
		//
		MethodPart methodPart = new MethodPart("convert", target, new ParameterPart(source, converterGenerator.getSourceParamName()));
		methodPart.setAnnotationWrappers(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		//
		ClassType classType = new ClassType();
		classType.setPackageName(packageName);
		classType.setName(String.format("%sTo%sConverter", capitalize(source.getSimpleName()), capitalize(target.getSimpleName())));
		classType.addMethodPart(methodPart);
		classType.addInterface(ClassWrapper.of(Converter.class, source, target));
		return classType;
	}

	public static ClassType generateBuilder(Class<?> target, String packageName) {
		//
		BuilderGenerator builderGenerator = new BuilderGenerator(target, null, PropertyCollectingType.DECLARED_FIELD);
		List<String> contents = builderGenerator.contents();
		//
		MethodPart methodPart = new MethodPart("build", target);
		methodPart.setAnnotationWrappers(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		//
		ClassType classType = new ClassType();
		classType.setPackageName(packageName);
		classType.setName(String.format("%sBuilder", capitalize(target.getSimpleName())));
		classType.addMethodPart(methodPart);
		classType.addInterface(ClassWrapper.of(Builder.class, target));
		return classType;
	}

}

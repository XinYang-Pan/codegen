package io.github.xinyangpan.codegen;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.Builder;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedHashSet;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Tools {

	public static MethodPart generateToString(Class<?> clazz) {
		return generateToString(clazz, PropertyCollectingType.DECLARED_FIELD);
	}

	public static MethodPart generateToString(Class<?> clazz, PropertyCollectingType propertyCollectingType) {
		return generateToString(CodeGenUtils.getFieldParts(clazz, propertyCollectingType));
	}

	public static MethodPart generateToString(List<FieldPart> fieldParts) {
		List<String> contents = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(fieldParts)) {
			contents.add("StringBuilder builder = new StringBuilder();");
			contents.add(String.format("builder.append(\"TblPerson [%s=\");", fieldParts.get(0).getName()));
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
		MethodPart methodPart = new MethodPart("toString", ClassWrapper.of(String.class));
		methodPart.setAnnotationWrapper(Lists.newArrayList(new AnnotationWrapper(Override.class)));
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
			for (int i = 0; i < fieldParts.size(); i++) {
				String name = fieldParts.get(i).getName();
				contents.add(String.format("copy.%s = this.%s;", name, name));
			}
		}
		contents.add("return copy;");
		//
		MethodPart methodPart = new MethodPart("copy", ClassWrapper.of(clazz));
		methodPart.setAnnotationWrapper(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		return methodPart;
	}

	public static ClassType generateConverter(Class<?> source, Class<?> target, String packageName, String paramName) {
		//
		ConverterGenerator converterGenerator = new ConverterGenerator(target, source, paramName, PropertyCollectingType.DECLARED_FIELD);
		List<String> contents = converterGenerator.contents();
		//
		MethodPart methodPart = new MethodPart("convert", ClassWrapper.of(target), new ParameterPart(source, converterGenerator.getSourceParamName()));
		methodPart.setAnnotationWrapper(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		//
		ClassType classType = new ClassType();
		classType.setPackageName(packageName);
		classType.setName(String.format("%sTo%sConverter", capitalize(source.getSimpleName()), capitalize(target.getSimpleName())));
		classType.setMethodParts(Lists.newArrayList(methodPart));
		LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();
		interfaces.add(ClassWrapper.of(Converter.class, source, target));
		classType.setInterfaces(interfaces);
		return classType;
	}

	public static ClassType generateBuilder(Class<?> target, String packageName) {
		//
		BuilderGenerator builderGenerator = new BuilderGenerator(target, null, PropertyCollectingType.DECLARED_FIELD);
		List<String> contents = builderGenerator.contents();
		//
		MethodPart methodPart = new MethodPart("build", ClassWrapper.of(target));
		methodPart.setAnnotationWrapper(Lists.newArrayList(new AnnotationWrapper(Override.class)));
		methodPart.setContents(contents);
		//
		ClassType classType = new ClassType();
		classType.setPackageName(packageName);
		classType.setName(String.format("%sBuilder", capitalize(target.getSimpleName())));
		classType.setMethodParts(Lists.newArrayList(methodPart));
		LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();
		interfaces.add(ClassWrapper.of(Builder.class, target));
		classType.setInterfaces(interfaces);
		return classType;
	}

}

package io.github.xinyangpan.codegen;

import static org.apache.commons.lang3.StringUtils.capitalize;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import freemarker.template.Template;
import io.github.xinyangpan.codegen.builder.BuilderGenerator;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.type.AbstractType;
import io.github.xinyangpan.codegen.classfile.type.ClassType;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.converter.ConverterGenerator;
import io.github.xinyangpan.codegen.core.template.TemplateHelper;
import io.github.xinyangpan.codegen.core.template.TemplateType;
import io.github.xinyangpan.commons.PropertyCollectingType;

public class Tools {

	public static void generateCode(AbstractType abstractType, Writer out) {
		try {
			TemplateHelper templateHelper = TemplateHelper.getInstance();
			Template template = templateHelper.getTemplate(TemplateType.convert(abstractType.getType()));
			template.process(abstractType, new OutputStreamWriter(System.out));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void generateConverter(Class<?> source, Class<?> target, String packageName, String paramName, Writer out) {
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
		// 
		generateCode(classType, out);
	}

	public static void generateBuilder(Class<?> target, String packageName, Writer out) {
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
		// 
		generateCode(classType, out);
	}

}

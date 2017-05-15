package io.github.xinyangpan.codegen;

import static org.apache.commons.lang3.StringUtils.capitalize;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import freemarker.template.Template;
import io.github.xinyangpan.codegen.classfile.ClassFile;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.converter.ConverterGenerator;
import io.github.xinyangpan.codegen.core.template.TemplateHelper;
import io.github.xinyangpan.commons.PropertyCollectingType;

public class Tools {

	public static void generateClassFile(ClassFile classFile, Writer out) {
		try {
			TemplateHelper templateHelper = TemplateHelper.getInstance();
			Template template = templateHelper.getClassTemplate();
			template.process(classFile, new OutputStreamWriter(System.out));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void generateConverter(Class<?> from, Class<?> to, String packageName, String paramName, Writer out) {
		// 
		ConverterGenerator converterGenerator = new ConverterGenerator(to, from, paramName, PropertyCollectingType.DECLARED_FIELD);
		List<String> contents = converterGenerator.contents();
		// 
		MethodPart methodPart = new MethodPart("convert", ClassWrapper.of(to), new ParameterPart(from, converterGenerator.getSourceParamName()));
		methodPart.setContents(contents);
		// 
		ClassFile classFile = new ClassFile();
		classFile.setPackageName(packageName);
		classFile.setName(String.format("%sTo%sConverter", capitalize(from.getSimpleName()), capitalize(to.getSimpleName())));
		classFile.setMethodParts(Lists.newArrayList(methodPart));
		LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();
		interfaces.add(ClassWrapper.of(Converter.class, from, to));
		classFile.setInterfaces(interfaces);
		// 
		generateClassFile(classFile, out);
	}

}

package io.github.xinyangpan.codegen;

import static org.apache.commons.lang3.StringUtils.capitalize;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashSet;

import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import freemarker.template.Template;
import io.github.xinyangpan.codegen.classfile.ClassFile;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.clazz.ClassWrapper;
import io.github.xinyangpan.codegen.converter.converter.SetGetMethodBased;
import io.github.xinyangpan.codegen.core.template.TemplateHelper;

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

	public static void generateConverter(Class<?> from, Class<?> to, String packageName, Writer out) {
		// 
		SetGetMethodBased methodBased = new SetGetMethodBased(to, from, null, "arg0");
		// 
		MethodPart methodPart = new MethodPart("convert", ClassWrapper.of(to), ClassWrapper.of(from));
		methodPart.setContents(methodBased.generateMethodContent());
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

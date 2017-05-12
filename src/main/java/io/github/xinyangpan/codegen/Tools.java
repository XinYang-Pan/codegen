package io.github.xinyangpan.codegen;

import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Template;
import io.github.xinyangpan.codegen.classfile.ClassFile;
import io.github.xinyangpan.codegen.core.template.TemplateHelper;

public class Tools {

	public static void generateClassFile(ClassFile classFile, Writer out) {
		try {
			TemplateHelper templateHelper = TemplateHelper.getInstance();
			Template template = templateHelper.getClassTemplate();
			template.process(classFile, new OutputStreamWriter(System.out));;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public static void generateConverter(Class<?> from, Class<?> to, String packageName) {

	}

}

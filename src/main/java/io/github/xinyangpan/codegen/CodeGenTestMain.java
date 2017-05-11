package io.github.xinyangpan.codegen;

import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;

import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.github.xinyangpan.codegen.classfile.ClassFile;
import io.github.xinyangpan.codegen.classfile.method.MethodPart;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;

public class CodeGenTestMain {
	
	public static void main(String[] args) throws Exception {
		Configuration cfg = configuration();
		
		//
		MethodPart methodPart = new MethodPart(ClassWrapper.of(Integer.class), ClassWrapper.of(String.class));
		methodPart.setName("convert");
//		cfg.getTemplate("method.ftlh").process(methodWrapper, new OutputStreamWriter(System.out));;
		
		//
		ClassFile classFile = new ClassFile();
		classFile.setPackageName("io.github.xinyangpan.test");
		classFile.setName("Test");
		classFile.setMethodWrappers(Lists.newArrayList(methodPart));
		LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();
		interfaces.add(ClassWrapper.of(Converter.class, String.class, Integer.class));
		classFile.setInterfaces(interfaces);
		Template temp = cfg.getTemplate("class.ftlh");
		temp.process(classFile, new OutputStreamWriter(System.out));;
	}

	private static Configuration configuration() {
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(CodeGenTestMain.class, "/template");
		cfg.setTemplateLoader(classTemplateLoader);
		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);
		return cfg;
	}
	
}

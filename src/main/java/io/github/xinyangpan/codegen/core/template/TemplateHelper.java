package io.github.xinyangpan.codegen.core.template;

import example.GenerateClassFileExample;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class TemplateHelper {
	private static TemplateHelper instance;
	private static Configuration CONFIGURATION;
	// 
	private Template classTemplate;
	private Template fieldTemplate;
	private Template methodTemplate;

	private TemplateHelper() {
	}
	
	// -----------------------------
	// ----- Static Methods
	// -----------------------------
	
	public static TemplateHelper getInstance() {
		if (instance == null) {
			instance = new TemplateHelper();
			instance.initTemplates(new TemplateVariable());
		}
		return instance;
	}

	public static void initConfiguration(Configuration configuration) {
		CONFIGURATION = configuration;
	}

	private static Configuration configuration() {
		if (CONFIGURATION == null) {
			CONFIGURATION = new Configuration(Configuration.VERSION_2_3_23);
			ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(GenerateClassFileExample.class, "/template");
			CONFIGURATION.setTemplateLoader(classTemplateLoader);
			CONFIGURATION.setDefaultEncoding("UTF-8");
			CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			CONFIGURATION.setLogTemplateExceptions(false);
		}
		return CONFIGURATION;
	}
	
	// -----------------------------
	// ----- Non-Static Methods
	// -----------------------------
	
	public void initTemplates(TemplateVariable templateVariable) {
		try {
			Configuration configuration = configuration();
			classTemplate = configuration.getTemplate(templateVariable.getClassTemplateFile());
			fieldTemplate = configuration.getTemplate(templateVariable.getFieldTemplateFile());
			methodTemplate = configuration.getTemplate(templateVariable.getMethodTemplateFile());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateHelper [classTemplate=");
		builder.append(classTemplate);
		builder.append(", fieldTemplate=");
		builder.append(fieldTemplate);
		builder.append(", methodTemplate=");
		builder.append(methodTemplate);
		builder.append("]");
		return builder.toString();
	}

	public Template getClassTemplate() {
		return classTemplate;
	}

	public void setClassTemplate(Template classTemplate) {
		this.classTemplate = classTemplate;
	}

	public Template getMethodTemplate() {
		return methodTemplate;
	}

	public void setMethodTemplate(Template methodTemplate) {
		this.methodTemplate = methodTemplate;
	}

	public Template getFieldTemplate() {
		return fieldTemplate;
	}

	public void setFieldTemplate(Template fieldTemplate) {
		this.fieldTemplate = fieldTemplate;
	}

}

package io.github.xinyangpan.codegen;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class TemplateHelper {
	private static TemplateHelper instance;
	// 
	private String classTemplateFile = "class.ftlh";
	private String fieldTemplateFile = "field.ftlh";
	private String methodTemplateFile = "method.ftlh";
	private Template classTemplate;
	private Template fieldTemplate;
	private Template methodTemplate;

	private TemplateHelper() {
	}

	public static TemplateHelper getInstance() {
		if (instance == null) {
			instance = new TemplateHelper();
			instance.initTemplates();
		}
		return instance;
	}

	public void initTemplates() {
		try {
			Configuration configuration = configuration();
			classTemplate = configuration.getTemplate(classTemplateFile);
			fieldTemplate = configuration.getTemplate(fieldTemplateFile);
			methodTemplate = configuration.getTemplate(methodTemplateFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Configuration configuration() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateHelper [classTemplateFile=");
		builder.append(classTemplateFile);
		builder.append(", methodTemplateFile=");
		builder.append(methodTemplateFile);
		builder.append(", classTemplate=");
		builder.append(classTemplate);
		builder.append(", methodTemplate=");
		builder.append(methodTemplate);
		builder.append("]");
		return builder.toString();
	}

	public String getClassTemplateFile() {
		return classTemplateFile;
	}

	public void setClassTemplateFile(String classTemplateFile) {
		this.classTemplateFile = classTemplateFile;
	}

	public String getMethodTemplateFile() {
		return methodTemplateFile;
	}

	public void setMethodTemplateFile(String methodTemplateFile) {
		this.methodTemplateFile = methodTemplateFile;
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

package io.github.xinyangpan.codegen.core.template;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import com.google.common.collect.Maps;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class TemplateHelper {
	private static TemplateHelper instance;
	private static Configuration CONFIGURATION;
	// 
	private Map<TemplateType, Template> typeToTemplate = Maps.newEnumMap(TemplateType.class);

	private TemplateHelper() {
	}

	// -----------------------------
	// ----- Static Methods
	// -----------------------------

	public static TemplateHelper getInstance() {
		if (instance == null) {
			instance = new TemplateHelper();
			instance.initTemplates();
		}
		return instance;
	}

	public static void initConfiguration(Configuration configuration) {
		CONFIGURATION = configuration;
	}

	private static Configuration configuration() {
		if (CONFIGURATION == null) {
			CONFIGURATION = new Configuration(Configuration.VERSION_2_3_23);
			CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(TemplateHelper.class, "/template"));
			CONFIGURATION.setDefaultEncoding("UTF-8");
			CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			CONFIGURATION.setLogTemplateExceptions(false);
		}
		return CONFIGURATION;
	}

	// -----------------------------
	// ----- Non-Static Methods
	// -----------------------------

	public void initTemplates() {
		this.initTemplates(Collections.emptyMap());
	}

	public void initTemplates(Map<TemplateType, String> typeTemplateMap) {
		try {
			typeToTemplate.clear();
			Configuration configuration = configuration();
			for (TemplateType templateType : TemplateType.values()) {
				String fileName = ObjectUtils.firstNonNull(typeTemplateMap.get(templateType), templateType.getFileName());
				typeToTemplate.put(templateType, configuration.getTemplate(fileName));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Template getTemplate(TemplateType templateType) {
		return typeToTemplate.get(templateType);
	}

	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateHelper [typeToTemplate=");
		builder.append(typeToTemplate);
		builder.append("]");
		return builder.toString();
	}

	public Map<TemplateType, Template> getTypeToTemplate() {
		return typeToTemplate;
	}

	public void setTypeToTemplate(Map<TemplateType, Template> typeToTemplate) {
		this.typeToTemplate = typeToTemplate;
	}

}

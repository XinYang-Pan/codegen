package io.github.xinyangpan.codegen.core.template;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import com.google.common.collect.Maps;

import io.github.xinyangpan.codegen.classfile.Type;

public enum TemplateType {
	CLASS("class.ftlh"), ENUM("enum.ftlh"), INTERFACE("interface.ftlh"), FIELD("field.ftlh"), METHOD("method.ftlh");
	
	private static Map<Type, TemplateType> typeToTemplateType = Maps.newEnumMap(Type.class);
	
	static {
		typeToTemplateType.put(Type.CLASS, TemplateType.CLASS);
		typeToTemplateType.put(Type.ENUM, TemplateType.ENUM);
		typeToTemplateType.put(Type.INTERFACE, TemplateType.INTERFACE);
	}
	
	public static TemplateType convert(Type type) {
		return requireNonNull(typeToTemplateType.get(type));
	}
	
	private final String fileName;

	private TemplateType(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}

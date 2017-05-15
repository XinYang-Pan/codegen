package io.github.xinyangpan.codegen.core.template;

public enum TemplateType {
	CLASS("class.ftlh"), ENUM("enum.ftlh"), INTERFACE("interface.ftlh"), FIELD("field.ftlh"), METHOD("method.ftlh");

	private final String fileName;

	private TemplateType(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}

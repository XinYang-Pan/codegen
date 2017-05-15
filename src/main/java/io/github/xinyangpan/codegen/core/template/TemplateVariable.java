package io.github.xinyangpan.codegen.core.template;

public class TemplateVariable {
	private String classTemplateFile = "class.ftlh";
	private String enumTemplateFile = "enum.ftlh";
	private String interfaceTemplateFile = "interface.ftlh";
	private String fieldTemplateFile = "field.ftlh";
	private String methodTemplateFile = "method.ftlh";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateVariable [classTemplateFile=");
		builder.append(classTemplateFile);
		builder.append(", enumTemplateFile=");
		builder.append(enumTemplateFile);
		builder.append(", interfaceTemplateFile=");
		builder.append(interfaceTemplateFile);
		builder.append(", fieldTemplateFile=");
		builder.append(fieldTemplateFile);
		builder.append(", methodTemplateFile=");
		builder.append(methodTemplateFile);
		builder.append("]");
		return builder.toString();
	}

	public String getClassTemplateFile() {
		return classTemplateFile;
	}

	public void setClassTemplateFile(String classTemplateFile) {
		this.classTemplateFile = classTemplateFile;
	}

	public String getFieldTemplateFile() {
		return fieldTemplateFile;
	}

	public void setFieldTemplateFile(String fieldTemplateFile) {
		this.fieldTemplateFile = fieldTemplateFile;
	}

	public String getMethodTemplateFile() {
		return methodTemplateFile;
	}

	public void setMethodTemplateFile(String methodTemplateFile) {
		this.methodTemplateFile = methodTemplateFile;
	}

	public String getEnumTemplateFile() {
		return enumTemplateFile;
	}

	public void setEnumTemplateFile(String enumTemplateFile) {
		this.enumTemplateFile = enumTemplateFile;
	}

	public String getInterfaceTemplateFile() {
		return interfaceTemplateFile;
	}

	public void setInterfaceTemplateFile(String interfaceTemplateFile) {
		this.interfaceTemplateFile = interfaceTemplateFile;
	}

}

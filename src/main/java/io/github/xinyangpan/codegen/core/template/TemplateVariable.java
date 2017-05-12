package io.github.xinyangpan.codegen.core.template;

public class TemplateVariable {
	private String classTemplateFile = "class.ftlh";
	private String fieldTemplateFile = "field.ftlh";
	private String methodTemplateFile = "method.ftlh";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateVariable [classTemplateFile=");
		builder.append(classTemplateFile);
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

}

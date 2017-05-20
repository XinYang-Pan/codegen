package io.github.xinyangpan.codegen.classfile.type;

import java.util.List;
import java.util.Set;

import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class EnumType extends AbstractType {

	private List<String> values;

	public EnumType() {
		this.type = Type.ENUM;
	}

	@Override
	public TemplateType templateType() {
		return TemplateType.ENUM;
	}

	public Set<Class<?>> getImports() {
		Set<Class<?>> imports = super.getImports();
		Import.toAdd(imports, interfaces);
		return imports;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnumType [values=");
		builder.append(values);
		builder.append(", accessModifier=");
		builder.append(accessModifier);
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", packageName=");
		builder.append(packageName);
		builder.append(", annotationWrappers=");
		builder.append(annotationWrappers);
		builder.append(", fieldParts=");
		builder.append(fieldParts);
		builder.append(", methodParts=");
		builder.append(methodParts);
		builder.append(", interfaces=");
		builder.append(interfaces);
		builder.append("]");
		return builder.toString();
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}

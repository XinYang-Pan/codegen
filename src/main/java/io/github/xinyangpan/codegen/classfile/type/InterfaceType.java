package io.github.xinyangpan.codegen.classfile.type;

import java.util.Set;

import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class InterfaceType extends AbstractType {

	public InterfaceType() {
		this.type = Type.INTERFACE;
	}

	@Override
	public TemplateType templateType() {
		return TemplateType.INTERFACE;
	}

	public Set<Class<?>> getImports() {
		Set<Class<?>> imports = super.getImports();
		Import.toAdd(imports, interfaces);
		return imports;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterfaceType [accessModifier=");
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

}

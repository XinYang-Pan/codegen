package io.github.xinyangpan.codegen.classfile.type;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class EnumType extends AbstractType {

	private List<String> values;
	private LinkedHashSet<ClassWrapper> interfaces;

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

	public LinkedHashSet<ClassWrapper> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(LinkedHashSet<ClassWrapper> interfaces) {
		this.interfaces = interfaces;
	}

}

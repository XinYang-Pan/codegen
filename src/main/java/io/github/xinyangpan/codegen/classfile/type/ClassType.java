package io.github.xinyangpan.codegen.classfile.type;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.util.List;
import java.util.Set;

import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.classfile.pojo.PojoField;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class ClassType extends AbstractType {

	private ClassWrapper superClass;

	public ClassType() {
		this.type = Type.CLASS;
	}

	public void addPojoField(PojoField pojoField) {
		this.fieldParts.add(pojoField);
		this.methodParts.add(pojoField.getReadMethod());
		this.methodParts.add(pojoField.getWriterMethod());
	}

	public void addPojoFields(List<PojoField> pojoFields) {
		for (PojoField pojoField : emptyIfNull(pojoFields)) {
			this.addPojoField(pojoField);
		}
	}

	@Override
	public TemplateType templateType() {
		return TemplateType.CLASS;
	}

	public Set<Class<?>> getImports() {
		Set<Class<?>> imports = super.getImports();
		Import.toAdd(imports, interfaces);
		Import.toAdd(imports, superClass);
		return imports;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassFile [superClass=");
		builder.append(superClass);
		builder.append(", interfaces=");
		builder.append(interfaces);
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
		builder.append("]");
		return builder.toString();
	}

	public ClassWrapper getSuperClass() {
		return superClass;
	}

	public void setSuperClass(ClassWrapper superClass) {
		this.superClass = superClass;
	}

}

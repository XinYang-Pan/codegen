package io.github.xinyangpan.codegen.classfile.type;

import java.util.LinkedHashSet;
import java.util.Set;

import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;

public class InterfaceType extends AbstractType {

	private ClassWrapper superClass;
	private LinkedHashSet<ClassWrapper> interfaces;

	public InterfaceType() {
		this.type = Type.CLASS;
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

	public LinkedHashSet<ClassWrapper> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(LinkedHashSet<ClassWrapper> interfaces) {
		this.interfaces = interfaces;
	}

}

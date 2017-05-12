package io.github.xinyangpan.codegen.classfile.part;

import io.github.xinyangpan.codegen.classfile.AccessModifier;

public class ClassDeclarePart extends ClassPart {

	protected AccessModifier accessModifier;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassDeclarePart [accessModifier=");
		builder.append(accessModifier);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", annotationWrapper=");
		builder.append(annotationWrapper);
		builder.append("]");
		return builder.toString();
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

}
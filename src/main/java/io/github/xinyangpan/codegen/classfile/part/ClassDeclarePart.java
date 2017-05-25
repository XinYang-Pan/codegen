package io.github.xinyangpan.codegen.classfile.part;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.core.template.HasTemplateType;

public abstract class ClassDeclarePart extends ClassPart implements HasTemplateType {

	protected AccessModifier accessModifier;
	protected boolean isStatic;
	protected boolean isFinal;

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

}

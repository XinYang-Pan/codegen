package io.github.xinyangpan.codegen.classfile.part;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class FieldPart extends ClassDeclarePart {

	public FieldPart() {
		this.accessModifier = AccessModifier.PRIVATE;
	}

	@Override
	public TemplateType templateType() {
		return TemplateType.FIELD;
	}

	public FieldPart(ClassWrapper classWrapper, String name) {
		this();
		this.type = classWrapper;
		this.name = name;
	}

	public FieldPart(Class<?> type, String name) {
		this();
		this.type = ClassWrapper.of(type);
		this.name = name;
	}

}

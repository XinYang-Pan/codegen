package io.github.xinyangpan.codegen.classfile.field;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.ClassPart;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;

public class FieldPart extends ClassPart {
	
	public FieldPart() {
		this.accessModifier = AccessModifier.PRIVATE;
	}
	

	public FieldPart(ClassWrapper type, String name) {
		this();
		this.type = type;
		this.name = name;
	}
	
}

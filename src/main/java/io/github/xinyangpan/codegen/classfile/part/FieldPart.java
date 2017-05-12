package io.github.xinyangpan.codegen.classfile.part;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.clazz.ClassWrapper;

public class FieldPart extends ClassDeclarePart {
	
	public FieldPart() {
		this.accessModifier = AccessModifier.PRIVATE;
	}
	

	public FieldPart(ClassWrapper type, String name) {
		this();
		this.type = type;
		this.name = name;
	}
	
}

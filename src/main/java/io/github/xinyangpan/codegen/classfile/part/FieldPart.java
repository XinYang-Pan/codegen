package io.github.xinyangpan.codegen.classfile.part;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class FieldPart extends ClassDeclarePart {

	private String initValue;
	
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
	
	// -----------------------------
	// ----- Static Methods
	// -----------------------------

	public static FieldPart stringConstant(String fieldName, String initValue) {
		FieldPart fieldPartMeta = new FieldPart(String.class, fieldName);
		fieldPartMeta.setStatic(true);
		fieldPartMeta.setFinal(true);
		fieldPartMeta.setAccessModifier(AccessModifier.PUBLIC);
		fieldPartMeta.setInitValue(String.format("\"%s\"", initValue));
		return fieldPartMeta;
	}
	
	
	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

}

package io.github.xinyangpan.codegen.tool;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import java.util.List;

import org.apache.commons.lang3.builder.Builder;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.pojo.PojoField;
import io.github.xinyangpan.codegen.classfile.type.ClassType;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.commons.functions.Copyable;

public class PojoClassBuilder implements Builder<ClassType> {
	private ClassType classType;
	private List<PojoField> pojoFields = Lists.newArrayList();
	private boolean hasToString;
	private boolean hasCopy;
	private boolean hasPropertyMeta;

	public static PojoClassBuilder create() {
		return new PojoClassBuilder();
	}

	public PojoClassBuilder name(String packageName, String className) {
		classType = new ClassType();
		classType.setPackageName(packageName);
		classType.setName(className);
		return this;
	}

	public PojoClassBuilder property(String propertyName, Class<?> type) {
		PojoField pojoField = new PojoField();
		pojoField.setName(propertyName);
		pojoField.setType(type);
		pojoFields.add(pojoField);
		return this;
	}

	public PojoClassBuilder property(String propertyName, ClassWrapper classWrapper) {
		PojoField pojoField = new PojoField();
		pojoField.setName(propertyName);
		pojoField.setType(classWrapper);
		pojoFields.add(pojoField);
		return this;
	}

	public PojoClassBuilder hasCopy(boolean hasCopy) {
		this.hasCopy = hasCopy;
		return this;
	}

	public PojoClassBuilder hasToString(boolean hasToString) {
		this.hasToString = hasToString;
		return this;
	}

	public PojoClassBuilder hasPropertyMeta(boolean hasPropertyMeta) {
		this.hasPropertyMeta = hasPropertyMeta;
		return this;
	}

	@Override
	public ClassType build() {
		classType.addPojoFields(pojoFields);
		if (hasToString) {
			classType.addMethodPart(Tools.generateToString(classType.getFieldParts(), classType.getName()));
		}
		if (hasCopy) {
			ClassWrapper thisClass = classType.getThisClass();
			ClassWrapper copyable = ClassWrapper.of(Copyable.class);
			copyable.getParameterizedTypes().add(thisClass);
			classType.addInterface(copyable);
			classType.addMethodPart(Tools.generateCopy(classType.getFieldParts(), thisClass));
		}
		if (hasPropertyMeta) {
			for (FieldPart fieldPart : Lists.newArrayList(classType.getFieldParts())) {
				String fieldName = fieldPart.getName();
				String staticFieldName = LOWER_CAMEL.to(UPPER_UNDERSCORE, fieldName);
				classType.addFieldPart(FieldPart.stringConstant(staticFieldName, fieldName));
			}
		}
		return classType;
	}

}

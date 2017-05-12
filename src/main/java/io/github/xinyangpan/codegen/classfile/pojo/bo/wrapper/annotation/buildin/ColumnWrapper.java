package io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.annotation.buildin;

import javax.persistence.Column;

import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.annotation.AnnotationWrapper;

public class ColumnWrapper extends AnnotationWrapper {

	public ColumnWrapper() {
		super(Column.class);
	}

//	@Override
//	public String getDisplayString() {
//		Object columnName = pojoField.getValueMap().get("columnName");
//		Preconditions.checkNotNull(columnName);
//		return String.format("@%s(name = \"%s\")", classWrapper.getName(), columnName);
//	}

}

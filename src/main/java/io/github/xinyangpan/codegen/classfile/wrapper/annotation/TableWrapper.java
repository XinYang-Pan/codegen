package io.github.xinyangpan.codegen.classfile.wrapper.annotation;

import javax.persistence.Table;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;

public class TableWrapper extends AnnotationWrapper {

	public TableWrapper() {
		super(Table.class);
	}

//	@Override
//	public String getDisplayString() {
//		Object tableName = pojoClass.getValueMap().get("tableName");
//		Preconditions.checkNotNull(tableName);
//		return String.format("@%s(name = \"%s\")", classWrapper.getName(), tableName);
//	}

}

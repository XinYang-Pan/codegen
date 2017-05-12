package io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.annotation.buildin;

import javax.persistence.Table;

import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.annotation.AnnotationWrapper;

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

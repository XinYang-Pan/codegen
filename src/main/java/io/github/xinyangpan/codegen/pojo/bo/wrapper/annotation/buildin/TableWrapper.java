package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin;

import javax.persistence.Table;

import com.google.common.base.Preconditions;

import io.github.xinyangpan.codegen.pojo.bo.PojoClass;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.ClassAnnotationWrapper;

public class TableWrapper extends ClassAnnotationWrapper {

	public TableWrapper() {
		super(Table.class);
	}

	@Override
	public String getDisplayString(PojoClass pojoClass) {
		Object tableName = pojoClass.getValueMap().get("tableName");
		Preconditions.checkNotNull(tableName);
		return String.format("@%s(name = \"%s\")", classWrapper.getName(), tableName);
	}

}

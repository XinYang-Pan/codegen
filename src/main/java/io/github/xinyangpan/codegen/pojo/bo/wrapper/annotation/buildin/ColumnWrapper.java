package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin;

import javax.persistence.Column;

import com.google.common.base.Preconditions;

import io.github.xinyangpan.codegen.pojo.bo.PojoField;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.FieldAnnotationWrapper;

public class ColumnWrapper extends FieldAnnotationWrapper {

	public ColumnWrapper() {
		super(Column.class);
	}

	@Override
	public String getDisplayString(PojoField pojoField) {
		Object columnName = pojoField.getValueMap().get("columnName");
		Preconditions.checkNotNull(columnName);
		return String.format("@%s(name = \"%s\")", classWrapper.getName(), columnName);
	}

}

package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;

public class EnumeratedWrapper extends AnnotationWrapper {
	
	private EnumType enumType;
	
	public EnumeratedWrapper(EnumType enumType) {
		super(Enumerated.class);
		this.enumType = enumType;
	}

	@Override
	public String getDisplayString() {
		String extra = "";
		if (enumType != null) {
			extra = String.format("(%s.%s)", EnumType.class.getSimpleName(), enumType.name());
		}
		return String.format("@%s%s", classWrapper.getName(), extra);
	}
	
	@Override
	public List<ClassWrapper> getImports() {
		List<ClassWrapper> classWrappers = Lists.newArrayList();
		classWrappers.addAll(super.getImports());
		if (enumType != null) {
			classWrappers.add(ClassWrapper.of(EnumType.class));
		}
		return classWrappers;
	}
	
}

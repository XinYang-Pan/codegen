package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;

public class GeneratedValueWrapper extends AnnotationWrapper {

	private GenerationType generationType;
	private String name;
	
	public GeneratedValueWrapper(GenerationType generationType, String name) {
		super(GeneratedValue.class);
		this.generationType = generationType;
		this.name = name;
	}

	@Override
	public String getDisplayString() {
		return String.format("@GeneratedValue(strategy=GenerationType.%s, generator=\"%s\")", generationType.name(), name);
	}
	
	@Override
	public List<ClassWrapper> getImports() {
		List<ClassWrapper> classWrappers = Lists.newArrayList();
		classWrappers.addAll(super.getImports());
		classWrappers.add(ClassWrapper.of(GenerationType.class));
		return classWrappers;
	}
	
}

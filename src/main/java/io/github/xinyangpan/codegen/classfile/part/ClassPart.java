package io.github.xinyangpan.codegen.classfile.part;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.clazz.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;

public class ClassPart implements Import {

	protected AccessModifier accessModifier;
	protected String name;
	protected ClassWrapper type;
	protected List<? extends AnnotationWrapper> annotationWrapper;

	public List<String> getAnnotationTexts() {
		List<String> annotationTexts = Lists.newArrayList();
		for (AnnotationWrapper annotationWrapper : emptyIfNull(annotationWrapper)) {
			annotationTexts.add(annotationWrapper.getDisplayString());
		}
		return annotationTexts;
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		Import.toAdd(targetSet, type);
		Import.toAdd(targetSet, annotationWrapper);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append(" [accessModifier=");
		builder.append(accessModifier);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", annotationWrapper=");
		builder.append(annotationWrapper);
		builder.append("]");
		return builder.toString();
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassWrapper getType() {
		return type;
	}

	public void setType(ClassWrapper type) {
		this.type = type;
	}

	public List<? extends AnnotationWrapper> getAnnotationWrapper() {
		return annotationWrapper;
	}

	public void setAnnotationWrapper(List<? extends AnnotationWrapper> annotationWrapper) {
		this.annotationWrapper = annotationWrapper;
	}

}

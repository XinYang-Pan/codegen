package io.github.xinyangpan.codegen.classfile.pojo;

import java.lang.annotation.Annotation;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class PojoField extends FieldPart {
	public enum AnnotationType {
		Field, Get, Set
	}

	private String name;
	private ClassWrapper type;
	private final List<AnnotationWrapper> getterAnnotationWrappers = Lists.newArrayList();
	private final List<AnnotationWrapper> setterAnnotationWrappers = Lists.newArrayList();

	public MethodPart getReadMethod() {
		String prefix = boolean.class == type.getClassIfPossible() ? "is" : "get";
		String methodName = String.format("%s%s", prefix, StringUtils.capitalize(name));
		MethodPart methodPart = new MethodPart(methodName, type);
		methodPart.setAnnotationWrappers(getterAnnotationWrappers);
		methodPart.setContents(Lists.newArrayList(String.format("return this.%s;", name)));
		return methodPart;
	}

	public MethodPart getWriterMethod() {
		String methodName = String.format("set%s", StringUtils.capitalize(name));
		ParameterPart parameterPart = new ParameterPart(type, name);
		MethodPart methodPart = new MethodPart(methodName, void.class, parameterPart);
		methodPart.setAnnotationWrappers(setterAnnotationWrappers);
		methodPart.setContents(Lists.newArrayList(String.format("this.%s = %s;", name, parameterPart.getName())));
		return methodPart;
	}

	// --------------------------------------
	// ---- Getter Setter toString
	// --------------------------------------

	public ClassWrapper getType() {
		return type;
	}

	public void setType(ClassWrapper type) {
		this.type = type;
	}

	public void setType(Class<?> type) {
		this.type = ClassWrapper.of(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PojoField [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", getterAnnotationWrappers=");
		builder.append(getterAnnotationWrappers);
		builder.append(", setterAnnotationWrappers=");
		builder.append(setterAnnotationWrappers);
		builder.append(", accessModifier=");
		builder.append(accessModifier);
		builder.append(", isStatic=");
		builder.append(isStatic);
		builder.append(", annotationWrappers=");
		builder.append(annotationWrappers);
		builder.append("]");
		return builder.toString();
	}

	public List<AnnotationWrapper> getGetterAnnotationWrappers() {
		return getterAnnotationWrappers;
	}

	public List<AnnotationWrapper> getSetterAnnotationWrappers() {
		return setterAnnotationWrappers;
	}

	public void addGetterAnnotationWrapper(AnnotationWrapper getterAnnotationWrapper) {
		this.getterAnnotationWrappers.add(getterAnnotationWrapper);
	}

	public void addGetterAnnotationWrappers(AnnotationWrapper... getterAnnotationWrappers) {
		if (getterAnnotationWrappers == null) {
			return;
		}
		for (AnnotationWrapper getterAnnotationWrapper : getterAnnotationWrappers) {
			this.getterAnnotationWrappers.add(getterAnnotationWrapper);
		}
	}

	public void addGetterAnnotation(Class<? extends Annotation> setterAnnotation) {
		this.addGetterAnnotationWrapper(new AnnotationWrapper(setterAnnotation));
	}
	
	@SafeVarargs
	public final void addGetterAnnotations(Class<? extends Annotation>... setterAnnotations) {
		if (setterAnnotations == null) {
			return;
		}
		for (Class<? extends Annotation> setterAnnotation : setterAnnotations) {
			this.addGetterAnnotation(setterAnnotation);
		}
	}

	public void addSetterAnnotationWrapper(AnnotationWrapper setterAnnotationWrapper) {
		this.setterAnnotationWrappers.add(setterAnnotationWrapper);
	}

	public void addSetterAnnotationWrappers(AnnotationWrapper... setterAnnotationWrappers) {
		if (setterAnnotationWrappers == null) {
			return;
		}
		for (AnnotationWrapper setterAnnotationWrapper : setterAnnotationWrappers) {
			this.setterAnnotationWrappers.add(setterAnnotationWrapper);
		}
	}

	public void addSetterAnnotation(Class<? extends Annotation> setterAnnotation) {
		this.addSetterAnnotationWrapper(new AnnotationWrapper(setterAnnotation));
	}
	
	@SafeVarargs
	public final void addSetterAnnotations(Class<? extends Annotation>... setterAnnotations) {
		if (setterAnnotations == null) {
			return;
		}
		for (Class<? extends Annotation> setterAnnotation : setterAnnotations) {
			this.addSetterAnnotation(setterAnnotation);
		}
	}
	
}

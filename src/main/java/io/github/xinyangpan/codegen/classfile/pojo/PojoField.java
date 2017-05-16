package io.github.xinyangpan.codegen.classfile.pojo;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.part.ParameterPart;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class PojoField {
	public enum AnnotationType {
		Field, Get, Set
	}

	private String name;
	private ClassWrapper type;
	private Map<AnnotationType, List<AnnotationWrapper>> annotationWrapperMap;

	public FieldPart getFieldPart() {
		FieldPart fieldPart = new FieldPart(type, name);
		fieldPart.setAnnotationWrapper(annotationWrapperMap.get(AnnotationType.Field));
		return fieldPart;
	}

	public MethodPart getReadMethod() {
		String prefix = boolean.class == type.getClassIfPossible() ? "is" : "get";
		String methodName = String.format("%s%s", prefix, StringUtils.capitalize(name));
		MethodPart methodPart = new MethodPart(methodName, type);
		methodPart.setAnnotationWrapper(annotationWrapperMap.get(AnnotationType.Get));
		methodPart.setContents(Lists.newArrayList(String.format("return this.%s;", name)));
		return methodPart;
	}

	public MethodPart getWriterMethod() {
		String methodName = String.format("set%s", StringUtils.capitalize(name));
		ParameterPart parameterPart = new ParameterPart(type, name);
		MethodPart methodPart = new MethodPart(methodName, ClassWrapper.of(void.class), parameterPart);
		methodPart.setAnnotationWrapper(annotationWrapperMap.get(AnnotationType.Get));
		methodPart.setContents(Lists.newArrayList(String.format("this.%s = %s;", name, parameterPart.getName())));
		return methodPart;
	}

	public PojoField addAnnotation(AnnotationType annotationType, Class<? extends Annotation> annotation) {
		Preconditions.checkNotNull(annotationType);
		Preconditions.checkNotNull(annotation);
		// 
		AnnotationWrapper wrapper = new AnnotationWrapper(annotation);
		this.addAnnotationWrapper(annotationType, wrapper);
		return this;
	}

	public void addAnnotationWrapper(AnnotationType annotationType, AnnotationWrapper annotationWrapper) {
		Preconditions.checkNotNull(annotationType);
		Preconditions.checkNotNull(annotationWrapper);
		// 
		if (annotationWrapperMap == null) {
			annotationWrapperMap = Maps.newHashMap();
		}
		List<AnnotationWrapper> list = annotationWrapperMap.get(annotationType);
		if (list == null) {
			annotationWrapperMap.put(annotationType, list = Lists.newArrayList());
		}
		list.add(annotationWrapper);
	}

	public List<AnnotationWrapper> getAnnotationWrappers(AnnotationType annotationType) {
		Preconditions.checkNotNull(annotationType);
		// 
		if (annotationWrapperMap == null) {
			return Collections.emptyList();
		}
		List<AnnotationWrapper> list = annotationWrapperMap.get(annotationType);
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}

	public Set<ClassWrapper> getImports() {
		Set<ClassWrapper> classes = Sets.newHashSet();
		if (annotationWrapperMap != null) {
			for (List<AnnotationWrapper> annotationWrappers : annotationWrapperMap.values()) {
				for (AnnotationWrapper annotationWrapper : annotationWrappers) {
					classes.addAll(annotationWrapper.getImports());
				}
			}
		}
		classes.addAll(this.getType().getImports());
		return classes;
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

	public Map<AnnotationType, List<AnnotationWrapper>> getAnnotationWrapperMap() {
		return annotationWrapperMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PojoField [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", annotationWrapperMap=");
		builder.append(annotationWrapperMap);
		builder.append("]");
		return builder.toString();
	}

}
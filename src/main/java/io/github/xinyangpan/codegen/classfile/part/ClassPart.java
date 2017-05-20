package io.github.xinyangpan.codegen.classfile.part;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;

public class ClassPart implements Import {

	protected ClassWrapper type;
	protected String name;
	protected List<AnnotationWrapper> annotationWrappers;

	// -----------------------------
	// ----- Constructors
	// -----------------------------
	public ClassPart() {
	}

	public ClassPart(ClassWrapper type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	public ClassPart(Class<?> type, String name) {
		super();
		this.type = ClassWrapper.of(type);
		this.name = name;
	}

	public ClassPart(ClassWrapper type, String name, List<AnnotationWrapper> annotationWrappers) {
		super();
		this.type = type;
		this.name = name;
		this.annotationWrappers = annotationWrappers;
	}

	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

	public List<String> getAnnotationTexts() {
		List<String> annotationTexts = Lists.newArrayList();
		for (AnnotationWrapper annotationWrapper : emptyIfNull(annotationWrappers)) {
			annotationTexts.add(annotationWrapper.getDisplayString());
		}
		return annotationTexts;
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		Import.toAdd(targetSet, type);
		Import.toAdd(targetSet, annotationWrappers);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassPart [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", annotationWrappers=");
		builder.append(annotationWrappers);
		builder.append("]");
		return builder.toString();
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

	public List<AnnotationWrapper> getAnnotationWrappers() {
		return annotationWrappers;
	}

	public void setAnnotationWrappers(List<AnnotationWrapper> annotationWrappers) {
		this.annotationWrappers = annotationWrappers;
	}

	public void addAnnotationWrapper(AnnotationWrapper annotationWrapper) {
		if (this.annotationWrappers == null) {
			this.annotationWrappers = Lists.newArrayList();
		}
		this.annotationWrappers.add(annotationWrapper);
	}

	public void addAnnotationWrappers(AnnotationWrapper... annotationWrappers) {
		if (annotationWrappers == null) {
			return;
		}
		for (AnnotationWrapper annotationWrapper : annotationWrappers) {
			this.addAnnotationWrapper(annotationWrapper);
		}
	}

	public void addAnnotation(Class<? extends Annotation> annotation) {
		this.addAnnotationWrapper(new AnnotationWrapper(annotation));
	}

	@SafeVarargs
	public final void addAnnotations(Class<? extends Annotation>... annotations) {
		if (annotations == null) {
			return;
		}
		for (Class<? extends Annotation> annotation : annotations) {
			this.addAnnotation(annotation);
		}
	}
}

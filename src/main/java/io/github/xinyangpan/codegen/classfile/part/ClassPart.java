package io.github.xinyangpan.codegen.classfile.part;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.pojo.bo.wrapper.clazz.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;

public class ClassPart implements Import {

	protected ClassWrapper type;
	protected String name;
	protected List<? extends AnnotationWrapper> annotationWrapper;

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

	public ClassPart(ClassWrapper type, String name, List<? extends AnnotationWrapper> annotationWrapper) {
		super();
		this.type = type;
		this.name = name;
		this.annotationWrapper = annotationWrapper;
	}

	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

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
		StringBuilder builder = new StringBuilder();
		builder.append("ClassPart [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", annotationWrapper=");
		builder.append(annotationWrapper);
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

	public List<? extends AnnotationWrapper> getAnnotationWrapper() {
		return annotationWrapper;
	}

	public void setAnnotationWrapper(List<? extends AnnotationWrapper> annotationWrapper) {
		this.annotationWrapper = annotationWrapper;
	}

}

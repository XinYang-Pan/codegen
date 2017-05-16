package io.github.xinyangpan.codegen.classfile.wrapper;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Function;

import io.github.xinyangpan.codegen.core.Import;

public class AnnotationWrapper implements Import {
	protected final ClassWrapper classWrapper;
	// should be BiFunction in JDK 8
	protected final Function<Object, String> function;
	protected Object source;
	
	// -----------------------------
	// ----- Constructors
	// -----------------------------
	public AnnotationWrapper(Class<? extends Annotation> annotationClass) {
		this(annotationClass, (Function<Object, String>)null);
	}
	
	public AnnotationWrapper(Class<? extends Annotation> annotationClass, final String displayValue) {
		this(annotationClass, new Function<Object, String>(){
			@Override
			public String apply(Object input) {
				return displayValue;
			}
		});
	}

	public AnnotationWrapper(Class<? extends Annotation> annotationClass, Function<Object, String> function) {
		this(ClassWrapper.of(annotationClass), function);
	}

	public AnnotationWrapper(ClassWrapper classWrapper) {
		this(classWrapper, null);
	}

	public AnnotationWrapper(ClassWrapper classWrapper, Function<Object, String> function) {
		super();
		this.classWrapper = classWrapper;
		this.function = function;
	}

	// -----------------------------
	// ----- Others
	// -----------------------------
	public String getDisplayString() {
		if (function == null) {
			return getAnnotationClassPart();
		} else {
			return String.format("@%s%s", this.classWrapper.getName(), function.apply(source));
		}
	}

	protected String getAnnotationClassPart() {
		return String.format("@%s", classWrapper.getName());
	}

	public ClassWrapper getClassWrapper() {
		return classWrapper;
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		classWrapper.addImportsTo(targetSet);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnnotationWrapper [classWrapper=");
		builder.append(classWrapper);
		builder.append(", function=");
		builder.append(function);
		builder.append(", source=");
		builder.append(source);
		builder.append("]");
		return builder.toString();
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

}

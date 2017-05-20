package io.github.xinyangpan.codegen.classfile.type;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.HasTemplateType;

public abstract class AbstractType implements HasTemplateType {
	protected AccessModifier accessModifier;
	protected Type type;
	protected String name;
	protected String packageName;

	protected final List<AnnotationWrapper> annotationWrappers = Lists.newArrayList();
	protected final List<FieldPart> fieldParts = Lists.newArrayList();
	protected final List<MethodPart> methodParts = Lists.newArrayList();
	protected final LinkedHashSet<ClassWrapper> interfaces = Sets.newLinkedHashSet();

	public AbstractType() {
		this.accessModifier = AccessModifier.PUBLIC;
	}

	@Override
	public String getRelativeFileName() {
		String fullName = this.getFullName().replaceAll("\\.", "/");
		String fileName = String.format("%s.java", fullName);
		return fileName;
	}

	public Set<Class<?>> getImports() {
		Set<Class<?>> imports = Sets.newHashSet();
		Import.toAdd(imports, annotationWrappers);
		Import.toAdd(imports, methodParts);
		Import.toAdd(imports, fieldParts);
		return imports;
	}

	public List<String> getFieldTexts() {
		try {
			List<String> texts = Lists.newArrayList();
			for (FieldPart part : emptyIfNull(fieldParts)) {
				texts.add(part.processToString());
			}
			return texts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getMethodTexts() {
		try {
			List<String> texts = Lists.newArrayList();
			for (MethodPart part : emptyIfNull(methodParts)) {
				texts.add(part.processToString());
			}
			return texts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getFullName() {
		return String.format("%s.%s", packageName, name);
	}

	// -----------------------------
	// ----- Get Set ToString HashCode Equals
	// -----------------------------

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<AnnotationWrapper> getAnnotationWrappers() {
		return annotationWrappers;
	}

	public void addAnnotationWrapper(AnnotationWrapper annotationWrapper) {
		this.annotationWrappers.add(annotationWrapper);
	}

	public void addAnnotationWrappers(AnnotationWrapper... annotationWrappers) {
		if (annotationWrappers == null) {
			return;
		}
		for (AnnotationWrapper annotationWrapper : annotationWrappers) {
			this.annotationWrappers.add(annotationWrapper);
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

	public List<FieldPart> getFieldParts() {
		return fieldParts;
	}

	public void addFieldPart(FieldPart fieldPart) {
		this.fieldParts.add(fieldPart);
	}

	public void addFieldParts(FieldPart... fieldParts) {
		if (fieldParts == null) {
			return;
		}
		for (FieldPart fieldPart : fieldParts) {
			this.fieldParts.add(fieldPart);
		}
	}

	public List<MethodPart> getMethodParts() {
		return methodParts;
	}

	public void addMethodPart(MethodPart methodPart) {
		this.methodParts.add(methodPart);
	}

	public void addMethodParts(MethodPart... methodParts) {
		if (methodParts == null) {
			return;
		}
		for (MethodPart methodPart : methodParts) {
			this.methodParts.add(methodPart);
		}
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public LinkedHashSet<ClassWrapper> getInterfaces() {
		return interfaces;
	}

	public void addInterface(ClassWrapper interface_) {
		this.interfaces.add(interface_);
	}

	public void addInterfaces(ClassWrapper... interfaces) {
		if (interfaces == null) {
			return;
		}
		for (ClassWrapper interface_ : interfaces) {
			this.interfaces.add(interface_);
		}
	}

	public void addInterface(Class<?> interface_) {
		this.addInterface(ClassWrapper.of(interface_));
	}

	public void addInterfaces(Class<?>... interfaces) {
		if (interfaces == null) {
			return;
		}
		for (Class<?> interface_ : interfaces) {
			this.addInterface(interface_);
		}
	}

}

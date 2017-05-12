package io.github.xinyangpan.codegen.classfile;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.io.StringWriter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateHelper;

public class ClassFile {
	private String name;
	private String packageName;
	private ClassWrapper superClass;
	private LinkedHashSet<ClassWrapper> interfaces;
	private List<AnnotationWrapper> annotationWrappers;
	private List<FieldPart> fieldParts;
	private List<MethodPart> methodParts;

	public Collection<Class<?>> getImports() {
		Set<Class<?>> imports = Sets.newHashSet();
		Import.toAdd(imports, interfaces);
		Import.toAdd(imports, annotationWrappers);
		Import.toAdd(imports, superClass);
		Import.toAdd(imports, methodParts);
		Import.toAdd(imports, annotationWrappers);
		return imports;
	}

	public List<String> getFieldTexts() {
		try {
			List<String> texts = Lists.newArrayList();
			for (FieldPart part : emptyIfNull(fieldParts)) {
				StringWriter sw = new StringWriter();
				TemplateHelper.getInstance().getFieldTemplate().process(part, sw);
				texts.add(sw.toString());
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
				StringWriter sw = new StringWriter();
				TemplateHelper.getInstance().getMethodTemplate().process(part, sw);
				texts.add(sw.toString());
			}
			return texts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassFile [name=");
		builder.append(name);
		builder.append(", packageName=");
		builder.append(packageName);
		builder.append(", superClass=");
		builder.append(superClass);
		builder.append(", interfaces=");
		builder.append(interfaces);
		builder.append(", annotationWrappers=");
		builder.append(annotationWrappers);
		builder.append(", fieldParts=");
		builder.append(fieldParts);
		builder.append(", methodParts=");
		builder.append(methodParts);
		builder.append("]");
		return builder.toString();
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

	public ClassWrapper getSuperClass() {
		return superClass;
	}

	public void setSuperClass(ClassWrapper superClass) {
		this.superClass = superClass;
	}

	public LinkedHashSet<ClassWrapper> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(LinkedHashSet<ClassWrapper> interfaces) {
		this.interfaces = interfaces;
	}

	public List<AnnotationWrapper> getAnnotationWrappers() {
		return annotationWrappers;
	}

	public void setAnnotationWrappers(List<AnnotationWrapper> annotationWrappers) {
		this.annotationWrappers = annotationWrappers;
	}

	public List<FieldPart> getFieldParts() {
		return fieldParts;
	}

	public void setFieldParts(List<FieldPart> fieldParts) {
		this.fieldParts = fieldParts;
	}

	public List<MethodPart> getMethodParts() {
		return methodParts;
	}

	public void setMethodParts(List<MethodPart> methodParts) {
		this.methodParts = methodParts;
	}

}

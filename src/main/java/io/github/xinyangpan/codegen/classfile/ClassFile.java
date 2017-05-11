package io.github.xinyangpan.codegen.classfile;

import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.TemplateHelper;
import io.github.xinyangpan.codegen.classfile.method.MethodWrapper;
import io.github.xinyangpan.codegen.pojo.bo.PojoClass;
import io.github.xinyangpan.codegen.pojo.bo.PojoField;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;

public class ClassFile {
	private String name;
	private String packageName;
	private ClassWrapper superClass;
	private LinkedHashSet<ClassWrapper> interfaces;
	private List<AnnotationWrapper<PojoClass>> annotationWrappers;
	private List<PojoField> pojoFields;
	private List<MethodWrapper> methodWrappers;

	public Set<ClassWrapper> getImports() {
		Set<ClassWrapper> classes = Sets.newHashSet();
		if (interfaces != null) {
			for (ClassWrapper interface_ : interfaces) {
				classes.addAll(interface_.getImports());
			}
		}
		if (annotationWrappers != null) {
			for (AnnotationWrapper<PojoClass> annotationWrapper : annotationWrappers) {
				classes.addAll(annotationWrapper.getImports());
			}
		}
		if (pojoFields != null) {
			for (PojoField pojoField : pojoFields) {
				classes.addAll(pojoField.getImports());
			}
		}
		if (superClass != null) {
			classes.addAll(superClass.getImports());
		}
		return classes.stream().filter(classWrapper -> !classWrapper.getPackageName().startsWith("java.lang")).collect(Collectors.toSet());
	}

	public List<String> getMethodTexts() {
		try {
			List<String> methodTexts = Lists.newArrayList();
			for (MethodWrapper methodWrapper : methodWrappers) {
				StringWriter sw = new StringWriter();
				TemplateHelper.getInstance().getMethodTemplate().process(methodWrapper, sw);
				methodTexts.add(sw.toString());
			}
			return methodTexts;
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
		builder.append(", pojoFields=");
		builder.append(pojoFields);
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

	public List<AnnotationWrapper<PojoClass>> getAnnotationWrappers() {
		return annotationWrappers;
	}

	public void setAnnotationWrappers(List<AnnotationWrapper<PojoClass>> annotationWrappers) {
		this.annotationWrappers = annotationWrappers;
	}

	public List<PojoField> getPojoFields() {
		return pojoFields;
	}

	public void setPojoFields(List<PojoField> pojoFields) {
		this.pojoFields = pojoFields;
	}

	public List<MethodWrapper> getMethodWrappers() {
		return methodWrappers;
	}

	public void setMethodWrappers(List<MethodWrapper> methods) {
		this.methodWrappers = methods;
	}

}

package io.github.xinyangpan.codegen.classfile;

import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.TemplateHelper;
import io.github.xinyangpan.codegen.classfile.method.MethodPart;
import io.github.xinyangpan.codegen.pojo.bo.PojoClass;
import io.github.xinyangpan.codegen.pojo.bo.PojoField;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;
import io.github.xinyangpan.codegen.util.Import;

public class ClassFile {
	private String name;
	private String packageName;
	private ClassWrapper superClass;
	private LinkedHashSet<ClassWrapper> interfaces;
	private List<AnnotationWrapper<PojoClass>> annotationWrappers;
	private List<PojoField> pojoFields;
	private List<MethodPart> methodParts;

	public Set<Class<?>> getImports() {
		Set<Class<?>> imports = Sets.newHashSet();

		Import.toAdd(imports, interfaces);
		Import.toAdd(imports, annotationWrappers);
		Import.toAdd(imports, superClass);
		
		Import.toAdd(imports, methodParts);
		Import.toAdd(imports, annotationWrappers);
		
//		if (pojoFields != null) {
//			for (PojoField pojoField : pojoFields) {
//				Import.toAdd(imports, pojoField.);
//				classes.addAll(pojoField.getImports());
//			}
//		}
		return imports;
	}
	
	public List<String> getMethodTexts() {
		try {
			List<String> methodTexts = Lists.newArrayList();
			for (MethodPart methodPart : methodParts) {
				StringWriter sw = new StringWriter();
				TemplateHelper.getInstance().getMethodTemplate().process(methodPart, sw);
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

	public List<MethodPart> getMethodWrappers() {
		return methodParts;
	}

	public void setMethodWrappers(List<MethodPart> methods) {
		this.methodParts = methods;
	}

}

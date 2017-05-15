package io.github.xinyangpan.codegen.classfile.type;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.Type;
import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.codegen.classfile.part.MethodPart;
import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateHelper;

public class AbstractType {
	protected AccessModifier accessModifier;
	protected Type type;
	protected String name;
	protected String packageName;

	protected List<AnnotationWrapper> annotationWrappers;
	protected List<FieldPart> fieldParts;
	protected List<MethodPart> methodParts;

	public AbstractType() {
		this.accessModifier = AccessModifier.PUBLIC;
	}

	public Set<Class<?>> getImports() {
		Set<Class<?>> imports = Sets.newHashSet();
		Import.toAdd(imports, annotationWrappers);
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

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}
}

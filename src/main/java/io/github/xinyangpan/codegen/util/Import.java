package io.github.xinyangpan.codegen.util;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;

import com.google.common.base.Preconditions;

public interface Import {

	void addImportsTo(Set<Class<?>> imports);

	public static void toAdd(Set<Class<?>> targetSet, Collection<? extends Import> imports) {
		Preconditions.checkNotNull(targetSet);
		if (imports == null) {
			return;
		}
		for (Import import_ : imports) {
			import_.addImportsTo(targetSet);
		}
	}

	public static void toAdd(Set<Class<?>> targetSet, Import import_) {
		Preconditions.checkNotNull(targetSet);
		if (import_ == null) {
			return;
		}
		import_.addImportsTo(targetSet);
	}

	public static boolean isNeedImport(Class<?> importClass) {
		if (importClass == null) {
			return false;
		}
		if (ClassUtils.isPrimitiveOrWrapper(importClass)) {
			return false;
		}
		if (importClass.isArray()) {
			return false;
		}
		// 
		Package package1 = importClass.getPackage();
		String packageName = package1.getName();
		if (packageName == null) {
			return false;
		}
		if (packageName.startsWith("java.lang")) {
			return false;
		}
		return true;
	}

}

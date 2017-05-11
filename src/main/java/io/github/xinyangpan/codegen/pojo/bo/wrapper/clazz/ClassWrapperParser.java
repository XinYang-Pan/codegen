package io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz;

import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import io.github.xinyangpan.commons.ContentPiece;
import io.github.xinyangpan.commons.StringUtils;

class ClassWrapperParser {

	static ClassWrapper parse(String classNameWithParameterizedTypes) {
		ContentPiece texts = StringUtils.splitContent(classNameWithParameterizedTypes, '<', '>');
		String parameterizedTypeStr = texts.getTarget();
		if (parameterizedTypeStr == null) {
			return new ClassWrapper(texts.getBefore(), null);
		}
		Iterable<String> parameterizedTypes = Splitter.on(',').trimResults().split(parameterizedTypeStr);
		//
		List<ClassWrapper> classWrappers = Lists.newArrayList();
		for (String parameterizedType : parameterizedTypes) {
			classWrappers.add(parse(parameterizedType));
		}
		return new ClassWrapper(texts.getBefore(), classWrappers);
	}

}

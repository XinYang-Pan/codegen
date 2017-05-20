<#list annotationTexts as annotationText>
	${annotationText}
</#list>
	${accessModifier.text} <#if static>static </#if>${type.typedName} ${name}(<#list parameters as parameter>${parameter.type.typedName}<#if parameter.varargs>...</#if> <#if parameter.name??>${parameter.name}<#else>arg${parameter?index}</#if><#sep>, </#list>)<#if throwClass??>throw ${throwClass}</#if> {
<#list contents as content>
		${content}
</#list>
	}
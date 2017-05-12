<#list annotationTexts as annotationText>
	${annotationText}
</#list>
	${accessModifier.string} ${type.typedName} ${name}(<#list parameters as parameter>${parameter.typedName} arg${parameter?index}<#sep>, </#list>)<#if throwClass??>throw ${throwClass}</#if> {
<#list contents as content>
		${content}
</#list>
	}
<#list annotationTexts as annotationText>
	${annotationText}
</#list>
	${accessModifier.text} <#if static>static </#if>${type.typedName} ${name};
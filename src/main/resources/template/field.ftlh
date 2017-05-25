<#list annotationTexts as annotationText>
	${annotationText}
</#list>
	${accessModifier.text} <#if static>static </#if><#if final>final </#if>${type.typedName} ${name}<#if initValue??> = ${initValue}</#if>;
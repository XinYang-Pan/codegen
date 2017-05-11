<#if annotations??>
	${annotations}
</#if>
	${accessModifier.string} ${returnClass.name} ${name}(<#list parameters as parameter>${parameter.typedName} arg${parameter?index}<#sep>, </#list>)<#if throwClass??>throw ${throwClass}</#if> {
<#if content??>
		${content}
</#if>
	}
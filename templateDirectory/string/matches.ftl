<#assign res = "foo"?matches("f.?o")>
<#if res>Matches.<#else>Does not match.</#if>
Matching sub-strings:
<#list res as m>
- ${m}
</#list>  

<#assign res = "aa/rx; ab/r;"?matches("(\\w[^/]+)/([^;]+);")>
<#list res as m>
- ${m} is ${m?groups[1]} per ${m?groups[2]}
</#list>  
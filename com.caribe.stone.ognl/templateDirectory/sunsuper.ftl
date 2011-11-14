Map<String, Object> map = new HashMap<String, Object>();
<#list key as x>
map.put("${x}","${x}+${x_index+1}");
</#list>  

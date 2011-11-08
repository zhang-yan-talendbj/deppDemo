<<<<<<< HEAD
<#include "include.ftl"> 

${manager} ${managerRefer} = ${manager}.getInstance();
${recommendManager} ${recommendManagerRefer} = ${recommendManager}.getInstance();
	
List list = arm.selectTopByRecommend(${top}, siteId, moduleId);
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:10px; padding-bottom:10px;">
<tr>
<%

for(int i=0; i<Math.max(StringUtil.size(list),${top}); i++) {
		${ArticleRecommend} ${ArticleRecommendRefer} = null;
		${Article} ${Article?lower_case} = null;
		if(i<StringUtil.size(list)) {
			ar = (${ArticleRecommend})list.get(i);
			${Article?lower_case} = am.getById(ar.getArticleId());
		}
		if(i==StringUtil.size(list)) {
			${Article?lower_case} = null;
		}
		
		if(${Article?lower_case}!=null) {
			%>
			<tr>
			<td>
			<%=${Article?lower_case}.getImageLink().setDefaultImage("http://file.ibicn.com/www/200810/23/ad.jpg").setImageSize(${x},${y}).setTarget("_blank")%>
			</td>
			</tr>
			<%
		} else {
		%>
			<td><img src="http://file.ibicn.com/www/200902/04/gg-2.gif" width="${x}" height="${y}" alt="广告招商中"/></td>
		<%}
		
	}


%>
	</tr>
</table>
=======
<#include "include.ftl"> 

${manager} ${managerRefer} = ${manager}.getInstance();
${recommendManager} ${recommendManagerRefer} = ${recommendManager}.getInstance();
	
List list = arm.selectTopByRecommend(${top}, siteId, moduleId);
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:10px; padding-bottom:10px;">
<tr>
<%

for(int i=0; i<Math.max(StringUtil.size(list),${top}); i++) {
		${ArticleRecommend} ${ArticleRecommendRefer} = null;
		${Article} ${Article?lower_case} = null;
		if(i<StringUtil.size(list)) {
			ar = (${ArticleRecommend})list.get(i);
			${Article?lower_case} = am.getById(ar.getArticleId());
		}
		if(i==StringUtil.size(list)) {
			${Article?lower_case} = null;
		}
		
		if(${Article?lower_case}!=null) {
			%>
			<tr>
			<td>
			<%=${Article?lower_case}.getImageLink().setDefaultImage("http://file.ibicn.com/www/200810/23/ad.jpg").setImageSize(${x},${y}).setTarget("_blank")%>
			</td>
			</tr>
			<%
		} else {
		%>
			<td><img src="http://file.ibicn.com/www/200902/04/gg-2.gif" width="${x}" height="${y}" alt="广告招商中"/></td>
		<%}
		
	}


%>
	</tr>
</table>
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60

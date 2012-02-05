<html>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<head>
	<f:loadBundle basename="com.corejsf.messages" var="msgs" />
	<title><h:outputText value="#{msgs.windowTitle}" /></title>
	</head>
	<body>
	<h:outputText value="#{msgs.pageTitle}" />
	<p><h:form>
		<h:dataTable value="#{tableData.names}" var="name">
			<h:column>
				<h:outputText value="#{name.last}" />
				<f:verbatim>,</f:verbatim>
			</h:column>
			<h:column>
				<h:outputText value="#{name.first}" />
			</h:column>
			<h:column>
				<h:commandButton actionListener="#{tableData.removeOneRecord}"
					value="remove"></h:commandButton>
				<f:attribute name="nameId" value="#{name.id}" />
			</h:column>
			<h:column>
				<h:selectBooleanCheckbox value="#{name.markedForDeletion}"></h:selectBooleanCheckbox>
			</h:column>
		</h:dataTable>
		
		<h:commandButton action="#{tableData.deleteNames}"></h:commandButton>
		
	</h:form>
	</body>
</f:view>
</html>









































<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Contacts</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css" />
</head>

<body>
<f:view>
	<h4>Contacts</h4>

	<h:messages infoClass="infoClass" errorClass="errorClass"
		layout="table" globalOnly="true" />

	<h:form>
		<h:commandLink binding="#{contactController.addNewCommand}"
			action="#{contactController.addNew}" value="Add New..." />
	</h:form>

	<h:form binding="#{contactController.form}" rendered="false"
		styleClass="form">

		<h:inputHidden value="#{contactController.contact.id}" />
		<h:panelGrid columns="6">
			<%-- First Name --%>
			<h:outputLabel value="First Name" for="firstName" accesskey="f" />
			<h:inputText id="firstName" label="First Name" required="true"
				value="#{contactController.contact.firstName}" size="10" />
			<h:message for="firstName" errorClass="errorClass" />

			<%-- Last Name --%>
			<h:outputLabel value="Last Name" for="lastName" accesskey="l" />
			<h:inputText id="lastName" required="true"
				value="#{contactController.contact.lastName}" size="15" />
			<h:message for="lastName" errorClass="errorClass" />
		</h:panelGrid>
		<h:panelGroup>
			<h:commandButton binding="#{contactController.persistCommand}"
				action="#{contactController.persist}" />
		</h:panelGroup>
	</h:form>
	<h:form>
		<h:dataTable value="#{contactController.contacts}" var="contact"
			rowClasses="oddRow, evenRow"
			rendered="#{not empty contactController.contacts}"
			styleClass="contactTable" headerClass="headerTable"
			columnClasses="normal,centered">
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Name" />
					</h:column>
				</f:facet>
				<h:outputText value="#{contact.lastName}, #{contact.firstName}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Action" />
					</h:column>
				</f:facet>
				<h:panelGrid columns="2">
					<h:commandLink value="remove" action="#{contactController.remove}">
						<f:setPropertyActionListener target="#{contactController.selectedContact}" value="#{contact}" />
					</h:commandLink>
					<h:commandLink value="edit" action="#{contactController.read}">
						<f:setPropertyActionListener target="#{contactController.selectedContact}" value="#{contact}" />
					</h:commandLink>
				</h:panelGrid>
			</h:column>

		</h:dataTable>
	</h:form>
</f:view>
</body>

</html>
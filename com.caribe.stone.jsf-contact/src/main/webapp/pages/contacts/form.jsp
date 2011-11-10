<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:subview id="form">

	<h:form binding="#{contactController.form}" rendered="false"
		styleClass="form">

		<h4>Contact Form</h4>


		<h:inputHidden value="#{contactController.contact.id}" />
		<h:panelGrid columns="6" columnClasses="input">
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

			<%-- Group --%>
			<h:outputLabel value="Group" for="group" accesskey="g" />
			<h:selectOneMenu id="group" validatorMessage="required"
				value="#{contactController.selectedGroupId}">
				<f:selectItems value="#{contactController.groups}" />
				<f:validateLongRange minimum="1" />
			</h:selectOneMenu>
			<h:message for="group" errorClass="errorClass" />

			<%-- Type --%>
			<h:outputLabel value="Type" for="type" accesskey="t" />
			<%--  
		<h:selectOneListbox id="type" 
		                 value="#{contactController.contact.type}">
			<f:selectItem itemValue="PERSONAL" itemLabel="personal"/>
			<f:selectItem itemValue="BUSINESS" itemLabel="business"/>
		</h:selectOneListbox>
		--%>
			<h:selectOneRadio id="type" value="#{contactController.contact.type}">
				<f:selectItem itemValue="PERSONAL" itemLabel="personal" />
				<f:selectItem itemValue="BUSINESS" itemLabel="business" />
			</h:selectOneRadio>
			<h:message for="type" errorClass="errorClass" />

			<%-- active --%>
			<h:outputLabel value="Active" for="active" accesskey="a" />
			<h:selectBooleanCheckbox id="active"
				value="#{contactController.contact.active}" />
			<h:message for="active" errorClass="errorClass" />

			<%-- email --%>
			<h:outputLabel value="email" for="email" accesskey="e" />
			<h:inputText id="email" size="20"
				value="#{contactController.contact.email}" />
			<h:message for="email" errorClass="errorClass" />
		</h:panelGrid>
		<div style="border: solid #000 1px">
		<h4>Phone Numbers</h4>
		<h:panelGrid columns="6" columnClasses="padding">
			<%-- Work --%>
			<h:outputLabel value="Work" for="work" accesskey="w" />
			<h:inputText id="work"
				value="#{contactController.contact.workPhoneNumber}" size="11" />
			<h:message for="work" errorClass="errorClass" />
			<%-- Home --%>
			<h:outputLabel value="Home" for="home" accesskey="h" />
			<h:inputText id="home"
				value="#{contactController.contact.homePhoneNumber}" size="11" />
			<h:message for="home" errorClass="errorClass" />
			<%-- Mobile --%>
			<h:outputLabel value="Mobile" for="mobile" accesskey="m" />
			<h:inputText id="mobile"
				value="#{contactController.contact.mobilePhoneNumber}" size="11" />
			<h:message for="mobile" errorClass="errorClass" />
		</h:panelGrid></div>
		<%-- Tags --%>
		<div style="border: solid #000 1px"><h:panelGrid columns="3">
			<h:outputLabel value="Tags" for="tags" accesskey="t"
				style="font: large;" />
			<%-- 
			<h:selectManyCheckbox id="tags"
				value="#{contactController.selectedTagIds}">
				<f:selectItems value="#{contactController.availableTags}" />
			</h:selectManyCheckbox>						
			<h:selectManyMenu id="tags" 
			                 value="#{contactController.selectedTagIds}">
				<f:selectItems value="#{contactController.availableTags}"/>
			</h:selectManyMenu>
			<h:selectManyListbox id="tags" 
			                 value="#{contactController.selectedTagIds}">
				<f:selectItems value="#{contactController.availableTags}"/>
			</h:selectManyListbox>						
			--%>
			<h:selectManyCheckbox id="tags"
				value="#{contactController.selectedTagIds}">
				<f:selectItems value="#{contactController.availableTags}" />
			</h:selectManyCheckbox>						

			<h:message for="tags" errorClass="errorClass" />
		</h:panelGrid></div>

		<%-- Description --%>
		<div style="border: solid #000 1px">
			<h:outputLabel value="Description" for="description" accesskey="d" style="font: large;" /> 
			<h:inputTextarea id="description" cols="80" rows="5" 
							 value="#{contactController.contact.description}" /> 
			<h:message for="description" errorClass="errorClass" />
		</div>

		<h:panelGroup>
			<h:commandButton binding="#{contactController.persistCommand}"
				action="#{contactController.persist}" />
			<h:commandButton type="reset" value="Reset" />
			<h:commandButton action="#{contactController.cancel}" value="Cancel"
				immediate="true" />
		</h:panelGroup>
	</h:form>
</f:subview>

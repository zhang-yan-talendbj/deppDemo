<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:subview id="listing">
	<h:form>
		<h:dataTable value="#{contactController.contacts}" var="contact"
			rowClasses="oddRow, evenRow"
			rendered="#{not empty contactController.contacts}"
			styleClass="contactTable" headerClass="headerTable"
			columnClasses="normal,normal,normal,centered">
			<h:column>
				<%-- Name column --%>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Name" />
					</h:column>
				</f:facet>
				<h:outputText value="#{contact.lastName}, #{contact.firstName}" />
			</h:column>
			<h:column>
				<%-- Group column --%>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Group" />
					</h:column>
				</f:facet>
				<h:outputText value="#{contact.group.name}" />
			</h:column>
			<h:column>
				<%-- Type column --%>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Type" />
					</h:column>
				</f:facet>
				<h:outputText value="#{contact.type}" />
			</h:column>
			<h:column>
				<%-- Action column (remove / edit) --%>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Action" />
					</h:column>
				</f:facet>
				<h:panelGrid columns="2">
					<h:commandLink value="remove" action="#{contactController.remove}">
						<f:setPropertyActionListener
							target="#{contactController.selectedContact}" value="#{contact}" />
					</h:commandLink>
					<h:commandLink value="edit" action="#{contactController.read}">
						<f:setPropertyActionListener
							target="#{contactController.selectedContact}" value="#{contact}" />
					</h:commandLink>
				</h:panelGrid>
			</h:column>

		</h:dataTable>
	</h:form>
</f:subview>

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
	<h3>Contacts (2nd version)</h3>
	<h:messages infoClass="infoClass" errorClass="errorClass"
		layout="table" globalOnly="true" />

	<h:form>
		<h:commandLink binding="#{contactController.addNewCommand}"
			action="#{contactController.addNew}" value="Add New..." />
	</h:form>

	<jsp:include page="form.jsp" />

	<jsp:include page="listing.jsp" />

</f:view>
</body>

</html>
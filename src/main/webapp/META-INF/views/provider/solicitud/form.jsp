<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="provider.solicitud.form.label.ticker" path="ticker" placeholder="RXXXX-99999"/>
	<acme:form-textbox code="provider.solicitud.form.label.title" path="title"/>
	<acme:form-moment code="provider.solicitud.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="provider.solicitud.form.label.text" path="text"/>
	<acme:form-money code="provider.solicitud.form.label.reward" path="reward"/>
	
	<acme:form-checkbox code="provider.solicitud.form.label.accept" path="accept"/>
	
	<acme:form-submit test="${command=='create'}" code="provider.solicitud.form.button.create" action="/provider/solicitud/create"/>
  	<acme:form-return code="provider.solicitud.form.button.return"/>
  	
</acme:form>

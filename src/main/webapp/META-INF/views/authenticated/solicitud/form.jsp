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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.solicitud.form.label.ticker" path="ticker" placeholder="RXXXX-99999"/>
	<acme:form-textbox code="authenticated.solicitud.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.solicitud.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.solicitud.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.solicitud.form.label.text" path="text"/>
	<acme:form-double code="authenticated.solicitud.form.label.reward" path="reward"/>
	
  	<acme:form-return code="authenticated.solicitud.form.button.return"/>
</acme:form>

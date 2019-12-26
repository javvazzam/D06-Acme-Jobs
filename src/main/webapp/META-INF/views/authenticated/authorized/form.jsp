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

<link rel="stylesheet" href="libraries/acme/css/challenge.css" />

<acme:form>

	<acme:form-textarea code="authenticated.authorized.form.label.body" path="body" />

	<jstl:if test="${command!='create'}">
		<acme:form-textbox code="authenticated.authorized.form.label.status" path="status" />
	</jstl:if>

	<jstl:if test="${status=='Accepted'}">
		<acme:form-return code="authenticated.auditor.form.button.create" action="/authenticated/auditor/create" />
	</jstl:if>

	<jstl:if test="${command=='create'}">
		<acme:form-submit code="authenticated.authorized.form.button.create" action="/authenticated/authorized/create"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.authorized.form.button.return" />
</acme:form>

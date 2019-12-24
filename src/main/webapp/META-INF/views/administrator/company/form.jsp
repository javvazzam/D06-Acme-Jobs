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
	<!--Campos del formulario  -->
	<acme:form-textbox code="administrator.company.form.label.name" path="name"/>
	<acme:form-checkbox code="administrator.company.form.label.incorporated" path="incorporated"/>
	<acme:form-textbox code="administrator.company.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="administrator.company.form.label.activities" path="activities"/>
	<acme:form-url code="administrator.company.form.label.url" path="url"/>
	<acme:form-textbox code="administrator.company.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.company.form.label.email" path="email"/>
	<acme:form-double code="administrator.company.form.label.stars" path="stars"/>
	
	<!-- Botones -->
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company.form.button.update"
		action="/administrator/company/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company.form.button.delete"
		action="/administrator/company/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.company.form.button.create"
		action="/administrator/company/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.company.form.button.update"
		action="/administrator/company/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.company.form.button.delete"
		action="/administrator/company/delete"/>
	
  	<acme:form-return code="administrator.company.form.button.return"/>
</acme:form>

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
	<acme:form-hidden path="direccionParticipant"/>
	<jstl:if test="${command=='show' }">
	<acme:form-textbox code="authenticated.participant.form.label.username" path="username" readonly="true"/>
	</jstl:if>

	<jstl:if test="${command=='create' }">
	<acme:form-select code="authenticated.participant.form.label.usuarioelegido" path="usuarioelegido">
	<jstl:forEach var="usuario" items="${usuarios}">
	<acme:form-option code="${usuario.userAccount.username}" value="${usuario.userAccount.id}"/>
	</jstl:forEach>
	</acme:form-select>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show'}" code="authenticated.participant.form.button.delete" action="/authenticated/participant/delete"/>
	<acme:form-submit test="${command == 'create'}" code="authenticated.participant.form.button.create" action="${direccionParticipant}"/>
  	<acme:form-return code="authenticated.participant.form.button.return"/>
</acme:form>

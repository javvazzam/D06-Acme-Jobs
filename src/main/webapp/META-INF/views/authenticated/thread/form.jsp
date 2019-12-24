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
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />
	<acme:form-moment code="authenticated.thread.form.label.creationDate" path="creationDate" readonly="true"/>
	
	
	<jstl:if test="${command != 'create' }">
	<acme:form-return code="authenticated.thread.form.button.messageList" action= "${direccion}"/>
	<acme:form-return code="authenticated.thread.form.button.message.create" action="${threadCreateMessage}" />
	<acme:form-return code="authenticated.thread.form.button.message.listarUsuario" action="${direccionListarUsuario}" />
	<acme:form-return code="authenticated.thread.form.button.message.anadirUsuario" action="${direccionAnadirUsuario}" />
	</jstl:if>
	
	<acme:form-submit test="${command=='create'}" code="authenticated.thread.form.button.create" action="/authenticated/thread/create" />
	<acme:form-return code="authenticated.thread.form.button.return" />
</acme:form>

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
	<acme:form-textbox code="administrator.investor.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.investor.form.label.sector" path="sector"/>
	<acme:form-textarea code="administrator.investor.form.label.statement" path="statement"/>
	<acme:form-textbox code="administrator.investor.form.label.stars" path="stars"/>
	
	<%-- Si estamos en vista de show, ya que dentro del show se puede actualizar y borrar datos --%>
	<acme:form-submit 
		test="${command == 'show'}"
		code="administrator.investor.form.button.update" 
		action="/administrator/investor/update"/>
	<acme:form-submit 
		test="${command == 'show'}"
		code="administrator.investor.form.button.delete" 
		action="/administrator/investor/delete"/>
		
	<%-- Si estamos en vista de create --%>
	<acme:form-submit 
		test="${command == 'create'}"
		code="administrator.investor.form.button.create" 
		action="/administrator/investor/create"/>
	
	<%-- Si estamos en vista de update --%>
	<acme:form-submit 
		test="${command == 'update'}"
		code="administrator.investor.form.button.update" 
		action="/administrator/investor/update"/>
	
	<%-- Si estamos en vista de delete --%>
	<acme:form-submit 
		test="${command == 'delete'}"
		code="administrator.investor.form.button.delete" 
		action="/administrator/investor/delete"/>
	
	
  	<acme:form-return code="administrator.investor.form.button.return"/>
</acme:form>

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

<link rel="stylesheet" href="libraries/acme/css/challenge.css"/> 

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	
	<!--  
	<div class="seccion">
		<span code="authenticated.challenge.form.label.bronzeLevel">Bronze level</span>
		<div>
			<acme:form-textarea code="authenticated.challenge.form.label.bronzeGoal" path="bronzeGoal"/>
			<acme:form-money code="authenticated.challenge.form.label.bronzeReward" path="bronzeReward"/>
		</div>
	</div>
	
	<div class="seccion">
		<span code="authenticated.challenge.form.label.silverLevel">Silver level</span>
		<div>
			<acme:form-textarea code="authenticated.challenge.form.label.silverGoal" path="silverGoal"/>
			<acme:form-money code="authenticated.challenge.form.label.silverReward" path="silverReward"/>
		</div>
	</div>
	
	<div class="seccion">
		<span code="authenticated.challenge.form.label.goldLevel">Gold level</span>
		<div>
			<acme:form-textarea code="authenticated.challenge.form.label.goldGoal" path="goldGoal"/>
			<acme:form-money code="authenticated.challenge.form.label.goldReward" path="goldReward"/>
		</div>
	</div>-->
	
	<acme:form-panel code="authenticated.challenge.form.label.bronzeLevel">
		<div class="sec">
			<acme:form-textarea code="authenticated.challenge.form.label.bronzeGoal" path="bronzeGoal"/>
			<acme:form-money code="authenticated.challenge.form.label.bronzeReward" path="bronzeReward"/>
		</div>
	</acme:form-panel>
	
	
	<acme:form-panel code="authenticated.challenge.form.label.silverLevel">
		<div class="sec">
			<acme:form-textarea code="authenticated.challenge.form.label.silverGoal" path="silverGoal"/>
			<acme:form-money code="authenticated.challenge.form.label.silverReward" path="silverReward"/>
		</div>
	</acme:form-panel>
	
	
	<acme:form-panel code="authenticated.challenge.form.label.goldLevel">
		<div class="sec">
			<acme:form-textarea code="authenticated.challenge.form.label.goldGoal" path="goldGoal"/>
			<acme:form-money code="authenticated.challenge.form.label.goldReward" path="goldReward"/>
		</div>
	</acme:form-panel>
	
  	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div ng-controller="projects">
	<c:if test="${not empty message }">
		<div id="status">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close" ng-click="{{hideAlert=true}}">&times;</a>
				<strong>Success!</strong>${message }
			</div>
		</div>
		<%
        request.getSession().removeAttribute("message");
		%>
	</c:if>
	<!-- <button type="button" class="btn btn-info btn-sm" data-toggle="modal"
		data-target="#newProject">New Project</button>-->
	<%@ include file="projects-table.jsp" %>
	<!-- Modal -->
	<% /*jsp:include page="new-project.jsp"/*/%>
	<jsp:include page="edit-project.jsp"/> 
</div>

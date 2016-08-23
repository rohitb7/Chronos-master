
<div ng-controller="ws">
	<button type="button" class="btn btn-info btn-sm button-new" data-toggle="modal"
		data-target="#newProject" ng-show="${user.isAdmin()}">New Project</button>
	<%@ include file="workspace-table.jsp" %>
	<!-- Modal -->
	<jsp:include page="new-project.jsp"/>
	<jsp:include page="edit-workspace.jsp"/>
	 
</div>
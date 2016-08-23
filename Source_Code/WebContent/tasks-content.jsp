<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div ng-controller="tasks">
		
	
		<div id="status" class="hidden alert alert-success">
				<a href="#" class="close" aria-label="close" onclick="$('#status').addClass('hidden')">&times;</a>
				Task {{id}} updated!
	</div>
	
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
	
	<table class="table" id="taskTable">
	
			<tr  >
			<td style="border:0;">
				<button type="button" class="btn btn-info btn-sm button-new" data-toggle="modal" data-target="#newTask">New Task</button>
			</td>
		</tr>
		<tr  >
			<td style="border:0;">
				<%@ include file="tasks-table.jsp"%>
			</td>
		</tr>
	</table>
	
	<!-- Modal -->
	<jsp:include page="new-task.jsp" />
	<jsp:include page="edit-task.jsp" />
	
	
</div>

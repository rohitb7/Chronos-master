<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main-table">
			
		<table class="table table-bordered" id="taskTable">
			<thead>			
				<tr>
					<th class="table-header">ID</th>
					<th class="table-header">Summary</th>
					<th class="table-header">Description</th>
					<th class="table-header">Created By</th>
					<th class="table-header">Assigned To</th>
					<th class="table-header">Due Date</th>
					<th class="table-header">Status</th>
					<th class="table-header"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tasks}" var="t">
					<tr class="table-row" id="${t.id }">
						<td>
							<a href="#" ng-click="get('${t.id}',false)">${t.id}</a>
						</td>
						<td>${t.summary }</td>
						<td>${t.description }</td>
						<td>${t.owner.firstName} &nbsp;
							${t.owner.lastName}</td>
						<td>${t.assignee.firstName} &nbsp;
							${t.assignee.lastName}</td>
						<c:choose>
							<c:when test="${not empty t.dueDate}">
								<td>${dateFormat.format(t.dueDate)}</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<td class="task-status">${t.status}</td>
				<%-- 		<td><input type="button" value="Edit"
							ng-click="get('${t.id}',true)" /></td> --%>
						<td>
							 <div class="dropdown" style="display: inline-block">
							    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
							    <span class="glyphicon glyphicon-cog"></span></button>
							    <ul class="dropdown-menu">
							      <li class="dropdown-header">Update Status</li>
							      <li><a href="#" ng-click="updateTaskStatus('${t.id }','COMPLETE')">Complete</a></li>
							      <li><a href="#"  ng-click="updateTaskStatus('${t.id }','IN_PROGRESS')">In Progress</a></li>
							      <li><a href="#"  ng-click="updateTaskStatus('${t.id }','IN_REVIEW')">In Review</a></li>
							      <li><a href="#"  ng-click="updateTaskStatus('${t.id }','ON_HOLD')">On Hold</a></li>
							    </ul>
							  </div>
							  <button class="btn btn-default" type="button" value="Edit" ng-click="get('${t.id}',true)">
							  	<span class="glyphicon glyphicon-edit"></span></button>
							  </button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
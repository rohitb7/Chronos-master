<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main-table">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th class="table-header">Project Key</th>
				<th class="table-header">Name</th>
				<th class="table-header">Summary</th>
				<th class="table-header">Manager</th>
				<th class="table-header">Start Date</th>
				<th class="table-header">Status</th>
				<th class="table-header"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${projects}" var="p">
				<tr>
					<td><a href='/chronos/tasks?action=view&amp;projectId=${p.id}'>${p.key}</a></td>
					<td>${p.name}</td>
					<td>${p.description}</td>
					<td>${p.owner.firstName}&nbsp;${p.owner.lastName}</td>
					<c:choose>
						<c:when test="${not empty p.startDate}">
							<td>${dateFormat.format(p.startDate)}</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					
					<td>${p.status}</td>
					<td><button type="button" class="btn btn-default" value="Edit"
						ng-click="get('${p.id}',true)">
						<span class="glyphicon glyphicon-edit"></span>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
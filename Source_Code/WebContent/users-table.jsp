<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main-table">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="table-header">Username</th>
					<th class="table-header">First Name</th>
					<th class="table-header">Last Name</th>
					<th class="table-header">Email</th>
					<th class="table-header">Roles</th>
					<th class="table-header">Projects</th>
					<th class="table-header">Admin User?</th>
					<th class="table-header"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="u">
					<tr>
						<!-- href='/chronos/users?action=view&amp;id=${u.id}' -->
						<td><a
							href="#" 
							ng-click="get('${u.id}',false)">${u.username}</a></td>
						<td>${u.firstName}</td>
						<td>${u.lastName}</td>
						<td>${u.email}
						<td>
							<c:forEach items="${u.roles}" var="role">
								<span class="label label-default">${role}</span>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${u.projects}" var="proj">
								<a href="/chronos/tasks?action=view&projectId=${proj.id}">
									${proj.key} <span class="badge"></span>
								</a>
							</c:forEach>
						</td>
						<td>${u.isAdmin()}</td>
						<td>
						
						<%--<input type="button" value="Edit"
							ng-click="get('${u.id}',true)" ng-disabled="${!adminUser}"/>--%>
							 <button class="btn btn-default" type="button" value="Edit" ng-click="get('${u.id}',true)" ng-disabled="${!adminUser}" >
							  	<span class="glyphicon glyphicon-edit"></span></button>
							 
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div ng-app="chronos" class="col-md-12 main-table" style="float:left; margin-left: 5.75%;">
	<table class="table table-striped table-bordered" >
		<thead>
			<tr>
				<th class="col-md-5 table-header">Name</th>
				<th class="col-md-5 table-header">Description</th>
				<th></th>
			</tr>
			<c:forEach var="w" items="${ws}">
				<tr>
					<td><c:out value="${w.name}" /></td>
					<td><c:out value="${w.description }"></c:out></td>
					<td><input type="button" value="Edit"
						ng-click="getEditForWorkspace('${w.name}','${w.description}')"
						data-target="#myModal" data-toggle="modal"
						class="btn btn-info btn-sm button-new" /></td>
				</tr>
			</c:forEach>
		</thead>
		<tbody>
	</table>
</div>
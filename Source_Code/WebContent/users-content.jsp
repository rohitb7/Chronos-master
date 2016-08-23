<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Include Core Tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div  ng-controller="users">
		<div id="status" class="hidden alert alert-success">
				<a href="#" class="close" aria-label="close" onclick="$('#status').addClass('hidden')">&times;</a>
				User {{id}} updated!
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
<%@ include file="users-table.jsp" %>
<div id="editUserModal" class="modal fade bs-example-modal-sm" role="dialog" data-backdrop="false">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-headcolor">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit User - {{user.username}} ({{user.id}})</h4>
			</div>
			<div class="modal-body">
				<form role="form" class="form-horizontal" method="POST" action="/chronos/users" id="editUserForm">
					<div class="form-group">
						<label class="control-label col-sm-5" for="fname">First Name</label>
						<div class="col-sm-5 margin-bottom-5">							
							<input type="text" id="fname" name="fname" value="{{user.fname}}" ng-model="user.fname" class="form-control" ng-disabled="readonly" />
						</div>						
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="fname">Last Name</label>
						<div class="col-sm-5 margin-bottom-5">							
							<input type="text" id="lname" name="lname" value="{{user.lname}}" ng-model="user.lname" class="form-control" ng-disabled="readonly" />
						</div>	
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="fname">Email</label>
						<div class="col-sm-5 margin-bottom-5">							
							<input type="text" id="email" name="email" value="{{user.email}}" ng-model="user.email" class="form-control" ng-disabled="readonly" />
						</div>	
					</div>
			
					<div class="form-group" ng-show="task.action!='create'">
						<label class="control-label col-sm-5" for="projectSelect"> Available Projects </label>
						<div class="col-sm-5 margin-bottom-5">
							<select name="availableProjects" id="projectSelect" ng-model="projectToAdd" class="form-control" ng-disabled="readonly">
								<option ng-repeat="p in projects" value="{{p.id}}">{{p.prjKey}}</option>
							</select>
							<button type="button" class="btn btn-primary margin-8percent" ng-disabled="readonly" ng-click="addProject()">Add Project</button>
						</div>					
					</div>
						
					<div class="form-group">
						<label class="control-label col-sm-5" > Projects </label>					
						<div ng-repeat="p in user.projects"  >
							<label class="col-sm-5 margin-bottom-5">{{p.prjKey}}</label>
							<input type="hidden"  name="projects" value="{{p.id}}"/>
						<!--<button ng-disabled="readonly" type="button" class="btn btn-default"
						ng-click="projectToRemove.index = $index; removeProject()">Remove</button>-->
						<!-- <select name="availableProjects" id="projectSelect" ng-model="projectToRemove" class="form-control" ng-disabled="readonly">
								<option ng-repeat="p in user.projects" value="{{p.id}}">{{p.prjKey}}</option>
							</select> -->
						</div>					
					</div>
						
					<input type="hidden" name="id" value="{{user.id}}" ng-model="user.id"></input>
					<input type="hidden" name="action" value="{{user.action}}" ng-model="user.action"/></input>
						
					<button type="submit" class="btn btn-primary sub-button" ng-disabled="readonly">Save</button>
					<button type="button" class="btn btn-default can-button" data-dismiss="modal" ng-disabled="readonly">Cancel</button>
				</form>
			</div>
			
		</div>

	</div>
</div>
			
</div>

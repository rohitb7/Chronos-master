<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="newTask" class="modal fade" role="dialog" data-backdrop="false">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-headcolor">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">New Task</h4>
			</div>
			<form role="form" class="form-horizontal" method="POST" action="/chronos/tasks" id="newTaskForm">
<!-- 			<form role="form" class="form-horizontal" method="POST" ng-submit="create()" id="newTaskForm"> -->
				<div class="modal-body">
					<div class="form-group">
						<label for="summary" class="control-label col-sm-2">Summary</label>
						<div class="col-sm-10 margin-bottom-5">
							<input type="text" name=taskSummary " id="taskSummary" class="form-control"
								ng-model="newTask.taskSummary" ng-required />
						</div>
					</div>
					<div class="form-group">
						<label for="desc" class="control-label col-sm-2">Description</label>
						<div class="col-sm-10 margin-bottom-5">
							<textarea id="desc" name="taskDesc"  class="form-control"
								ng-model="newTask.taskDesc" ng-required></textarea>

						</div>
					</div>
					<div class="form-group">
						<label for="assignee" class="control-label col-sm-2">Assignee</label>
						<div class="col-sm-4 margin-bottom-5">
							<input type="text" name="assignee" id="assignee" class="form-control"
								ng-model="newTask.taskAssignee" ng-required />
						</div>
						<label for="dueDate" class="control-label col-sm-2">Due Date</label>
						<div class="col-sm-4 margin-bottom-5">
							<input type="date" name="dueDate" id="dueDate" class="form-control"
								ng-model="newTask.dueDate" ng-required />
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary sub-button">Create Task</button>
						<button type="button" class="btn btn-default can-button" data-dismiss="modal">Cancel</button>
					</div>

					<input type="hidden" name="action" ng-model="newTask.action"
						value="create" />
				</div>
			</form>
		</div>

	</div>
</div>
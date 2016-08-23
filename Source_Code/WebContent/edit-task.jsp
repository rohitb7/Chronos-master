<div id="editTask" class="modal fade bs-example-modal-sm modal-center" role="dialog" data-backdrop="false">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-headcolor" >
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Task {{task.id}}</h4>
			</div>
			<div class="modal-body">
				<form role="form" class="form-horizontal" method="POST" action="/chronos/tasks" id="editTaskForm">
					<div class="form-group">
						<label class="control-label col-sm-4"for="summary">Summary</label>
						<div class="col-sm-6 margin-bottom-5">
							<input type="text" name="taskSummary" id="taskSummary" class="form-control" ng-disabled="readonly"
								ng-model="task.taskSummary" ng-required />
						</div>
					</div>
					<div class="form-group" ng-show="task.action!='create'">
						<label class="control-label col-sm-4"for="status">Status</label>
						<div class="col-sm-6 margin-bottom-5">
							<select id="status" name="taskStatus" ng-model="task.taskStatus" class="form-control" ng-disabled="readonly">
								<option value="COMPLETE">Complete</option>
								<option value="IN_PROGRESS">In Progress</option>
								<option value="IN_REVIEW">In Review</option>
								<option value="ON_HOLD">On Hold</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"for="desc">Description</label>
						<div class="col-sm-6 margin-bottom-5">
							<textarea id="desc" name="taskDesc" class="form-control txtarea-align" ng-disabled="readonly"
								ng-model="task.taskDesc" ng-required></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"for="owner">Owner</label>
						<div class="col-sm-6 margin-bottom-5">
							<input type="text" name="taskOwner" id="owner" class="form-control" ng-disabled="readonly"
								ng-model="task.taskOwner"  />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"for="assignee">Assignee</label>
						<div class="col-sm-6 margin-bottom-5">
							<input type="text" name="taskAssignee" id="assignee" class="form-control" ng-disabled="readonly"
								ng-model="task.taskAssignee" ng-required />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"for="dueDate">Due Date</label>
						<div class="col-sm-6 margin-bottom-5">
							<input type="date" name="dueDate" id="dueDate" class="form-control" ng-disabled="readonly"
								ng-model="task.dueDate" ng-required />
						</div>
					</div>

					<input type="hidden" name="action" ng-model="task.action" value="edit" />
					<input type="hidden" name="id" ng-model="task.id" value="{{task.id}}" />

					<div class="form-group">
						<button type="submit" class="btn btn-primary sub-button" ng-disabled="readonly">Save</button>
						<button type="button" class="btn btn-default can-button" data-dismiss="modal" ng-disabled="readonly">Cancel</button>
					</div>
				</form>
			</div>
			
		</div>

	</div>
</div>
<%@page import="coen275.chronos.project.User"%>
<div id="editProject" class="modal fade bs-example-modal-sm" role="dialog" data-backdrop="false">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-headcolor">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Project # {{project.id}}</h4>
			</div>
			<div class="modal-body">
				<form role="form" action="/chronos/projects" method="POST">
					<div class="form-group">
						<label for="name">Name</label>
						<fieldset>
							<input type="text" name="prjName" id="prjName"
								ng-model="project.prjName" ng-required value={{project.prjName}}/>
						</fieldset>
					</div>
					<div class="form-group">
						<label for="key">Project Key</label>
						<fieldset>
							<input type="text" name="prjKey" id="prjKey"
								ng-model="project.prjKey" ng-readonly="true"/>
						</fieldset>
					</div>
					<div class="form-group">
						<label for="status">Status</label>
						<fieldset>
							<select id="prjStatus" name="prjStatus" ng-model="project.prjStatus">
								<option value="COMPLETE">Complete</option>
								<option value="IN_PROGRESS">In Progress</option>
								<option value="NOT_STARTED">Not Started</option>
								<option value="SUSPENDED">Suspended</option>
								<option value="CANCELLED">Cancelled</option>
							</select>
						</fieldset>
					</div>
					
					<div class="form-group" ng-show="${user.isAdmin()}">
						<label for="active">Active</label>
						<fieldset>
							<input type="radio" name="isActive" ng-model="project.isActive" ng-value="true"/>Yes<br>
						</fieldset>
						<fieldset>
							<input type="radio" name="isActive" ng-model="project.isActive" ng-value="false"/>No<br>
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label for="desc">Description</label>
							<textarea class="txtarea-align" id="desc" name="prjDesc" rows="3" cols="64"
								ng-model="project.prjDesc" ng-required>
							</textarea>
						</fieldset>
					</div>
					<div class="form-group">
						<label for="owner">Owner</label>
							<input type="text" name="prjOwner" id="owner"
								ng-model="project.prjOwner"  />
						</fieldset>
					</div>
					<!--div class="form-group">
						<label for="assignee">Assignee</label>
							<input type="text" name="assignee" id="assignee"
								ng-model="project.prjAssignee" ng-required />
						</fieldset>
					</div-->
					<div class="form-group">
						<label for="startDate">Start Date</label>
							<input type="date" name="prjStartDate" id="startDate"
								ng-model="project.prjStartDate"  ng-required />
						</fieldset>
					</div>
					<div class="form-group">
						<label for="endDate">End Date</label>
							<input type="date" name="prjEndDate" id="endDate"
								ng-model="project.prjEndDate" ng-required />
						</fieldset>
					</div>

					<input type="hidden" name="action" ng-model="project.action" value="edit" />
					<input type="hidden" name="id" ng-model="project.id" value="{{project.id}}" />
					<button type="submit" class="btn btn-primary sub-button">Update Project</button>
					<button type="button" class="btn btn-default can-button" data-dismiss="modal">Cancel</button>
				</form>
			</div>
			<!-- div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>-->
		</div>

	</div>
</div>
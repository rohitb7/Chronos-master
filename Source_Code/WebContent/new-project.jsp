<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="newProject" class="modal fade" role="dialog" data-backdrop="false">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-headcolor">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">New Project</h4>
			</div>
			<div class="modal-body">
				<form role="form" action="/chronos/projects" method="POST">
					<div class="form-group">
					<label for="name">Name</label>
					<fieldset>
							<input type="text" name="prjName" id="prjName"
								ng-model="newProject.prjName" ng-required />
						</fieldset>
					</div>
					<div class="form-group">
					<label for="key">Project Key</label>
					<fieldset>
							<input type="text" name="prjKey" id="prjKey"
								ng-model="newProject.prjKey" ng-required />
						</fieldset>
					</div>
					<div class="form-group">
						<label for="desc">Description</label>
						<fieldset >
							<textarea id="prjDesc" name="prjDesc" rows="3" cols="64"
								ng-model="newProject.prjDesc" ng-required></textarea>
						</fieldset>
					</div>
					<div class="form-group">
						<label for="prjManager">Project Manager</label>
						<fieldset >
							<input type="text" id="prjOwner" name="prjOwner" 
							ng-model="newProject.prjOwner" ng-required></textarea>
						</fieldset>
					</div>
					<div class="form-group">
						<label for="active">Active</label>
						<fieldset>
							<input type="radio" name="isActive" value="true"/>Yes<br>
						</fieldset>
						<fieldset>
							<input type="radio" name="isActive" value="false"/>No<br>
						</fieldset>
					</div>
					<div class="form-group">
						<label for="startDate">Start Date</label>
						<fieldset>
							<input type="date" name="prjStartDate" id="prjStartDate"
								ng-model="newProject.startDate" ng-required />
						</fieldset>
					</div>
					<div class="form-group">
						<label for="endDate">End Date</label>
						<fieldset>
							<input type="date" name="prjEndDate" id="prjEndDate"
								ng-model="newProject.endDate" ng-required />
						</fieldset>
					</div>
					
					<input type="hidden" name="action" ng-model="newProject.action" value="create" />
					<button type="submit" class="btn btn-primary sub-button">Create Project</button>
					<button type="button" class="btn btn-default can-button" data-dismiss="modal">Cancel</button>
				</form>
			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>-->
		</div>

	</div>
</div>
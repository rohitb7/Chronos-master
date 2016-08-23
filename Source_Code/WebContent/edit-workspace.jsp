<!-- Modal -->
<div class="modal fade" id="myModal" class="modal fade bs-example-modal-sm modal-center" role="dialog" data-backdrop="false">

	<div class="modal-dialog"
		<!-- Modal content action="workspaces" since workspace servlet-->

			<div class="modal-content">
				<div class="modal-header modal-headcolor">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Workspace</h4>
				</div>
				<form action="/chronos/workspaces" method="post" id="editWorkspaceForm" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label col-sm-2"for="editWsName">Workspace Name</label>
						<div class="col-sm-10 margin-bottom-5">
							<input type="text" name="name" id="wsName" class="form-control"
								ng-model="name" ng-required />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"for="editWsDesc">Description</label>
						<div class="col-sm-10 margin-bottom-5">
							<input type="textarea" name="description" id="wsDesc" class="form-control" ng-disabled="readonly"
								ng-model="description" ng-required />
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<input type="hidden" name="action" ng-model="workspace.action" value="edit" />
					<input type="hidden" name="id" ng-model="workspace.id" value="{{workspace.id}}" />
					<input type="submit" class="btn btn-primary sub-button" ></input>
					<button type="button" class="btn btn-default can-button" data-dismiss="modal">Close</button>
				</div>
				</form>	
			</div>
	</div>
</div>
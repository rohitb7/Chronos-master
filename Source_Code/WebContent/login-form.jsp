	
	<jsp:include page="message.jsp"/>
	<form role="form" method="post" action="/chronos/login" style="width:40%; margin:10% 10% 10% 20%">
		<div class="panel panel-primary">
			<div class="panel-heading"  style="background-color: #205081">Login</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="username" class="control-label col-sm-5">Username</label>
					<div class="col-sm-5 margin-bottom-5">
						 <input type="text"
						class="form-control" id="username" name="username" class="col-sm-5 margin-bottom-5">
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="control-label col-sm-5">Password</label>
					<div class="col-sm-5 margin-bottom-5">
						 <input type="text"
						class="form-control" id="password" name="password" class="col-sm-5 margin-bottom-5">
					</div>
				</div>			
				<input type="hidden" name="action" value="login" />
			</div>
			<div class="form-group" style="margin-right:10px;">
				<button type="submit" class="btn btn-primary sub-button">Login</button> 
				<button type="reset"  class="btn btn-secondary can-button">Reset </button>
				<button style="float:right" type="button" class="btn btn-info" value="Sign Up" data-target="#signupModal" data-toggle="modal">
			Sign Up
		</button>
			</div>
			
		</div>
	</form>
	<div>
		
		<div id="signupModal" class="modal fade" role="dialog" data-backdrop="false">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Sign Up</h4>
						</div>
						<div class="modal-body container"  ng-controller="users">
							<jsp:include page="new-user.jsp"></jsp:include>
						</div>
						<div class="modal-footer">
						</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container hidden">
		<div class="row">
	        <div class="col-md-4 col-md-offset-4">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title">Welcome to my site</h3>
	                </div>
	                <div class="panel-body">
	                    <form role="form">
	                        <fieldset>
	                            <div class="form-group">
	                                <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
	                            </div>
	                            <div class="form-group">
	                                <input class="form-control" placeholder="Password" name="password" type="password" value="">
	                            </div>
	                            <input type="hidden" name="action" value="login" />
	                            <!-- Change this to a button or input when using this as a form -->
	                            <button type="button" class="btn btn-success btn-block">Login</button>
	                            <p>New Member? <a href="signUp.html" class="">Sign up</a></p>
	                        </fieldset>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<form role="form" action="/chronos/users" method="POST" class="form-horizontal"
		ng-controller="users">
		{{user.message}}

		<br>
		<br>
		<div class="form-group" class="col-md-4" style="margin:0 10%">
			<label for="username" class="col-md-2">Username</label> 
			<input class="col-md-2" type="text" name="username" id="username" ng-model="user.username" ng-required />
		</div>
		<br>
		<br>
		<div class="form-group" class="col-md-4" style="margin:0 10%">
			<label for="password" class="col-md-2">Password</label>
			 <input class="col-md-2" type="password" name="password" id="password" ng-model="user.password" ng-required />
		</div>
		<br>
		<br>
		<div class="form-group" class="col-md-4" style="margin:0 10%">
			<label for="confirm" class="col-md-2">Confirm Password</label> 
			<input class="col-md-2" type="password" name="confirm" id="confirm" ng-model="user.confirmPassword" ng-required />
		</div>
		<br>
		<br>
		<div class="form-group" class="col-md-4" style="margin:0 10%">
			<label for="fname" class="col-md-2">First Name</label>
			 <input class="col-md-2" type="text" name="fname" id="fname" ng-model="user.fname" ng-required />
		</div>
		<br>
		<br>
		<div class="form-group" class="col-md-4" style="margin:0 10%">
			<label for="lname" class="col-md-2">Last Name</label> 
			<input type="text" class="col-md-2" name="lname"	id="lname" ng-model="user.lname" ng-required />
		</div>
		<br>
		<br>
		<div class="form-group" class="col-md-4" style="margin:0 10%">
			<label for="email" class="col-md-2">Email</label> 
			<input type="email" name="email" id="email" ng-model="user.email" ng-required class="col-md-2" />
		</div>
		<br>
		<input type="hidden" name="action" ng-model="user.action" class="col-md-2" value="{{user.action}}" />
		<button type="submit" class="btn btn-default btn-primary"  class="col-md-2" style="margin:3% 3% 3% 12% ">Create Account</button>
		<button type="button" class="btn btn-default btn-danger" data-dismiss="modal"  class="col-md-2">Close</button>
	
</table>
</form>

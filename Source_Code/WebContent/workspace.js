$( document ).ready(function() {
    var x=$(window).width();
    var y=$(window).height();
    var hheader=y/9;
    var hfooter=y/14;
    var hsidenav=y/1.22;   
    var wsidenav=x*0.15;
    var wmaindiv=x*0.85;

    $(".body-div").height(y+'px');
    $(".header").height(hheader+'px');
    $(".sitemap").height(hfooter+'px');
    $(".side-nav").height(hsidenav+'px');
    $(".side-nav").width(wsidenav+'px');
    $(".main-div").height(hsidenav+'px');
    $(".main-div").width(wmaindiv+'px');
});

var app = angular.module('chronos', []);

app.controller('ws', function($scope,$http) {

	$scope.getEditForWorkspace  = function(name, description) {
		$scope.name=name;
		$scope.description=description;
		console.log("Edit Button Clicked ",name,description);
	};

	
	$scope.saveDataWorkspace = function(name, description){
		
		 $scope.w = {
			        name : name,
			        description :description
			    };
		 
		 data = $scope.w; 
		
		$http.post('/workspaces/action=edit?name:', data)
        .then(function(response) {
           console.log("Success");
          }, function(response) {
        	  console.log("Saving Fail");
        });
			console.log("Save Button Clicked", $scope.w);
	}	

});

app.controller('projects', function($scope,$http) {
	$scope.project = {
			id: "",
			key:"",
			prjName: "",
			prjDesc:"Enter Description here",
			prjOwner:"",
			prjStartDate:"",
			prjEndDate:"",
			action: "create"
	};
	$scope.newProject = {
			id: "",
			key:"",
			prjName: "",
			prjDesc:"Enter Description here",
			prjOwner:"",
			prjStartDate:"",
			prjEndDate:"",
			action: "create"
	};
	
	//$scope.readonly = true;
	
	$scope.get = function(id, edit) {
		console.log("Get Projects");
		
		$scope.id = id;
		$http({
			url: "/chronos/projects",
			method: "GET",
			params: {action: "view", type: "json",id: id}
		}).then(function(response){
			console.log("Response from backend: "+ JSON.stringify(response.data));
			response.data.prjStartDate = new Date(response.data.prjStartDate);
			response.data.prjEndDate = new Date(response.data.prjEndDate);
			angular.extend($scope.project,response.data);
			$scope.project.action="edit";
			$scope.readonly = !edit;
			console.log("Project: "+$scope.project.prjDesc);
			$("#editProject").modal('show');
		});
	};
});


app.controller('tasks', function($scope,$http) {
	$scope.task = {
			id: "",
			taskSummary: "",
			taskDesc:"",
			taskStatus:"",
			taskAssignee:"",
			projectID:"",
			dueDate: "",
			action: "create"
	};
	$scope.newTask = {
			id: "",
			taskSummary: "",
			taskDesc:"",
			taskStatus:"",
			taskAssignee:"",
			projectID:"",
			dueDate: "",
			action: "create",
	};
	$scope.id = "";
	$scope.url = "/chronos/tasks";
	$scope.hideAlert=true;
	$scope.create = function() {
		return;
		$http({
			method: "POST",
			headers : {'Content-Type': 'application/x-www-form-urlencoded'},
			url: $scope.url,
			data: $('#newTaskForm').serialize()
		}).success(function(data){
			if(data.errors) {
				
			}else {
				$scope.message = data.message;
				$scope.hideAlert=false;
				$('#newTask').modal('hide');
				if(data.id!=$scope.newTask.id || $data.id>-1) {
					alert("Task Created Successfully..");	
					location.reload();
					$scope.newTask = {};
				}else {
					alert("Task Creation Failed");
				}
			}
		});
	}
	$scope.edit = function() {
		$http({
			method: "POST",
			headers : {'Content-Type': 'application/x-www-form-urlencoded'},
			url: $scope.url,
			data: $('#editTaskForm').serialize()
		}).success(function(data){
			if(data.errors) {
				
			}else {
				$scope.message = data.message;
				$scope.hideAlert=false;
				$('#editTask').modal('hide');
				if(data.id!=$scope.newTask.id || $data.id>-1) {
					alert("Task Updated..");	
					location.reload();
					$scope.newTask = {};
				}else {
					alert("Task Update Failed");
				}
			}
		});
	}
	
	$scope.readonly = true;
	
	$scope.get = function(id, edit) {
		console.log("Get Tasks");
		
		$scope.id = id;
		$http({
			url: "/chronos/tasks",
			method: "GET",
			params: {action: "view", type: "json",id: id}
		}).then(function(response){
			console.log("ET resp:"+JSON.stringify(response.data));
			response.data.dueDate = new Date(response.data.dueDate);
			angular.extend($scope.task,response.data);
			$scope.task.action="edit";
			$scope.readonly = !edit;
			console.log("Task: "+$scope.task.taskSummary);
			$("#editTask").modal('show');
		});
	};
	
	$scope.updateTaskStatus = function(id, status) {
		$http({
			url: "/chronos/tasks",
			method: "get",
			params: {action: "edit", type: "status",id: id, taskStatus: status}
		}).success(function(data){
			if(data.errors) {
				
			}else {
				$scope.message = data.message;
//				$scope.hideAlert=false;
				
				if(data.id!=$scope.newTask.id || data.id>-1) {
					$("#taskTable").find("tr#"+data.id).find(".task-status").html(data.taskStatus);
					$scope.id = data.id;
					$("#status").removeClass('hidden');
					
				}else {
					alert("Task Update Failed");
				}
			}
		});
	}
});


app.controller('users', function($scope,$http) {
	$scope.users = {};
	$scope.user = {
			username:"",
			password:"",
			fname: "",
			lname:"",
			email:"",
			confirmPassword:"",
			action: "signup",
			projects: []
	};
	$scope.projects = [];
	$scope.readonly = true;
	$scope.projectToAdd = {};
	$scope.projectToRemove = {};
	$scope.saveUser = function() {
		
	}
	
	$scope.addProject = function() {
		console.log('adding...');
//		$scope.user.projects = [];
		for(i = 0;i<$scope.projects.length;i++) {
			if($scope.projects[i].id==$scope.projectToAdd) {
				$scope.user.projects.push($scope.projects[i]);
			}
		}
	};
	
	$scope.removeProject = function() {//TODO FIX this binding in UI
		console.log("removing")
		delete $scope.projects[idx];
	};
	
	
	$scope.get = function(id, edit) {
		console.log("Get User");
		
		$scope.id = id;
		//get all projects
		$http({
			url: "/chronos/projects",
			method: "GET",
			params: {action: "view", type: "json",scope: "workspace"}
		})
		.then(function(response){
			$scope.projects = response.data.projects;
		});
		$http({
			url: "/chronos/users",
			method: "GET",
			params: {action: "view", type: "json",id: id}
		}).then(function(response){
			angular.extend($scope.user,response.data);
			$scope.user.action="edit";
			$scope.readonly = !edit;
			console.log("User: "+$scope.user.username);
			$("#editUserModal").modal('show');
		});
	};
});
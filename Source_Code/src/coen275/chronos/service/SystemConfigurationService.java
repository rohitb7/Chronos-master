package coen275.chronos.service;

import java.util.List;
import java.util.Map;

import coen275.chronos.project.Project;
import coen275.chronos.project.Workspace;
import coen275.chronos.service.impl.WorkspaceException;

public interface SystemConfigurationService {
	Workspace createWorkspace(String name, String displayname, String description) throws WorkspaceException;
	Workspace updateWorkspace(Workspace w);
	Project createProject(int workspaceId, Project p);
	Project createProject(String workspaceName, Project p);
	Workspace getWorkspace(String name);
	List<Workspace> getWorkspaces();
	List<Project> getProjects(String workspaceName);
}

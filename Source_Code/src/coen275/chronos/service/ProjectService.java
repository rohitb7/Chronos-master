/**
 * 
 */
package coen275.chronos.service;

import java.util.List;

import coen275.chronos.project.Project;
import coen275.chronos.project.Task;

/**
 * @author kaushik
 *
 */
public interface ProjectService {
	Task createTask(int projectID, Task t);
	/**
	 * Update Due date, reassign, owner, status  etc
	 * @param t
	 * @return
	 */
	boolean updateTask(Task t);
	boolean deleteTask(Task t);
	boolean addTeamMember(int projectID, String username);
	boolean removeTeamMember(int projectId, String username);
	Project createProjectClone(Project project);
	List<Project> getProjects(String username);
	List<Task> getTasks(int projectID);
	boolean updateProject(Project p);
	Task getTask(int taskId);
	Project getProject(int projectID);
}

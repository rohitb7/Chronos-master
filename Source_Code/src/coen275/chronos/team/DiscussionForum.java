package coen275.chronos.team;

import java.util.*;

/**
 * 
 */
public class DiscussionForum {

	/**
	 * Default constructor
	 */
	public DiscussionForum() {
	}

	/**
	 * 
	 */
	protected Set<Post> posts;

	/**
	 * @return the posts
	 */
	public Set<Post> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
}
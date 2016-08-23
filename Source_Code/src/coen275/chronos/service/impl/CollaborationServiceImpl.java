/**
 * 
 */
package coen275.chronos.service.impl;

import java.sql.Date;

import coen275.chronos.service.CollaborationService;
import coen275.chronos.team.Comments;
import coen275.chronos.team.Post;

/**
 * @author kaushik
 *
 */
public class CollaborationServiceImpl implements CollaborationService {

	/* (non-Javadoc)
	 * @see coen275.chronos.service.CollaborationService#createPost(java.lang.String, java.lang.String, java.sql.Date, java.lang.String)
	 */
	@Override
	public Post createPost(String title, String summary, Date timestamp, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.CollaborationService#createPost(coen275.chronos.team.Post)
	 */
	@Override
	public Post createPost(Post p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.CollaborationService#addCommentToPost(int, java.lang.String, java.lang.String)
	 */
	@Override
	public Comments addCommentToPost(int postId, String comment, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.CollaborationService#deletePost(int)
	 */
	@Override
	public Post deletePost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

}

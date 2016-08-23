package coen275.chronos.service;

import java.sql.Date;

import coen275.chronos.team.Comments;
import coen275.chronos.team.Post;

public interface CollaborationService {
	Post createPost(String title, String summary, Date timestamp,String username);
	Post createPost(Post p);
	Comments addCommentToPost(int postId,String comment, String username);
	Post deletePost(int postId);
}

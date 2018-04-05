package com.niit.dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;

public interface BlogPostLikesDAO {
	
	BlogPostLikes hasUserLikedBlog(int blogid, String email);

	BlogPost updateLikes(int id, String email);

}

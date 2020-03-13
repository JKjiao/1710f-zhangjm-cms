package com.zhangjiaming.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjiaming.dao.CommentDao;
import com.zhangjiaming.dao.UserDao;
import com.zhangjiaming.pojo.Comment;
import com.zhangjiaming.pojo.User;
import com.zhangjiaming.service.ArticleService;
import com.zhangjiaming.service.CommentService;
import com.zhangjiaming.service.UserService;
@Service
public class CommentServiceImpl implements CommentService {


	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;

	@Override
	public boolean add(Comment comment) {
		comment.setCreated(new Date());
		boolean result = commentDao.insert(comment)>0;
		/** 修改文章的评论数 **/
		boolean result2 = articleService.updateCommentCnt(comment.getArticleId());
		return result && result2;
	}

	@Override
	public PageInfo<Comment> getPageInfo(Integer articleId, Integer pageNo, Integer pageSize) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		PageHelper.startPage(pageNo, pageSize);
		List<Comment> commentList = commentDao.select(comment);
		/*
		 * commentList.forEach(c->{ User user = userDao.selectById(c.getUserId());
		 * c.setNickname(user.getNickname()); c.setHeadimg(user.getHeadimg()); });
		 */
		return new PageInfo<>(commentList);
	}

		
}

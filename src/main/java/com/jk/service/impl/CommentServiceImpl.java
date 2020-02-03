package com.jk.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.dao.CommentDao;
import com.jk.dao.UserDao;
import com.jk.pojo.Comment;
import com.jk.pojo.User;
import com.jk.service.ArticleService;
import com.jk.service.CommentService;
import com.jk.service.UserService;
import com.zhangjm.util.RandomUtil;
import com.zhangjm.util.StringUtil;
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
		commentList.forEach(c->{
			User user = userDao.selectById(c.getUserId());
			c.setNickname(user.getNickname());
			c.setHeadimg(user.getHeadimg());
		});
		return new PageInfo<>(commentList);
	}

	@Override
	public Comment getRandomComment() {
		Comment comment = new Comment();
		return comment;
	}
}

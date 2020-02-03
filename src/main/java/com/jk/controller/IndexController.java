package com.jk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.jk.pojo.Article;
import com.jk.pojo.Category;
import com.jk.pojo.Channel;
import com.jk.pojo.Comment;
import com.jk.pojo.Slide;
import com.jk.pojo.User;
import com.jk.service.ArticleService;
import com.jk.service.CommentService;
import com.jk.service.SlideService;
import com.jk.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService; 
	/**
	 * @Title: index   
	 * @Description: 首页   
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/")
	public String index(Model model) {
		hot(model, 1);
		return "index";
	}
	/**
	 * @Title: hot   
	 * @Description: 热门分页   
	 * @param: @param model
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/hot/{pageNum}.html")
	public String hot(Model model,@PathVariable Integer pageNum) {
		List<Channel> channelList = articleService.getChannelAll();
		List<Slide> slideList = slideService.getAll();
		PageInfo<Article> pageInfo = articleService.getHotList(pageNum,4);
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("channelList", channelList);
		model.addAttribute("slideList", slideList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("newArticleList", newArticleList);
		return "index";
	}
	
	/**
	 * @Title: channel   
	 * @Description: 频道页   
	 * @param: @param model
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/{channelId}/{cateId}/{pageNum}.html")
	public String channel(Model model,@PathVariable Integer channelId,@PathVariable Integer cateId,@PathVariable Integer pageNum) {
		List<Channel> channelList = articleService.getChannelAll();
		List<Slide> slideList = slideService.getAll();
		PageInfo<Article> pageInfo = articleService.getList(channelId,cateId,pageNum,2);
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		Channel channel = articleService.getChannelByChannelId(channelId);
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("channelList", channelList);
		model.addAttribute("cateList", cateList);
		model.addAttribute("slideList", slideList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("channel", channel);
		model.addAttribute("newArticleList", newArticleList);
		return "index";
	}
	/**
	 * @Title: articleDetail   
	 * @Description: 文章详情页  
	 * @param: @param id
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/article/detail/{id}.html")
	public String articleDetail(@PathVariable Integer id,@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,Model model) {
		Article article = articleService.getById(id);
		User user = userService.getById(article.getUser_id());
		article.setNickname(user.getNickname());
		model.addAttribute("article", article);
		/** 查询相关文章 **/
		List<Article> relArticelList = articleService.getRelArticelList(article.getChannel_id(), article.getCategory_id(), article.getId(), 3);
		model.addAttribute("relArticelList", relArticelList);
		/** 设置文章点击量，若点击量大于20成为热点文章 **/
		articleService.setHitsAndHot(id);
		/** 评论列表 **/
		PageInfo<Comment> commentPageInfo = commentService.getPageInfo(article.getId(), pageNum, 10);
		model.addAttribute("pageInfo", commentPageInfo);
		return "article-detail";
	}
}

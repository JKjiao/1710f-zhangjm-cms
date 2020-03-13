package com.zhangjiaming.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sun.tools.javac.resources.compiler;
import com.zhangjiaming.commons.CmsConst;
import com.zhangjiaming.pojo.Article;
import com.zhangjiaming.pojo.Category;
import com.zhangjiaming.pojo.Channel;
import com.zhangjiaming.pojo.Collect;
import com.zhangjiaming.pojo.Comment;
import com.zhangjiaming.pojo.CommentType;
import com.zhangjiaming.pojo.Link;
import com.zhangjiaming.pojo.Slide;
import com.zhangjiaming.pojo.User;
import com.zhangjiaming.service.ArticleService;
import com.zhangjiaming.service.CollectService;
import com.zhangjiaming.service.CommentService;
import com.zhangjiaming.service.LinkService;
import com.zhangjiaming.service.SlideService;
import com.zhangjiaming.service.UserService;
import com.zhangjm.util.DateUtil;

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
	@Autowired
	private LinkService linkService;
	@Autowired
	private CollectService collectService;
	
	/**
	 * @Title: index   
	 * @Description: 首页   
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/")
	public String index(HttpSession session) {
		hot(1, session);
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
	public String hot(@PathVariable Integer pageNum,HttpSession session) {
		
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println("访问开始时间+========================——"+currentTimeMillis);
		
		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
		Thread t4 = null;
		Thread t5 = null;
		
		t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				/** 频道 **/
				List<Channel> channelList = articleService.getChannelAll();
				session.setAttribute("channelList", channelList);
			}
		});
		
		
		t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				/** 轮播图 **/
				List<Slide> slideList = slideService.getAll();
				session.setAttribute("slideList", slideList);
			}
		});
		
		t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/** 热点文章 **/
				Article article = new Article();
				article.setStatus(1);
				article.setHot(1);
				article.setCreated(DateUtil.getDateByBefore());
				PageInfo<Article> hotArticleInfo = articleService.getPageInfo(article, 1, 3);
				session.setAttribute("hotArticleInfo", hotArticleInfo);;
				
			}
		});
		
		
		
		t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/** 最新图片 **/
				Integer commentType = CommentType.IMAGE.getCode();
				List<Article> selectArticleofImages = articleService.selectArticleofImages(commentType);
				session.setAttribute("articleofImages", selectArticleofImages);
			}
		});
		
		t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/** 最新文章 **/
				List<Article> newArticleList = articleService.getNewList(6);
				session.setAttribute("newArticleList", newArticleList);
			}
		});
		
		
		
		// 启动线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		try {
			// 让子线程都执行完,在执行主线程
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		/** 友情链接 **/
		List<Link> linkList = linkService.getLinkListAll();
		session.setAttribute("linkList", linkList);
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
		PageInfo<Article> pageInfo = articleService.getList(channelId,cateId,pageNum,4);
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		Channel channel = articleService.getChannelByChannelId(channelId);
		List<Article> newArticleList = articleService.getNewList(6);
		//频道
		model.addAttribute("channelList", channelList);
		//分类
		model.addAttribute("cateList", cateList);
		//轮播图
		model.addAttribute("slideList", slideList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("channel", channel);
		model.addAttribute("newArticleList", newArticleList);
		
		/** 最新图片 **/
		Integer commentType = CommentType.IMAGE.getCode();
		List<Article> selectArticleofImages = articleService.selectArticleofImages(commentType);
		model.addAttribute("articleofImages", selectArticleofImages);
		
		/** 热文 **/
		PageInfo<Article> hotArticleInfo = articleService.getHotList(1, 4);
		model.addAttribute("hotArticleInfo", hotArticleInfo);	
		/** 友情链接 **/
		List<Link> linkList = linkService.getLinkListAll();
		model.addAttribute("linkList", linkList);
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
	public String articleDetail(@PathVariable Integer id,@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
								Model model, HttpSession session) {
		Article article = articleService.getById(id);
		User user = userService.getById(article.getUser_id());
		article.setNickname(user.getNickname());
		model.addAttribute("article", article);
		/** 查询相关文章 **/
		List<Article> relArticelList = articleService.getRelArticleList(article.getChannel_id(), article.getCategory_id(), article.getId(), 3);
		model.addAttribute("relArticelList", relArticelList);
		/** 设置文章点击量，若点击量大于20成为热点文章 **/
		articleService.setHitsAndHot(id);
		/** 评论列表 **/
		PageInfo<Comment> commentPageInfo = commentService.getPageInfo(article.getId(), pageNum, 6);
		model.addAttribute("pageInfo", commentPageInfo);
		
		//查询该文章是否被用户收藏过
		User user2 = (User) session.getAttribute(CmsConst.UserSessionKey);
		if(user2 != null) {
			Collect collect = collectService.selectByTtileAndUserId(article.getTitle(), user2.getId());
			model.addAttribute("collect", collect);
		}
		return "article-detail";
	}
	
	@ResponseBody
	@RequestMapping("collect")
	 public boolean collect(Collect collect,HttpSession session) {
		
		User user = (User) session.getAttribute(CmsConst.UserSessionKey);
		if(null ==user) {//session过期。
			return false;
		}
		collect.setUser_id(user.getId());
		collect.setCreated(new Date());
		return collectService.insert(collect) >0;
	 }
	
//取消收藏
	@ResponseBody
	@RequestMapping("unCollect")
	public boolean unCollect(String  id,HttpSession session) {
		User user = (User) session.getAttribute(CmsConst.UserSessionKey);
		if(null ==user) {//session过期。
			return false;
		}
		return collectService.delete(id) >0;
	}
	
}

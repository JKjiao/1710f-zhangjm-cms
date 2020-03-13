package com.zhangjiaming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhangjiaming.commons.CmsConst;
import com.zhangjiaming.commons.JsonResult;
import com.zhangjiaming.pojo.Article;
import com.zhangjiaming.pojo.Category;
import com.zhangjiaming.pojo.Channel;
import com.zhangjiaming.pojo.Favorite;
import com.zhangjiaming.pojo.User;
import com.zhangjiaming.service.ArticleService;
import com.zhangjiaming.service.FavoriteService;
@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	//展示我的收藏夹
		@RequestMapping("selects")
		public String selects(Model model,@RequestParam(defaultValue = "1")int pageNum,HttpSession session) {
			User user = (User) session.getAttribute(CmsConst.UserSessionKey);
			if(user!=null) {
				PageInfo info = favoriteService.getFavoriteList(pageNum,8,user);
				model.addAttribute("pageInfo", info);
				return "article/favorite";
			}else {
				//如果没有登录  去登录
				return "user/login";
			}		
		}
			
			
		//收藏
		@ResponseBody
		@RequestMapping("addFavo")
		public int addFavo(Favorite favo,HttpSession session) {
			//获得登录的对象
			User user = (User) session.getAttribute(CmsConst.UserSessionKey);
			if(user!=null) {
				favo.setUser(user);
			}	
			//执行收藏
			int i=0;
			try {
				i = favoriteService.addFavorite(favo);
			} catch (Exception e) {
				i=-1;
				e.getMessage();//打印异常的信息
				e.printStackTrace();
			}		
			return i;
		}
		
		//展示我的收藏夹
		@ResponseBody
		@PostMapping("deleteFavorite")
		public int deleteFavorite(Integer id) {
			return favoriteService.deleteFavorite(id);
		}
		
		
		@GetMapping("images")
		public String toPublishImage() {
			
			return "article/images";
		}
	
	/**
	 * @Title: add   
	 * @Description: 跳转到文章编辑页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/add")
	public String toAdd(Integer id,Model model) {
		List<Channel> channelList = articleService.getChannelAll();
		model.addAttribute("channelList", channelList);
		if(id!=null) {
			Article article = articleService.getById(id);
			List<Category> cateList = articleService.getCateListByChannelId(article.getChannel_id());
			model.addAttribute("article", article);
			model.addAttribute("cateList", cateList);
		}
		return "article/add";
	}
	/**
	 * @Title: save   
	 * @Description: 保存文章   
	 * @param: @param article
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@PostMapping("/save")
	public @ResponseBody JsonResult save(Article article,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		articleService.save(article);
		return JsonResult.sucess();
	}
	/**
	 * @Title: getCateListByChannelId   
	 * @Description: 根据频道Id查询分类列表  
	 * @param: @param channelId
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@GetMapping("/getCateListByChannelId")
	public @ResponseBody JsonResult getCateListByChannelId(Integer channelId) {
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		return JsonResult.sucess(cateList);
	}
	
	/**
	 * @Title: articles   
	 * @Description: 文章管理（我的文章）   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/articles")
	public String articles(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",defaultValue="2") Integer pageSize) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		PageInfo<Article> pageInfo = articleService.getPageInfo(article, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "article/articles";
	}
	/**
	 * @Title: deleteByIds   
	 * @Description: 根据Ids批量删除文章   
	 * @param: @param ids
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@PostMapping("/deleteByIds")
	public @ResponseBody JsonResult deleteByIds(String ids) {
		articleService.deleteByIds(ids);
		return JsonResult.sucess();
	}
}

package com.zhangjiaming.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Document(indexName = "1710findex", type="article")
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 所属栏目 **/
	private String channel_name;
	/** 所属分类 **/
	private String category_name;
	/** 用户昵称 **/
	private String nickname;
	
	private String statusStr;
	
	@Id
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer id;
	/** 标题 **/
	@Field(store=true, index=true, type=FieldType.Text, analyzer="ik_smart", searchAnalyzer = "ik_smart")
	private String title;
	/** 文章内容 **/
	@Field(store=true, index=false, type=FieldType.Text, analyzer="ik_smart", searchAnalyzer = "ik_smart")
	private String content;
	/** 标题图片 **/
	@Field(store=true, index=false, type=FieldType.Text)
	private String picture;
	/** 所属栏目 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer channel_id;
	/** 所属分类 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer category_id;
	/** 用户Id **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer user_id;
	/** 点击次数 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer hits;
	/** 是否热门 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer hot;
	/** 0：刚发布；2：草稿；1审核通过；-1 审核未通过;3:禁用 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer status;
	/** 0:正常,1:删除 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer deleted;
	/** 发布时间 **/
	@Field(store=true, index=false, type=FieldType.Date)
	private Date created;
	/** 更新时间 **/
	@Field(store=true, index=false, type=FieldType.Date)
	private Date updated;
	/** 评论数量 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer commentCnt;
	/** 投诉次数 **/
	@Field(store=true, index=false, type=FieldType.Integer)
	private Integer tousuCnt;
	
	/** 评论类型0,文本， 1，图片 **/
	private CommentType commentType;

	
	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}

	public Integer getTousuCnt() {
		return tousuCnt;
	}

	public void setTousuCnt(Integer tousuCnt) {
		this.tousuCnt = tousuCnt;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	public void setCommentType(CommentType commentType) {
		this.commentType = commentType;
	}

	@Override
	public String toString() {
		return "Article [channel_name=" + channel_name + ", category_name=" + category_name + ", nickname=" + nickname
				+ ", statusStr=" + statusStr + ", id=" + id + ", title=" + title + ", content=" + content + ", picture="
				+ picture + ", channel_id=" + channel_id + ", category_id=" + category_id + ", user_id=" + user_id
				+ ", hits=" + hits + ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", created="
				+ created + ", updated=" + updated + ", commentCnt=" + commentCnt + ", tousuCnt=" + tousuCnt
				+ ", commentType=" + commentType + "]";
	}

	public Article(String channel_name, String category_name, String nickname, String statusStr, Integer id,
			String title, String content, String picture, Integer channel_id, Integer category_id, Integer user_id,
			Integer hits, Integer hot, Integer status, Integer deleted, Date created, Date updated, Integer commentCnt,
			Integer tousuCnt, CommentType commentType) {
		super();
		this.channel_name = channel_name;
		this.category_name = category_name;
		this.nickname = nickname;
		this.statusStr = statusStr;
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.channel_id = channel_id;
		this.category_id = category_id;
		this.user_id = user_id;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.commentCnt = commentCnt;
		this.tousuCnt = tousuCnt;
		this.commentType = commentType;
	}

	public Article() {
		super();
	}
	
	
}

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title }</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<body>
	<div class="header">
		<ul class="nav">
			<li class="nav-item" style="margin-left: 12px;">
				<a	class="navbar-brand" href="#"> 
					<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg"	width="30" height="30" alt="">
				</a>
			</li>
			<li class="nav-item"><a class="nav-link active" h	ref="/">首页</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">个人中心</a></li>
			<li class="nav-item"><a class="nav-link" href="#">登录</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">退出</a></li>
		</ul>
	</div>
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-1">
			<div class="col-6">
				<h1>${article.title }</h1>
				<h3 style="color: #777;">${article.nickname }    发布时间：<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd日"/></h3>
				<div>
					<div class="article-content">
						${article.content }
					</div>  
				</div>
				<form id="commentForm">
					<div class="row" style="margin-top: 20px">
						<input type="hidden" id="articleId" name="articleId" value="${article.id }">
						  <div class="col-8 form-group">
							<!--<label for="exampleFormControlTextarea1" style="color:blue">评论</label> -->
						    <textarea class="form-control" id="content" name="content" rows="1"></textarea>
						  </div>
						  <div class="col-4">
							  <button type="button" class="btn btn-primary" onclick="submitComment()">发布</button>					  
						  </div>
						  <div class="media">
							  <img src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87" class="mr-3" alt="..." style="width: 32px">
							  <div class="media-body">
							    <h5 class="mt-0">Media heading</h5>
							    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
							  </div>
						</div>
						 <div class="media">
							  <img src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87" class="mr-3" alt="..." style="width: 32px">
							  <div class="media-body">
							    <h5 class="mt-0">Media heading</h5>
							    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
							  </div>
						</div>
						 <div class="media">
							  <img src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87" class="mr-3" alt="..." style="width: 32px">
							  <div class="media-body">
							    <h5 class="mt-0">Media heading</h5>
							    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
							  </div>
						</div>
					</div>
				</form>
			</div>
			
			<div class="col-3">
				<div class="right">
					<div>相关文章</div>
					<ul class="list-unstyled">
						<c:forEach items="${relArticelList }" var="item">
							<li class="media">
								<a href="/article/detail/${item.id }.html" target="_blank"><img src="${item.picture }" style="height: 72px; width: 72px;" class="mr-3" alt="..."></a>
								<div class="media-body">
									<h5 class="mt-0 mb-1"><a href="/article/detail/${item.id }.html" target="_blank">${item.title }</a></h5>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function submitComment() {
			var formData = $("#commentForm").serialize();
			console.log(formData);
			$.post('/comment/add',formData,function(res) {
						
					})
		}
	</script>
</body>
</html>
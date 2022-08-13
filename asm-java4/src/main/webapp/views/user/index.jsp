<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="container-fluid tm-container-content tm-mt-60">
		<div class="row mb-4">
			<h2 class="col-6 tm-text-primary">Latest Videos</h2>
			<div class="col-6 d-flex justify-content-end align-items-center">
				<form action="" class="tm-text-primary">
					Page <input type="text" value="1" size="1"
						class="tm-input-paging tm-text-primary"> of 200
				</form>
			</div>
		</div>
		<div class="row tm-mb-90 tm-gallery">
			<c:forEach items="${videos}" var="video">
				<div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
					<h5 class="card-title-success">
						${video.title}
					</h5>
					<figure class="effect-ming tm-video-item">
						<img src="<c:url value='/template/user/img/img-01.jpg'/>"
							alt="Image" class="img-fluid">
						<figcaption
							class="d-flex align-items-center justify-content-center">
							<h2>View more</h2>
							<a href="<c:url value='/video?action=watch&id=${video.href}'/>">View more</a>
						</figcaption>
					</figure>
					<div class="d-flex justify-content-between tm-text-gray">
						<span class="tm-text-gray-light">${video.views} views</span> 
						<span class="tm-text-gray-light">${video.shares} shares</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- row -->

		<div class="row tm-mb-90">
			<div
				class="col-12 d-flex justify-content-between align-items-center tm-paging-col">
				<c:if test="${currentPage == 1}">
					<a href="javascript:void(0);" class="btn btn-primary tm-btn-prev mb-2 ">Previous</a>
				</c:if>			
				<div class="tm-paging d-flex">
					<c:forEach varStatus = "i" begin = "1" end = "${maxPage}">
						<a href="index?page=${i.index}" class="${currentPage == i.index ? 'active' : '' } tm-paging-link">${i.index}</a>
					</c:forEach>
				</div>
				<c:if test="${currentPage == maxPage}">
				
				</c:if>			
				<a href="javascript:void(0);" class="btn btn-primary tm-btn-next">Next
					Page</a>
			</div>
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
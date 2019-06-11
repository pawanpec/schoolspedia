<%@ include file="/WEB-INF/pages/include.jsp"%>
<style>
#list4 { width:320px; font-family:Georgia, Times, serif; font-size:15px; }
#list4 ul { list-style: none; }
#list4 ul li { }
#list4 ul li a { display:block; text-decoration:none; color:#337ab7; background-color:#FFFFFF; line-height:20px;
  border-bottom-style:solid; border-bottom-width:1px; border-bottom-color:#CCCCCC; padding-left:10px; cursor:pointer; }
#list4 ul li a:hover { color:blue;}
#list4 ul li a strong { margin-right:10px; }
</style>
<title>${fn:toLowerCase(content.title)} School Address Contact Detail |Reviews Rating</title>
	<c:if test="${not empty content.review.oar}">
		 <span itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating" 
		 style="position: absolute; font-size:0px; left:0; height:0; width:0; line-height: 0; top:0; overflow: hidden;">
		  <span itemprop="itemReviewed" itemscope itemtype="http://schema.org/Thing">
    		<span itemprop="name">${fn:toLowerCase(content.title)}</span>
  		  </span>
		 <span itemprop="ratingValue">${content.review.oar}</span> out of 
      	 <span itemprop="bestRating">5</span> with
     	 <span itemprop="ratingCount">${fn:length(reviews)}</span> ratings
		</span>
	</c:if>
	<c:if test="${empty content.review.oar}">
		 <span itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating" 
		 style="position: absolute; font-size:0px; left:0; height:0; width:0; line-height: 0; top:0; overflow: hidden;">
			  <span itemprop="itemReviewed" itemscope itemtype="http://schema.org/Thing">
	    		<span itemprop="name">${fn:toLowerCase(content.title)}</span>
	  		  </span>
			 <span itemprop="ratingValue">2.5</span> out of 
	      	 <span itemprop="bestRating">5</span> with
	     	 <span itemprop="ratingCount">1</span> ratings
		</span>
	</c:if>
	
	

<div class="container">
<ol vocab="http://schema.org/" typeof="BreadcrumbList" class="breadcrumb">
		
		<li property="itemListElement" typeof="ListItem"><a href="/" property="item" typeof="WebPage" ><img
				src="<%=WebConstants.IMAGE_URL%>images/spacer.gif"
				class="cus-icon cus-home-small" alt="home"></a>
		</li>
		<c:if test="${not empty content.location.province}">
		<li property="itemListElement" typeof="ListItem"  class="active"><a property="item" typeof="WebPage" 
				href="/india/schools-in/${content.location.province}">Schools in
					${content.location.province}</a></li>
		</c:if>
		<c:if test="${not empty content.location.city}">
			<li property="itemListElement" typeof="ListItem"  class="active"><a 
			property="item" typeof="WebPage" href="/india/${content.location.province}/schools-in/${content.location.city}">
      Top Schools In  ${content.location.city}</a></li>
		</c:if>
		<li property="itemListElement" typeof="ListItem"  class="active">${fn:toLowerCase(content.title)}</li>
	</ol>
	<c:if test="${not empty msg}">
		<div class="isa_success">${msg}for ${fn:toLowerCase(content.title)}</div>
		<br></br>
	</c:if>
	<c:if test="${not empty content.schoolsImages.LogoPath}">
	<c:set var="logoPath"
		value="${contextPath}/${content.schoolsImages.LogoPath}" />
</c:if>
<c:if test="${empty content.schoolsImages.LogoPath}">
	<c:set var="logoPath" value="${contextPath}/images/logo.png" />
</c:if>
	<h1>
		${fn:toLowerCase(content.title)}
		<img
		src="${logoPath}" width="20px" height="20px" />
	</h1>
	<%@include file="schoolBanner.jsp"%>
</div>
<div class="container">
	<div class="left_coloum">
		<div class="details_grid">
			<%@include file="school_main_image.jsp"%>
			<%@include file="school_main_content.jsp"%>
			<%@include file="schoolOverview.jsp"%>
			<br></br>
			<c:if test="${content.location.province eq 'delhi'}">
					<%@include file="admissionPage.jsp"%>
			</c:if>
			<c:if test="${not empty reviews}">
				<%@include file="schoolReviewWidget.jsp"%>
			</c:if>
			<%@include file="schoolmap.jsp"%>
			<c:forEach items="${content.tags}" var="tag">	
			<a href="#">${tag}</a> |
			</c:forEach>
		</div>
	</div>
	<div class="right_coloum">
		<!--%@include file="rating.jsp"%</br> -->
		<%@include file="admissionApplication.jsp"%>
		<c:if test="${newsCount>0}">
			<%@include file="schoolNews.jsp"%>
		</c:if>

		<br>
		<c:if test="${not empty relatedSchool}">
		<%@include file="relatedSchool.jsp"%>
		</c:if>
		
		
	</div>
</div>

<style>
.isa_success {
	color: #4F8A10;
	background-color: #DFF2BF;
	text-align: center;
	font: 20px Arial, Helvetica, sans-serif;
	text-transform: uppercase;
}
</style>

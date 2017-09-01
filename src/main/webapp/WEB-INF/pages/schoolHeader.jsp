<%@ page import="com.spedia.utils.SocialUtility"%>
<%@ include file="include.jsp"%>
<%
String username=SocialUtility.getCookieByKey(request, "username");
Boolean isLogin=Boolean.FALSE;
if(username!=null){
	isLogin=true;
}
String isAdmin=SocialUtility.getCookieByKey(request, "isAdmin");
String socialLoginId=SocialUtility.getCookieByKey(request, "socialLoginId");
%>

<c:set var="username" value="<%=username%>" scope="request" />
<c:set var="isAdmin" value="<%=isAdmin%>"   scope="request" />
<c:set var="isLogin" value="<%=isLogin%>"   scope="request" />
<c:set var="socialLoginId" value="<%=socialLoginId%>"   scope="request" />
<c:set var="orgURL" value="${requestScope['javax.servlet.forward.request_uri']}"  scope="session"/>
<script type="text/javascript">
var isLogin='<c:out value="${isLogin}"/>';
function formSubmit() {
	document.getElementById("logoutForm").submit();
}
</script>
<style>
.clearfix{ clear:both}
  h1{ margin-top:5px;}
  .footer { clear:both}
  .clr{ clear:both}
@media (max-width: 650px) {
.navbar-toggle {
    position: absolute;
    top: 70px;
    right: 0;
}
}

</style>
<div class="top_header">
	<div class="container">
	
	<div class="social_icons">
		<div id="loginSuccess">
			<c:if test="${username!=null}">
					 Welcome : ${username} | 
					 <input type='button' value='Logout' onclick='Logout();' />
			</c:if></div>
			<ul>
				<li class="" id="loginLink"><c:if test="${username==null}">
						<a href="javascript:void(0)" onclick="Login()"><img
							src="<%=WebConstants.IMAGE_URL%>images/facebook_login1.jpg" style="width: 72px;  margin-top: 4px;"/></a>
					</c:if></li>
				<!-- <li><a
					href="/spedia/our-fp-product.html?cat=baby_care" >Our Product</a></li> -->
			   <li class="facebook"><a
					href="https://www.facebook.com/schoolspedia" rel="nofollow" target="_blank"><i
						class="fa fa-facebook"></i></a></li>
				<li class="twitter"><a href="https://twitter.com/schoolspedia" rel="nofollow" target="_blank"><i
						class="fa fa-twitter"></i></a></li>
				<li class="google"><a
					href="https://plus.google.com/+SchoolsPediaNurseryAdmission/posts" rel="nofollow" target="_blank"><i
						class="fa fa-google"></i></a></li>
				<li class="email"><c:if test="${isLogin }"> <a href="/spedia/userHome.html">My Home</a></c:if></li>
				<!--  <li class="linkdin"><a href=""><i class="fa fa-linkedin"></i></a></li>
            <li class="email"><a href=""><i class="fa fa-envelope"></i></a></li> -->
			</ul>
			
		</div>
		
		<div class="glbl-srch-new">
	<%@include file="adsenseSearchBox.jsp"%>
	</div>
		
	</div>
</div>

<header id="fixedNavigation" class="clearfix" style="background:url(<%=WebConstants.IMAGE_URL%>images/btmbgrpt.png) repeat-x bottom">
	<div class="clearfix" id="fixed">
		<div class="container" id="sphdrnew">
			<div class="xs-headerl">
				<button type="button" class="navbar-toggle mobilemnubtn"
					id="navbar-toggle" data-target="#navbar-collapse1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="logo">
					<a href="/"><img
						src="<%=WebConstants.IMAGE_URL%>images/logo.png"
						alt="Schoolspedia" style=" height:75px"></a>
				</div>
			</div>
			
			<nav class="main-mnu" id="navbar-collapse1">
				<ul class="nav navbar-nav">
					<li class="home"><i class="top"></i><a href="/"><img
							src="<%=WebConstants.IMAGE_URL%>images/spacer.gif" /><span>home</span></a><i
						class="bot"></i></li>
					<li class="discover"><i class="top"></i><a
						href="/spedia/searchSchool.html"><img
							src="<%=WebConstants.IMAGE_URL%>images/spacer.gif" /><span>Search
								School</span></a><i class="bot"></i></li>
					<li class="jobs"><i class="top"></i><a
						href="/nursery-admission"><img
							src="<%=WebConstants.IMAGE_URL%>images/spacer.gif" /><span>Nursery
								Admission</span></a><i class="bot"></i></li>
					<li class="interview"><i class="top"></i><a
						href="/summer-camp"><img
							src="<%=WebConstants.IMAGE_URL%>images/spacer.gif" /><span>Summer
								Camp</span></a><i class="bot"></i></li>
					<li class="interview"><i class="top"></i><a
						href="/school-jobs"><img
							src="<%=WebConstants.IMAGE_URL%>images/spacer.gif" /><span>School 
								jobs</span></a><i class="bot"></i></li>
					<li class="insights"><i class="top"></i><a
						href="/forum/category/10/nursery-admission-delhi"><img
							src="<%=WebConstants.IMAGE_URL%>images/spacer.gif" /><span>Forum
								</span></a><i class="bot"></i></li>
					
					
				</ul>
				<div class="clearfix visible-sm"></div>
			<div class="admin-nav">
			 	<c:if test="${isAdmin }"> <a href="/spedia/add_news.html">Add Content</a> <span>|</span></c:if>
			   <c:if test="${isAdmin }"> <a href="/spedia/get_all_unmoderated_review.html">Review Moderation</a> <span>|</span></c:if>
			    <c:if test="${isAdmin }"> <a href="/spedia/get_all_unmoderated_content.html">Content Moderation</a> <span>|</span></c:if>
			   <c:if test="${isAdmin }"> <a href="/spedia/add_school_info.html">|Add School</a></c:if>
			   <c:if test="${isAdmin }"> <a href="/spedia/get_all_application.html">|All Application</a></c:if>
			</div>
			</nav>
			
			<c:if test="${not isAdmin }"> 
           <a href="/"><img	src="<%=WebConstants.IMAGE_URL%>images/sp-add-banner.jpg" alt="Schoolspedia" class="sp-add-banner"/></a>
           </c:if>
		</div>
	</div>
</header>

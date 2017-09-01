<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<script type="text/javascript" src="<%=WebConstants.JS_URL %>js/ckeditor/ckeditor.js"></script>
<html>
<head>
</head>
<body>
	<div class="addnews-form">
		<h3>Add News</h3>
		<form:form method="POST" action="submitNews.html"
			acceptCharset="utf-8" modelAttribute="content" enctype="multipart/form-data">
			
			<label><span>Title </span><form:input path="title"  /></label>
			
			<br/>
			<label>Body</label>
			<form:textarea cssClass="ckeditor" path="body_content"  />
			<br />
			<label><span>Load image</span> <form:input type="file" path="imageFile" /></label>
			<br />
			<c:if test="${empty content.title}">
				<label>Tags <form:input path="tags" /></label>
			<br />
			<label><span>Type</span> <form:select path="type">
				<form:option value="jobs">Job</form:option>
				<form:option value="schools_news">Schools News</form:option>
				<form:option value="articles">Article</form:option>
				<form:option value="parents_tips">Parenting Tips</form:option>
				<form:option value="summer_camp">Summer Camps</form:option>
				<form:option value="school_admission">School Admission</form:option>
				<form:option value="nursery_admission_news">Nursery Admission News</form:option>
			</form:select></label>
			<form:hidden path="sid" value="${param.sid}" />
			</c:if>
			<c:if test="${not empty content.alias}">
				<form:hidden path="alias" value="${content.alias}" />
			</c:if>
			<form:hidden path="uid" value="${socialLoginId}" />
			<form:hidden path="updatedBy" value="${socialLoginId}" />
			<br />
			<label><span>Meta keywords </span><form:input path="meta_keywords"/></label>
			<label><span>Meta description </span><form:input path="meta_description"/></label>
			<label><span>Robots</span><form:input path="robots"/></label>
			<label><span></span><input type="submit" value="Submit" class="add-sbmt-btn" /></label>
			
		</form:form>
	</div>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<title>Ask Your Query</title>
<script type="text/javascript" src="<%=WebConstants.JS_URL %>js/ckeditor/ckeditor.js"></script>
<script>
	function validateForm() {
		var name = document.forms["askForm"]["name"].value;
		if (name == null || name == "Your Name") {
			alert("name must be filled out");
			return false;
		}
		var email = document.forms["askForm"]["email"].value;
		if (email == null || email == "Your Email") {
			alert("email must be filled out");
			return false;
		}
		
	}
</script>
	<div class="addnews-form">
		<h3>Ask Your Query</h3>
		<form:form method="POST" action="submitNews.html" onsubmit="return validateForm()"  name="askForm"
			acceptCharset="utf-8" modelAttribute="content" enctype="multipart/form-data">
			
			<label><span>Subject </span><form:input path="title"  /></label>
			
			<br/>
			<label>Question Detail</label>
			<form:textarea cssClass="ckeditor" path="body_content"  />
			<form:hidden path="type" value="discussion" />
			<form:hidden path="sid" value="${param.sid}" />
		
			<form:hidden path="uid" value="${socialLoginId}" />
			<form:hidden path="updatedBy" value="${socialLoginId}" />
			<span class="isa_warning">${errorMsg}</span>
			<c:if test="${isLogin eq 'false'}">
				<img src="/spedia/jcaptcha">
				</br>
				<input type='text' name='j_captcha_response' value='Type The text as shown in image' 
				onfocus="(this.value == 'Type The text as shown in image') && (this.value = '')"
	       onblur="(this.value == '') && (this.value = 'Type The text as shown in image')"
				>
				</br>
				</td>
				<input name="name" type='text' value="Your Name" onfocus="(this.value == 'Your Name') && (this.value = '')"
	       onblur="(this.value == '') && (this.value = 'Your Name')" />
	       <br></br>
				<input name="email" type='text' value="Your Email" onfocus="(this.value == 'Your Email') && (this.value = '')"
	       onblur="(this.value == '') && (this.value = 'Your Email')" />
	       </br>
       </c:if>
			<label><span></span><input type="submit" value="Submit" class="add-sbmt-btn" /></label>
			
		</form:form>
	</div>

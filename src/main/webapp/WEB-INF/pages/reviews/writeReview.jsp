<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<title>Write the Review for ${content.title}</title>
<style>
.isa_warning {
    color: #9F6000;
    background-color: #FEEFB3;
}

.star-main {
    width: 100%;
    float: left;
    clear: both;
    margin-bottom: 7px;
    padding: 8px 2.5% 3px 2.5%;
    border: 1px solid #ebe9e9;
    position: relative;
    overflow: hidden;
    height: 42px;
}
.star-main h2{clear:both; margin:0; padding:0;  background: url(<%=WebConstants.IMAGE_URL%>images/wr-icn.png) 0 0 no-repeat; padding-left:25px; font-family: "robotolight",arial; font-size:16px; color:#2b2a2a; font-weight:bold; line-height:24px; margin-bottom:5px; }
.rating {
    width: 130px;
    position: absolute;
    top: 0px;
    right: 0px;
    background: #ebe9e9;
    padding: 12px 5px;
    height: 40px;
}
.rating-input {
    float: right;
    width: 18px;
    height: 18px;
    padding: 0;
    margin: 0 5px 0 -18px !important;
    opacity: 0;
}
.rating:hover .rating-star:hover,
.rating:hover .rating-star:hover ~ .rating-star,
.rating-input:checked ~ .rating-star {
    background-position: 0 0;
}
.rating-star,
.rating:hover .rating-star {
    position: relative;
    float: right;
    display: block;
    width: 18px;
    height: 18px;
    background: url(<%=WebConstants.IMAGE_URL%>images/wr-star.png) 0 -18px no-repeat;
}
.wrt-rvs-hd{font-family: "robotolight",arial; font-size:16px; color:#2b2a2a; font-weight:bold; line-height:26px; margin-bottom:15px;}
.wrt-rvs-hd a{ color:#23527c; font-size:20px;}
.wrt-rvs-hd span{width: 0; 	height: 0; 	border-left:7px solid transparent;	border-right: 7px solid transparent;
		border-top: 7px solid #a4a3a3; margin:0px 5px 0 5px; position:relative; top:-2px; display:inline-block}
.wrt-main{ max-width:500px; width:100%}
@media screen and (max-width:450px){
.rating { -webkit-transform:scale(.8); -moz-transform:scale(.8); transform:scale(.8);     top: -5px;    right: -13px;    padding: 18px 5px;    height: 50px;}
.star-main h2{background:none; padding-left:0px;font-size:15px; line-height:20px;}
}
</style>
<script>
	function validateForm() {
		var x = document.forms["reviewForm"]["review"].value;
		if (x == null || x == "") {
			alert("Review must be filled out");
			return false;
		}
		if (x.length < 10) {
			alert("Review must have at least 10 char");
			return false;
		}
	}
</script>
<body>
	<div class="wrt-main">
	<c:if test="${empty param.type}">
		<h3 class="wrt-rvs-hd">Please Write the Review for <span></span><br><a href="/spedia/search.html?nid=${param.nid}">${content.title}</a></h3>
	</c:if>
		<form:form method="POST" action="submitReview.html" name="reviewForm"
			modelAttribute="reviews" onsubmit="return validateForm()"  target="_parent">

<div class="star-main">
<h2>Academic Result </h2>
<div class="rating">
<input id="rating-input-a1" name="a" class="rating-input" type="radio" value="1">
<label for="rating-input-a1" class="rating-star"></label>

<input id="rating-input-a2" name="a" class="rating-input" type="radio" value="2">
<label for="rating-input-a2" class="rating-star"></label>

<input id="rating-input-a3" name="a" class="rating-input" type="radio" value="3" checked="">
<label for="rating-input-a3" class="rating-star"></label>

<input id="rating-input-a4" name="a" class="rating-input" type="radio" value="4">
<label for="rating-input-a4" class="rating-star"></label>

<input id="rating-input-a5" name="a" class="rating-input" type="radio" value="5">
<label for="rating-input-a5" class="rating-star"></label>
</div>
</div>


<div class="star-main">
<h2>Infrastructure and facilities </h2>
<div class="rating">
<input id="rating-input-b1" name="b" class="rating-input" type="radio" value="1">
<label for="rating-input-b1" class="rating-star"></label>

<input id="rating-input-b2" name="b" class="rating-input" type="radio" value="2">
<label for="rating-input-b2" class="rating-star"></label>

<input id="rating-input-b3" name="b" class="rating-input" type="radio" value="3" checked="">
<label for="rating-input-b3" class="rating-star"></label>

<input id="rating-input-b4" name="b" class="rating-input" type="radio" value="4">
<label for="rating-input-b4" class="rating-star"></label>

<input id="rating-input-b5" name="b" class="rating-input" type="radio" value="5">
<label for="rating-input-b5" class="rating-star"></label>
</div>
</div>

<div class="star-main">
<h2>Attention to students </h2>
<div class="rating">
<input id="rating-input-c1" name="c" class="rating-input" type="radio" value="1">
<label for="rating-input-c1" class="rating-star"></label>

<input id="rating-input-c2" name="c" class="rating-input" type="radio" value="2">
<label for="rating-input-c2" class="rating-star"></label>

<input id="rating-input-c3" name="c" class="rating-input" type="radio" value="3" checked="">
<label for="rating-input-c3" class="rating-star"></label>

<input id="rating-input-c4" name="c" class="rating-input" type="radio" value="4">
<label for="rating-input-c4" class="rating-star"></label>

<input id="rating-input-c5" name="c" class="rating-input" type="radio" value="5">
<label for="rating-input-c5" class="rating-star"></label>
</div>
</div>


<div class="star-main">
<h2>Co-curricular activities </h2>
<div class="rating">
<input id="rating-input-d1" name="d" class="rating-input" type="radio" value="1">
<label for="rating-input-d1" class="rating-star"></label>

<input id="rating-input-d2" name="d" class="rating-input" type="radio" value="2">
<label for="rating-input-d2" class="rating-star"></label>

<input id="rating-input-d3" name="d" class="rating-input" type="radio" value="3" checked="">
<label for="rating-input-d3" class="rating-star"></label>

<input id="rating-input-d4" name="d" class="rating-input" type="radio" value="4">
<label for="rating-input-d4" class="rating-star"></label>

<input id="rating-input-d5" name="d" class="rating-input" type="radio" value="5">
<label for="rating-input-d5" class="rating-star"></label>
</div>
</div>
			
<div class="star-main">
<h2>Quality of faculty </h2>
<div class="rating">
<input id="rating-input-e1" name="e" class="rating-input" type="radio" value="1">
<label for="rating-input-e1" class="rating-star"></label>

<input id="rating-input-e2" name="e" class="rating-input" type="radio" value="2">
<label for="rating-input-e2" class="rating-star"></label>

<input id="rating-input-e3" name="e" class="rating-input" type="radio" value="3" checked="">
<label for="rating-input-e3" class="rating-star"></label>

<input id="rating-input-e4" name="e" class="rating-input" type="radio" value="4">
<label for="rating-input-e4" class="rating-star"></label>

<input id="rating-input-e5" name="e" class="rating-input" type="radio" value="5">
<label for="rating-input-e5" class="rating-star"></label>
</div>
</div>


		
			<form:hidden path="uid" value="<%=uid%>" />
			<form:hidden path="nid" value="${nid}" />
			<form:hidden path="city" value="${content.location.city}" />
			<label for="edit-a">Your Reviews</label>
			</br>
			<form:textarea path="review" style="width: 100%;    border: 1px solid #cfcece;    height: 50px; outline: none;" />
			<form:errors path="review" />
			</br>
			<span class="isa_warning">${errorMsg}</span>
			</br>
			<c:if test="${isLogin eq 'false'}">
				<img src="/spedia/jcaptcha">
				</br>
				<input type='text' name='j_captcha_response' value='Type The text as shown in image' 
				onfocus="(this.value == 'Type The text as shown in image') && (this.value = '')"
	       onblur="(this.value == '') && (this.value = 'Type The text as shown in image')"
				>
				</br>
				</td>
				<form:input path="name"  value="Your Name" onfocus="(this.value == 'Your Name') && (this.value = '')"
	       onblur="(this.value == '') && (this.value = 'Your Name')" />
	       <br></br>
				<form:input path="email" value="Your Email" onfocus="(this.value == 'Your Email') && (this.value = '')"
	       onblur="(this.value == '') && (this.value = 'Your Email')" />
	       </br>
       </c:if>
			<input type="submit" value="Submit Review" style="background: #d63144;    border: none;    outline: none;    padding: 0px 20px;
    color: #ffffff;    width: 150px;    text-align: center;    border-radius: 3px;    float: left;    clear: both;    height: 35px;    line-height: 35px;    font-size: 15px;">
		</form:form>
	</div>
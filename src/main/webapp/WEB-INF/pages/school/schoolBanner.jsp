<style>
.showratingbox{ padding:8px;  background:#ffffff; display:none; z-index:999;  position: absolute; border:1px solid #cccccc; left:0px; top:30px}
.show-review-gbox{ padding:20px; background:#000000; opacity:0.7; position:fixed; top:0px; left:0px; bottom:0px; right:0px;  z-index:9999; overflow:auto; display:none}
.show-review-gbox-in{ width:500px; margin:auto; background:#ffffff; border-radius:5px; padding:20px; position: absolute; top:50px; left:50%; margin-left:-250px; height:500px; z-index:99999; -webkit-transform:scale(0); -moz-transform:scale(0); transform:scale(0); -webkit-transition:all .5s ease-in-out; -moz-transition:all .5s ease-in-out; transition:all .5s ease-in-out}
.show-review-gbox-in-close{width: 35px;
    border: 1px solid #666666;    border-radius: 100%;    background: #fff;    text-align: center;    height: 35px;    line-height: 35px;
    font-size: 15px;    color: #333;    display: block;    position: absolute;    top: -10px;    right: -10px;}
@media screen and (max-width:450px){
.show-review-gbox-in{width:300px !important; margin-left:-150px !important}
}
</style>
<script>
$(document).ready(function(){

    $(".count").mouseover(function(){
        $(".showratingbox").css("display", "block");
    });
    $(".count").mouseout(function(){
        $(".showratingbox").css("display", "none");
    });
	
	$(".rwtrvws").click(function(){
	 $(".show-review-gbox").show();
	 $(".show-review-gbox-in").css({"-webkit-transform":"scale(1)", "-moz-transform":"scale(1)", "transform":"scale(1)"});
	});
	
	$(".show-review-gbox-in-close").click(function(){
	 $(".show-review-gbox").hide();
	 $(".show-review-gbox-in").css({"-webkit-transform":"scale(0)", "-moz-transform":"scale(0)", "transform":"scale(0)"});
	});
});
</script>



<%
	int randBgImageIndex = 1 + (int) (Math.random() * 8);
	String defaultBGPath = WebConstants.IMAGE_URL + "images/bg_images/"
			+ randBgImageIndex + ".jpg";
%>
<c:if test="${not empty content.schoolsImages.BackGroundImagePath}">
	<c:set var="bgpath"
		value="${contextPath}/${content.schoolsImages.BackGroundImagePath}" />
</c:if>
<c:if test="${empty content.schoolsImages.BackGroundImagePath}">
	<c:set var="bgpath" value="<%=defaultBGPath%>" />
</c:if>
<div>
<c:set var="reviewCount" value="${fn:length(reviews)}" />
	<span class="fl fileupload hreview-aggregate"> 
	<span> 
	<span class="rating1"> <c:choose>
					<c:when
						test="${not empty content.review.oar}">
						<span class="stars"><fmt:formatNumber
								value="${content.review.oar}" pattern="0.0" /></span>
						<fmt:formatNumber value="${content.review.oar}" pattern="0.0" />/5
						<c:if test="${reviewCount>0}">
								<a class="count" href="#reviews"> ( ${reviewCount} Reviews )</a>
						</c:if>
						<c:if test="${reviewCount<=0}">
								<a class="count" href="#reviews"> ( 1 Reviews )</a>
						</c:if>
						
                       </c:when>
                       
					<c:otherwise>
						<span class="stars">2.5</span> 2.5/5
						<a class="count" href="#reviews"> (1 Reviews ) </a>
					</c:otherwise>
				</c:choose>
		</span>
		<div class="showratingbox"><%@include file="rating.jsp"%></div>
		</span>
		<span style="padding-left: 10px; border-left:1px solid #cccccc; margin-left:10px">
			<c:if test="${isAdmin}">
				<a href="${contextPath}/edit_school_info.html?sid=${content.nid}">Edit School</a> |
			</c:if>
			<c:if test="${isAdmin}">
				<a href="${contextPath}/add_news.html?sid=${content.nid}">Add News</a>
			</c:if>
			</span>
			
			<span class="listing-add-this">
				<div class="addthis_native_toolbox"></div>
			</span>
					<c:if test="${fn:contains(content.f, uid)}">
						<input type="button" class="btn btn-xs btn-primary"
							id="followSchool" onclick="follow(${content.nid},this,'${uid}');"
							value="FOLLOWING">
					</c:if>
					<c:if test="${not fn:contains(content.f, uid)}">
						<input type="button" class="btn btn-xs btn-primary"
							id="followSchool" onclick="follow(${content.nid},this,'${uid}');"
							value="FOLLOW">
			</c:if>
	</span>
	<div class="clearfix schoolsubnav">
			<a href="#basic-Details">School Information</a>
			<c:if test="${content.location.province eq 'delhi'}">
				<a href="#admission">Nursery Admission/Fees Details</a>
			</c:if>
			<a href="#contact-Details">Contact Details</a>
			<c:if test="${not empty content.result}">
				<a href="#result-Details">CBSE Result 2015</a>
			</c:if>
			<a href="#map-canvas">View School Map</a>
			<c:if test="${not empty reviews}">
			<a href="#reviews">Reviews</a>
			</c:if>	
		  <a href="#" id="writeReview" class="rwtrvws">Write Review</a>
		  <a href="${contextPath}/ask_question.html?sid=${content.nid}">Ask Your Query</a>
			
        
			
	</div>
</div>

<div class="show-review-gbox"></div>
<div class="show-review-gbox-in">
<a href="#" class="show-review-gbox-in-close">X</a>
<iframe src="/spedia/write_review.html?nid=${content.nid}&type=popup" style="width:100%; height:460px; border:none"></iframe>
</div>
<!-- <script>
	$(document).ready(function() {
		setTimeout(function() {
			//trigger data target here data-target="#myModal
			if (localStorage.getItem('reviewOnce') !== 'true') {
				$("#writeReview").trigger("click");
				localStorage.setItem('advertOnce', 'true');
			}
		}, 30000);
	});
</script> -->

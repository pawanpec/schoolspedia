<%@ page import="java.util.*"%>
<%
Integer rand[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14};
Collections.shuffle(Arrays.asList(rand));
int i=1;
%>

<style>
.follow_btn-new{ display:inline; position:relative; top:-3px}
.follow_btn-new input{width: 75px;
    background: #1b95e0;
    color: #fff;
    border-radius: 3px;
    font-size: 10px;
    height: 22px;
    line-height: 22px;
    border: none;}
	.school_rate{ display:inline}
	.featured_school .head{height:85px}
</style>

<div class="featured_school">
  <div class="title">FEATURED Schools In <span>Delhi</span></div>
    <c:forEach items="${topSchools}" var="school" varStatus="status">
    <c:set var="index" value="${status.index}"/>
    <div class="col-sm-3">
    <div class="tile">
     
      <div class="head">
       <!-- <div class="head_logo"></div> -->
        <div class="head_t">
          <div class="school_name"><a href="/${school.alias}" target="_blank" title="${school.title}">${school.title} </a></div>
          <div class="school_rate">
<span class="stars"><fmt:formatNumber value="${school.review.oar}" pattern="0.0"/></span> <fmt:formatNumber value="${school.review.oar}" pattern="0.0" />/5</div>


<span class="follow_btn-new">
        <c:if test="${fn:contains(school.f, uid)}">
          <input type="button"  id="followSchool_${index}" onclick="follow(${school.nid},this,'${uid}');" value="FOLLOWING">
        </c:if>
        <c:if test="${not fn:contains(school.f, uid)}">
          <input type="button" id="followSchool_${index}" onclick="follow(${school.nid},this,'${uid}');" value="FOLLOW">
        </c:if>
      </span>

        </div>
      </div>
      <div class="image"><a href="/${school.alias}" target="_blank" title="${school.title}">
      <img src="<%=WebConstants.IMAGE_URL%>images/static/<%=(int)( Math.random() * 14 ) %>.jpg" alt="${school.title}" />
      </a></div>
      <div class="reviews">
      	${school.review.count} Reviews
      </div>
      </div>
      </div>
      </c:forEach>
</div>

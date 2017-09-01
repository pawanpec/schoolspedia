<div class="news_subscribe">
     <h4> <a href="/india/${content.location.province}/schools-in/${content.location.city}">
      Top Schools In  ${content.location.city}</a></h4>
</div>
<div id="list4">
   <ul>
<c:forEach items="${relatedSchool}" var="c" begin="1" end="10">		
	<div class="news_tick">				
  <div class="news_title"> <li> <span></span><a href="/${c.alias}">${fn:toLowerCase(c.title)}</a></li></div>
   </div>
</c:forEach>  
</ul>
</div>    
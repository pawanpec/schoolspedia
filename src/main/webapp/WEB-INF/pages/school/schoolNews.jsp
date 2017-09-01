<div class="news_subscribe">
        <h4>Recent Update</h4>
</div>
<div id="list4">
   <ul>
<c:forEach items="${news}" var="newsContent">						
      <div class="news_tick">
        <c:if test="${not empty newsContent.imagePath}">
        <div class="image"></div>
        </c:if>
        <div class="image_data">
         <div class="news_title"><li> <a href="/${newsContent.alias}">${fn:toLowerCase(newsContent.title )}</a></li></div>
        <%--   <div class="news_desc">${newsContent.body.summary}.</div> --%>
        </div>
      </div>
</c:forEach>    
</ul>
</div>    

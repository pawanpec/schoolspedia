<%
int random=(int)Math.floor((Math.random()*10+1));
String defaultPath1=WebConstants.IMAGE_URL+"images/static/"+random+".jpg";
%>
<div class="details_image">
<c:if test="${empty content.schoolsImages.BackGroundImagePath}">
								<img src='<%=defaultPath1%>' id="about_img" />
						   </c:if>
						   <c:if test="${not empty content.schoolsImages.BackGroundImagePath}">
							<img src='<%=WebConstants.IMAGE_URL %>${content.schoolsImages.BackGroundImagePath}' id="about_img" />
</c:if>
</div>
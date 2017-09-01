<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%@ page import="com.spedia.utils.WebConstants"%>
<script type="text/javascript" src="<%=WebConstants.JS_URL%>js/sp.js"></script>
Interested in this school
<div id="resultMessageForApplication"></div>
<div id="application">
<form id="submitApplication" name="submitApplication" method="POST" action="saveRequest.json" >
					<input id="name" name="name"
						class="form-control" placeholder="Please enter Enter" /> 
					<input type="email" id="email" name="email"
						class="form-control" placeholder="Please enter email id" /> 
					<input type="hidden" id="sid" name="sid"
						value="${content.id}" /> 
					<input type="hidden" id="sname" name="sname"
						value="${content.title}" /> 
					<input id="mobile" name="mobile"
						class="form-control" placeholder="Please enter mobile" /> 
						<input id="submitApplicationButton" onclick="saveRequest()"
						type="button" class="btn btn-primary" value="Submit" style="margin-top: 10px" />
</form>
</div>
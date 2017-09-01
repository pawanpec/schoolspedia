<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.spedia.utils.WebConstants"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 100%;
	padding: 20px 15px;
	background: #fff;
	 display:table
}

.login-box-left{border: 2px solid #e7e5e5; padding:2.5%; width:35%;    display: table-cell;    vertical-align: top; border-radius:5px; font-family: "robotolight",arial; font-size:13px; line-height:22px;}
.login-box-left h2{font-size: 24px; color:#4c4b4b; margin:0 0 10px 0}
.login-box-left h2 span{ font-weight:bold;color:#e14444}
.login-box-mdl{ width:3%; display: table-cell;    vertical-align: top;}
.login-box-right{border: 2px solid #e7e5e5; padding:2.5%; width:62%; display: table-cell;    vertical-align: top; border-radius:5px; font-family: "robotolight",arial;}
.login-box-right h2{font-size: 24px; color:#4c4b4b; margin:0 0 20px 0; width:100%; float:left; text-align:center}
.login-box-right h2 span{ font-weight:bold;color:#e14444}
.login-box-right td{ vertical-align:middle; padding:0 0 15px 0}
.login-box-right td table td{ width:100%}
.login-box-right td table td input[type="text"], input[type="password"]{ width:100%; height: 40px;    border: 1px solid #afafaf;
    border-radius: 3px; display: block; padding:5px 10px; color: #292725; font-size: 14px; outline: none;}
.login-box-right td table td span{    display: block;    padding-bottom: 5px;    font-size: 16px;    font-weight: bold;}
.login-box-right td table td input.sbmts{background: #e14444;    border: none;    width: 100px;    color: #ffffff;    height: 40px;    font-weight: bold;    font-size: 18px;    outline: none;    border-radius: 3px;}

@media (max-width: 650px) {
.login-box-left{ width:100%; display: block;}
.login-box-right{ width:100%; display: block; margin-top:15px}
.login-box-mdl{ width:100%; display: block;}
}

</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<div id="login-box">
    
    <div class="login-box-left">
    <h2>About <span>Schoolspedia</span></h2>
 Schoolspedia.com Is Started By Team Of Expert Professionals To Build The India's Biggest Schools Network. Our Mission Is To Create The Online Presence Of Each And Every Schools In India. Our Team Has Vast Experience On Building Large Scale Web Portal.

Schoolspedia.com Will Connect Private,Public,Government,Day Boarding,Residential,Primary,Higher Secondary,Senior Secondary All Type Of Schools In India. It Will Give Our Guru’s (Principal,Headmaster,Teachers) To Share Their Experience In Education. </div>
    
        <div class="login-box-mdl">
        </div>
    
    <div class="login-box-right">
        <h2>Welcome Back to <span>Schoolspedia</span></h2>
        
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        
        <tr>
        <td width="40%">
        <div  class="facebook_login" id="loginLink"><a href="javascript:void(0)" onClick="Login()"><img src="<%=WebConstants.IMAGE_URL%>images/sign-with-fcbk.png" width="100%"/></a>
        </div>
        </td>
        <td width="15%" style="text-align:center; background:url(<%=WebConstants.IMAGE_URL%>images/lnbgs.png) repeat-y center"><img src="<%=WebConstants.IMAGE_URL%>images/or.png"/></td>
       
        <td width="45%" valign="top">
        <c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg"><b>${msg}</b></div>
		</c:if>
        
        <form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'>

			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
                    <span>User:</span>
					<input type='text' name='username'>
                    </td>
				</tr>
				<tr>
					<td>
                    <span>Password:</span>
					<input type='password' name='password' />
                    </td>
				</tr>
				<tr>
					<td><input name="submit" type="submit"
						value="submit" class="sbmts" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
        
        
        </td>
        </tr>
        
        </table>
        
    </div>
    
    <div class="clr"></div>
        
	</div>

</body>
</html>
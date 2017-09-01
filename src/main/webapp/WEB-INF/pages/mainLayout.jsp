<%@ include file="/WEB-INF/pages/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="noIndex.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link  href="http://schoolspedia.com" hreflang="en-us" />
<link rel="shortcut icon" type="image/ico"
	href="<%=WebConstants.IMAGE_URL%>images/favicon.ico" />
<meta name="Author" content=" http://schoolspedia.com/" />
<meta name="reply-to" content="schoolspediaatgmaildotcom" />
<meta name="revisit-after" content="daily" />
<meta name="distribution" content="global" />
<meta name="Rating" content="General" />
<meta name="expires" content="never" />
<meta name="language" content="english" />
<!-- css Includes -->
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/stylesheet.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/bootstrap-slider.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>fonts/font.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/star-rating.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/autosuggest.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/sp.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/jquery.bxslider.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/font-awesome.css" />
<link rel="stylesheet"
	href="<%=WebConstants.CSS_URL%>css/jquery-ui.css" />
<script src="<%=WebConstants.JS_URL%>js/jquery-1.10.2.min.js"
	type="text/javascript"></script>
<script src="<%=WebConstants.JS_URL%>js/jquery-ui.js"
	type="text/javascript">
    </script>
<script type="text/javascript">
var compareSchoolData = [];
    function shuffle(array) {
    	var currentIndex = array.length, temporaryValue, randomIndex;

    	// While there remain elements to shuffle...
    	while (0 !== currentIndex) {

    		// Pick a remaining element...
    		randomIndex = Math.floor(Math.random() * currentIndex);
    		currentIndex -= 1;

    		// And swap it with the current element.
    		temporaryValue = array[currentIndex];
    		array[currentIndex] = array[randomIndex];
    		array[randomIndex] = temporaryValue;
    	}

    	return array;
    }
	    var appUrl="<%=WebConstants.APPLICATION_URL%>";
		var random = shuffle([ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ]);
		$(function() {
			$('span.stars').stars();
		});
		$('document').ready(function(e) {
			$('.navbar-toggle').on('click', function() {
				$('nav.main-mnu').toggleClass('open');
			})
		});
	</script>
<script
	src="http://schoolspedia.com/sites/default/files/sp_mailer_static/slide.js"></script>
<link
	href="http://schoolspedia.com/sites/default/files/sp_mailer_static/sp_cms1.css"
	rel="stylesheet" type="text/css" />
<meta name="google-site-verification" content="nTGysMFL1hut1kYFbMcXB5KTTSbt0Ixso9zAKD4oB5w" />
</head>
<body>
	<div id="fb-root"></div>
	<div class="slidemenu">
		<div>
			<tiles:insertAttribute name="header" />
		</div>

		<div class="row" id="staticmsg">
			<div
				class="col-xs-10 z2new text-center col-md-8 alert alert-warning margincenter alert-dismissible"
				role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<span class="txtmsg"></span>
			</div>
		</div>
		<!-- </div> -->

		<!-- <div class="container modal" id="myModal2" tabindex="-2" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
		<div class="parentcont">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- </div> -->

		<!-- <div class="wrapper"> -->
		<tiles:insertAttribute name="footer" />
		<!-- </div> -->

		<!-- close wrapper -->
		<%-- <div class="content-preloader" id="preloader">
			<img src="<%=WebConstants.IMAGE_URL%>images/small_loader.gif" />
		</div> --%>
	</div>
	<!-- Js Includes -->
	<script type="text/javascript" src="<%=WebConstants.JS_URL%>js/sp.js"></script>
	<script type="text/javascript"
		src="<%=WebConstants.JS_URL%>js/fbutils.js"></script>
	<script type="text/javascript"
		src="<%=WebConstants.JS_URL%>js/autosuggest2.js"></script>
	<script type="text/javascript"
		src="<%=WebConstants.JS_URL%>js/suggestions2.js"></script>
	<script type="text/javascript"
		src="<%=WebConstants.JS_URL%>js/jquery.bxslider.js"></script>
	<script src="<%=WebConstants.JS_URL%>js/bootstrap.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="<%=WebConstants.JS_URL%>js/star-rating.js"></script>
</body>
</html>

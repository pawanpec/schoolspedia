<html>
<%@ page import="com.spedia.utils.WebConstants"%>
<meta charset="UTF-8">
	<script src="<%=WebConstants.JS_URL %>js/jquery-1.10.2.min.js" type="text/javascript"></script> 
</head>
<script>
function saveFbGroupData(d) {
	// var queryString="?data="+d;
	 var aurl="/spedia/savefbgroupdata.html";
	 $.ajax({
		    url : aurl,
		    method : 'post',
		    data:'data='+d,
		    success : function(data) {
		        console.log(data);

		    }
	 	   
	 		
		})
	
}
</script>
<body>
	<div id="fb-root"></div>
	<script>
$iLimit = 99;
$appId = '191358334217968';
  window.fbAsyncInit = function() {
    FB.init({
      appId      : $appId, // Set YOUR APP ID
     // channelUrl : 'http://hayageek.com/examples/oauth/facebook/oauth-javascript/channel.html', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });
 
    FB.Event.subscribe('auth.authResponseChange', function(response) 
    {
     if (response.status === 'connected') 
    {
        document.getElementById("message").innerHTML +=  "<br>Connected to Facebook";
       // getFriendsList();
        //SUCCESS
 
    }    
    else if (response.status === 'not_authorized') 
    {
        document.getElementById("message").innerHTML +=  "<br>Failed to Connect";
 
        //FAILED
    } else 
    {
        document.getElementById("message").innerHTML +=  "<br>Logged Out";
 
        //UNKNOWN ERROR
    }
    }); 
 
    };
            function sortMethod(a, b) {
    	            var x = a.name.toLowerCase();
    	            var y = b.name.toLowerCase();
    	            return ((x < y) ? -1 : ((x > y) ? 1 : 0));
            }
    function Login()
    {
 
        FB.login(function(response) {
           if (response.authResponse) 
           {
                getUserInfo();
                
            } else 
            {
             console.log('User cancelled login or did not fully authorize.');
            }
         },{scope: 'email,public_profile,user_about_me,user_activities,user_birthday,user_education_history,user_friends,user_location,user_work_history,manage_notifications'});
 
    }
	getFriendsList = function() {
        FB.api('/me/friends',{fields: 'name,id,location,birthday'},function(response) {
                    console.log("friends"+JSON.stringify(response));
        });
   };
  function getUserInfo() {
        FB.api('/me?scope=email', function(response) {
        console.log("User "+JSON.stringify(response));
      var str="<b>Name</b> : "+response.name+"<br>";
          str +="<b>Link: </b>"+response.link+"<br>";
          str +="<b>Username:</b> "+response.username+"<br>";
          str +="<b>id: </b>"+response.id+"<br>";
          str +="<b>Email:</b> "+response.email+"<br>";
          str +="<input type='button' value='Get Photo' onclick='getPhoto();'/>";
          str +="<input type='button' value='Get Friends' onclick='getFriends();'/>";
          str +="<input type='button' value='Logout' onclick='Logout();'/>";
          document.getElementById("status").innerHTML=str;
 
    });
        getFriends('/me/friends?fields=id,name,work,education');
        //getGroupData('/1506585852913224/feed');
    }
  function getGroupData(groupURL) {
      FB.api(groupURL, function(response) {
    	  var jsonData=JSON.stringify(response.data);
    	  var jObject = JSON.parse(jsonData);
    	  var pos = response['paging']['next'].indexOf("v1.0/");
    	  var groupURL = response['paging']['next'].slice(pos+4);
    	  console.log("group data----"+groupURL);
	      for(i=0;i<jObject.length;i++){
	    		 saveFbGroupData(JSON.stringify(jObject[i]));
	      }
    	  if(response!=null&&jObject!=null&&jObject.length!=0){
    		  getGroupData(groupURL);
    	  }
    	 
      });
  }

    function getPhoto()
    {
      FB.api('/me/picture?type=normal', function(response) {
 
          var str="<br/><b>Pic</b> : <img src='"+response.data.url+"'/>";
          document.getElementById("status").innerHTML+=str;
 
    });
 
    }
    function Logout()
    {
        FB.logout(function(){document.location.reload();});
    }
    function getFriends(url)
    {
    // get friends
      FB.api(url, function(response) {
                            var result_holder = document.getElementById('result_friends');
    	                      var jsonData=JSON.stringify(response.data);
    	                  	  var jObject = JSON.parse(jsonData);
    	                  	  console.log(response.paging.next);
    	                      var pos = response.paging.next.indexOf("v2.0/");
    	                  	  var url = response['paging']['next'].slice(pos+4);
    	                  	  console.log("group data----"+url);
    	              	      for(i=0;i<jObject.length;i++){
    	              	    	     console.log(JSON.stringify(jObject[i]));
    	              	    		 //saveFbGroupData(JSON.stringify(jObject[i]));
    	              	      }
    	                  	  if(response!=null&&jObject!=null&&jObject.length!=0){
    	                  		     console.log("calling getFriends----");
    	                  		     getFriends(url);
    	                  	  }
                            // and display them at our holder element
    	                        result_holder.innerHTML = '<h2>Result list of your friends:</h2>' + results;
    	                    });
    }
 
  // Load the SDK asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
 
</script>
	<div align="center">
		<h2>Facebook OAuth Javascript Demo</h2>

		<div id="status">
			Click on Below Image to start the demo: <br /> <img
				src="http://hayageek.com/examples/oauth/facebook/oauth-javascript/LoginWithFacebook.png"
				style="cursor:pointer;" onclick="Login()" />
		</div>

		<br /> <br /> <br /> <br /> <br />

		<div id="message">
			Logs:<br />
		</div>
		<div id="result_friends"></div>
	</div>
</body>
</html>
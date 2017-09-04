var isUserInfoCalled=false;
window.fbAsyncInit = function() {
	FB.init({
		appId : '191358334217968', // Set YOUR APP ID
		// channelUrl : 'http://hayageek.com/examples/oauth/facebook/oauth-javascript/channel.html', // Channel File
		status : true, // check login status
		cookie : true, // enable cookies to allow the server to access the session
		xfbml : true,
		version    : 'v2.3'
	// parse XFBML
	});

	FB.Event
			.subscribe(
					'auth.authResponseChange',
					function(response) {
						if (response.status === 'connected') {
							//SUCCESS
							console.log("isLogin "+isLogin);
							if(isLogin=='false'&&!isUserInfoCalled){
								getUserInfo();
								isUserInfoCalled=true;
							}
						} else if (response.status === 'not_authorized') {
							document.getElementById("loginSuccess").innerHTML += "<br>Connected to Facebook";

							//FAILED
						} else {
							document.getElementById("loginSuccess").innerHTML += "<br>Logged Out";

							//UNKNOWN ERROR
						}
					});

};  
  function Login() {
		FB.login(
						function(response) {
							if (response.authResponse) {
								if(!isUserInfoCalled){
									getUserInfo();
								}
							} else {
								console
										.log('User cancelled login or did not fully authorize.');
							}
						}, {
							scope : 'email,public_profile,user_about_me,user_birthday,user_education_history,user_friends,user_location,user_work_history'
						});

	}

	function getUserInfo() {
		FB.api('/me', function(response) {
			//console.log(JSON.stringify(response));
			registerUser(JSON.stringify(response));
			//location.reload();
			console.log("login Done");
			document.getElementById("loginLink").style.display='none';
			if(isLogin=='true'&&isUserInfoCalled){
			   document.getElementById("loginSuccess").innerHTML='Welcome : '+response.name +
					' | <input type="button" value="Logout" onclick="Logout();" />';
			}
			document.getElementById("loginSuccess").style.display='';
			
		});
		
	}
	function Logout() {
		FB.logout(function() {
			console.log("logout Done");
			document.getElementById("loginLink").innerHTML='<a href="javascript:void(0)" onclick="Login()">'+
			'<img src="/spedia/images/facebook_login1.jpg"/></a>';
			document.getElementById("loginLink").style.display='';
			document.getElementById("loginSuccess").style.display='none';
			logoutFROMSP();
		});
	}
	function logoutFROMSP() {
		var aurl = "/spedia/logout.html";
		$.ajax({
			url : aurl,
			success : function(result) {
				console.log("after logout " + result);
				 window.location=result;
			}
		});
	}
	(function(d) {
		var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement('script');
		js.id = id;
		js.async = true;
		js.src = "//connect.facebook.net/en_US/all.js";
		ref.parentNode.insertBefore(js, ref);
	}(document));

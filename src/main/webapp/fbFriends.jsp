<%@ page import="com.spedia.utils.WebConstants"%>
<!DOCTYPE html>
<html lang="en" xmlns:fb="https://www.facebook.com/2008/fbml">
<head>
<meta charset="utf-8" />
<title>Facebook API - Get friends list | Script Tutorials</title>
	<script src="<%=WebConstants.JS_URL %>js/jquery-1.10.2.min.js" type="text/javascript"></script> 
<style type="text/css">
#result_friends {
	margin: 0 auto;
	overflow: hidden;
	width: 900px;
}

#result_friends h2 {
	text-align: center;
}

#result_friends div {
	border: 1px solid #888;
	box-shadow: 3px 3px 0 #000;
	float: left;
	margin-bottom: 10px;
	margin-right: 10px;
	padding: 5px;
	width: 275px;
}

#result_friends img {
	float: left;
	margin-right: 10px;
}
</style>
</head>
<body>
	<header>
		<h2>Facebook API - Get friends list</h2>
		<a
			href="https://www.script-tutorials.com/facebook-api-get-friends-list/"
			class="stuts">Back to original tutorial on <span>Script
				Tutorials</span></a>
	</header>
	<img src="facebook.png" class="facebook" alt="facebook" />

	<center>
		<h1>Authorization step:</h1>
		<div id="user-info"></div>
		<button id="fb-auth">Please login here</button>
	</center>

	<div id="result_friends"></div>
	<div id="fb-root"></div>

	<script>
        $iLimit = 99;
        $appId = '191358334217968';
        function sortMethod(a, b) {
            var x = a.name.toLowerCase();
            var y = b.name.toLowerCase();
            return ((x < y) ? -1 : ((x > y) ? 1 : 0));
        }

        window.fbAsyncInit = function() {
            FB.init({ appId:  $appId, 
                status: true, 
                cookie: true,
                xfbml: true,
                oauth: true
            });

            function updateButton(response) {
                var button = document.getElementById('fb-auth');

                if (response.authResponse) { // in case if we are logged in
                    var userInfo = document.getElementById('user-info');
                    FB.api('/me', function(response) {
                        userInfo.innerHTML = '<img src="https://graph.facebook.com/' + response.id + '/picture">' + response.name;
                        button.innerHTML = 'Logout';
                    });

                    // get friends
                    FB.api('/me/friends?limit='+$iLimit, function(response) {
                        var result_holder = document.getElementById('result_friends');
                        var friend_data = response.data.sort(sortMethod);
						console.log("friend_data "+JSON.stringify(friend_data));
                        var results = '';
                        for (var i = 0; i < friend_data.length; i++) {
                            results += '<div><img src="https://graph.facebook.com/' + friend_data[i].id + '/picture">' + friend_data[i].name + '</div>';
                        }

                        // and display them at our holder element
                        result_holder.innerHTML = '<h2>Result list of your friends:</h2>' + results;
                    });

                    button.onclick = function() {
                        FB.logout(function(response) {
                            window.location.reload();
                        });
                    };
                } else { // otherwise - dispay login button
                    button.onclick = function() {
                        FB.login(function(response) {
                            if (response.authResponse) {
                                window.location.reload();
                            }
                        }, {scope:'email'});
                    }
                }
            }

            // run once with current status and whenever the status changes
            FB.getLoginStatus(updateButton);
            FB.Event.subscribe('auth.statusChange', updateButton);    
        };
            
        (function() {
            var e = document.createElement('script'); e.async = true;
            e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
            document.getElementById('fb-root').appendChild(e);
        }());
        </script>

</body>
</html>

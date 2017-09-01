<script>
function initialize() {
	var myLatlng = { lat: ${content.loc.coordinates[1]}, 
					 lng: ${content.loc.coordinates[0]}
	               };
  var mapOptions = {
     center: myLatlng,
     zoom: 16
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
  var image = 'http://www.weaponize.com/Jpgs/Icons/School-Icon.png';
  var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      icon: image,
      title: '${content.title}'
  });
}

<!-- function loadScript() {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp' +
      '&signed_in=true&callback=initialize&key=AIzaSyDDDnST4FfwAhVEcG7pGY-G7oQWD_JnxYc';
  document.body.appendChild(script);
}

window.onload = loadScript;

    </script> -->
 <div id="map-canvas" class="map_school">
 
 </div>
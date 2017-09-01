<%@ page import="com.spedia.utils.WebConstants"%>
<style type="text/css">
.glbl-srch-new{float: left;    width: 50%; position:relative; background:#ffffff; margin:5px 0 0 0}
.cse-branding-logo{ position:absolute; top:7px; left:5px}
.cse-branding-logo imgg{ margin-right:5px;}
.cse-branding-form{ position:relative; margin:0 0 0 70px}
.cse-branding-form input{ width:100%; background: #fff; outline:none;    border: none;  height: 30px; float:left}
.cse-branding-form input.srchbtn{width: 80px; background: #3f9f3f;    color: #fff;    position: absolute;    right: 0px;    top: 0px;}
@media (max-width: 650px) {
.glbl-srch-new{ width:100%}
}
</style>


<script>
function submitAdsenseSearch() {
    document.getElementById("cse-search-box").submit();
}
</script>

<div class="cse-branding-logo">
    <img src="http://www.google.com/images/poweredby_transparent/poweredby_FFFFFF.gif" alt="Google" />
  </div>
  
  <div class="cse-branding-form">
    <form action="<%=WebConstants.APPLICATION_URL%>spedia/search-content.html" id="cse-search-box" target="_blank">
        <input type="hidden" name="cx" value="partner-pub-9195507629739815:1255984516" />
        <input type="hidden" name="cof" value="FORID:10" />
        <input type="hidden" name="ie" value="UTF-8" />
        <input type="text" name="q" size="100" />
        <input type="button" name="sa" value="Search" class="srchbtn" onclick="submitAdsenseSearch()"/>
    </form>
  </div>
  
  


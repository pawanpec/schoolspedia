<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Subscribe to our newsletter for the
					latest updates</h4>
				<div id="resultMessage"></div>
			</div>
			<div id="subscriptionForm" class="modal-body">
				<form id="subscription" name="subscription">
					<input type="email" id="userEmail" name="userEmail"
						class="form-control" placeholder="Please enter email id" /> <input
						type="button" class="btn btn-primary" value="Subscribe"
						onclick="subscibeUser()" style="margin-top: 10px" />
				</form>
			</div>
		</div>

	</div>
</div>
<div class="subs_container">
	<div class="subs">
		<div class="">
			<a href="javascript:void(0);" data-toggle="modal" id="subscribe"
				data-target="#myModal"><img src="/spedia/images/subs.png" /></a>
		</div>
	</div>
</div>
<%@include file="footer_links.jsp"%>
<script>
	$(document).ready(function() {
		setTimeout(function() {
			//trigger data target here data-target="#myModal
			if (localStorage.getItem('advertOnce') !== 'true') {
				$("#subscribe").trigger("click");
				localStorage.setItem('advertOnce', 'true');
			}
		}, 20000);
	});
</script>
<%-- <%@ include file="ga.jsp"%>
<script type="text/javascript"
		src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-552ba612323b5ad7"
		async="async"></script> --%>
<style>
	.open {
	left: 180px;
}
</style>

<div id="resultMessageForApplication"></div>
<div id="application">
<div class="news_subscribe">
        <h4>Interested in this school</h4>
</div>
<form id="submitApplication" name="submitApplication" method="POST" action="saveRequest.json" >
					<input id="name" name="name"
						class="form-control" placeholder="Please Enter Your Name" /> 
					<input type="email" id="email" name="email"
						class="form-control" placeholder="Please Enter Email Id" /> 
					<input type="hidden" id="sid" name="sid"
						value="${content.nid}" /> 
					<input type="hidden" id="sname" name="sname"
						value="${content.title}" /> 
					<input id="mobile" name="mobile"
						class="form-control" placeholder="Please Enter Mobile (+91)" />

					<textarea rows="4" cols="50" id="message" name="message" 
						class="form-control" placeholder="Please Enter Details Here"> </textarea>
					 <span><input id="tc" type="checkbox" name="tc">
					 <label for="tc">&nbsp;I accept T&amp;C and allow schoolspedia to contact me.</label>
					 </span>
						<input id="submitApplicationButton" onclick="saveRequest()"
						type="button" class="btn btn-primary" value="Submit" style="margin-top: 10px" />
</form>
</div>
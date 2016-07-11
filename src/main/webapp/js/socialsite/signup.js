// Main Package of the application
var SocialSite = SocialSite || {};

// Signup package
SocialSite.SignUp = SocialSite.SignUp || {};
// shows the option for admin user
SocialSite.SignUp.Admin = {
    hide: function(){
        
        $("input[type='radio']:not(input.admin)").click(function(){
            $(".university").hide();
        });
		$("input.admin").click(function(){
			 $(".university").show();
		});
		
		// hide if the admin radio is not checked
		if(!$("input:radio:checked").hasClass("admin"))
		{
			$(".university").hide();
		}
    }
};
// register the handlers
$().ready(function(){
    SocialSite.SignUp.Admin.hide();
});




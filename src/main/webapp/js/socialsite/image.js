// Main Package of the application
var SocialSite = SocialSite || {};
// util package
SocialSite.Util = SocialSite.Util || {};
// shows the changeimage link when the user hovers over
// the image
SocialSite.Util.Image = {
    // setup the handlers
    setUp: function(){
        $('image').hover(this.showChangeLink, this.hideChangeLink);
        SocialSite.Util.Image.hideChangeLink();
    },
    // shows the link
    showChangeLink: function(){
        $('imagelink').show();
    },
    // hides the link
    hideChangeLink: function(){
        $('imagelink').hide();
    }
};
// registers the handlers
$(document).ready(function(){
    SocialSite.Util.Image.setUp();
});


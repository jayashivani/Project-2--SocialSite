// Main Package of the application
var SocialSite = SocialSite || {};
// util package
SocialSite.Util = SocialSite.Util || {};

// FIXME send the converted(HTML) code to the server during the(need some more
// workaround)
// ajax form submit
SocialSite.Util.Editor = {
	// wmd options
	wmdOptions : {
		"preview" : true,
		"helpLink" : "http://daringfireball.net/projects/markdown/",
		"helpHoverTitle" : "Markdown Help"
	},
	// initiallizes the editor
	setUp : function() {
		var editor = $('textarea.richEditor:not(textarea.processed)');
		// setup up the editor
		editor.wmd(this.wmdOptions);
		editor.each(this.createEditor).val('').TextAreaResizer();
		// creates the editor after each ajax response
		SocialSite.Ajax.registerPostAjax(this.PostAjaxSetUp);
		SocialSite.Util.Slider.setUp();
	},

	// set up the editor after the ajax call
	PostAjaxSetUp : function(changed$) {
		var that = SocialSite.Util.Editor;
		var editor = changed$.find('textarea.richEditor');
		// wmd options
		var wmdAjaxOptions = that.wmdOptions;
		wmdAjaxOptions.isAjax = true;
		// setup the editor
		editor.wmd(wmdAjaxOptions);
		editor.each(that.createEditor).TextAreaResizer();
	},
	// adds hooks to the editor
	createEditor : function() {
		var textArea = $(this);
		var form = textArea.parents('form');
		// can't add a hook to the form submit due to the bug WICKET-1448
		// textArea.parents('form')[0].onsubmit = (function(){
		// var form = $(this);
		// console.log('hook called', form);
		// form.find('textarea.richEditor').val(form.find('div.wmd-priview').html());
		// return false;
		// });

		// FIXME this won't work if the user submit the form using the return

		// store the onclick function declared by wicket and call it
		// after setting the value in the text editor
		var ajaxSubmitLink = form.find('a.editor-text')[0];
		// save this so we can call it later
		var wicketOnClick = ajaxSubmitLink.onclick;
		ajaxSubmitLink.onclick = function() {
			var preview = form.find('div.wmd-preview'); 
			form.find('textarea.richEditor').val(
					preview.html());
			preview.text("");
			
			return wicketOnClick();
		};

		form.find('a.editor-text').click(function() {
			// clear the textarea after submitting the form
				// TODO find a clean way to do it
				form.find('textarea.richEditor').val("");
			});
	}
};

$().ready(function() {
	SocialSite.Util.Editor.setUp();
});


$(document).ready(function () {
	// body...
	$('#loginForm').slideDown('slow');
	
	$('#loginContent').submit(function() {
		$('.error').slideUp('slow');
		$.ajax({
			url: 'loginController',
			type: 'POST',
			dataType: 'json',
			data: $('#loginContent').serialize(),
			success: function(data) {
				if(data.isValid == true) {
					window.location = 'dashboard?pageId=0';
				}else {
					$('.error').slideDown('slow');
				}
			}
		});
		return false;
	});
});

$(function() {
	
	$.ajax({
		url: 'dashboard',
		type: 'post',
		data: {},
		dataType: 'json',
	});

	$('#progress-section').slideDown('slow', function() {
		$('#bmi-section').slideDown('slow', function() {
			$('#schedule-section').slideDown('slow');
		});
	});
	
	$('.tab-options li').on('click', function() {
		$('.tab-options li').removeClass('selected');
		$(this).addClass('selected', 250);
	});
	
	setTimeout(function() {
		$('').text();
	}, 1000);
});

function setSelected(element) {
}

$(function() {

	$('#progress-section').slideDown('slow', function() {
		$('#bmi-section').slideDown('slow', function() {
			$('#schedule-section').slideDown('slow');
		});
	});
	
});
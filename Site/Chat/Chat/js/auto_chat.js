$(document).ready(function(){
	var interval = setInterval(function(){
		$.ajax({
			url: 'messages.php',
			success: function(data){
				$('#messages').html(data);
			}
		});
	},1000);
});
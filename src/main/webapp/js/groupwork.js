$(document).ready(function(){
	$('.btn-xoa').click(function(){
		
		var id = $(this).attr('id-project')
		var This = $(this)
		
		$.ajax({
			method:"GET",
			url: `http://localhost:8080/cm06/api/project?id=${id}`,
			//data: { name: "john", location: "boston"}
		})
		.done(function(result){
			if(result.data){
//				This.parent().parent().remove()
				This.closest('tr').remove()
			} else{
				alert("Xoa that bai")
			}
		});
	})
})
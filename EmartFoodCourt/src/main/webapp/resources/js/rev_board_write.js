/**
 * 
 */

function formchk(f){
	if(f.rev_subject.value.trim()== ""){
		alert('제목을 적어주십시오.');
		f.rev_subject.focus();
		return false;
	}
	
	var plainText = $($("#summernote").summernote("code")).text;
	 var MyDiv2 = document.getElementsByClassName('note-editable');
     m = MyDiv2[0];
	var board_content = document.getElementById('board_content');
	board_content.innerHTML = m.innerHTML;
		f.submit();
}

function setThumbnail(event) {
$('#image_container > img').remove();	/* 값자체를 제거하는것 */
/*	$( '#image_container > img' ).removeAttr( 'src' ); 특성제거하는것(placeholder,src등 */  
	alert("효과12345");
		 for (var image of event.target.files) { 
			 var reader = new FileReader(); 
			 reader.onload = function(event) { 
				  var img = document.createElement("img");
				  img.setAttribute("src", event.target.result);
				  img.setAttribute("name","file_image");
				  img.setAttribute("width","450px");
				  document.querySelector('div#image_container').appendChild(img);
			  };
			   console.log(image); reader.readAsDataURL(image);

	}	
		 
}
function image_plus(){
	var newDivHtml=
		 "<div class='row'><div class='cell col1'><label for='img_name'>추가 이미지</label></div><div class='cell col2'><input multiple='multiple' name='img_name' type='file' id='img_name' accept='image/*' onchange='setThumbnail(event);'/><input type='button' onClick='image_plus()' value='+'><input type='button' class='image_minus' value='-'></div></div>";
	 var row= document.createElement("div");
	 row.className="image_plus";
	 row.innerHTML = newDivHtml;
	 
	 document.querySelector('div#jb_table').appendChild(row);
}




function test(){
	var boardform = document.getElementById("boardform");
	var image_plus = document.getElementById("image_plus");
	boardform.appendChild(image_plus);
}


$(document).ready(function(){
	
	$(document).on('click','.image_minus',function (){
		$(this).parent().parent().remove();
	})
})


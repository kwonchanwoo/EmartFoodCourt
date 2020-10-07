function formchk(f) {
	if (f.board_subject.value.trim() == '') {
		alert("제목을 입력해주세요!");
		return false;
	}
	var plainText = $($("#summernote").summernote("code")).text;
	var MyDiv2 = document.getElementsByClassName('note-editable');
	m = MyDiv2[0];
	var board_content = document.getElementById('board_content');
	board_content.innerHTML = m.innerHTML;
	f.submit();
}
function cancel_confirm(id){
	var check = confirm("사이트에서 나가시겠습니까?\n변경사항이 저장되지 않을수 있습니다.");
	if(check==true){
		location.href="boardList.bd";
	}
}


$(document).ready(function(){
	$(window).resize(function(){
		if (matchMedia("screen and (max-width: 1066px)").matches) {
			
			/*$('.free_container').css("width","100%");
			$('.free_container').css("padding-left","0px");
			$('.header').css("width","100%");
			$('.header').css("left","0px");
			$('#table').css("width","100%");
			$('#table').css("left","0px");*/
			
		}else{
			/*$('.free_container').css("width","1200px");
			$('.free_container').css("padding-left","150px");
			$('.header').css("width","700px");
			$('.header').css("left","250px");
			$('#table').css("width","700px");
			$('#table').css("left","250px");*/
		}
	});
});
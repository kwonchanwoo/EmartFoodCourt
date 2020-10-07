/**
 * 
 */
function chkForm(f){
	var MyDiv2 = document.getElementsByClassName('note-editable');
	m = MyDiv2[0];
	if (f.board_subject.value.trim() == "") {
		alert('제목을 적어주십시오.');
		f.board_subject.focus();
		return false;
	}
	if(m.innerHTML.trim() == ''){
		alert("내용을 입력하세요.");
		return false;
	}
	var board_content = document.getElementById("board_content");
	board_content.innerHTML = m.innerHTML;
	f.submit();
}

function cancel_confirm(id){
	var check = confirm("사이트에서 나가시겠습니까?\n변경사항이 저장되지 않을수 있습니다.");
	if(check==true){
		location.href="boardList.bd";
	}
}
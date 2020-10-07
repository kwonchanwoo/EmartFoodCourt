
function formchk(f) {

	if (f.rev_subject.value.trim() == "") {
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



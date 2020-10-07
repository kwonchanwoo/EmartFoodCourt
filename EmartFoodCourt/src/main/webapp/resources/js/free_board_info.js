
function board_delete_check(board_num,id){
	var check = confirm("정말 삭제하시겠습니까?");
	if(check==true){
		
		location.href="/EmartFoodCourt/boardList_delete.bd?board_num="+board_num+"&id="+id
	}
}
function reply_delete(id, reply_num, board_num) {
	var check = confirm("댓글을 삭제하시겠습니까?");
	if(check==true){
		$.ajax({
			url : "/EmartFoodCourt/boardList_reply_delete.bd?id=" + id
					+ "&reply_num=" + reply_num + "&board_num=" + board_num,
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			datatype : "json",
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			},
			success : function(result) {
				alert("삭제되었습니다.");
				document.location.reload();
			}
		})
	}
}

$(document).ready(function() {
	$('.wrap').on('keyup', 'textarea', function(e) {
		$(this).css('height', 'auto');
		$(this).height(this.scrollHeight);
	});
	$('.wrap').find('textarea').keyup();
});

var chkId = false;
var idcheck;
/* var newURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname; */
var count = -1;

document.addEventListener('keydown', function(event) {
	  if (event.keyCode === 13) {
	    event.preventDefault();
	  };
	}, true);

function upload() {
	$("#reply_form").ajaxForm({
		url : "/EmartFoodCourt/boardList_reply_write.bd",
		enctype : "multipart/form-data",
		dataType : "json",
		error : function() {
		},
		success : function(result) {

			document.location.reload();
		}
	});
	$("#reply_form").submit();
}

function reply_modify(f) {
	var form = document.getElementsByClassName("new_reply_form")[f];
	$(form).ajaxForm({
		url : "/EmartFoodCourt/boardList_reply_modify.bd",
		enctype : "multipart/form-data",
		dataType : "json",
		error : function() {
		},
		success : function(result) {

			document.location.reload();
		}
	});
	$(form).submit();
}

function reply_upload(f) {
	var form = document.getElementsByClassName("new_reply_form")[f];
	$(form).ajaxForm({
		url : "/EmartFoodCourt/boardList_reply_write.bd",
		enctype : "multipart/form-data",
		dataType : "json",
		error : function() {
		},
		success : function(result) {

			document.location.reload();
		}
	});
	$(form).submit();
}

function reply_write_create(f, d,value) {
	var test = document.getElementsByClassName("reply_write_form_hidden")[f];
	var elements = document.getElementsByClassName("reply_write_form_hidden");
	var submit = document.getElementsByClassName("reply_submit_2")[f];
	var modify = document.getElementsByClassName("reply_modify")[f];
	var content = document.getElementsByClassName("reply_content_class")[f];

	for (var i = 0, l = elements.length; i < l; i++) {
		if (test == elements[i]) {
			if (test.style.display == 'none') {
				if (d == 1) {
					
					test.style.display = 'block';
					
					modify.style.display = 'none';
					submit.style.display = 'block';
					
					
					
				} else if (d == 2) {
					
					test.style.display = 'block';
					modify.style.display = 'block';
					submit.style.display = 'none';
					content.value=value;
					
				}
			} else {
				test.style.display = 'none';
				content.value="";
			}
		}
	}
}
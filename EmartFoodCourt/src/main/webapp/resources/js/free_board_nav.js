
function wrapWindowByMask(){ 
    //화면의 높이와 너비를 구한다.
	var sessionData = "true";
	sessionStorage.setItem("popup_chk", sessionData );
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();  

    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
    $('#mask').css({'width':maskWidth,'height':maskHeight});  

    // fadeIn :투명도 변하는 속도 ,화면이 fadeTo 0.5투명도로 바뀜 
    $('#mask').fadeIn(0);
    $('#mask').fadeTo("slow",0.5);    
}


$(document).ready(function(){
    //검은 막 띄우기
	             /*resize: 화면사이즈변화에 따라 발생하는 이벤트*/
		$(window).resize(function (){
			if (matchMedia("screen and (min-width: 1066px)").matches) {
		        $('#mask, .window,.window2').hide();
				  // 1024px 이상에서 사용할 JavaScript
				} else if(sessionStorage.getItem("popup_chk")=='true') {
					 var maskHeight = $(document).height();
					    var maskWidth = $(window).width();  

					    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
					    $('#mask').css({'width':maskWidth,'height':maskHeight});  
				}
		 })
		
	
	
    $('.openMask').click(function(e){
        e.preventDefault();
        wrapWindowByMask();
        $('.window').show();
    });
	$('.free_board_list_icon').click(function(e){
		 e.preventDefault();
	     wrapWindowByMask();
		$('.window2').show();
	});

    //닫기 버튼을 눌렀을 때
    $('.window2_close_button').click(function (e) {
        //링크 기본동작은 작동하지 않도록 한다.
        e.preventDefault();
        $('#mask, .window, .window2').hide();
    });       

    //검은 막을 눌렀을 때
    $('#mask').click(function () {
        $(this).hide();
        $('.window').hide();
        $('.window2').hide();
    });
});


$( document ).ready( function() {
    var board = $('.free_board_nav').offset();
    $( window ).scroll( function() {
       
        if ( $( document ).scrollTop() > board.top ) {
    	  		
    		  $( '.free_board_nav' ).addClass( 'jbFixed' );
    	  
      }
      else {
          	
        $( '.free_board_nav' ).removeClass( 'jbFixed' );
        
      }
    });
  } );


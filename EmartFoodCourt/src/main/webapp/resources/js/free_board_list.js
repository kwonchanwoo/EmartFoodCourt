


function search_form(board_orderby,board_category){
	var form = document.createElement("form");
	var category = document.getElementsByClassName("category")[0].value;
	var search = document.getElementsByClassName("search")[0].value;

	  form.setAttribute("charset", "UTF-8");
      form.setAttribute("method", "Post");  //Post 방식
      form.setAttribute("action", "/EmartFoodCourt/boardList.bd?board_orderby="+board_orderby+"&board_category="+board_category); //요청 보낼 주소
     
      var hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", "category");
      hiddenField.setAttribute("value", category);
      
      form.appendChild(hiddenField);
      
      var hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", "search");
      hiddenField.setAttribute("value", search);
      form.appendChild(hiddenField);
      
      document.body.appendChild(form);
      form.submit();
}

function search_form1(){
	var form_id = document.getElementById("search_form1");
	if(form_id.search.value.trim()==''){
		alert("값을 입력해주세요!");
		return false;
	}
	form_id.submit();
}


$( document ).ready( function() {
	$('.header').css({position:'relative'}).css({
        left: ($('.free_container').width() - $('#table').outerWidth())/2
    });
	$('#table').css({position:'relative'}).css({
        left: ($('.free_container').width() - $('#table').outerWidth())/2
    });
    var board = $('.free_board_nav').offset();
    
    var sidebar = $('.sidebar').offset();
    var table_right = table.left+$('#table').width();
    
   $( window ).scroll( function() {
	   var free_container = $('.free_container').offset(); 
	    var table = $('#table').offset();
	   var result = table.left; 

    	var board_width = ($('.free_container').width() - $('#table').outerWidth())/2;
    	
    	
 	  
	    	if($( document ).scrollTop() > board.top){
	    		
	    		$('.header').css("position","fixed");
	    		/*$('.header').css("left","400px");*/
	    		$('.header').css("z-index","1");
	    		$(".header").css("top","50px");
	    		$('.header').css("left",result);
	    		$('#table').css("left",board_width);
	    	}else{
	    		$('.header').css("position","relative");
	    		/*$('.header').css("left","250px");*/
	    		$('.header').css("z-index","0");
	    		$(".header").css("top","");
	    		$('.header').css("left",board_width);
	    		$('#table').css("left",board_width);
	    	}

    });

    $(window).resize(function(){
    	 var free_container = $('.free_container').offset(); 
    	    var table = $('#table').offset();
    	 var result = table.left; 
    	var board_width = ($('.free_container').width() - $('#table').outerWidth())/2;
    	if(window.matchMedia("screen and (max-width: 1066px)").matches){
    		$(".header").css("left","");
    		$("#table").css("left","");
    	}else if(window.matchMedia("screen and (min-width: 1066px)").matches){
    		if($('.header').css("position")=='fixed'){
    			$('#table').css("left",board_width);
        		$('.header').css("left",result);
    		}else if($('.header').css("position")=='relative'){
    			$('#table').css("left",board_width);
        		$('.header').css("left",board_width);
    		}    		
    	}
    
    });
    

  } );
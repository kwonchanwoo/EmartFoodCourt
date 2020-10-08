<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="resources/css/free_board_list.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%@ include file="../board/free_board_nav.jsp" %>
			
	<div class="window">				
		<select name="category" class="category">
			<c:choose>
				<c:when test="${category=='subject'}">
					<option value="subject" selected >제목</option>
					<option value="writer">작성자</option>
				</c:when>
				<c:when test="${category=='writer'}">
					<option value="subject" >제목</option>
					<option value="writer" selected>작성자</option>
				</c:when>
				<c:otherwise>
					<option value="subject">제목</option>
					<option value="writer">작성자</option>
				</c:otherwise>
			</c:choose>	
		</select>
		
		<c:choose>
			<c:when test="${search!=null}">
					<input type="text" name="search" class="search" placeholder="검색" id="search" value="${search}">
			</c:when>
			<c:otherwise>
				<input type="text" name="search" class="search" placeholder="검색" id="search">
			</c:otherwise>
		</c:choose>
		<a href="javascript:search_form('${board_orderby}','${board_category}');">
			<img src="/EmartFoodCourt/resources/images/icon-search@2x.png" id="search_icon" width="60">
		</a>					
	</div>
	
	<div class="free_container">
		<jsp:include page="../board/sidebar.jsp"/>
	
		<div class="header">
			<div id="header-two">
				<div id="header-two-1">
					<c:choose>
						<c:when test="${board_orderby=='popularity'}">
							<a href="/EmartFoodCourt/boardList.bd?board_orderby=popularity&board_category=${board_category}">
							<img src="/EmartFoodCourt/resources/images/icon-hot-on@2x.png">&nbsp;인기</a>
						</c:when>
						<c:otherwise>
							<a href="/EmartFoodCourt/boardList.bd?board_orderby=popularity&board_category=${board_category}">
							<img src="/EmartFoodCourt/resources/images/icon-hot@2x.png">&nbsp;인기</a>
						</c:otherwise>
					</c:choose>		
				</div>
				
				<div id="header-two-2">		
					<c:choose>
						<c:when test="${board_orderby=='new'}">
							<a href="/EmartFoodCourt/boardList.bd?board_orderby=new&board_category=${board_category}">
							<img src="/EmartFoodCourt/resources/images/icon-new-on@2x.png">&nbsp;최신</a>
						</c:when>
						<c:otherwise>
							<a href="/EmartFoodCourt/boardList.bd?board_orderby=new&board_category=${board_category}"><img src="/EmartFoodCourt/resources/images/icon-new@2x.png">&nbsp;최신</a>
						</c:otherwise>
					</c:choose>	
				</div>
				
				<div id="header-two-3">
					<form action="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=${board_category}" method="post" id="search_form1">
						<select name="category">			
							<c:choose>									
								<c:when test="${category=='subject'}">
									<option value="subject" selected>제목</option>
									<option value="writer">작성자</option>
								</c:when>
								<c:when test="${category=='writer'}">
									<option value="subject">제목</option>
									<option value="writer" selected>작성자</option>
								</c:when>
								<c:otherwise>
									<option value="subject">제목</option>
									<option value="writer">작성자</option>
								</c:otherwise>
							</c:choose>			
						</select>
						
						<c:choose>
							<c:when test="${search!=null}">
								<input type="text" name="search" id="search" placeholder="검색" id="search" value="${search}">
							</c:when>
							<c:otherwise>
								<input type="text" name="search" id='search' placeholder="검색" id="search">
							</c:otherwise>
						</c:choose>
		
						<a href="javascript:search_form1();">
							<img src="/EmartFoodCourt/resources/images/icon-search@2x.png" id="search_icon" width="30px" height="27px">
						</a>
					</form>
				</div>
				
				<div id="header-two-4">
					<a href="#" class="openMask"><img src="/EmartFoodCourt/resources/images/icon-search@2x.png"></a>
					<a href="/EmartFoodCourt/boardList_writeForm.bd?board_orderby=${board_orderby}&board_category=${board_category}"><img src="/EmartFoodCourt/resources/images/icon-write@2x.png" width="40px" height="40px"></a>
				</div> 
			</div>
		</div>
		
		<div id="table">
			<div class="row-title">
				<div id="title">
					<div class="cell col1">추천</div>
					<div class="cell col2">작성자</div>
					<div class="cell col3">제목</div>
					<div class="cell col4">조회</div>
					<div class="cell col5">등록일</div>
				</div>
				
				<div id="content">
					<c:choose>	
						<c:when test="${free_board!=null}">			
							<c:forEach var="list" items="${free_board}">
							<div class="board_row">
								<div class="cell col1">
									<div class="recom_icon">
										<img src="/EmartFoodCourt/resources/images/icon-vote-up.png">${list.vote_total}
									</div>
								</div>
								
								<c:choose>
									<c:when test="${list.board_orderby=='공지'}">
										<div class="cell col2">
											<font color="red">${list.id}</font>
										</div>
									</c:when>
									<c:otherwise>
										<div class="cell col2">
											${list.id}
										</div>
									</c:otherwise>
								</c:choose>
									<div class="cell col3">
										<a href="/EmartFoodCourt/boardList_detail.bd?board_num=${list.board_num}&board_orderby=${board_orderby}">${list.board_subject}											
											<c:if test="${list.count_reply>0}">
												<font style="color:green">[${list.count_reply}]</font>
											</c:if>									
										</a>
									</div>
									
									<div class="cell col4">
										${list.board_readcount}
									</div>
									
									<div class="cell col5">
										<c:choose>
											<c:when test="${list.year!=0}">
												${list.year }년전
											</c:when>
											<c:when test="${list.month!=0}">
												${list.year }달전
											</c:when>
											<c:when test="${list.day!=0}">
												${list.day }일전
											</c:when>
											<c:when test="${list.hour!=0}">
												${list.hour }시간전
											</c:when>
											<c:when test="${list.minute!=0 }">
												${list.minute }분전
											</c:when>
											<c:otherwise>
											${list.second}초전
											</c:otherwise>
										</c:choose>
									</div>			
								</div>
								
								<div class="m_board_row">
									<div class="cell col1">
										<div class="recom_icon">
											<img src="/EmartFoodCourt/resources/images/icon-vote-up.png">${list.vote_total}
										</div>
									</div>
									
									<div class="cell col2">
										<a href="/EmartFoodCourt/boardList_detail.bd?board_num=${list.board_num}&board_orderby=${board_orderby}">${list.board_subject}											
											<c:if test="${list.count_reply>0}">
												<font style="color:green">[${list.count_reply}]</font>
											</c:if>									
										</a>
										<br>
										<c:choose>
											<c:when test="${list.board_category=='notice'}">
												공지사항
											</c:when>
											<c:when test="${list.board_category=='event'}">
												이벤트
											</c:when>
											<c:when test="${list.board_category=='free'}">
												자유
											</c:when>
											<c:when test="${list.board_category=='humor'}">
												유머
											</c:when>
											<c:when test="${list.board_category=='video'}">
												영상
											</c:when>
										</c:choose>
										&nbsp;&nbsp;
										<c:choose>
											<c:when test="${list.year!=0}">
												${list.year }년전
											</c:when>
											<c:when test="${list.month!=0}">
												${list.year }달전
											</c:when>
											<c:when test="${list.day!=0}">
												${list.day }일전
											</c:when>
											<c:when test="${list.hour!=0}">
												${list.hour }시간전
											</c:when>
											<c:when test="${list.minute!=0 }">
												${list.minute }분전
											</c:when>
											<c:otherwise>
												${list.second}초전
											</c:otherwise>
										</c:choose>
										&nbsp;&nbsp;${list.id}
									</div>
									
									<div class="cell col3">					
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<img src="/EmartFoodCourt/resources/images/img-notfound@2x.png">
							<p>검색 결과가 없습니다.</p>
							<p>단어의 철자가 맞는지 확인해 주세요.</p>
							<p>검색어의 단어 수를 줄이거나, 일반적인 단어로 검색해 주세요.</p>
							<p>키워드에 있는 특수문자를 뺀 후에 검색해 주세요.</p>
						</c:otherwise>	
					</c:choose>
				</div>			
			</div>
		</div>
		
		<div class="page_info">
			<c:if test="${mypage-1!=0 && board_count!=0}">
				<button onclick="location.href = '/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=${board_category}&page=${mypage-1}&category=${category}&search=${search}'">이전</button>
			</c:if>			
			<c:if test="${board_count!=mypage && board_count!=0}">
				<button onclick="location.href = '/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=${board_category}&page=${mypage+1}&category=${category}&search=${search}'">다음</button>
			</c:if>		
		</div>
	</div>
	
		<!-- footer -->
		<jsp:include page="../footer.jsp" />
		<!-- //footer -->
		
	<script type="text/javascript" src="resources/js/free_board_list.js"></script>
</body>
</html>
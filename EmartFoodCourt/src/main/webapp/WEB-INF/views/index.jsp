<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<html>
<head>

<title>
이마트 푸드코트 추천 사이트
</title>

<meta charset="UTF-8">
</head>
<body>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>
	<jsp:include page="nav.jsp" />
	<!-- banner bottom -->
	<section class="bottom-w3pvt py-5">
		<div class="container py-xl-5 py-lg-3">
			<div class="top-cont px-lg-5">
				<p>저희 이마트 푸드코트를 이용해 주신 고객분들에게 감사의 인사드립니다. 저희 이마트 푸드코트는 고객님들의 편의
					시설과 보다 맛있는 음식으로 보답드리겠습니다. 현재 이 사이트는,First 고객님들의 좋은 후기와 정성으로 운영되고
					있으며, 고객님들에게 좋은 서비스를 제공드리기 위해 만들어진 사이트 입니다. 저희 이마트 푸트코트를 이용하신
					고객님들께서는 조금 귀찮으시더라도 다른 고객님들과 저희 이마트 푸드코트의 더 나은 발전을 위해 좋은 글, 소중한 글쓰기
					부탁드리겠습니다. 저희 이마트 푸트코트도 고객님들의 불편한 사항들을 겸허히 받아들이고 앞으로 보다 좋은 서비스를
					제공드리기 위해 적극적으로 검토해나가겠습니다. 감사합니다. 앞으로도 이마트 푸트코트와 좋은 추억 행복한 날만 가득하시기를
					기원하겠습니다.</p>
				<p class="my-4">현재 저희 페이지는 푸트코드 이용고객님들에게 음식 추천 기능과 더불어, 직접 이용하셨던
					고객님들의 평가 게시물을 담고 있습니다. 이마트 푸트코트를 처음 이용하시는 고객님들께서는 기존의 고객님들의 글을 한 번
					보시고 이용할 수 있고, 시간적 여유가 없어 빠른 음식추천을 원하시는 고객님들께서는 상단 메뉴의 '음식 추천'기능을
					이용해주시면 감사하겠습니다. 불편한 사항은 저희 담당자에게 연락을 주시고, 보다 좋은 서비스를 위해 노력하는 이마트
					푸트코트가 되겠습니다. 감사합니다.</p>
				<p>Thank you for using our E-mart food court. Our E-mart food
					court will reward you with your convenience and delicious food.
					This site is currently Operated with good reviews and sincerity of
					customers, this site was created to provide good service to
					customers. Customers who use our E-Mart foot coats may be a little
					annoying Please write good and valuable writing for the better
					development of our food court. We, E-Mart Foot Court, will humbly
					accept your inconveniences and actively review to provide better
					service in the future. Thank you. I wish you all the best in E-Mart
					food coats and happy memories. Currently, our page contains food
					recommendation features for our customers, as well as their
					testimonials. Customers who use E-Mart Foot Court for the first
					time can see and use the existing customer's articles, and if you
					do not have time to recommend fast food recommendation, you can use
					the 'Food Recommendation' function on the top menu. If you have any
					inconvenience, please contact our staff and we will be E-Mart
					Footcoats for better service. Thank you.</p>
			</div>
			<!-- middle section 1 -->
			<section class="row middle-2 py-lg-5 py-4">
				<div class="col-lg-7 text-xl-right text-center">
					<img class="img-fluid" src="/EmartFoodCourt/resources/images/foodCourt2.PNG" alt="">
				</div>
				<div class="col-lg-5 who-left-w3pvt mt-lg-0 mt-4">
					<h3 class="w3ls-title font-weight-bold text-da mb-4">새로운 식생활
						문화 창조와 고객 만족 추구.</h3>
					<p>E-mart Food Court는 국내 푸드 업계의 기업으로 꾸준한 경영 혁신과 과감한 투자, 우리 입맛에
						맞는 새로운 상품 개발로 업계 신화를 창조해가고 있습니다. 한국인의 입맛을 사로잡는 메뉴 개발 및 빠르게 변해가는
						고객의 취향과 욕구를 반영한 트렌디한 매장 구성,등고객 만족을 위한 이마트 푸트코트의 끝없는 노력은 오늘도 지속되고
						있습니다.</p>
					<p class="mt-3">국내 식품 관련 산업의 발전을 이끌며 푸드코트의 장으로써 큰 성공을 이루어 온
						E-mart Food Court는 전국 어디든 고객들의 입맛을 사로잡도록 노력하겠습니다.</p>

				</div>
			</section>
			<!-- //middle section 1 -->
			<div class="top-cont px-lg-5">
				<p>E-mart Food Court is a company in the domestic food industry,
					which is creating an industry myth with steady management
					innovation, bold investment, and development of new products to
					suit our tastes.E-mart Food Court's endless efforts to satisfy
					customers, such as the development of menus that capture the taste
					of Koreans, the construction of trendy stores that reflect rapidly
					changing tastes and desires of customers, are continuing.</p>
				<p class="my-3">E-mart Food Court, which has led the development
					of the domestic food-related industry and achieved great success as
					a food court, will strive to capture the taste of customers
					anywhere in the country.</p>
				<p>Jabore et dolore magna aliqua uta enim ad minim ven iam quis
					nostrud exercitation ullamco labor nisi utaliquip exea commodo
					consequat duis aute irudre dolor in elit sed uta labore dolore
					reprehender.</p>
				<h3 class="my-4">
					<a href="blog2.html"
						class="text-bl font-weight-bold blog-grid-title mt-4 mb-3">8월의
						고객의 소리</a>
				</h3>
				<div class="row">
					<div class="col-md-6">
						<p></p>
					</div>
				</div>
				<p class="my-3">실제 푸드코트를 이용하시고 후기를 남겨주신 분들에게는 추첨을 통해 소정의 상품을
					드립니다.</p>
			</div>
			<div class="quote">
				<p class="text-wh">
					<span class="fa fa-quote-left"></span> 이번에 엄마따라 이마트를 갔다가 우연한 기회로
					식사를하게 되었어요^^ 자주 먹지 않는 이마트 푸트코트라 어떤 음식이 맛있는 지 몰라서 계산대 앞에서 한참을 망설이고
					있었어요. 그런데 이런 사이트가 있다는 정보를 직원분께 추천받았고 덕분에 시간지체 안하고 맛있는 점심 한끼 잘
					먹었습니다.!! 그리고 많은 사람들이 올려놓으신 후기를 보면서 이마트 푸트코트의 장점과 단점을 알게되었고 앞으로 음식에
					대한 신뢰도를 가지면서 자주 이용할 수 있을 것 같아요!! 감사합니다!! 항상 번창하세요 <span
						class="fa fa-quote-right"></span>
				</p>
			</div>
		</div>
	</section>
	<!-- //banner bottom -->


	<!-- //content -->
	<!-- footer -->
	<jsp:include page="footer.jsp" />
	<!-- //footer -->

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 작성</title>
<style>
	#subbtn{
		background: rgb(26, 188, 156);
	 	color: white;
	 	border: none;
	 	border-radius: 10px;
	 	padding: 10px;
	 	width: 100px;
	 	margin-left: 300px;
	}
	#memberReviewInput{
		width: 700px;
		margin: 0 auto;
		text-align: left;
	}
</style>
</head> 
<body>
	<%@ include file="../common/top.jsp" %>
	<%@ include file="../common/sideBar.jsp" %>
	<div id="wrapper" class="toggled">
        <div id="page-content-wrapper">
	    	<div class="container-fluid">
	        	<div class="container text-center">
	        		<h4 style="margin-right: 560px;"><b>후기 작성하기</b></h4>
	        		<br><br>
		        		<br>
				        <div class="form-group">
				              <label for="exampleFormControlInput1">평점</label><br><br>
				            <input type="number" class="form-control" id="exampleFormControlInput1" name="score" placeholder=" 원하는 점수를 입력하세요">
				        </div>
				        <br>
				        <div class="form-group">
				            <label for="exampleFormControlTextarea1">내용</label><br><br>
				            <textarea class="form-control" id="exampleFormControlTextarea1" name="reviewContent" rows="10" style="resize: none"></textarea>
				        </div>
				        <br><br>
				    <button type="submit" name="review" id="subbtn">등록하기</button>
	        	</div>
			</div>
	    </div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br>
		<footer>
			<%@ include file="../common/bottom.jsp" %>
		</footer>
</body>
</html>
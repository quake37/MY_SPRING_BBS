<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}

.pagination {
	width: 100%;
}

.pagination li {
	list-style: none;
	float: left;
	padding: 3px;
	border: 1px solid blue;
	margin: 3px;
}

.pagination li a {
	margin: 3px;
	text-decoration: none;
}
</style>
<title>Insert title here</title>
<script>
var bno = 2;

function getAllList() {
	
	$.getJSON("/replies/all/" + bno, function(data) {
		//alert(data.length);
		
		var str = "";
		console.log(data.length);
		
		$(data).each(
			function() {
				str +="<li data-rno='" + this.rno + "' class='replyLi'>"
				    + this.rno + ":<span>" + this.replytext + "</span>"
				    + "<button>MOD</button></li>";
			}
		);
		$("#replies").html(str);
	});
}




$(window).load(function(){
	getAllList();
	
	$('#replyAddBtn').click(function() {
		var replyer = $('#newReplyWriter').val();
		var replytext = $('#newReplyText').val();
		
		$.ajax({
			type : 'POST',
			url : '/replies',
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'POST'
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				if(result == 'SUCCESS') {
					alert('등록 되었습니다.');
					getAllList();
				}
			}
		});
	});
	
	$('#replies').on('click', '.replyLi button', function() {
		var reply = $(this).parent();
		var rno = reply.attr("data-rno");
		var replytext = $(reply).find('span').text(); //  reply.text();
		
		$('.modal-title').text(rno);
		$('#replytext').val(replytext);
		$('#modDiv').show("slow");
	});
	
	$('#replyDelBtn').on("click", function() {
		var rno = $(".modal-title").text();
		var replytext = $('#replytext').val();
		
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			header : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result : " + result);
				if (result == 'SUCCESS') {
					alert("삭제 되었습니다.");
					$('#modDiv').hide("slow");
					getAllList();
				}
			}
		});
	});
	
	$('#replyModBtn').click(function(){
		var rno = $(".modal-title").text();
		var replytext = $('#replytext').val();

		$.ajax({
			type : 'put',
			url : '/replies/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				replytext : replytext
			}),
			dataType : 'text',
			success : function(result) {
				console.log("result : " + result);
				if (result == 'SUCCESS') {
					alert("수정 되었습니다.");
					$('#modDiv').hide("slow");
					getAllList();
					
				}
			}
		});
		
	});
	
	
});
 

</script>
</head>

<body>
	<h2>Ajax Test Page</h2>
	
	<div>
		<div>
			REPLYER <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id='replyAddBtn'>ADD REPLY</button>
	</div>
	
	<ul id="replies">
	</ul>
	
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type="text" id="replytext"/>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>

</body>
</html>
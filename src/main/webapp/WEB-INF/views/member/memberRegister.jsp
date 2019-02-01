<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>

<script>
	var memberid = document.getElementById("userid");
	var checkRequest = new XMLHttpRequest();

	function checkFunction() {

		var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		var id = document.getElementById("userid").value;
		if (check.test(id)) {
			alert("한글을 포함하고있습니다.");
			return;
		}
		$.ajax({
			type : 'POST',
			url : '/member/checkMemberid?userid=' + id,
			data : id,
			dataType : 'text',
			success : function(result) {
				console.log("result : " + result);
				if (result == "0") {
					alert("사용가능한 아이디입니다.");
				} else {
					alert("사용불가능한 아이디입니다.");
				}
			},
			error : function(xhr, status, error) {
				alert("status : " + status + ", xhr : " + xhr
						+ ", error : " + error);
			}

		});
	}
</script>



<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER MEMBER</h3>
				</div>
				<!-- /.box-header -->
				<form  method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="userid">아이디</label> <input type="text" name="userid"
								class="form-control" id="userid" placeholder="ID"
								required="required" autofocus="autofocus">
								<br>
							<button class=" btn btn-primary" onclick="checkFunction();">ID 중복체크</button>
						</div>
						<div class="form-group">
							<label for="username">이름</label> <input type="text"
								name="username" class="form-control" placeholder="Name"
								required="required">
						</div>
						<div class="form-group">
							<label for="inputPassword">비밀번호</label> <input type="password"
								name="userpw" class="form-control" placeholder="Password"
								required="required">
						</div>

						<div class="form-group">

							<label for="inputEmail">이메일 주소</label> <input type="email"
								name="useremail" class="form-control"
								placeholder="Email address" required="required">

						</div>
					</div>
					<div class="box-footer">
						<button class="btn btn-primary" type="submit">회원가입</button>
					</div>
				</form>

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
</div>

<%@include file="../include/footer.jsp"%>
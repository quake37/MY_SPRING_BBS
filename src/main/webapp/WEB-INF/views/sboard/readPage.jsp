<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<%@include file="../include/header.jsp"%>



<!-- Main content -->
<section class="content">
	<input type="hidden" id='userid' value="${login.userid}"> 
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" action="modifyPage" method="post">

					<input type='hidden' name='bno' value="${boardVO.bno}"> <input
						type='hidden' name='page' value="${cri.page}"> <input
						type='hidden' name='perPageNum' value="${cri.perPageNum}">
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">
				</form>

				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label> <input type="text"
							name='title' class="form-control" value="${boardVO.title}"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label> <input type="text"
							name="writer" class="form-control" value="${boardVO.writer}"
							readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">

					<c:if test="${login.userid == boardVO.writer}">
						<button type="submit" class="btn btn-warning modifyBtn">Modify</button>
						<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>
					</c:if> 
					<button type="submit" class="btn btn-primary goListBtn">GO
						LIST</button>
				</div>
				<script>
					
					
					$(document).ready(function() {
						var formObj = $("form[role='form']");

						console.log(formObj);

						$(".modifyBtn").on("click", function() {
							formObj.attr("action", "/sboard/modifyPage");
							formObj.attr("method", "get");
							formObj.submit();
						});

						$(".removeBtn").on("click", function() {
							formObj.attr("action", "/sboard/removePage");
							formObj.submit();
						});

						$(".goListBtn").on("click", function() {
							formObj.attr("method", "get");
							formObj.attr("action", "/sboard/list");
							formObj.submit();
						});

					});
				</script>

				<div class="box box-default">
					<div class="box-header">
						<h3 class="box-title">Reply BOARD</h3>
					</div>
					<div class="box-body">
						<div>
							<div class="form-group">
								<label for="exampleInputEmail1">Replyer</label> <input
									type="text" name='replyer' class="form-control"
									id='newReplyWriter' readonly="readonly" value="${login.userid}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Reply Text</label> <input
									type="text" name='replytext' class="form-control"
									id='newReplyText'>
							</div>
							<button id='replyAddBtn' class="btn btn-primary">ADD REPLY</button>
						</div>
						<br>
<script >
	var bno = ${boardVO.bno}
	var getrno;
	var userid = document.getElementById('userid').value;
	
	
	
	function getAllList() {
		$.getJSON("/replies/all/" + bno,function(data) {
			var str = "";
			console.log(data.length);
		
			$(data).each(function() {
				var str2=""
				if(userid == this.replyer){
					str2="<button class='btn btn-warning' id='replymod'>MOD</button>";
				}
				
				str += "<li data-replyer='" + this.replyer + "' data-rno='"+this.rno+"' id='replyLi' class='list-group-item'>"
					+ "<font style='color:#A569BD; font-size:25px;'>"
					+ this.replyer
					+ " :</font> &nbsp;&nbsp;"
					+ " <span><font style='color: #3498DB; font-size:20px;'>"
					+ this.replytext
					+ "</font></span> &nbsp;&nbsp;"
					+ str2
					+ "<div id='"+this.rno+"' style='display: none;' class='modDiv'> </div></li>";
					});
		$("#replies").html(str);
	});
	}
	</script>
	<script >
	$(window).load(function() {
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
					if (result == 'SUCCESS') {
						alert('등록 되었습니다.');
						getAllList();
					}
				},
				error : function(xhr, status, error) {
					alert("status : " + status + ", xhr : " + xhr+ ", error : " + error);
				}
			});
		});
		
	$('#replies').on('click','#replymod',function() {
	
		
		var reply = $(this).parent();
		var replyer = reply.attr("data-replyer");
		var rno = reply.attr("data-rno");
		var replytext = $(reply).find('span').text(); //  reply.text();
		var id = "#" + rno;
		var str = "";
		getrno = rno;
		
		
		
		$('.modDiv').hide("slow");
		str += "<div class='modal-title"+rno+"' id='modal-id"+rno+"'  style='color:#A569BD; font-size:20px;' ></div>"
				+ "<div><input type='text' id='replytext"+rno+"'/></div>"
				+ "<div>"
				+ "<button class='btn btn-warning' type='button' id='replyModBtn"+rno+"'>Modify</button>&nbsp;"
				+ "<button class='btn btn-danger' type='button' id='replyDelBtn"+rno+"'>Delete</button>&nbsp;"
				+ "<button class='btn btn-primary' type='button' id='closeBtn"+rno+"'>Close</button>"
				+ "</div>";
		$(id).html(str);

		$('.modal-title' + rno).text(replyer);
		$('#replytext' + rno).val(replytext);
		$(id).show("slow");

		$("#closeBtn" + rno).click(function() {
			$('.modDiv').hide("slow");
		});

		$("#replyDelBtn" + rno).click(function() {
			    var replytext = $("#replytext"+ getrno).val();
				var rno = getrno;

				$.ajax({type : 'delete',
						url : '/replies/'+ rno,
						header : {
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "DELETE"
						},
						dataType : 'text',
						success : function(result) {
						console.log("result : "+ result);
						if (result == 'SUCCESS') {
							alert("삭제 되었습니다.");
							$('.modDiv').hide("slow");
							getAllList();
						}
						},
						error : function(xhr, status, error) {
							alert("status : " + status + ", xhr : " + xhr
									+ ", error : " + error);
						}
				});
		});
			$("#replyModBtn" + rno).click(function() {
				var replytext = $("#replytext"+ getrno).val();
				var rno = getrno;

				$.ajax({type : 'put',
						url : '/replies/'+ rno,
						headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "PUT"
						},
						data : JSON.stringify({replytext : replytext}),
						dataType : 'text',
						success : function(result) {
						console.log("result : "+ result);
						if (result == 'SUCCESS') {
							alert("수정 되었습니다.");
						$('.modDiv').hide("slow");
						getAllList();

						}
						},
						error : function(xhr, status, error) {
							alert("status : " + status + ", xhr : " + xhr
									+ ", error : " + error);
						}
			});

		});
	});
});
</script>

						<ul id="replies" class="list-group">
						</ul>

						<!-- <div id='modDiv' style="display: none;">
							<div class='modal-title'></div>
							<div>
								<input type="text" id="replytext" />
							</div>
							<div>
								<button type="button" id="replyModBtn">Modify</button>
								<button type="button" id="replyDelBtn">Delete</button>
								<button type="button" id="closeBtn">Close</button>
							</div>
						</div> -->
					</div>

					<div class="box-footer"></div>
				</div>


			</div>
			<!-- /.box -->

		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->


</section>
<!-- /.content -->

</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>

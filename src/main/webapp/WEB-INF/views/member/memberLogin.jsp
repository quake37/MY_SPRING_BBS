<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>



<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>




<section class="content" >
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">LOGIN</h3>
				</div>
				<!-- /.box-header -->

				<form  method="post" >
					<div class="form-group">
						<div class="form-label-group col-xs-3">
							<label for="inputEmail">ID</label> <input type="text"
								class="form-control" placeholder="ID" name="userid" />
						</div>
					</div>
					<div class="form-group">

						<div class="form-label-group col-xs-3">
							<label for="inputPassword">Password</label> <input
								type="password" class="form-control" placeholder="Password"
								name="userpw" />
						</div>
					</div>

					<div class="checkbox">
						<label> <input type="checkbox" name="rememberId"
							value="userid" />아이디 기억
						</label>
					</div>
					<button type="submit" class="btn btn-lg btn-success ">로그인</button>

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
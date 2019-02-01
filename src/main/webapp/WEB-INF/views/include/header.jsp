<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBS_PROJECT | THGboard</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- Font Awesome Icons -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet"
	type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="/resources/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<!-- jQuery 2.1.4 -->
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<a href="/" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>TAEHO's</b> PROJECT</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Notifications: style can be found in dropdown.less -->
						<li class="dropdown notifications-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-bell-o"></i> <span class="label label-warning">10(아직안만듬)</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 10 notifications</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li><a href="#"> <i class="fa fa-users text-aqua"></i>
												5 new members joined today
										</a></li>
										<li><a href="#"> <i class="fa fa-warning text-yellow"></i>
												Very long description here that may not fit into the page
												and may cause design problems
										</a></li>
										<li><a href="#"> <i class="fa fa-users text-red"></i>
												5 new members joined
										</a></li>

										<li><a href="#"> <i
												class="fa fa-shopping-cart text-green"></i> 25 sales made
										</a></li>
										<li><a href="#"> <i class="fa fa-user text-red"></i>
												You changed your username
										</a></li>
									</ul>
								</li>
								<li class="footer"><a href="#">View all</a></li>
							</ul></li>

						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><c:choose>
								<c:when test="${login != null}">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<img src="/resources/dist/img/user2-160x160.jpg"
										class="user-image" alt="User Image" /> <span
										class="hidden-xs">${login.userid}님 환영합니다.</span>
									</a>
								</c:when>
								<c:when test="${login == null }">
									<a href="/member/memberLogin"><span class="hidden-xs">로그인</span></a>
								</c:when>
							</c:choose>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img
									src="/resources/dist/img/user2-160x160.jpg" class="img-circle"
									alt="User Image" />
									<p>${login.userid}<small>Member since
											${login.regdate}</small>
									</p></li>
								<!-- Menu Footer-->
								<li class="user-footer">

									<div class="pull-right">
										<a href="/member/memberLogout"
											class="btn btn-default btn-flat">Log out</a>
									</div>
								</li>
							</ul></li>
						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"><i
								class="fa fa-gears"></i></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<c:choose>
							<c:when test="${login ne null }">
								<img src="/resources/dist/img/user2-160x160.jpg"
									class="img-circle" alt="User Image" />
							</c:when>
							<c:when test="${login eq null }">
								<img src="/resources/dist/img/anonymous.png" class="img-circle"
									alt="User Image" />
							</c:when>
						</c:choose>
					</div>
					<div class="pull-left info">
						<c:choose>
							<c:when test="${login ne null }">
								<p>${login.userid}</p>
								<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
							</c:when>
							<c:when test="${login eq null }">
								<p></p>
								<a href="#"><i class="fa fa-circle text-warning"></i>Offline</a>
							</c:when>
						</c:choose>
					</div>
				</div>


				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li class="treeview"><a href="#"> <i
							class="fa fa-dashboard"></i> <span>회원</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<c:if test="${login eq null }">
								<li><a href="/member/memberRegister"><i
										class="fa fa-circle-o"></i> 회원가입</a></li>
							</c:if>
							<li><a href="#"><i class="fa fa-circle-o"></i>
									회원정보(아직안만듬)</a></li>
						</ul></li>

					<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
							<span>작성</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li class="active"><a href="/sboard/register"><i
									class="fa fa-circle-o"></i> 게시글 작성</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-laptop"></i>
							<span>게시판</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="/board/listAll"><i class="fa fa-circle-o"></i>
									게시판(페이징X)</a></li>
							<li><a href="/board/listPage"><i class="fa fa-circle-o"></i>
									게시판(페이징O)</a></li>
							<li><a href="/sboard/list"><i class="fa fa-circle-o"></i>
									게시판(검색O)</a></li>
						</ul></li>
					<li class="header">LABELS</li>
					<li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
					<li><a href="#"><i class="fa fa-circle-o text-yellow"></i>
							<span>Warning</span></a></li>
					<li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
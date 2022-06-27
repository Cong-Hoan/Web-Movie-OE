<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<div class="col mt-4">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="videoEditing-tab" data-toggle="tab"
			href="#videoEditing" role="tab" aria-controls="videoEditing"
			aria-selected="true">Favorites</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="videoList-tab" data-toggle="tab" href="#videoList" role="tab"
			aria-controls="videoList" aria-selected="false"> Favorites User </a>
		</li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="ShareFriends-tab" data-toggle="tab" href="#ShareFriends"
			role="tab" aria-controls="ShareFriends" aria-selected="false">Share
				Friends</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditing"
			role="tabpanel" aria-labelledby="videoEditing-tab">
					<table class="table table-bordered mt-3">
						<tr>
							<td>Video Title</td>
							<td>Favorites Count</td>
							<td>Lasted Date</td>
							<td>Oldest Date</td>
						</tr>
						<c:forEach var="item" items= "${favList }">
						<tr>
							<td>${item.videoTitle }</td>
							<td>${item.favoriteCount }</td>
							<td>${item.newstDate }</td>
							<td>${item.oldestDate }</td>
						</tr>
						</c:forEach>
					</table>
		</div>
		<div class="tab-pane fade" id="videoList" role="tabpanel"
			aria-labelledby="videoList-tab">
			<form action="" method="get">
				<div class="row mt-3">
					<div class="col">
						<div class="form-inline">
							<div class="form-group">
								<label for=""> Video Title 
								<select name="videoUserId" id="videoUserId"
									class="form-control ml-3">
									<c:forEach var="item" items="${vidList }">
										<option value="${item.videoId }" 
										${item.videoId == videoUserId? 'selected':'' }>${item.title }</option>
									</c:forEach>
								</select>
								</label>
								<button class="btn btn-info ml-2">Report</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">
						<table class="table table-bordered">
								<tr>
									<td>Username</td>
									<td>Fullname</td>
									<td>Email</td>
									<td>Favorite</td>
								</tr>
							<c:forEach var="item" items="${favUsers }">
								<tr>
									<td>${item.username }</td>
									<td>${item.fullname }</td>
									<td>${item.email }</td>
									<td>${item.likeDate }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="ShareFriends" role="tabpanel"
			aria-labelledby="ShareFriends-tab">
			<form action="" method="get">
				<div class="row mt-3">
					<div class="col">
						<div class="form-inline">
							<div class="form-group ml-3">
								<label for=""> Video Title <select name="" id=""
									class="form-control">
										<option value="">Java</option>
								</select>
								</label>
								<button class="btn btn-info ml-2">Report</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">
						<table class="table table-bordered">
							<tr>
								<td>Sender Name</td>
								<td>Sender Email</td>
								<td>Receiver Email</td>
								<td>Sent Date</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
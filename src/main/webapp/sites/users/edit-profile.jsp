<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<div class="col-8 offset-2">
	<form action="EditProfile" method="post">
		<div class="card">
			<div class="card-header">
				<b>Edit Profile</b>
			</div>
			<div class="card-body">
			<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="row">	
					<div class="col">
						<div class="form-group">
							<label for="">Username</label> 
							<input type="text"
								class="form-control" name="username" id="username" value="${user.username }"
								aria-describedby="usernameHdi" placeholder="Username"> <small
								id="usernameHid" class="form-text text-muted">Username
								is Required</small>
						</div>
						<div class="form-group">
							<label for="fullname">Fullname</label> <input type="text" value="${user.fullname }"
								class="form-control" name="fullname" id="fullname"
								aria-describedby="fullnameHid" placeholder="Fullname"> <small
								id="fullnameHid" class="form-text text-muted">FullName
								is Required</small>
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label for="Password">Password</label> <input type="password"
								class="form-control" name="password" id="password"
								placeholder="Password">
						</div>
						<div class="form-group">
							<label for="emailAddress">Email Address</label> 
							<input
								type="text" class="form-control" name="email" value="${user.email }"
								id="emailAddress" aria-describedby="emailAddressHid"
								placeholder="Email Address"> <small id="emailAddressHid"
								class="form-text text-muted">Help text</small>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Update</button>
			</div>
		</div>
	</form>
</div>

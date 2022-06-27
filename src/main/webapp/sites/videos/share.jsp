<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<div class="offset-3 col-6">
	<form action="ShareVideo" method="post">
		<input type="hidden" name="videoId" value="${videoId }" />
		<div class="card">
			<div class="card-header">Send Video To Your Friends</div>
			<div class="card-body">
			<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label for="email">Your Friends Email: </label> <input
								type="text" class="form-control" name="email" id="email"
								aria-describedby="emailHelperId" placeholder="Emails"> <small
								id="emailHelperId" class="form-text text-muted">Email is
								required!</small>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer">
				<button class="btn btn-success">
					<i class="fa-solid fa-circle-envelope"></i> Send
				</button>
			</div>
		</div>
	</form>
</div>
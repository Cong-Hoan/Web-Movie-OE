<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<div class="col-9">
	<div class=" row p-2">
		<c:forEach var="item" items="${videos }">
			<div class="col-3 mt-2">
				<div class="card text-center">
					<div class="card-body">
						<img src="${empty item.poster ? 'images/Crystal_Project_computer.png' : item.poster }" alt="" class="img-fluid"
							width="90%">
						<div class="row border-top mt-2">
							<b>${item.title }</b>
						</div>
					</div>
					<div class="card-footer">
						<a href="LikeVideo?videoId="
							${item.videoId }" class="btn btn-success">LIKE</a> <a
							href="ShareVideo?videoId=${item.videoId }" class="btn btn-info">SHARE</a>
					</div>
				</div>
		</c:forEach>
	</div>
	<div class="row">
		<div class="col">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>
<div class="col-3">
	<div class="row mt-3 mb-3">
		<div class="col">
			<div class="card">
				<div class="card-header">
					<i class="fa fa-thumbs-up" aria-hidden="true"></i> Favorties
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Computer</li>
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Laptop</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Pagina dei Risultati</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<!-- Fixed navbar -->
		<jsp:include page="../navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Lista dei risultati</h5> 
				    </div>
				   
				    <div class='card-body'>
				     <c:if test="${userInfo.isLogged() && userInfo.isUser()}">
				    	<a class="btn btn-primary " href="PrepareInsertAnnuncioServlet">Add New</a>
				     </c:if>
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Testo</th>
				                        <th>Prezzo</th>
				                        <th>Data Pubblicazione</th>
				                        <th>Stato</th>
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${annuncio_list_attribute }" var="annuncioItem">
										<tr>
											<td>${annuncioItem.testoAnnuncio }</td>
											<td>${annuncioItem.prezzo }</td>
											<td><fmt:formatDate type = "date" value = "${annuncioItem.data }" /></td>
											  <td><c:choose>
											  <c:when test="${annuncioItem.aperto}">
											  aperto
											   </c:when>
											<c:otherwise>
											chiuso
											</c:otherwise>
											</c:choose></td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/user/ExecuteVisualizzaAnnuncioServlet?idAnnuncio=${annuncioItem.id }">Dettagli</a>
												<a class="btn  btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/user/PrepareUpdateAnnuncioServlet?idAnnuncio=${annuncioItem.id }">Modifica</a>
												<a class="btn  btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/user/PrepareDeleteAnnuncioServlet?idAnnuncio=${annuncioItem.id }">Elimina</a>
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   
					<!-- end card-body -->			   
			    </div>
			<!-- end card -->
			</div>	
		   
		 <!-- end container -->  
		  </div>
		  
		</main>
		
		<!-- Footer -->
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>
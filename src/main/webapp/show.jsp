<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="header.jsp" />
	 	
	   <title>Dettaglio annuncio</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Stato annuncio:</dt>
							   <c:if test="${show_annuncio_attr.aperto}">
							  <dd class="col-sm-9">aperto</dd>
							  </c:if>
							   <c:if test="${!show_annuncio_attr.aperto}">
							  <dd class="col-sm-9">chiuso</dd>
							  </c:if>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Di pubblicazione:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" /></dd>
					    	</dl>
					    	
					    </div>
					    <!-- end card body -->
					    
					    <div class='card-footer'>
					<c:choose>
						<c:when test="${userInfo.isLogged()}">
							<c:set value="${pageContext.request.contextPath}/user/ExecuteAcquistaServlet" var="address"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="PrepareLoginServlet"
								var="address"></c:set>
						</c:otherwise>
					</c:choose>
					
					<form method="post" action="${address}" class="row g-3"
						novalidate="novalidate">
						
						<input type="hidden" name="idAnnuncio" value="${show_annuncio_attr.id}">
						<input type="hidden" name="prezzoAnnuncio" value="${show_annuncio_attr.prezzo}">
						
						<div class="col-12">
						
							<a href="ExecuteCercaAnnuncioServlet"
								class='btn btn-outline-secondary'> 
							<i class='fa fa-chevron-left'></i> Back </a>
							
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-outline-warning">Compra</button>
							
						</div>
					</form>
				</div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="footer.jsp" />
	  </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="header.jsp" />
	   
	   <title>Ricerca</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="navbar.jsp"></jsp:include>
	    
			
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
				        <h5>Cerca Annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteSearchAnnunciServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label>Testo ricerca</label>
									<input type="text" name="testo" id="testo" class="form-control" placeholder="Annuncio che vuoi cercare..." >
								</div>
								
								<div class="col-md-6">
									<label>Prezzo </label><label style="color: grey">(a partire da:)</label>
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire il prezzo" >
								</div>
							
								<div class="col-md-6">
									<label>Data di Pubblicazione</label>
	                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="data"  >
								</div>
								
								
								<div class="col-md-6">
									<label>Categorie:</label> <br>
								<c:forEach items="${categoria_list_attribute}" var="categoriaItem">
										<label><input type="checkbox" name="categoriaItem" value="${categoriaItem.id}">${categoriaItem.descrizione}</label><br>
									</c:forEach>
								</div>
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
								
						</form>
  
				    
				    
					<!-- end card-body -->			   
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
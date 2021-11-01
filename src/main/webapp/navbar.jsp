<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PrepareSearchAnnuncioServlet">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Annunci</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/home">Home</a></li>
              <c:if test="${!userInfo.isLogged()}">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
              </c:if>
            </ul> 
          </li> 
          
             <c:if test="${userInfo.isUser()}"> 
           <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Account</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07"> 
            
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareInsertAnnuncioServlet">Crea annuncio</a></li>
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareGestioneAcquistiServlet">Portafoglio</a></li>
            </ul>
                </li> 
                </c:if>
          
            <c:if test="${userInfo.isAdmin()}"> 
           <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Gestione utente</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07"> 
            
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/PrepareSearchUtenteServlet">Ricerca Utenti</a></li>
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/PrepareInsertUtenteServlet">Inserisci Utenti</a></li>
            </ul>
                </li> 
                </c:if>
        </ul>
      </div>
      <c:if test="${userInfo.isLogged()}">
      <div class="col-md-3 text-end">
        <p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })
    	 <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></p>
      </div>
      </c:if>
        <c:if test="${!userInfo.isLogged()}">
      <div class="col-md-3 text-end">
        <p class="navbar-text">
    	 <a href="${pageContext.request.contextPath}/login.jsp">Login</a></p>
      </div>
       </c:if>
    </div>
  </nav>

  
  
</header>
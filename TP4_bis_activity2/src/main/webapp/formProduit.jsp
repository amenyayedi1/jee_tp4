<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- formProduit.jsp -->
<form action="ProduitEditionController" method="POST">
    <label for="code">Code</label>
    <input type="text" name="code" id="code" required>
    
    <label for="designation">Désignation</label>
    <input type="text" name="designation" id="designation" required>
    
    <label for="prix">Prix</label>
    <input type="number" step="0.01" name="prix" id="prix" required>
    
    <button type="submit">Ajouter</button>
</form>

</body>
</html>
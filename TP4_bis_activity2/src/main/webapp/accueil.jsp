<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css"/>
</head>
<body>
    <div>
    <a href="formProduit.jsp">Ajouter un produit</a>
<a href="listProduits.jsp">Afficher la liste des produits</a>
    
        <a href="UserForm.jsp">Ajouter un utilisateur</a>
        
        <hr>
        <a href="UserListController">Liste des utilisateurs</a>
    </div>
</body>
</html>

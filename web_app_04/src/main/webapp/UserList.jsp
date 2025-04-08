<%@ page language="java" import="java.util.List, metier.User" %>
<%@ include file="entete.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Consultation</title>
</head>
<body>

    <h1>Liste des utilisateurs</h1>
    <hr>
    <table border="1">
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Login</th>
            <th>Mot de passe</th>
            <th colspan="2">Actions</th>
        </tr>

        <%
            List<User> users = (List<User>) session.getAttribute("listOfUsers");
            if (users != null) {
                for (User user : users) {
                    out.println("<tr>");
                    out.println("<td>" + (user.getNom()) + "</td>");
                    out.println("<td>" + (user.getPrenom()) + "</td>");
                    out.println("<td>" + (user.getLogin()) + "</td>");
                    out.println("<td>" + (user.getPassword()) + "</td>");
                    out.println("<td><a href='UserEditionController?id=" + user.getId() + "&mode=Edition'>Modifier</a></td>");
                    out.println("<td><a href='UserEditionController?id=" + user.getId() + "&mode=Suppression' onclick='return confirm(\"Voulez-vous vraiment supprimer cet utilisateur ?\")'>Supprimer</a></td>");
                    out.println("</tr>");
                }
            }
        %>
        
    </table>

    <hr>
    <a href="UserForm.jsp">Ajouter un utilisateur</a>
    
</body>
</html>

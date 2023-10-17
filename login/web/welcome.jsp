<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bonjour Client</title>
    <!-- Ajoutez ici vos liens vers les feuilles de style, scripts, etc. -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        h1 {
            font-size: 24px;
            color: #007BFF;
        }
        p {
            font-size: 16px;
        }
        form {
            margin-top: 20px;
        }
        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Bonjour, Client</h1>
    <p>Bienvenue sur la page d'accueil pour les clients.</p>
    
    <!-- Vous pouvez ajouter ici le contenu spécifique aux clients -->
    
    <form action="auth.jsp" method="GET">
        <input type="submit" value="Se déconnecter">
    </form>

</body>
</html>

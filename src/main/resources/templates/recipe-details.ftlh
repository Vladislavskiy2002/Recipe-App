<!DOCTYPE html>
<html>
<head>
    <title>Receipt Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container-xl">
    <h1>Receipt Information</h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Receipt Name</th>
            <th>Created by</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${recipe.id}</td>
            <td>${recipe.name}</td>
            <td>${recipe.user.name}</td>
        </tr>
        </tbody>
    </table>
    <h2>Products:</h2>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Weight</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.weight}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<form method="get" action="/receipt/myRecipes">
    <button class="btn btn-primary" type="submit">Previous Page</button>
</form>
</body>
</html>

<!doctype html>
<html lang="en" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<div class="container my-5">
    <h1>User Info</h1>
    <table class="table">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${User.name}</td>
            <td>${User.surname}</td>
            <td>${User.email}</td>
        </tr>
        </tbody>
    </table>
    <div class="row">
        <div class="col-md-3">
            <form method="get" action="/receipt/myRecipes">
                <button class="btn btn-primary" type="submit">All my recipes</button>
            </form>
        </div>
        <div class="col-md-3">
            <form method="get" action="/receipt/allRecipes">
                <button class="btn btn-primary" type="submit">All recipes</button>
            </form>
        </div>
        <#list roles as role>
            <#if role.name == 'ROLE_ADMIN'>
                <div class="col-md-3">
                    <form method="get" action="/mvc/admin/allUsers">
                        <button class="btn btn-primary" type="submit">All Users</button>
                    </form>
                </div>
            </#if>
        </#list>
        <div class="col-md-3">
            <form method="get" action="/receipt/recipes/new">
                <button class="btn btn-primary" type="submit">Add Recipe</button>
            </form>
        </div>
        <br>
        <br>
        <div class="col-md-3">
            <form method="get" action="/logout">
                <button class="btn btn-primary" type="submit">Logout</button>
            </form>
        </div>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.1/js/bootstrap.min.js"></script>
</body>
</html>

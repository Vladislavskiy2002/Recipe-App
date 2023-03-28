<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Receipt List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th>
<form method="get" action="/receipt/receipts/findByName">
    <button class = "btn btn-dark" type="submit">Find by receipt name</button>
</form>
        </th>
        <th>
<form method="get" action="/receipt/receipts/findByProductName">
    <button class = "btn btn-dark" type="submit">Find by product </button>
</form>
        </th>

    </tr>
    </thead>
</table>

<div class="container-xl">
    <h1>My Receipts</h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>
                <form method="get" action="/receipt/mySortedByNameRecipes">
                    <#if sortOrder == 'asc'>
                        <input type="hidden" class="text" value='desc'>
                        <button class = "btn btn-dark" type="submit">Name</button>
                    </#if>
                    <#if sortOrder == 'desc'>
                        <input type="hidden" class="text" value='asc'>
                        <button class = "btn btn-dark" type="submit" class="dar">Name</button>
                    </#if>
                </form>
            </th>
            <th>
                <form method="get" action="/receipt/mySortedByAuthorRecipes">
                    <#if sortOrder == 'asc'>
                        <input type="hidden" class="text" value='desc'>
                        <button class = "btn btn-dark" type="submit">Author</button>
                    </#if>
                    <#if sortOrder == 'desc'>
                        <input type="hidden" class="text" value='asc'>
                        <button class = "btn btn-dark" type="submit" class="dar">Author</button>
                    </#if>
                </form>
            </th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>

        <#list Recepts as recept>
            <tr>
                <td>${recept.name}</td>
                <td>${recept.user.name}</td>
                <td>${recept.description}</td>
                <td>
                    <form method="get" action="/receipt/detailsRecipe">
                        <input type="hidden" name="id" value="${recept.id}"/>
                        <button class="btn btn-info" type="submit">Details</button>
                    </form>
                    <form method="get" action="/receipt/updateRecipe">
                        <input type="hidden" name="id" value="${recept.id}"/>
                        <input type="hidden" name="name" value="${recept.name}"/>
                        <input type="hidden" name="userName" value="${recept.user.name}"/>
                        <input type="hidden" name="description" value="${recept.description}"/>
                        <button class="btn btn-warning" type="submit">Update Recipe</button>
                    </form>
                    <form method="get" action="/receipt/deleteRecipe">
                        <input type="hidden" name="id" value="${recept.id}"/>
                        <button class="btn btn-danger" type="submit">Delete Recipe</button>
                    </form>
                </td>
            </tr>
        </#list>

        </tbody>
    </table>
    <form method="get" action="/mvc/user">
        <button class="btn btn-primary" type="submit">Previous Page</button>
    </form>
</div>
</body>
</html>


<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Table</title>
    <!-- Add the Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th>
            <form method="get" action="/receipt/allReceipts/findReceptByAuthorName">
                <button class="btn btn-dark" type="submit">Find by author name</button>
            </form>
        </th>
        <th>
            <form method="get" action="/receipt/allReceipts/findByName">
                <button class="btn btn-dark" type="submit">Find by receipt name</button>
            </form>
        </th>
        <th>
            <form method="get" action="/receipt/allReceipts/findByProductName">
                <button class="btn btn-dark" type="submit">Find by product</button>
            </form>
        </th>
    </tr>
    </thead>
</table>
<div class="container-xl">
    <h1>All Receipt</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <!-- Table header -->
            <thead class="thead-dark">
            <tr>
                <th>
                    <form method="get" action="/receipt/allSortedByNameRecipes">
                        <#if sortOrder == 'asc'>
                            <input type="hidden" class="text" value='desc'>
                            <button type="submit" class="btn btn-dark">Name</button>
                        </#if>
                        <#if sortOrder == 'desc'>
                            <input type="hidden" class="text" value='asc'>
                            <button type="submit" class="btn btn-dark">Name</button>
                        </#if>
                    </form>
                </th>
                <th>
                    <form method="get" action="/receipt/allSortedByAuthorRecipes">
                        <#if sortOrder == 'asc'>
                            <input type="hidden" class="text" value='desc'>
                            <button type="submit" class="btn btn-dark">Author</button>
                        </#if>
                        <#if sortOrder == 'desc'>
                            <input type="hidden" class="text" value='asc'>
                            <button type="submit" class="btn btn-dark">Author</button>
                        </#if>
                    </form>
                </th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <!-- Table body -->
            <tbody>
            <#list Recepts as recept>
                <tr>
                    <td>${recept.name}</td>
                    <td>${recept.user.name}</td>
                    <td>${recept.description}</td>
                    <td>
                        <form method="get" action="/receipt/allRecipe/detailsRecipe">
                            <input type="hidden" name="id" value="${recept.id}"/>
                            <button class="btn btn-info" type="submit">Details</button>
                        </form>
                        <#list roles as role>
                            <#if role.name == 'ROLE_ADMIN'>

                                <form method="get" action="/receipt/allRecipe/deleteRecipe">
                                    <input type="hidden" name="id" value="${recept.id}"/>
                                    <input class="btn btn-danger" type="submit" value="delete Recipe"/>
                                </form>

                            </#if>
                        </#list>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <!-- Add a previous page button -->
        <form method="get" action="/mvc/user">
            <button type="submit" class="btn btn-primary">Previous Page</button>
        </form>
    </div>
</div>

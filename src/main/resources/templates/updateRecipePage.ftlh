<!doctype html>
<head xmlns:form="http://www.w3.org/1999/html">

</head>

<body>
<div>
    <form action="/receipt/addRecipe" method="post">
        <input type="hidden" name="id" value="${Recept.id}">
        <input name = "name" placeholder="Name" value="${Recept.name}">
        <input name = "description" placeholder="Description" value="${Recept.description}">
        <input type="submit" value="update">
    </form>
</div>
</body>
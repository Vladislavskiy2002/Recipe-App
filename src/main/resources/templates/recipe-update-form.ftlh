<!DOCTYPE html>
<html>
<head>
    <title>Update Recipe</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Update Recipe</h1>
    <form action="/receipt/recipes/${recipe.id}/update" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="${recipe.name}" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" required>${recipe.description}</textarea>
        </div>
        <hr>
        <h2>Products:</h2>
        <div id="products-container">
            <#list recipe.products as product>
                <div class="form-row product-row">
                    <div class="col-4">
                        <label for="product-name-${product_index}">Name:</label>
                        <input type="text" class="form-control" id="product-name-${product_index}"
                               name="products[${product_index}].name" value="${product.name}" required>
                    </div>
                    <div class="col-4">
                        <label for="product-weight-${product_index}">Weight:</label>
                        <input type="number" class="form-control" id="product-weight-${product_index}"
                               name="products[${product_index}].weight" value="${product.weight}" required>
                    </div>
                    <div class="col-4">
                        <button type="button" class="btn btn-danger remove-product-button">Remove</button>
                    </div>
                </div>
            </#list>
        </div>
        <button type="button" class="btn btn-secondary" id="add-product-button">Add Product</button>
        <hr>
        <button type="submit" class="btn btn-primary">Update Recipe</button>
        <form method="get" action="/receipt/myRecipes">
            <button class="btn btn-primary" type="submit">Previous Page</button>
        </form>
    </form>

</div>
<script>
    var productCount = ${recipe.products?size};
    var productsContainer = document.getElementById("products-container");
    var addProductButton = document.getElementById("add-product-button");
    addProductButton.addEventListener("click", function () {
        var productDiv = document.createElement("div");
        productDiv.className = "form-row product-row";
        var product_index = productCount;
        productDiv.innerHTML =
            '<div class="col-4">' +
            '<label for="product-name-' + product_index + '">Name:</label>' +
            '<input type="text" class="form-control" id="product-name-' + product_index + '" name="products[' + product_index + '].name" required>' +
            '</div>' +
            '<div class="col-4">' +
            '<label for="product-weight-' + product_index + '">Weight:</label>' +
            '<input type="number" class="form-control" id="product-weight-' + product_index + '" name="products[' + product_index + '].weight" required>' +
            '</div>' +
            '<div class="col-4">' +
            '<button type="button" class="btn btn-danger remove-product-button">Remove</button>' +
            '</div>';
        productsContainer.appendChild(productDiv);
        productCount++;
    });
    productsContainer.addEventListener("click", function (event) {
        if (event.target.classList.contains("remove-product-button")) {
            event.target.closest(".product-row").remove();
        }
    });
</script>

</body>
</html>
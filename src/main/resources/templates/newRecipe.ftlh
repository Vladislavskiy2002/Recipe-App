<!DOCTYPE html>
<html>
<head>
    <title>New Recipe</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>New Recipe</h1>
    <form action="/receipt/recipes/new" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <hr>
        <h2>Products:</h2>
        <div id="products-container">
            <div class="form-row product-row">
                <div class="col-4">
                    <label for="product-name-0">Name:</label>
                    <input type="text" class="form-control" id="product-name-0" name="products[0].name" required>
                </div>
                <div class="col-4">
                    <label for="product-weight-0">Weight:</label>
                    <input type="number" class="form-control" id="product-weight-0" name="products[0].weight" required>
                </div>
                <div class="col-4">
                    <button type="button" class="btn btn-danger remove-product-button">Remove</button>
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-secondary" id="add-product-button">Add Product</button>
        <hr>
        <button type="submit" class="btn btn-primary">Save Recipe</button>
    </form>
</div>
<script>
    var productCount = 1;
    var productsContainer = document.getElementById("products-container");
    var addProductButton = document.getElementById("add-product-button");
    addProductButton.addEventListener("click", function() {
        var productDiv = document.createElement("div");
        productDiv.className = "form-row product-row";
        productDiv.innerHTML =
            '<div class="col-4">' +
            '<label for="product-name-' +
            productCount + '">Name:</label>' +
            '<input type="text" class="form-control" id="product-name-' + productCount + '" name="products[' + productCount + '].name" required>' +
            '</div>' +
            '<div class="col-4">' +
            '<label for="product-weight-' + productCount + '">Weight:</label>' +
            '<input type="number" class="form-control" id="product-weight-' + productCount + '" name="products[' + productCount + '].weight" required>' +
            '</div>' +
            '<div class="col-4">' +
            '<button type="button" class="btn btn-danger remove-product-button">Remove</button>' +
            '</div>';
        productsContainer.appendChild(productDiv);
        productCount++;
    });

    productsContainer.addEventListener("click", function(event) {
        if (event.target.classList.contains("remove-product-button")) {
            event.target.closest(".product-row").remove();
        }
    });
</script>
</body>
</html>

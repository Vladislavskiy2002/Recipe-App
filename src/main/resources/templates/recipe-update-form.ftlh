<form method="post" action="/receipt/updateRecipe/${recipe.id}">
    <input type="hidden" name="_method" value="PUT">
    <div>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${recipe.name}" required>
    </div>
    <div>
        <label for="description">Description</label>
        <textarea id="description" name="description" required>${recipe.description}</textarea>
    </div>
    <div>
        <label>Products</label>
        <div id="products">
            <#list recipe.products as product>
                <div class="product">
                    <input type="hidden" name="products[${product_index}].id" value="${product.id}">
                    <input type="text" name="products[${product_index}].name" value="${product.name}" required>
                    <input type="number" name="products[${product_index}].weight" value="${product.weight}" required>
                    <button type="button" class="delete-product">Delete</button>
                </div>
            </#list>
        </div>
        <button type="button" id="add-product">Add Product</button>
    </div>
    <button type="submit">Update Recipe</button>
</form>

<form method="get" action="/receipt/myRecipes">
    <td>
        <input type="submit" value="previous page">
    </td>
</form>
<script>
    let productCount = ${recipe.products?size};
    document.querySelector("#add-product").addEventListener("click", () => {
        const productsDiv = document.querySelector("#products");
        const productDiv = document.createElement("div");
        productDiv.classList.add("product");
        productDiv.innerHTML = `
            <input type="text" name="products[${productCount}].name" required>
            <input type="number" name="products[${productCount}].weight" required>
            <button type="button" class="delete-product">Delete</button>
        `;
        productsDiv.appendChild(productDiv);
        productCount++;
    });
    document.querySelectorAll(".delete-product").forEach(deleteButton => {
        deleteButton.addEventListener("click", () => {
            const productDiv = deleteButton.closest(".product");
            productDiv.parentNode.removeChild(productDiv);
        });
    });
</script>

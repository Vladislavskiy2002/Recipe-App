<form method="post" action="/receipt/saveRecipe">
    <div>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div>
        <label for="description">Description</label>
        <textarea id="description" name="description" required></textarea>
    </div>
    <div>
        <label>Products</label>
        <div id="products">
            <#list 0..(productCount - 1) as i>
                <#if i == 0>
                    <div class="product">
                        <input type="text" name="products[0].name" required>
                        <input type="number" name="products[0].weight" required>
                    </div>
                <#else>
                    <div class="product">
                        <input type="text" name="products[${i}].name" required>
                        <input type="number" name="products[${i}].weight" required>
                        <button type="button" class="delete-product">Delete</button>
                    </div>
                </#if>
            </#list>
        </div>
        <button type="button" id="add-product">Add Product</button>
    </div>
    <button type="submit">Save Recipe</button>
</form>
<script>
    let productCount = ${productCount};
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
        const deleteButton = productDiv.querySelector(".delete-product");
        deleteButton.addEventListener("click", () => {
            productDiv.remove();
            productCount--;
        });
    });
</script>

<form method="get" action="/mvc/user">
    <td>
        <input type="submit" value="previous page">
    </td>
</form>
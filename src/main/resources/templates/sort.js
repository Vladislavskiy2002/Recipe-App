let sortOrder = 'asc'; // default sort order is ascending

function sortByName() {
    $.ajax({
        url: "/receipt/myRecipes?sortBy=name&sortOrder=" + sortOrder,
        type: "GET"
    });
    // Toggle the sort order
    sortOrder = (sortOrder === 'asc') ? 'desc' : 'asc';
}

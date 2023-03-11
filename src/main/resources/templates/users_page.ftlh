<!doctype html>
<body xmlns:form="http://www.w3.org/1999/html">
<div>
    <table>
        <tr>
            <th>   ID</th>
            <th>   First Name </th>
            <th>   Last Name </th>
            <th>   Email </th>
        </tr>
        <tr>
            <td>   ${User.id}</td>
            <td>   ${User.name}</td>
            <td>   ${User.surname}</td>
            <td>   ${User.email}</td>
        </tr>
        <form method="get"action="/receipt/myRecipes">
            <td>
                <input type = "submit" value = "All my recipes"/>
            </td>
        </form>
    </table>
</div>
</body>
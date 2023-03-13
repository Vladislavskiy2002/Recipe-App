<!doctype html>
<body>
<div>
    <table>
        <tr>
            <th>   ID </th>
            <th>   Receipt Name </th>
            <th>   Created by </th>
            <th>   Description </th>
        </tr>
        <#list Recepts as recept>
        <tr>
            <td>   ${recept.id}</td>
            <td>   ${recept.name}</td>
            <td>   ${recept.user.name}</td>
            <td>   ${recept.description}</td>

            <form method="get"action="/receipt/updateRecipe">
                <td>
                    <input type="hidden" name="id" value="${recept.id}" />
                    <input type="hidden" name="name" value="${recept.name}" />
                    <input type="hidden" name="userName" value="${recept.user.name}" />
                    <input type="hidden" name="description" value="${recept.description}" />
                    <input type = "submit" value = "update Recipe"/>
                </td>
            </form>
            <form method="get"action="/receipt/deleteRecipe">
                <td>
                    <input type="hidden" name="id" value="${recept.id}" />
                    <input type = "submit" value = "delete Recipe"/>
                </td>
            </form>
        </tr>
        </#list>
    </table>
</div>
</body>
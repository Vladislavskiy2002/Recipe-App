<!doctype html>
<body>
<div>
    <table>
        <tr>
            <th> ID</th>
            <th> Receipt Name</th>
            <th> Created by</th>
            <th> Description</th>
        </tr>
        <#list Recepts as recept>
            <tr>
                <td>   ${recept.id}</td>
                <td>   ${recept.name}</td>
                <td>   ${recept.user.name}</td>
                <td>   ${recept.description}</td>
            </tr>
            <#list roles as role>
                <#if role.name == 'ROLE_ADMIN'>
                    <form method="get" action="/receipt/deleteRecipe">
                        <td>
                            <input type="hidden" name="id" value="${recept.id}"/>
                            <input type="submit" value="delete Recipe"/>
                        </td>
                    </form>
                </#if>
            </#list>
        </#list>
        <form method="get" action="/mvc/user">
            <td>
                <input type="submit" value="previous page">
            </td>
        </form>
    </table>
</div>
</body>
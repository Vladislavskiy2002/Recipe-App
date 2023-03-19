<!doctype html>
<body>
<div>
    <table>
        <tr>
            <th> ID</th>
            <th> First Name</th>
            <th> Last Name</th>
            <th> Email</th>
        </tr>
        <#list Users as user>
            <tr>
                <td>   ${user.id}</td>
                <td>   ${user.name}</td>
                <td>   ${user.surname}</td>
                <td>   ${user.email}</td>
            </tr>
            <form method="get" action="/mvc/admin/deleteUser">
                <td>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="delete User"/>
                </td>
            </form>
        </#list>
        <form method="get" action="/mvc/user">
            <td>
                <input type="submit" value="previous page">
            </td>
        </form>
    </table>
</div>
</body>
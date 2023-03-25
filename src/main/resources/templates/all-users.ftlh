<!DOCTYPE html>
<html>
<head>
    <title>Receipt Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div>
    <table>
        <tr>
            <th> ID</th>
            <th> First Name</th>
            <th> Last Name</th>
            <th> Email</th>
            <th> Action</th>
        </tr>
        <#list Users as user>
            <tr>
                <td>   ${user.id}</td>
                <td>   ${user.name}</td>
                <td>   ${user.surname}</td>
                <td>   ${user.email}</td>
                <form method="get" action="/mvc/admin/deleteUser">
                    <td>
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input class= "btn btn-danger" type="submit" value="delete User"/>
                    </td>
                </form>
            </tr>
            <!-- <form method="post" action="/mvc/admin/giveAdminRole">
                <td>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="email" value="${user.email}"/>
                    <input type="submit" value="give admin role"/>
                </td>
            </form> ТИМЧАСОВО НЕРОБОЧЕ ПОКИ НЕЗНАЙДУ ВИХІД З ЕНКОДЕРОМ-->

        </#list>
        <form method="get" action="/mvc/user">
            <td>
                <button type="submit" class="btn btn-primary">previous page</button>
            </td>
        </form>
    </table>
</div>
</body>
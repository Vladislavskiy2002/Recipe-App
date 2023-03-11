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
        </tr>
        </#list>
    </table>
</div>
</body>
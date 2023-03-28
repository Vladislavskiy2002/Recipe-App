<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Table</title>
    <!-- Add the Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
<div>
    <form action="/signUp" method="post">
        <input name="name" placeholder="FirstName">
        <input name="surname" placeholder="LastName">
        <input name="email" placeholder="Email">
        <input name="password" placeholder="password" type="password">
        <input type="submit" value="SignUp">
    </form>
    <#if errors?has_content>
        <ul>
            <#list errors as error>
                <li class="text-danger">${error.defaultMessage}</li>
            </#list>
        </ul>
    </#if>
</div>
</body>
</html>
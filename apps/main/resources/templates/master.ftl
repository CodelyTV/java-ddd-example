<#include "partials/header.ftl">
<#include "partials/footer.ftl">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">

    <title>${title}</title>
    <title>${description}</title>
</head>
<body>
<@header/>

<div class="container mx-auto px-4 p-5">
    <@main/>
</div>

<@footer/>
</body>
</html>

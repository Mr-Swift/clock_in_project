<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <script src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(function () {
           window.location.href="<%=request.getContextPath()%>/login/toLogin.do";
        });
    </script>
</head>

<body >
</body>
</html>


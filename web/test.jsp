<%--
  Created by IntelliJ IDEA.
  User: tym
  Date: 17-7-24
  Time: 上午8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Title</title>

    <link rel="stylesheet" href="css/test.css"/>
</head>
<body onload="changeImg()">
<div class="main_bar">
    <div id="login_form" class="login_form">
        <div class="title"></div>
        <form action="${pageContext.request.contextPath}/login.action" method="post">
            <div id="form_widgt">
                <input type="text" name="name" class="name" placeholder="请输入账号""><br>
                <input type="password" name="pwd" class="pwd"  placeholder="请输入密码"><br>
                <p class="yzm"><input type="text" name="code" id="codeInput" class="code" placeholder="验证码">
                    <span id="code" class="code_pic" title="看不清，换一张"></span></p>
                <p class="errorTips" id="errorTips"></p>
                <%--<a href="javascript:;" name="sbm" class="sbm_btn" onclick="return check()">登录</a>--%>
                <input class="sbm_btn" type="submit" onclick="return check()" value="登录"/>
            </div>
        </form>
        <div class="re_pwd"><a href="">忘记密码了</a></div>
    </div>
    <script src="js/test.js" rel="stylesheet"></script>

</div>


</body>
</html>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="#" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>登录</title>

</head>
<body>
	<br>
    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal" action="login.jsp" method="post">
                    <span class="heading">用户登录</span>
                    <div class="form-group">
                        <input type="text" class="form-control" id="userName" placeholder="用户名或电子邮件" name="username">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="passWord" placeholder="密　码" name="password">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
    				<div class="form-group">
    					<input type="checkbox" name="rememberMe" value="true"/>Remember Me?
    				 	<i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <label for="checkbox1"></label>
                        <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
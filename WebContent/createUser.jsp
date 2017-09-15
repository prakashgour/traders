<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>

 
        <form action="login"  method="post" >
        <h2>Gerrit user creation</h2>
        User name:  <input type="text" name="user" />*<br/><br/>
        Password :  <input type="password" name="pass" />*<br/><br/>
        <input type="hidden" name ="register" value="create" />
        <input type="submit" value="Register" /><br/><br/>
        </form><br/><br/>
        

</body>
</html>
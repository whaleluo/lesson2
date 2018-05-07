<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored ="false"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript" src="./My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function checkForm(){
	var name=document.getElementById("name").value;
	var birthday=document.getElementById("birthday").value;
	var description=document.getElementById("description").value;
	var score=document.getElementById("score").value;
	if(name==null||name==""){
		document.getElementById("error").innerHTML="姓名不能为空！";
		return false;
	}
	if(birthday==null||birthday==""){
		document.getElementById("error").innerHTML="出生日期不能为空！";
		return false;
		}else if(birthday.match()){
			
		}
	
	if(description==null||description==""){
		document.getElementById("error").innerHTML="备注不能为空！";
		return false;
	}
	if(score==null||score==""){
		document.getElementById("error").innerHTML="分数不能为空！";
		return false;
	}else if(score<0||score>100){
		document.getElementById("error").innerHTML="分数必须在0~100之间！";
		return false;
	}
}
</script>
<title>Insert title here</title>
</head>
<body>

<form action="student?action=add" method="post" onsubmit="return checkForm()">
	<span id="error" style="color:red"></span>
	<input type="hidden" name="olddata" value="${student.id },${student.name },${student.birthday },${student.description }"></intupt>
	<table>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" id="name" value="${student.name }"></input</td>
			
		</tr>
			<tr>
			<td>出生日期</td>
			<td><input type="text" name="birthday" id="birthday"  readonly="readonly" value="${student.birthday }"></input><img onclick="WdatePicker({el:'birthday'})" src="./My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" name="description" id="description" value="${student.description }" ></input</td>
		</tr>
		<tr>
			<td>平均分</td>
			<td><input type="text" name="score" id="score" value="<fmt:parseNumber value="${student.score}" type="number" integerOnly="true"/>" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')"></input></td>
		</tr>
	</table>
	<input type="submit" value="提交"></input>
</form>
</body>
</html>
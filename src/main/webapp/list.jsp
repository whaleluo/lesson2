<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored ="false"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function jumpPage() {
		var jpage = document.getElementById("jp").value;
		if(0<parseInt(jpage)&&parseInt(jpage)<=${lastPage}){
			window.location.href="student?action=list&currentPage="+jpage;
		}else{
			document.getElementById("error").innerHTML="当前只有${lastPage}页,请输入大于0小于${lastPage}的整数"
		}
	}
	
	function checkPageUp() {
		if(parseInt(${currentPage})>1){
			return true
		}else{
			return false
		}
	}
	
	function checkPageDown() {
		if(parseInt(${currentPage})<parseInt(${lastPage})){
			return true
		}else{
			return false
		}
	}
	
</script>
<title>Insert title here</title>
</head>
<body>
<div class="studentlist" align="center">

	<table class="table table-hover table-bordered">
			<tr>
				<th>序号</th>
				<th>学生ID</th>
				<th width="60px">姓名</th>
				<th width="100px">出生日期</th>
				<th width="120px">备注</th>
				<th width="80px">平均数</th>&nbsp
				<th width="110px">操作</th>
				<th width="80px"><button class="btn" type="button" onclick="javascript:window.location.href='student?action=preadd'">添加</button>&nbsp;</th>
			</tr>
			 <c:forEach items="${studentList }" var="sl" varStatus="status">
				<tr>
					<td align="center">${status.index+1 }</td>
					<td align="center"><c:out value="${sl.id}"/></td>
					<td align="center"><c:out value="${sl.name}"/></td>
					<td align="center"><c:out value="${sl.birthday}"/></td>
					<td align="center" title="${sl.description}">${fn:substring(sl.description, 0, 5)}</td>
					<td align="center"><fmt:parseNumber value="${sl.score}" type="number" integerOnly="true"/></td>
					<td>
						<button class="btn" type="button" onclick="javascript:window.location.href='student?action=preadd&id=${sl.id}&score=${sl.score}'">修改</button>&nbsp;
						<button class="btn" type="button" onclick="javascript:window.location.href='${pageContext.request.contextPath }/student?action=rem&data=${sl.id },${sl.name },${sl.birthday },${sl.description }'">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
</div>
<div align="center">
	<a href="${pageContext.request.contextPath}/student?action=list&currentPage=1" onclick="return checkPageUp()">首页</a>
	<a href="${pageContext.request.contextPath}/student?action=list&currentPage=${currentPage-1}" onclick="return checkPageUp()">上一页</a>
	<a href="${pageContext.request.contextPath}/student?action=list&currentPage=${currentPage}">第${currentPage}页</a>
	<a href="${pageContext.request.contextPath}/student?action=list&currentPage=${currentPage+1}" onclick="return checkPageDown()">下一页</a>
	<a href="${pageContext.request.contextPath}/student?action=list&currentPage=${lastPage}" onclick="return checkPageDown()">最后一页</a>
	
	<button onclick="jumpPage()">跳转到指定页</button>
	<input id="jp" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" s name="f_order" value="1" style="width:20px;"/>
</div>
	<span id="error" style="color:red;position: absolute;right:300px"></span>
</body>
</html>
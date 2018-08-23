<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>App功能套餐管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a>功能套餐列表</a></li>
    <shiro:hasPermission name="sys:appfunction:edit">
        <li><a href="${ctx}/sys/appfunction/form?appid=${appId}">功能套餐添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="appFunction" action="${ctx}/sys/appfunction/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="appId" name="appId" type="hidden" value="${appId}"/>
    &nbsp;&nbsp;<label>App ：</label>${app.appname }
    &nbsp;<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> -->
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>功能套餐</th>
        <th>描述</th>
        <shiro:hasPermission name="sys:appfunction:edit">
            <th>操作</th>
        </shiro:hasPermission></tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="appfunction">
        <tr>
            <td><a href="${ctx}/sys/appfunction/form?id=${appfunction.id}">${appfunction.functionName}</a></td>
            
            <td>${appfunction.remarks}</td>
            <shiro:hasPermission name="sys:appfunction:edit">
                <td>
                    <a href="${ctx}/sys/appfunction/form?id=${appfunction.id}">修改</a>
                    <a href="${ctx}/sys/appfunction/delete?id=${appfunction.id}"
                       onclick="return confirmx('确认要删除该App吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>App管理</title>
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
    <li class="active"><a href="${ctx}/sys/apps/">App列表</a></li>
    <shiro:hasPermission name="sys:apps:edit">
        <li><a href="${ctx}/sys/apps/form">App添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="apps" action="${ctx}/sys/apps/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>类型：</label><form:select id="type" path="type" class="input-medium"><form:option value=""
                                                                                           label=""/><form:options
        items="${typeList}" htmlEscape="false"/></form:select>
    &nbsp;&nbsp;<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50"
                                               class="input-medium"/>
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>App名称</th>
        <th>类型</th>
        <th>描述</th>
        <shiro:hasPermission name="sys:apps:edit">
            <th>操作</th>
        </shiro:hasPermission></tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="apps">
        <tr>
            <td><a href="${ctx}/sys/apps/form?id=${apps.id}">${apps.appname}</a></td>
            <td><a href="javascript:"
                   onclick="$('#type').val('${apps.type}');$('#searchForm').submit();return false;">${apps.type}</a>
            </td>
            <td>${apps.description}</td>
            <shiro:hasPermission name="sys:apps:edit">
                <td>
                    <a href="${ctx}/sys/apps/form?id=${apps.id}">修改</a>
                    <a href="${ctx}/sys/apps/delete?id=${apps.id}&type=${apps.type}"
                       onclick="return confirmx('确认要删除该App吗？', this.href)">删除</a>
                       <shiro:hasPermission name="sys:appfunction:view">
                       <a href="${ctx}/sys/appfunction/list?appId=${apps.id}">功能套件</a>
                       </shiro:hasPermission>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
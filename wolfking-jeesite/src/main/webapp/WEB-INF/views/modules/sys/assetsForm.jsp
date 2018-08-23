<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>assets管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#value").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
   <!--  <li><a ></a></li> -->
    <li class="active"><a >资产权利</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="assets" action="${ctx}/sys/apps/saveassets" method="post" class="form-horizontal">
    <form:hidden path="eid"/>
    <form:hidden path="appid"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">存储限制:</label>
        <div class="controls">
            <form:input path="storage" htmlEscape="false" maxlength="50" class="required"/>GB
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">带宽限制:</label>
        <div class="controls">
            <form:input path="bandwidth" htmlEscape="false" maxlength="50" class="required"/>MB/S
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">账户数限制:</label>
        <div class="controls">
            <form:input path="naccount" htmlEscape="false" maxlength="50" class="required"/>人
        </div>
    </div>
     <div class="control-group">
        <label class="control-label">终端数量限制:</label>
        <div class="controls">
            <form:input path="ndevices" htmlEscape="false" maxlength="50" class="required"/>台
        </div>
    </div>
     <div class="control-group">
        <label class="control-label">功能套餐:</label>
        <div class="controls">
            <%-- <form:input path="workMode" htmlEscape="false" maxlength="50" class="required"/> --%>
            <select id="workMode" name="workMode" value="${assets.workMode }" style="width:200px;">
            <c:forEach var="wm" items="${aflist}" >
            <option value="${wm.id}" <c:if test="${wm.id eq assets.workMode }">selected</c:if>>${wm.functionName}</option>
            </c:forEach>
			</select>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sys:apps:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                         value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>分配Apptouser</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        $(function() {
            //实现全选反选
            $("#theadInp").on('click', function() {
                $("tbody input:checkbox").prop("checked", $(this).prop('checked'));
            })
            $("tbody input:checkbox").on('click', function() {
                //当选中的长度等于checkbox的长度的时候,就让控制全选反选的checkbox设置为选中,否则就为未选中
                if($("tbody input:checkbox").length === $("tbody input:checked").length) {
                    $("#theadInp").prop("checked", true);
                } else {
                    $("#theadInp").prop("checked", false);
                }
            })
        })
	 function btnbaocun(){
	    obj = document.getElementsByName("mycheck");
	    check_val = [];
	    for(k in obj){
	        if(obj[k].checked){
	        	var selectid=obj[k].value+"role";
	        	var selectvalue= document.getElementById(selectid);
	        	var index=selectvalue.selectedIndex ;
	        	var vall=selectvalue.options[index].value;
	        	//alert(vall);
	        	if(vall=='0'){
	        		alert("请选择App角色");
	        		return false;
	        	}
	            check_val.push(obj[k].value+"##"+vall);
	        }
	    }
	    $("#appids").val(check_val);
	    //alert(check_val);
	    $("#searchForm").submit();
	}
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a>分配App</a></li>
    <%-- <shiro:hasPermission name="sys:apps:edit">
        <li><a href="${ctx}/sys/apps/form">App添加</a></li>
    </shiro:hasPermission> --%>
</ul>
<form:form id="searchForm" modelAttribute="apps" action="${ctx}/sys/apps/saveapptouser" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="officeid" name="officeid" type="hidden" value="${office.id}"/>
     <input id="userid" name="userid" type="hidden" value="${user.id}"/>
    <input id="appids" name="appids" type="hidden" value=""/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>用户名称：</label>${user.name }
    
   <%--  <label>类型：</label><form:select id="type" path="type" class="input-medium"><form:option value=""
                                                                                           label=""/><form:options
        items="${typeList}" htmlEscape="false"/></form:select>
    &nbsp;&nbsp;<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50"
                                               class="input-medium"/>
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> --%>
    &nbsp;<input id="btn" onclick="btnbaocun();" class="btn btn-primary" type="button" value="保存分配"/>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
    <th> <input type="checkbox" id="theadInp" /></th>
        <th>App名称</th>
        <th>角色</th>
        <%-- <shiro:hasPermission name="sys:apps:edit">
            <th>操作</th>
        </shiro:hasPermission> --%></tr>
    </thead>
    <tbody>
    <c:forEach items="${aolist}" var="ao" >
        <tr>
        <td> 
        <c:if test="${!empty ao.userid }">
        <input type="checkbox" checked="checked" id="${ao.appId}" value="${ao.appId}" name="mycheck"/>
        </c:if>
        <c:if test="${empty ao.userid }">
        <input type="checkbox" id="${ao.appId}"  value="${ao.appId}" name="mycheck"/>
        </c:if>
        </td>
            <td>${ao.app.appname}</td>
            <td>
            <%-- <form:select path="roleid" class="input-xlarge">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('app_role')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select> --%>
            <select id="${ao.appId}role">
              <option value="0">-请选择-</option>
			  <option value ="1" <c:if test="${'1' eq ao.roleid}">selected</c:if>>管理员</option>
			  <option value ="2" <c:if test="${'2' eq ao.roleid}">selected</c:if>>专家</option>
			  <option value="3" <c:if test="${'3' eq ao.roleid}">selected</c:if>>工程师</option>
			  <option value="4" <c:if test="${'4' eq ao.roleid}">selected</c:if>>普通用户</option>
			</select>
            </td>
            
           <%--  <shiro:hasPermission name="sys:apps:edit">
                <td>
                    <a href="${ctx}/sys/apps/assets?appid=${apps.id}&officeid=${office.id}">资产权利</a>
                   
                </td>
            </shiro:hasPermission> --%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%-- <div class="pagination">${page}</div> --%>
</body>
</html>
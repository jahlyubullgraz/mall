<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="subShopList" method="post">
        <ul class="bjui-searchBar">
            <li><label>关键词：</label>
            <input type="text" id="key" name="key"  size="15" value="${key!''}"/>
            <button type="submit" class="btn-default" data-icon="search">查询</button>
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>可以搜索商户手机号</li>
        </ul>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
			<tr>
			<!--
				<th orderField="code">商户全称</th>
                <th orderField="name">状态</th>
             -->   
             	<th>商户帐号</th>
                <th>微信账号</th>
                <th>账户余额</th>
                <th>商户类别</th>
                <th>注册时间</th>
              	<th width="150">操作</th>
			</tr>
		</thead>
		<tbody>
        <#list userList as user>
            <tr>
                <td>${user.no}</td>
                <td>${user.nickname}</td>
                <td>${user.commission!""}</td>
                <td>${user.typeName}</td>
                <td>${user.createDate}</td>
                <td>
                	<a href="subSubShopList.action?id=${user.id?string('#')}" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="subSubShopList" data-mask="true">下级商户</a>
                	<#if flag = "1">
						<a href="toUpgrade.action?id=${user.id?string('#')}" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="toUpgrade" data-mask="true">升级</a>
                	</#if>
                    <!-- 
                    <a href="userEdit.action?id=${user.id?string('#')}" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="userEdit" data-mask="true">编辑</a>
                    <a href="userDelete.action?id=${user.id?string('#')}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？">删</a>
               		 -->
                </td>
            </tr>
		</#list>
		</tbody>
    </table>
    <#include "pageBar.ftl"/>
</div>
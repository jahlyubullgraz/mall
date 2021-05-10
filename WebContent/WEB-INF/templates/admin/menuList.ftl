<div class="bjui-pageHeader">
        <ul class="bjui-searchBar">
            <li>
			<a href="adminAdd" class="btn btn-default" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-mask" data-mask="true">添加</a>
			</li>
        </ul>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
			<tr>
				<th>ID</th>
                <th>菜单名称</th>
                <th>菜单URL</th>
                <th>创建时间</th>
                <th width="90">操作</th>
			</tr>
		</thead>
		<tbody>
        <#list menuList as menu>
            <tr>
                <td>${menu.id}</td>
                <td>${menu.menuName}</td>
                <td>${menu.menuUrl}</td>
                <td>${menu.createDate}</td>
                <td>
                    <a href="menuEdit?id=${menu.id}" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="menuEdit" data-mask="true">编辑</a>
                    <a href="menuDelete?id=${menu.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？">删</a>
                </td>
            </tr>
		</#list>
		</tbody>
    </table>
</div>
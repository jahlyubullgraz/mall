<div class="bjui-pageContent">
    <form action="menuUpdate" method="post" class="pageForm" data-toggle="validate" data-reload-navtab="true">
        <input type="hidden" name="menu.id" value="${menu.id}">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover">
            	<thead>
                    <tr>
                        <td colspan="2" align="center"><h3>修改菜单</h3></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    	<td>
                            <label for="j_dialog_code" class="control-label x90">菜单名称：</label>
                            <input type="text" name="menu.menuName" id="menuName" data-rule="required" size="20" value="${menu.menuName}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="j_dialog_name" class="control-label x90">菜单URL：</label>
                            <input type="password" name="menu.menuUrl" id="menuUrl" data-rule="" size="100">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close">关闭</button></li>
                <li><button type="submit" class="btn-default">保存</button></li>
            </ul>
        </div>
    </form>
</div>
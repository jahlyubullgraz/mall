<div class="bjui-pageContent">
    <form action="upgrade" class="pageForm" data-toggle="validate" data-reload-navtab="true">
        <input type="hidden" name="user.id" value="${user.id?string('#')}">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover">
                <tbody>
                    <tr>
                        <td colspan="2" align="center"><h3>商户升级</h3></td>
                    </tr>
                    <tr>
                    	<td>
                            <label for="j_dialog_code" class="control-label x90">编号：</label>
                            <input type="text" name="user.no" readonly="readonly" id="no" data-rule="required" size="20" value="${user.no}">
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label for="j_dialog_code" class="control-label x90">用户名：</label>
                            <input type="text" name="user.name" readonly="readonly" id="name" data-rule="required" size="20" value="${user.name}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="j_dialog_name" class="control-label x90">渠道商级别：</label>
                            <select name="user.type" class="control-label x90">
                            	<option value="4">钻石商户</option>
                            	<option value="3">铂金商户</option>
                            	<option value="2">白金商户</option>
                            </select>
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
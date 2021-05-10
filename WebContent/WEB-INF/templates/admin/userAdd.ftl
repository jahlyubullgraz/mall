<form action="userUpdate" class="pageForm" data-toggle="validate" data-reload-navtab="true">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover">
                <tbody>
                    <tr>
                        <td colspan="2" align="center"><h3>下级渠道管理</h3></td>
                    </tr>
                    <tr>
                    	<td>
                            <label for="j_dialog_code" class="control-label x90">渠道商帐号：</label>
                            <input type="text" name="user.no" id="no" data-rule="required" size="20" >
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label for="j_dialog_code" class="control-label x90">渠道商名称：</label>
                            <input type="text" name="user.name" id="name" data-rule="required" size="20" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="j_dialog_name" class="control-label x90">支付宝帐号：</label>
                            <input type="text" name="user.password" id="password" data-rule="required" size="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="j_dialog_name" class="control-label x90">支付宝佣金率：</label>
                            <input type="text" name="phone" id="phone" data-rule="required" size="20" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="j_dialog_name" class="control-label x90">营业执照扫描件：</label>
                            <input type="text" name="balance" id="balance" data-rule="required" size="20" >
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="submit" class="btn-default">保存</button></li>
            </ul>
        </div>
    </form>

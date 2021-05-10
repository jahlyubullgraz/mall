<div class="bjui-pageContent">
	<form id="j_withdraw_form" method="post" action="withdrawSave" data-toggle="validate" method="post">
		<div class="pageFormContent" data-layout-h="0">
            <hr>
            <div class="form-group">
                <label for="j_pwschange_oldpassword" class="control-label x85">可用余额：</label>
                <input data-rule="required" value="${balanceAmt}" disabled />
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label for="j_pwschange_newpassword" class="control-label x85">提现金额：</label>
                <input data-rule="提现金额:required" name="withdraw.money" id="money" value="" placeholder="提现金额" size="20">
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label for="j_pwschange_newpassword" class="control-label x85">提现人：</label>
                <img src="${headimgurl}" height="40px" width="40px" />
                ${nickname}
            </div>
		</div>
		<div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close">取消</button></li>
                <li><button type="submit" class="btn-default">提交</button></li>
            </ul>
		</div>
	</form>
</div>
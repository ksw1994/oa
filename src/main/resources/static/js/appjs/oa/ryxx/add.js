$().ready(function() {
	$.ajax({
		type : "get",
		url : "/oa/xmz/getAll",
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data) {
				for (var i = 0; i < data.length; i++) {
					$('#teamId').append('<option value="'+data[i].id+'">'+data[i].team+'</option>');
				};
			}
		}
	});
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/ryxx/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	//手机号码验证
	jQuery.validator.addMethod("isPhone",function(value,element){
		var length = value.length;
		var phone=/^1[3|4|5|7|8][0-9]\d{8}$/;
		return this.optional(element)||(length == 11 && phone.test(value));
	},icon + "请填写正确的11位手机号");

	$("#signupForm").validate({
		rules : {
			teamId : "required",
			name : "required",
			sex : "required",
			cardType : "required",
			cardId : "required",
			phone : {
				required:true,
				isPhone:true
			},
			email : {
				required:true,
				email:true
			},
			companyName : "required",
			site : "required",
			pactSdate : "required",
			pactEdate : "required",
			inSdate : "required",
			status : "required"
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
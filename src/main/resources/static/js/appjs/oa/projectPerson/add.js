$().ready(function() {
	dataMin();
	projectidselect();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
//退场时间设定不能小于进场
function dataMin(){
	var einlassTime = $("#einlass").val();
	
		
		$("#exitTime").attr("min",einlassTime);
	
}

//userid下拉

//点击事件
function c(){
	var strList="";
	$('input[type="checkbox"][name="item"]:checked').each(function(){
		if(strList==""){ 
		    strList = strList+$(this).val();
		}else{ 
		    strList = strList+","+$(this).val();
		}
	});
	//alert(strList);
	$('#input1').val(strList);
}
//显示div
function f(){
	$("#input1").click(function(){

	var input=$("#input1");
	var offset =input.offset();
	$("#div1").css("left",offset.left + "px")
	          .css("top",offset.top + input.height()+4+"px")
	          .css("width",input.width() - 10+"px")
	          .fadeIn();
	});
}
//鼠标离开
function l(){
	$("#div1").hide("slow")
}

//
function addUserId() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '320px' ],
		content : '/oa/projectPerson/addUserId' // iframe的url
	});
}

//
//function getUserIdCheckbox(){
//	var s = $("input[name='item']:checked");
//	var checkedBoxValue="";
//	s.each(function(){
//		checkedBoxValue += $(this).val()+",";
//	})
//	checkedBoxValue = checkedBoxValue.substring(0,checkedBoxValue.length-1);
//	alert(checkedBoxValue);
//	$("#userId").attr("value",checkedBoxValue);
//	alert($("#userId").val())
//}

//获取projectId遍历到input。list组
function projectidselect(){
	$.ajax({
		type:"get",
		url:"/oa/project/list",
		dataType:"json",
		success:function(data){
				//debugger;
				var options ="";
				for (var i=0; i<data.rows.length;i++){
					options+="<option value=\""+data.rows[i].id+"\">"+data.rows[i].itemName+"</option>";
					}
				$("#projectIdList").html(options);
				
		
		}
	})
}

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/projectPerson/save",
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
$(function(){
	
});

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
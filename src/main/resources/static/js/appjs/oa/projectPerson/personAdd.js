$().ready(function() {
	getUserTreeData();
	
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		getAllSelectNodes();
		save();
	}
});
//userTree 
var userIds;
function loadUserTree(userTree) {
	$('#userTree').jstree({
		"plugins" : [ "wholerow", "checkbox" ],
		'core' : {
			'data' : userTree
		},
		"checkbox" : {
			//"keep_selected_style" : false,
			//"undetermined" : true
			//"three_state" : false,
			//"cascade" : ' up'
		}
	});
	$('#userTree').jstree().close_all();
}
function getAllSelectNodes() {
	console.log("getallselect");
	var ref = $('#userTree').jstree(true); // 获得整个树
	userIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
	$("#userTree").find(".jstree-undetermined").each(function(i, element) {
		userIds.push($(element).closest('.jstree-node').attr("id"));
	});
	console.log(userIds); 
}

function getUserTreeData() {
	$.ajax({
		type : "GET",
		url : "/oa/projectPerson/tree/",
		success : function(data) {
			loadUserTree(data);
			console.log(data); 
		}
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

////获取projectId遍历到input。list组
//function projectidselect(){
//	$.ajax({
//		type:"get",
//		url:"/oa/project/list",
//		dataType:"json",
//		success:function(data){
//				//debugger;
//				var options ="";
//				for (var i=0; i<data.rows.length;i++){
//					options+="<option value=\""+data.rows[i].id+"\">"+data.rows[i].itemName+"</option>";
//					}
//				$("#projectIdList").html(options);
//				
//		
//		}
//	})
//}

function save() {
	$('#userIds').val(userIds);
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
			projectId : {
				required : true
			},
			einlass : {
				required : true
			},
			exitTime : {
				required : true
			}
		},
		messages : {
			projectId : {
				required : icon + "请选择项目"
			},
			einlass : {
				required : icon + "请选择入场时间"
			},
			exitTime : {
				required : icon + "请选择退场时间"
			}
		}
	})
}
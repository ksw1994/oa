
var prefix = "/oa/project"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},{
									field : 'itemName', 
									title : '项目名称' 
								},/*
																{
									field : 'isJhItem', 
									title : '是否建行项目' 
								},
																{
									field : 'frame', 
									title : '使用建行新一代框架' 
								},
																{
									field : 'isZngjItem', 
									title : '是否中农工交银行项目' 
								},
																{
									field : 'isNozngjItem', 
									title : '是否中农工交以外其他银行' 
								},*/
																{
									field : 'itemType', 
									title : '项目业务类别' 
								},
																{
									field : 'sdate', 
									title : '开始日期' 
								},
																{
									field : 'edate', 
									title : '结束日期' 
								},
								{
									field : 'workNum', 
									title : '需求总工作量' ,formatter:function(value,row,index){
						            	var  html =  "<a id='workNum"+row.id+"' onmouseover='getWorkInfo("+row.id+")' onmouseout = 'closeWorkInfo("+row.id+")'>"+value+"</a>";
						                return html;
					            	}
									
								},
																{
									field : 'witness', 
									title : '证明人' 
								},
																{
									field : 'telephone', 
									title : '证明人电话' 
								},
								/*								{
									field : 'desc', 
									title : '项目简述' 
								},
																{
									field : 'companyName', 
									title : '公司名称' 
								},
																{
									field : 'inSdate', 
									title : '进场日期' 
								},
																{
									field : 'outSdate', 
									title : '退场日期' 
								},
								
																{
									field : 'cWorkNum', 
									title : '初级需求工作量' 
								},
																{
									field : 'zWorkNum', 
									title : '中级需求工作量' 
								},
																{
									field : 'gWorkNum', 
									title : '高级需求工作量' 
								},
																{
									field : 'demand', 
									title : '维护要求' 
								},
																{
									field : 'proceedId', 
									title : '事项编号' 
								},
																{
									field : 'pactId', 
									title : '合同编号' 
								},
																{
									field : 'helpName', 
									title : '协管员' 
								},
																{
									field : 'pactNum', 
									title : '合同人数' 
								},
																{
									field : 'spotNum', 
									title : '在场人数' 
								},
																{
									field : 'inNum', 
									title : '按合同入场人数' 
								},
																{
									field : 'changeStaff', 
									title : '变更人员' 
								},
																{
									field : 'failStaff', 
									title : '不符合资质人员' 
								},
																{
									field : 'status', 
									title : '项目状态' 
								},*/
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var u = '<a class="btn btn-success btn-sm " href="#" title="上传考勤信息"  mce_href="#" onclick="buttonUpload(\''
												+ row.id
												+ '\')"><i class="fa fa-upload"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="考勤信息"  mce_href="#" onclick="getAttendanceInfo(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d + u + f;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function buttonUpload(projectId){
	if ($("#fileUpload")) {
        $("#fileUpload").click();
    }
	$("#uploadProjectId").val(projectId);
}

function upload() {
/*	// todo 显示loading界面
	var index = layer.msg("导入中....", {
        icon: 0,
        time: 2000000
    });*/
	
    var s = $('#fileUpload')[0].files[0];
    var formData = new FormData();
    formData.append("uploadFile", s);
    formData.append("projectId", $("#uploadProjectId").val());
    //清空地址
	$("#fileUpload").val('');
	$("#uploadProjectId").val('');
	
    $.ajax({
    	url: '/oa/attendance/upload' ,
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
        	if(result.code==0){
        		/*layer.close(index);*/
        		layer.msg("导入成功");
				reLoad();
    	        /*if(result.data!=null&&result.data.length>0){
    	        	layer.msg("导入成功");
    	        	Feng.tip("返回信息",result.data);
        		}*/
        	}else{
        		layer.msg("导入失败！     "+result.msg,{icon: 2});
        	}
        },
        error: function (err) {
        	layer.msg("导入失败!",{icon: 2});
        }
    });
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}
/**
 * 获取项目工作量详情
 * @param projectId
 * @returns
 */
function getWorkInfo(projectId){
	layer.open({
		type : 2,
		title: false,
	    closeBtn: 0,
	    shadeClose: true,
	    skin: 'yourclass',
		area : [ '400px', '300px' ],
		content : prefix + '/getWorkInfo/' + projectId // iframe的url
	});
}
	
/**
 * 获取考勤信息详情
 * @param projectId
 * @returns
 */
function getAttendanceInfo(projectId){
	layer.open({
		type : 2,
		title: false,
	    closeBtn: 0,
	    shadeClose: true,
	    skin: 'yourclass',
		area : [ '600px', '450px' ],
		content : prefix + '/getAttendanceInfo/' + projectId // iframe的url
	});
	
/*	$.ajax({
		url : prefix+"/getWorkInfo",
		type : "post",
		data : {
			'projectId' : projectId
		},
		success : function(data) {
			layer.tips(data.workNum, "#workNum"+projectId, {
				  tips: 3,
				  time:100000
			 });
		}
	});
*/
}

function closeWorkInfo(userId){
	layer.closeAll('tips');
}


function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
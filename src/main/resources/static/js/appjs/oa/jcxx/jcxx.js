var prefix = "/oa/jcxx"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list",// 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val()
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    /*						{
                field : 'id',
                title : 'id',
                align : "center",
                //visible :	false

            },*/
                    {
                        field: 'name',
                        title: '姓名',
                        align: "center"
                    },
                    {
                        field: 'cardId',
                        title: '身份证号',
                        align: "center"
                    },
                    {
                        field: 'graduateYear',
                        title: '毕业年限',
                        align: "center"
                    },
                    {
                        field: 'itemName',
                        title: '最新项目',
                        align: "center"
                    },
                    {
                        field: 'entranceTime',
                        title: '最后进场时间',
                        align: "center"
                    },
                    {
                        field: 'exitTime',
                        title: '退场时间',
                        align: "center"
                    },
                    {
                        field: 'predictExitTime',
                        title: '预计退场时间',
                        align: "center"
                    },
                    {
                        field: 'isEntrance',
                        title: '是否入场',
                        align: "center",
                        formatter: function (value) {
                            return value == '0' ? '是' : '否';
                        }
                    },
                    {
                        field: 'isFj',
                        title: '是否上传附件',
                        align: "center",
                        formatter: function (value) {
                            return value == '0' ? '否' : '是';
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm ' + s_exportJcxx_h + '" href = "/oa/jcxx/exportJcxx/'+row.id +'" title="导出简历"  mce_href="#" ><i class="fa  fa-cloud-download"></i></a> ';
                            var g = '<div style="position: relative;" class="btn btn-success btn-sm ' + s_filesUpload_h + '" title="上传附件">' +
                                ' <input type="file" name="files"  style="width:30px;height:30px;display: block;opacity: 0;position: absolute;\n' +
                                '    top: 0;\n' +
                                '    left: 0;" id="uploadFiles" onchange="filesUpload(this.files,\''+row.id+'\')" multiple> <i class="fa  fa-cloud-upload"></i></div> ';
                            return e + d + f + g;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function filesUpload(files,id) {
    //初始化FormData对象
    var fd = new FormData();
    var fileList = files;
    //console.log(fileList);
    var jcxxId = id;
    fd.append("jcxxId",jcxxId);
    for(var i=0;i<fileList.length;i++){
        fd.append("files",fileList[i]);
    }
    //console.log("fd:"+fd);
    $.ajax({
        url : "/oa/fj/filesUpload",
        type : "POST",
        data :fd,
        dataType : "json",
        success : function(result) {
            alert("上传成功");
        },

        error : function(result) {
            alert("上传失败");
        },
        cache : false,
        contentType : false,
        processData : false
    });
};


function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}


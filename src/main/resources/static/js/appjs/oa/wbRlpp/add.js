$(function(){
	var items=3,
			curTime = new Date();
	var curDate = curTime.getFullYear() + '-' + (curTime.getMonth()+1+'').padStart(2,'0');

	function getNewDate(sp,n,date){
		if(arguments.length === 1 && typeof arguments[0] === 'number') {
			n = sp;
			sp = '-';
		}
		if(!n) n = -1;
		if(!date) date = curDate;
		var d = new Date(date);
		var dm = d.getMonth() + n;
		d.setMonth(dm);
		d_year = d.getFullYear();
		d_month = (d.getMonth()+1+'').padStart(2,'0');
		return (d_year + sp + d_month);
	}

	function rendTBody(){
		$('#table1 tbody').html('<tr>'+
			'<td style="text-align:center;">'+curDate+'</td>'+
			'<td style="text-align:center;"><input name="items1-'+curDate.replace('-','')+'" class="form-control" type="text"></td>'+
			'<td></td>'+
		'</tr>'+
		'<tr>'+
			'<td style="text-align:center;">'+getNewDate(-1)+'</td>'+
			'<td style="text-align:center;"><input name="items2-'+getNewDate('',-1)+'" class="form-control" type="text"></td>'+
			'<td></td>'+
		'</tr>'+
		'<tr>'+
			'<td style="text-align:center;">'+getNewDate(-2)+'</td>'+
			'<td style="text-align:center;"><input name="items3-'+getNewDate('',-2)+'" class="form-control" type="text"></td>'+
			'<td></td>'+
		'</tr>');
		curDate = getNewDate(-2);
	}

	function init(){
		$.ajax({
			type : "get",
			url : "/oa/project/getAll",
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				//if (data.code == 0) {
					console.log(data);
			}
		});

		$('#endDate').val(curDate);
		rendTBody();
	}
	init();

	$('#endDate').change(function(){
		var $this = $(this);
		items = 3;
		if($this.val()) curDate = $this.val();
		rendTBody();
	});

	$('.addmore a').click(function(){
		curDate = getNewDate(-1);
		items++;
		$('#table1').append('<tr><td style="text-align:center;">'+ curDate +'</td><td style="text-align:center;"><input class="form-control" name="items'+items+'-'+curDate.replace('-','')+'" type="text"></td><td><a class="btn btn-warning btn-sm" href="#" title="删除" onclick="removeTR(this)"><i class="fa fa-remove"></i></a></td></tr>');
	})

	$('#signupForm').submit(function(e){
		e.preventDefault();
		save();
		return;
	})

	function save() {
		$.ajax({
			cache : true,
			type : "POST",
			url : "",
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
})
function removeTR(t){
	$(t).parent().parent().remove();
}
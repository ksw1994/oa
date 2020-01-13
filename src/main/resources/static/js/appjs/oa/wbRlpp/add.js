$(function(){
	var items = 3,
			fdata = [],
			dateArr = [],
			curTime = new Date(),
			curDate = curTime.getFullYear() + '-' + (curTime.getMonth()+1+'').padStart(2,'0');

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
		dateArr.push(curDate, getNewDate(-1), getNewDate(-2));
		$('#table1 tbody').html('<tr>'+
			'<td style="text-align:center;">'+getNewDate(-2)+'</td>'+
			'<td style="text-align:center;"><input name="items1" class="form-control js_endD" data-edate="'+getNewDate('',-2)+'" type="number"></td>'+
			'<td><input name="monthSum1" class="form-control js_monthSum" type="number"></td>'+
			'<td></td>'+
		'</tr>'+
		'<tr>'+
			'<td style="text-align:center;">'+getNewDate(-1)+'</td>'+
			'<td style="text-align:center;"><input name="items2" class="form-control js_endD" data-edate="'+getNewDate('',-1)+'" type="number"></td>'+
			'<td><input name="monthSum2" class="form-control js_monthSum" type="number"></td>'+
			'<td></td>'+
		'</tr>'+
		'<tr>'+
			'<td style="text-align:center;">'+curDate+'</td>'+
			'<td style="text-align:center;"><input name="items3" class="form-control js_endD" data-edate="'+curDate.replace('-','')+'" type="number"></td>'+
			'<td><input name="monthSum3" class="form-control js_monthSum" type="number"></td>'+
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
		dateArr.push(curDate);
		items++;
		$('#table1').prepend('<tr><td style="text-align:center;">'+curDate+'</td><td style="text-align:center;"><input class="form-control js_endD" data-edate="'+curDate.replace('-','')+'" name="items'+items+'" type="number"></td><td><input name="monthSum'+items+'" class="form-control js_monthSum" type="number"></td><td><a class="btn btn-warning btn-sm" href="#" title="删除" onclick="removeTR(this,\''+curDate+'\')"><i class="fa fa-remove"></i></a></td></tr>');
	})

	$('#signupForm').submit(function(e){
		e.preventDefault();
		save();
		return;
	})

	function save() {
		//if(!validateRule()) return;
		var projectId = $('#projectId').val();
		var endDate = $('#endDate').val();
		var compact = $('#compact').val();
		$('.js_endD').each(function(i){
			$this = $(this);
			var val_d = $this.data('edate');
			var val_v = $this.val();
			var monthSum = $('.js_monthSum').eq(i).val();
			fdata.push({
				'projectId':projectId,
				'compact':compact,
				'endDate':endDate,
				'date':val_d,
				'count':val_v,
				'monthSum':monthSum
			})
		})
		var params = fdata;
		$.ajax({
			type : "POST",
			url : "/oa/wbRlpp/save",
			data : JSON.stringify(params),
			contentType : "application/json;charsetset=UTF-8",//必须
			dataType:"json",//必须
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
					parent.layer.alert(data.msg);
				}
			}
		});
	}

	// function validateRule() {
	// 	var compact = $('#compact').val();
	// 	var t = /\.([0-9]*)/.exec(compact);
	// 	if( t[1].length>2 ) {
	// 		parent.layer.alert('只能保留两位小数！');
	// 		return false;
	// 	}
	// }

	function removeTR(t,d){
		$this = $(t);
		var idx = dateArr.indexOf(d);
		dateArr.splice(idx,1);
		curDate = dateArr.slice(-1);
		$this.parent().parent().remove();
	}
	window.removeTR = removeTR;
})

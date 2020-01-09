$(function(){
	var items=3,
			curTime = new Date();
	var curDate = curTime.getFullYear() + '-' + (curTime.getMonth()+1+'').padStart(2,'0');

	function getNewDate(date,n){
		if(!n) n = 1;
		var d = new Date(date);
		var dm = d.getMonth() + n;
		d.setMonth(dm);
		d_year = d.getFullYear();
		d_month = (d.getMonth()+1+'').padStart(2,'0');
		return (d_year+'-'+d_month);
	}

	function rendTBody(){
		$('#table1 tbody').html('<tr>'+
									'<td style="text-align:center;">'+getNewDate(curDate,-2)+'</td>'+
									'<td style="text-align:center;"><input name="items1" class="form-control" type="text"></td>'+
									'<td></td>'+
								'</tr>'+
								'<tr>'+
									'<td style="text-align:center;">'+getNewDate(curDate,-1)+'</td>'+
									'<td style="text-align:center;"><input name="items2" class="form-control" type="text"></td>'+
									'<td></td>'+
								'</tr>'+
								'<tr>'+
									'<td style="text-align:center;">'+curDate+'</td>'+
									'<td style="text-align:center;"><input name="items3" class="form-control" type="text"></td>'+
									'<td></td>'+
								'</tr>');
	}

	function init(){
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
		curDate = getNewDate(curDate);
		items++;
		$('#table1').append('<tr><td style="text-align:center;">'+ curDate +'</td><td style="text-align:center;"><input class="form-control" name="item'+items+'" type="text"></td><td><a class="btn btn-warning btn-sm" href="#" title="删除" onclick="removeTR(this)"><i class="fa fa-remove"></i></a></td></tr>');
	})

})
function removeTR(t){
	$(t).parent().parent().remove();
}
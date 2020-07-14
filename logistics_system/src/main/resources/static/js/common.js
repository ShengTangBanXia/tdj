//加载下拉框函数
function showOption(id_str,str) {
	if (id_str=="first")	var pid = 0;
	else	var pid = $(id_str).val();
	var name = "选择省份";
	if (str.startsWith("#city"))	name = "选择城市";
	if (str.startsWith("#area"))	name = "选择区/县";
	$.ajax({
		url:"/order/address/" + pid,
		type:"POST",
		async:false,
		contentType: "application/json",
		success:function (data) {
			//console.log(data);
			//console.log(data[0].name);
			var str1 = "";
			for (var i=0;i<data.length;i++) {
				str1 += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
			}
			//清除下拉框内容
			$(str).empty();
			//重新载入新的下拉框内容
			$(str).append("<option selected='selected'>" + name +"</option>");
			$(str).append(str1);
			//selectOption("#city1", a);
		},
		error:function () {
			alert("服务器出错!");
		}
	});
}

function initOption () {

	//清空所有下拉框
	$("#province1").empty();
	$("#province2").empty();
	
	$("#city1").empty();
	$("#city2").empty();
	
	$("#area1").empty();
	$("#area2").empty();
	
	//加载省份下拉框
	showOption("first", "#province1");
	showOption("first", "#province2");

}

//加载城市下拉框
$("#province1").change(function() {
	showOption("#province1", "#city1");
});

$("#province2").change(function() {
	showOption("#province2", "#city2");
});

//加载区县下拉框
$("#city1").change(function() {
	showOption("#city1", "#area1");
});

$("#city2").change(function() {
	showOption("#city2", "#area2");
});

var pageSize = 5;

//初始化订单页面
function initOrderTable(pageSize) {
	
	$('#ordertable').DataTable({
		'paging': true, //分页
		"serverSide": true, //开启后端分页
		"pagingType": "full_numbers", //分页样式的类型simple/simple_numbers/full/full_numbers
		"pageLength": pageSize, //定义初始的页长
		"processing": false, 
		"destroy": true, //允许销毁替换，在表格重新查询时，可以自动销毁以前的data
		'lengthChange': true, //控制是否能够调整每页的条数
		'searching': true,		//是否开启搜索功能
		'data-show-refresh': true,	//是否需要刷新
		'ordering': true,	//是否启用排序
		'autoWidth': false,
		"ajax": function (data, callback, settings) {
			// 从data获取查询数据
			var columIndex = data.order[0].column;
			var sort = data.order[0].dir;
			var orderBy = data.columns[columIndex].name;
			pageSize = data.length == undefined ? pageSize : data.length;

			var searchVo = {};
			searchVo.currentPage = (data.start / pageSize) + 1;
			searchVo.pageSize = pageSize;
			searchVo.orderBy = orderBy;
			searchVo.sort = sort;
			//为了解决因为输入多个中文时打拼音出现单引号从而导致后台SQL语句注入出错，每次将关键字中的单引号用空格代替
			//new RegExp("'",'g')中的g表示全局匹配所有指定的单引号，否则每次只能替换第一个单引号
			searchVo.keyWord = data.search.value.replace(new RegExp("'",'g')," ");
			

			$.ajax({
				url : "/order/orders",
				type : "post",
				contentType: "application/json",
				data : JSON.stringify(searchVo),
				success : function (rs) {
					var fData = {
						draw :0,
						recordsTotal: 0,
						recordsFiltered: 0,
						data: []
					};
					if (!rs) {
						layer.alert("请求出错，请稍后重试" + rs.errmsg, {icon: 2});
						callback(fData);
						return;
					};
					if (rs.list == null) {
						$('#ordertable tbody tr').remove();
						$('#loading').remove();
						callback(fData);
						return;
					}
					$('#loading').remove();
					var gearDatas = [];
					for (var i = 0; i < rs.list.length; i++) {
						
						//包装行数据
						var rowData = new TData(rs.list[i].orderNum, rs.list[i].orderSender, 
								rs.list[i].orderReceiver, rs.list[i].orderStatus, rs.list[i].orderPrincipal);
						// 将行数据放到数组里
						gearDatas.push(rowData);
					}
					
					fData.data = gearDatas;
					fData.recordsTotal = rs.total;
					fData.recordsFiltered = rs.total;
					callback(fData);
				},
				error : function (data) {
					layer.alert(data.responseText, {icon: 0});
				}
			});
		},
		"columns": [ //定义行数据字段
			{data: 'orderNum', name: "order_num", sortable: true}, 
			{data: 'orderSender', name: "order_sender", sortable: true}, 
			{data: 'orderReceiver', name: "order_receiver", sortable: true}, 
			{data: 'orderStatus', name: "order_status", sortable: true}, 
			{data: 'orderPrincipal', name: "order_principal", sortable: true}, 
			{data: 'operate', width: '80px', sortable: false}
		]
	});
	
}

//行数据结构
function TData(orderNum, orderSender, orderReceiver, orderStatus, orderPrincipal) {
	this.orderNum = orderNum;
	this.orderSender = orderSender;
	this.orderReceiver = orderReceiver;
	this.orderStatus = orderStatus;
	this.orderPrincipal = orderPrincipal;
	this.operate = function () {
		return "<a href='#' class='btn_editcolor' data-toggle='modal' data-target='#orderEditModal' " +
			"onclick='initEditModal(\"" + orderNum + "\")'>编辑</a>&nbsp;" + 
			"<a href='javascript:void(0);' onclick='deleteOrder(\"" + orderNum + "\")' class='btn_editcolor'>删除</a>";
	}
}


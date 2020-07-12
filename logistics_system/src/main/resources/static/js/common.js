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
		},
		error:function () {
			alert("服务器出错!");
		}
	});
}
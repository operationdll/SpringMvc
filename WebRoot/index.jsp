<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<link rel="stylesheet" type="text/css"
		href="<%=basePath%>js/fileUpload/jquery.fileupload.css?v8.9" />
	<script type="text/javascript"
		src="<%=basePath%>js/jquery-1.8.0.min.js">
	</script>
	<script src="<%=basePath%>js/fileUpload/bootstrap.js?v2.3.2"></script>
	<script src="<%=basePath%>js/fileUpload/js/jquery.ui.widget.js?v8.9"></script>
	<script
		src="<%=basePath%>js/fileUpload/js/jquery.iframe-transport.js?v8.9"></script>
	<script src="<%=basePath%>js/fileUpload/js/jquery.fileupload.js?v8.9"></script>
	<script
		src="<%=basePath%>js/fileUpload/js/jquery.fileupload-process.js?v8.9"></script>
	<script type="text/javascript">
	$(function(){
		//导入提交
		$('#file').fileupload({
            dataType: 'json',
            add: function (e, data) {
				data.url="<%=basePath%>test/testImport.do";
				data.submit();
        	},
            success :function (result, textStatus, jqXHR) {
      				alert(result.retStr);
			},
		    error: function (){
		    	alert("导入失败!");
		    }
		});
	});
	</script>
	<body>
		<div style="padding: 5px; width: 280px; height: 90px;">
			<span><a id="ft"><b>上传公示清单</b> </a> <input id="file"
					type="file" name="file" /> </span>
		</div>
		<a href="<%=basePath%>test/init.do">跳转页面</a>
		<a href="<%=basePath%>template/priceTaskTemp.xls">下载模板</a>
		<a
			href="<%=basePath%>test/exportFile.do?url=E:/workspace/SpringMvcTest/WebRoot/template/work.txt">导出</a>
	</body>
</html>

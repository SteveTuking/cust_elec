
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
  <head>
   <title>添加用户</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
   
<Script language="javascript">
	function check_null(){
		if($("select[name='riskrank']").val()=="")
		{
			alert("请选择事故级别");
			$("select[name='riskrank']")[0].focus();
			return false;
		}
	    if($("select[name='risktype']").val()=="")
		{
			alert("请选择风险类别");
			$("select[name='risktype']")[0].focus();
			return false;
		}
	    if($.trim($("input[name='riskdate']").val())=="")
		{
			alert("事故时间不能为空");
			$("input[name='riskdate']")[0].focus();
			return false;
		}
       
 	   //上传的文件不能为空
      
	   /**正则表达式的使用*/
  	   $("#Form1").attr("action","${pageContext.request.contextPath }/system/elecRiskAction_save.do");
	   $("#Form1").submit();
	}
	function checkTextAreaLen(){
  		var remark = new Bs_LimitedTextarea('remark', 250); 
  		remark.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
  		remark.draw();	
    }
    window.onload=function(){
		checkTextAreaLen();
    }
    //ajax的二级联动，使用选择的所属单位，查询该所属单位下对应的单位名称列表
    function findJctUnit(o){
    	//货物所属单位的文本内容
    	var jct = $(o).find("option:selected").text();
    	$.post("elecUserAction_findJctUnit.do",{"jctID":jct},function(data,textStatus){
	   	    //先删除单位名称的下拉菜单，但是请选择要留下
	   	    $("#jctUnitID option").remove();
	        if(data!=null && data.length>0){
	            for(var i=0;i<data.length;i++){
	   		       	var ddlCode = data[i].ddlCode;
	   		       	var ddlName = data[i].ddlName;
	   		       	//添加到单位名称的下拉菜单中
	   		       	var $option = $("<option></option>");
	   		       	$option.attr("value",ddlCode);
	   		       	$option.text(ddlName);
	   		       	$("#jctUnitID").append($option);
	   	        }
	        }
        });
    	
    }
    function fileTr(){
    	var value = $("#BT_File").val();
		if(value == "添加设备"){
			$("#trFile").css("display","");
			$("#BT_File").val("隐藏设备");
			$("#item").css("display","");
		}
		else{
			$("#trFile").css("display","none");
			$("#BT_File").val("添加设备");
			$("#item").css("display","none");
		}
    }
    function insertRows(){ 
    	//获取表格对象
    	var tb1 = $("#filesTbl");
    	var tempRow = $("#filesTbl tr").size();//获取表格的行数,+1的目的去掉添加选项的按钮
    	
    	var $tdNu = $("<td align='center'></td>");
    	$tdNu.html(tempRow);
    	
    	var $tdNum = $("<td align='center'></td>");
    	$tdNum.html("<input name=\"deviceNum\"  type=\"text\" size=\"25\" id=\""+tempRow+"\"> ");
    	
    	var $tdtime = $("<td align='center'></td>");
    	$tdtime.html("<input name=\"deviceDate\"  type=\"text\" size=\"25\" onClick=\"WdatePicker()\">");
    	
    	var $tddetail = $("<td align='center'></td>");
    	$tddetail.html("<input name=\"deviceDetail\"  type=\"text\" size=\"25\">");
    	
    	var $tdDel = $("<td align='center'></td>");
    	$tdDel.html("<a href='javascript:delTableRow(\""+tempRow+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>");
    	
    	
    	// 创建tr，将3个td放置到tr中
    	var $tr = $("<tr></tr>");
    	$tr.append($tdNu)
    	$tr.append($tdNum);
    	$tr.append($tdtime);
    	$tr.append($tddetail);
    	$tr.append($tdDel);
    	//在表格的最后追加新增的tr
    	tb1.append($tr);
    } 

    function delTableRow(rowNum){ 
       //改变行号和删除的行号
       var tb1 = $("#filesTbl");
       var tempRow = $("#filesTbl tr").size();//获取表格的行数
       if (tempRow >rowNum){     
    	  //获取删除行的id指定的对象，例如：<input name=\"itemname\" type=\"text\" id=\""+tempRow+"\" size=\"45\" maxlength=25>
    	  $("#"+rowNum).parent().parent().remove();
    	  //加1表示寻找下一个id，目的是将后面tr的格式向上移动
          for (i=(parseInt(rowNum)+1);i<tempRow;i++){
        	  //将i-1的值赋值给编号
        	  $("#"+i).parent().prev().html(i-1);
        	  //将i-1的值赋值给超链接的删除
        	  $("#"+i).parent().next().html("<a href='javascript:delTableRow(\""+(i-1)+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>");//
        	  //将i-1的值赋值给文本框的id，用于删除
        	  $("#"+i).attr("id",(i-1));//将id设置成i-1
          }
       }
    } 
   </script>
  </head>
  
 <body>
 
  <form name="Form1" id="Form1" method="post" enctype="multipart/form-data">
 <br>
    <table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

    <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>记录风险</strong></font>
		</td>
    </tr>
     <tr>
    	<td align="center" bgColor="#f5fafe" class="ta_01">事故级别：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.riskranks" name="riskrank" id="riskrank"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="请选择"
					  cssStyle="width:155px">
			</s:select>
		</td>
    </tr>
    <tr>
    	<td align="center" bgColor="#f5fafe" class="ta_01">风险类别：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.risktypes" name="risktype" id="risktype"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="请选择"
					  cssStyle="width:155px">
			</s:select>
		</td>
    </tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">风险日期：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:textfield name="riskdate" id="riskdate" maxlength="50"  size="20" onClick="WdatePicker()"></s:textfield>
		</td>
	</tr>
	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">描述：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
			<s:textarea name="riskdetail"  cssStyle="WIDTH:95%"  rows="4" cols="52"></s:textarea>
		</TD>
	</TR>
	
	<TR>
	<td  align="center"  colSpan="4"  class="ta_01" style="WIDTH: 100%" align="left" bgColor="#f5fafe">
		<input type="button" id="BT_File" name="BT_File" value="添加设备"  style="font-size:12px; color:black; height=22;width=55"   onClick="fileTr()">
		<input type="button" id="item" name="item" value="添加选项" style="difont-size:12px; color:black; display: none;height=20;width=80 " onClick="insertRows()">
	</td>
	</TR>
	
	<TR id="trFile" style="display: none">
	<td  align="center"  colSpan="4"  class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe">
			<table cellspacing="0"   cellpadding="1" rules="all" bordercolor="gray" border="1" id="filesTbl"
		    style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
						
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
					<td class="ta_01" align="center" width="10%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						编号
					</td>
					<td class="ta_01" align="center" width="10%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						设备编号
					</td>
					<td class="ta_01" align="center" width="40%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						时间
					</td>
					<td class="ta_01" align="center" width="40%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						描述
					</td>
					<td class="ta_01" align="center" width="10%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						删除
					</td>
				</tr>
			
	     </table>
		</td>
	</TR>
	<TR>
	<td  align="center"  colSpan="4"  class="sep1"></td>
	</TR>
	<tr>
		<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
		<input type="button" id="BT_Submit" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
		 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">
			
		</td>
	</tr>
</table>　
</form>

</body>
</html>

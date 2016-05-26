
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

 <script language="javascript"> 
   function deleteAll(){
	 var selectuser = document.getElementsByName("userID");
	 var flag = false;
     for(var i=0;i<selectuser.length;i++){
     	if(selectuser[i].checked){
     		flag = true;
     	} 
     }
     if(!flag){
     	alert("没有选择执行操作的用户！不能执行该操作");
     	return false;
     }
     else{
     	var confirmflag = window.confirm("你确定执行批量删除吗？");
     	if(!confirmflag){
     		return false;
     	}
     	else{
     		document.Form2.action = "elecUserAction_delete.do";
     		document.Form2.submit();
     		return true;
     	}
     }
   }
  //用户:全部选中/全部不选中
   function checkAllUser(user){
	  var selectuser = document.getElementsByName("riskID");
      for(var i=0;i<selectuser.length;i++){
     	 selectuser[i].checked = user.checked;
      }
   }
   function query() {debugger;
	   //src="http://localhost:8080/cust_elec/system/elecRiskAction_piePng.do"
	   var btime = $("#onDutyDateBegin").val();
	   var etime = $("#onDutyDateEnd").val();
	   if(btime==""){
		   var sarr =  btime.split("-");
		   var earr = etime.split("-");
		   if(sarr[0]!=earr[0]){
			   alert("只能统计同一年的风险");
		   }
		   if((earr[1]<sarr[1])||(earr[2]>sarr[2])){
			   alert("后一个时间必须大于前一个");
		   }
		   $("#piePng").hide();
	   }else{
		   document.forms[0].submit();
	   }
   }
   function calc(){debugger;
	   var btime = $("#onDutyDateBegin").val();
	   var etime = $("#onDutyDateEnd").val();
	   if(btime!=""){
		   $("#piePng").show();
		   $("#pieImg").attr("src","http://localhost:8080/cust_elec/system/elecRiskAction_piePng.do?stime="+new Date()); 
	   }
   }
  </script>

<HTML>
	<HEAD>
		<title>风险识别</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
	</HEAD>
		
	<body >
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath }/system/elecRiskAction_calcRisk.do" method="post" style="margin:0px;"> 
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
				<TR height=10><td></td></TR>
				<tr>
					<td class="ta_01" colspan="4" align="center" background="../images/b-info.gif">
						<font face="宋体" size="2"><strong>风险信息管理</strong></font>
					</td>
					
				</tr>
				<tr>
					<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					风险时间：</td>
					<td class="ta_01" colspan="3">
						<input type="text" name="startDate" value="<s:date name="startDate" format="yyyy-MM-dd"/>" id="onDutyDateBegin" maxlength="50" size="20" onclick="WdatePicker()"/>
						~
						<input type="text" name="endDate" value="<s:date name="endDate" format="yyyy-MM-dd"/>" id="onDutyDateEnd" maxlength="50" size="20" onclick="WdatePicker()"/>
					</td>
				</tr>

		    </table>	
		</form>




		<form id="Form2" name="Form2" action="/system/elecRiskAction_risk.do" method="post">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
				<tr>
				  	<td>
		                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
										<TR>
											<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
											<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">风险列表</TD>
										</TR>
			             </TABLE>
                   </td>
					<td class="ta_01" align="right">
					    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="查询" name="BT_find" 
						 onclick="query()">&nbsp;&nbsp;
					    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="评估" name="BT_find" 
						 onclick="calc()">&nbsp;&nbsp;
					</td>
				</tr>
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">时间</td>
								<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">违章、缺陷</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">人身四级事件至一级事件</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">电力安全五级事件及一级事件</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备四级事件及一级事件</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">一般人身事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">较大人身事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">一般电力安全事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">较大电力安全事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">一般设备事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">较大设备事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">重大人身事故</td>
								<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">特大人身事故</td>
								<td align="center" width="8%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">重大电力安全事故</td>
								<td align="center" width="8%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">重大设备事故</td>
								<td align="center" width="8%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">特别重大设备事故</td>
								<td align="center" width="3%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">总计</td>
							</tr>
							<s:if test="#request.elemRiskVOs!=null && #request.elemRiskVOs.size()>0">
								<s:iterator value="#request.elemRiskVOs">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:date name="time" format="yyyy-MM"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="5%">
											<s:property value="breakRule"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="peopleFourOne"/>
										</td>	
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="elecFiveOne"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="deviceFourOne"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="simplePeople"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="midPeople"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="simpleElec"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="midElec"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="midDevice"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="superPeople"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="6%">
											<s:property value="ssuperPeople"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="8%">
											<s:property value="superElec"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="8%">
											<s:property value="superDevice"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="8%">
											<s:property value="ssuperDevice"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="3%">
											<s:property value="ssuperDevice"/>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>					
					</td>
				</tr>       
			</TBODY>
		</table>
		<div id="piePng" style="width: 1124px ;float: right;">
           <img  id="pieImg" border="0"/>
        </div>
		</form>
	</body>
</HTML>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

 <script language="javascript"> 
   /**jquery对象*/
   function deleteAll(){
	 var $selectuser = $("input[type=deviceID][name=deviceID]");
	 var flag = false;
	 $selectuser.each(function(){
		 if(this.checked){
			 flag=true;
			 return false;//退出循环
		 }
	 })
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
     		$("#Form2").attr("action","elecUserAction_delete.do");
     		$("#Form2").submit(); 	
     		return true;
     	}
    }
  }
  //用户:全部选中/全部不选中
  /**DOM对象*/
  /**
   function checkAllUser(user){
	  var selectuser = document.getElementsByName("userID");
      for(var i=0;i<selectuser.length;i++){
     	 selectuser[i].checked = user.checked;
      }
   }
  */
  /**jquery对象*/
  function checkAllUser(user){
	  $("input[type=checkbox][name=deviceID]").attr("checked",user.checked);
  }
  </script>

<HTML>
	<HEAD>
		<title>设备管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
	</HEAD>
		
	<body >
		<form id="Form2" name="Form2" action="/system/userAction_main.do" method="post">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg"><input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)"></td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备号</td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">故障时间</td>
								<td align="center" width="8%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">描述</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">查看</td>
							</tr>
							<s:if test="#request.elecDevices!=null && #request.elecDevices.size()>0">
								<s:iterator value="#request.elecDevices">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="5%">
											<input type="checkbox" name="deviceID" id="deviceID" value="<s:property value="deviceID"/>">
										</td>
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:property value="deviceNum"/>
										</td>	
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:date name="deviceDate" format="yyyy-MM-dd"/>
										</td>								
										<td style="HEIGHT:22px" align="center" width="8%">
											<s:property value="deviceDetail"/>
										</td>
										
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
										   <a href="#" onclick="openWindow('${pageContext.request.contextPath }/system/elecUserAction_edit.do?userID=<s:property value="userID"/>','900','700');">
										   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="CURSOR:hand"></a>													
										</td>
										
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">
											<a href="#" onclick="openWindow('${pageContext.request.contextPath }/system/elecUserAction_edit.do?userID=<s:property value="userID"/>&viewflag=1','900','700');">
											<img src="${pageContext.request.contextPath }/images/button_view.gif" width="20" height="18" border="0" style="CURSOR:hand"></a>												
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>					
						
					</td>
				</tr>        
			</TBODY>
		</table>
		</form>




	</body>
</HTML>

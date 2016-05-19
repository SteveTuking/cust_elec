var privilegeDate = [
  {
    mid: 'aa',
    pid: '0',
    isParent: true,
	icon:'../images/MenuIcon/jishusheshiweihuguanli.gif',
	//open:true,
    name: '技术设施维护管理',
	nodes:[
		{
			mid:'ab',
			pid:'aa',
			isParent:false,
			target:'mainFrame',
			url:'../equapment/equapmentIndex.jsp',
			icon:'../images/MenuIcon/yiqishebeiguanli.gif',
			name:'仪器设备管理'
		},{
			mid:'ac',
			pid:'aa',
			isParent:false,
			target:'mainFrame',
			url:'../equapment/adjustIndex.jsp',
			icon:'../images/MenuIcon/shebeijiaozhunjianxiu.gif',
			name:'设备校准检修'
		}
	]
}, {
    mid: 'ae',
    pid: '0',
    name: '安全操作指导资料',
	icon:'../images/MenuIcon/jishuziliaotuzhiguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'af',
			pid:'ae',
			isParent:false,
			icon:'../images/MenuIcon/ziliaotuzhiguanli.gif',
			target:'mainFrame',
			url:'../dataChart/dataChartIndex.jsp',
			name:'资料图纸管理'
		}
	]
}, {
    mid: 'ak',
    pid: '0',
    name: '风险识别',
	icon:'../images/MenuIcon/jiancetaijianzhuguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'al',
			pid:'ak',
			isParent: false,
			icon:'../images/MenuIcon/jiancetaijianzhu.gif',
			target:'mainFrame',
			url:'../building/buildingIndex.jsp',
			name:'风险统计'
		}
	]
},
 {
    mid: 'am',
    pid: '0',
    name: '系统管理',
	icon:'../images/MenuIcon/xitongguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'an',
			pid:'am',
			name:'用户管理',
			icon:'../images/MenuIcon/yonghuguanli.gif',
			target:'mainFrame',
			url:'../system/elecUserAction_home.do',
			isParent:false
		},{
			mid:'ao',
			pid:'am',
			name:'角色管理',
			icon:'../images/MenuIcon/jueseguanli.gif',
			target:'mainFrame',
			url:'../system/elecRoleAction_home.do',
			isParent:false
		},{
			mid:'ap',
			pid:'am',
			name:'运行监控',
			icon:'../images/MenuIcon/daibanshiyi.gif',
			target:'mainFrame',
			url:'../system/elecCommonMsgAction_home.do',
			isParent:false
		},{
			mid:'aq',
			pid:'am',
			name:'数据字典维护',
			icon:'../images/MenuIcon/shujuzidianguanli.gif',
			target:'mainFrame',
			url:'../system/elecSystemDDLAction_home.do',
			isParent:false
		}
	]
}];

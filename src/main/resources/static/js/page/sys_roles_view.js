$(document).ready(function () {
    /*
    初始化消息提示
     */
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    $.jgrid.defaults.styleUI = "Bootstrap";
    $("#table_list_2").jqGrid({
        url: "../sys_roles/serch.do",
        datatype: "json",
        height: 350,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ["id", "角色名称", "描述", "创建人", "最后修改人"],
        colModel: [{
            name: "roleid",
            index: "roleid",
            editable: true,
            width: 60,
            search: false,
            hidden: true
        }, {
            name: "rolename",
            index: "rolename",
            editable: true,
            width: 60,
            search: true
        }, {
            name: "description",
            index: "description",
            editable: true,
            width: 60,
            search: true
        }, {
            name: "creatuser",
            index: "creatuser",
            editable: true,
            width: 60,
            search: true
        }, {
            name: "modifyuser",
            index: "modifyuser",
            editable: true,
            width: 60,
            search: true
        }],
        pager: "#pager_list_2",
        viewrecords: true,
        //caption : "jqGrid 示例2",
        add: true,
        edit: true,
        addtext: "Add",
        edittext: "Edit",
        hidegrid: false,
        multiselect: true,
    });
    
    //$("#table_list_2").setSelection(4, true);
    $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
        edit: false,
        add: false,
        del: false,
        search: true,
        editfunc: function (id) {
            var idStr = "#" + id;
            var $currRow = $("#table_list_2").find(idStr);
            var menu_id = $currRow.find("td:eq(1)").text();
            editdata(menu_id);
        },
        addfunc: function (id) {
            adddata();
        },
        delfunc: function (id) {
            var idStr = "#" + id;
            var $currRow = $("#table_list_2").find(idStr);
            var menu_id = $currRow.find("td:eq(1)").text();
            deldata(menu_id);
        }
    }, {
            height: 250,
            reloadAfterSubmit: true
        });
    $(window).bind("resize", function () {
        var width = $(".jqGrid_wrapper").width();
        //$("#table_list_1").setGridWidth(width);
        $("#table_list_2").setGridWidth(width);
    });
    $("#table_list_alert").jqGrid({
        url: "../sys_models/serch.do",
        datatype: "json",
        height: "auto",
        width: "100%",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ["id", "模块名称", "描述"],
        colModel: [{
            name: "modelid",
            index: "modelid",
            editable: false,
            width: 60,
            search: false,
            hidden: true
        }, {
            name: "modelname",
            index: "modelname",
            editable: false,
            width: 300,
            search: true,
            formatter: showicon
        }, {
            name: "description",
            index: "description",
            editable: false,
            width: 350,
            search: true
        }],
        //pager : "#pager_list_2",
        viewrecords: true,
        //caption : "jqGrid 示例2",
        add: false,
        edit: false,
        addtext: "Add",
        edittext: "Edit",
        hidegrid: true,
        multiselect: true,
        cellEdit: true,
        cellsubmit: 'clientArray'
    });
    $("#using_json").jstree({
        "core": {
            "check_callback": true,
            "data": {
                'url': '../sys_models/models_Tree.do',
                'dataType': 'json',
            }
        }
    });
    $("#using_json").on('load_node.jstree', function (event, obj) {
        var option = {};
        option.flat = true;
        option.no_state = true;
        option.no_data = true;
        option.no_li_attr = true;
        option.no_a_attr = true;
        var json = $("#using_json").jstree(true).get_json(null, option);
        var listData = [];
        $.ajax({
            type: "POST",
            url: "../sys_models/modelmenuswithmenus.do",
            dataType: "json",
            contentType: 'application/json',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            success: function (datas) {
                var lstModelMenus = {};
                var lstModelMenusParent="";
                for (var i = 0; i < datas.length; i++) {
                    if (lstModelMenus[datas[i].modelid] && lstModelMenus[datas[i].modelid].length > 0) {
                        lstModelMenus[datas[i].modelid].push(datas[i].menuid + "," + datas[i].modelmenuid);
                    } else {
                        lstModelMenus[datas[i].modelid] = [];
                        lstModelMenus[datas[i].modelid].push(datas[i].menuid + "," + datas[i].modelmenuid);
                    }
                    if(lstModelMenusParent.indexOf(datas[i].menuid)<0){
                    	lstModelMenusParent=lstModelMenusParent+datas[i].menuid+";";
                    }
                }
                for (var i = 0; i < json.length; i++) {
                    var dataone = {};
                    dataone.id = json[i].id;
                    dataone.icons = json[i].icon;
                    dataone.parent = json[i].parent;
                    dataone.text = json[i].text;
                    var node = $("#using_json").jstree(true).get_node(json[i].id);
                    if (lstModelMenus[json[i].id] && lstModelMenus[json[i].id].length > 0 ) {
                        for (var j = 0; j < lstModelMenus[json[i].id].length; j++) {
                            var dataones = lstModelMenus[json[i].id][j];
                            dataone[dataones.split(",")[0]] = false;
                            dataone[dataones.split(",")[0] + "modelmenuid"] = dataones.split(",")[1];
                        }
                    }
                    if(node.children.length>0){
                    	var lst=lstModelMenusParent.split(";")
                    	for (var j = 0; j < lst.length; j++) {
                            var dataones = lst[j];
                            dataone[dataones] = false;
                        }
                    }
                    var level = node.parents.length;
                    dataone.level = level;
                    if (dataone.parent == "#") {
                        dataone.parent = null;
                    }
                    if (node.children.length > 0) {
                        dataone.isLeaf = false;
                    } else {
                        dataone.isLeaf = true;
                    }
                    dataone.expanded = true;
                    dataone.loaded = true;
                    listData.push(dataone);
                }
                $.ajax({
                    type: "POST",
                    url: "../sys_menus/queryTreeMenu.do",
                    dataType: "json",
                    contentType: 'application/json',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    success: function (dataTreeMenu) {
                        var colNames = ["id", "模块名称"];
                        var colModel = [{ name: "id", index: "id", editable: false, width: 60, search: false, hidden: true },
                        { name: "text", index: "text", editable: false, width: 300, search: true, formatter: showicon }];
                        for (var i = 0; i < dataTreeMenu.length; i++) {
                            colNames.push(dataTreeMenu[i].menuname);
                            colModel.push({ name: dataTreeMenu[i].menuid, index: dataTreeMenu[i].menuid, editable: false, width: 100, search: true, formatter: showcheckbox });
                        }
                        //加载json数据到datagrid的treegrid中
                        $.jgrid.gridUnload("table_list_alert_menu");
                        $("#table_list_alert_menu").jqGrid({
                            data: listData,
                            datatype: "local",
                            height: "auto",
                            width: "100%",
                            treeGrid: true,
                            treeGridModel: 'adjacency',
                            ExpandColumn: 'text',
                            autowidth: true,
                            shrinkToFit: true,
                            multiselect: true,
                            rowNum: 10,
                            rowList: [10, 20, 30],
                            colNames: colNames,
                            treeIcons: { plus: 'fa fa-plus-square-o', minus: 'fa fa-minus-square-o', leaf: 'fa fa-file-archive-o' },
                            colModel: colModel,
                            viewrecords: true,
                            add: false,
                            edit: false,
                            addtext: "Add",
                            edittext: "Edit",
                            hidegrid: true,
                            multiselect: true,
                            cellEdit: false,
                            cellsubmit: 'clientArray',
                            treedatatype: "json",
                            loadonce: true,
                            rowNum: 10000,
                            pager: "#pager_menu",
                            treeReader: {
                                level_field: "level",
                                parent_id_field: "parent",
                                leaf_field: "isLeaf",
                                expanded_field: "expanded"
                            }
                        });
                        $("#table_list_alert_menu").jqGrid().trigger("reloadGrid"); //重载JQGrid
                    }
                });

            }
        });

    });

    layui.use(['form', 'layedit'], function () {
        var form = layui.form, layer = layui.layer;
    });
    
    $("#table_list_alert_funcs").jqGrid({
        url: "../sys_modelfuncs/serch.do",
        datatype: "json",
        height: "auto",
        width: "100%",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ["id", "modelid","方法名称", "所在模块","描述"],
        colModel: [{
            name: "funcid",
            index: "funcid",
            editable: false,
            width: 60,
            search: false,
            hidden: true
        }, {
            name: "modelid",
            index: "modelid",
            editable: false,
            hidden: true,
            width: 60,
            search: false
        },{
            name: "funcname",
            index: "funcname",
            editable: false,
            width: 200,
            search: true,
            formatter: showfuncicon
        }, {
            name: "modelname",
            index: "modelname",
            editable: false,
            width: 200,
            search: true,
            formatter: showfuncgroup
        }, {
            name: "description",
            index: "description",
            editable: false,
            width: 250,
            search: true
        }],
        //pager : "#pager_list_2",
        viewrecords: true,
        //caption : "jqGrid 示例2",
        add: false,
        edit: false,
        addtext: "Add",
        edittext: "Edit",
        hidegrid: true,
        multiselect: true,
        cellEdit: true,
        cellsubmit: 'clientArray',
        sortable: true,
        sortname: 'funcid', //设置默认的排序列
        sortorder: 'asc',
        grouping:true,
        groupingView : {
          groupField : ['modelname']
        },
        loadComplete : function() {
        	//绑定已经分配的方法
        }
    });
});
function showicon(cellvalue, options, rowObject) {
    return cellvalue + '&nbsp;&nbsp;&nbsp;&nbsp;<i class="' + rowObject.icons + '"></i>';
}
function showcheckbox(cellvalue, options, rowObject) {
    if (cellvalue == true) {
        return '<input datatype="modelmenus" parentid="' + rowObject.parent + '" type="checkbox" checked modelid="' + options.rowId + '" modelmenuid="' + rowObject[options.colModel.index + "modelmenuid"] + '"  menuid="' + options.colModel.index + '" />';
    } else if (cellvalue == false) {
        return '<input datatype="modelmenus" parentid="' + rowObject.parent + '" type="checkbox" modelid="' + options.rowId + '" modelmenuid="' + rowObject[options.colModel.index + "modelmenuid"] + '"   menuid="' + options.colModel.index + '" />';
    } else {
        return "";
    }
}
function fnAdd() {
    adddata();
    return false;
}
function fnEdit() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.roleid;
        editdata(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function fnDel() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.roleid;
        deldata(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function fnSetMoAu() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.roleid;
        setbutton(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function fnSetMeAu() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.roleid;
        setMebutton(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function setMebutton(id) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , anim: 5
            , isOutAnim: false
            , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            , id: "divShowLayerMe" //防止重复弹出
            , content: $("#menu_showwin"),
            area: [700 + 'px', 500 + 'px'],
            btn: ['确定', '取消'],
            btnAlign: 'c' //按钮居中
            , shade: 0 //不显示遮罩
            , yes: function () {
                //先保存数据，
                var modelmenusResult = [];
                $('input:checkbox[datatype="modelmenus"]:checked').each(function (i) {
                    if ($(this).attr("modelid") && $(this).attr("menuid") && $(this).attr("modelid")!="undefined"&& $(this).attr("modelid")!=undefined && $(this).attr("menuid")!="undefined") {
                        modelmenusResult.push({ "modelid": $(this).attr("modelid"), "menuid": $(this).attr("menuid"), "rolemenumodelid": "", "roleid": id });
                    }
                });
                $.ajax({
                    type: "POST",
                    url: "../sys_roles/updateRoleModelMenus.do",
                    data: JSON.stringify({
                        "sys_rolemodelmenus": modelmenusResult, "roleid": id
                    }),
                    dataType: "json",
                    contentType: 'application/json',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    success: function (data) {
                        messageHelper(data, reloadGrid());
                        //然后关闭
                        layer.closeAll();
                    }
                });
            },
            btn2: function (index, layero) {
                layer.closeAll();
            },
            success: function (layero, index) {
//                $('input:checkbox[datatype="modelmenus"]').on("click", function () {
//                    var modelidch = $(this).attr("modelid");
//                    var menuidch = $(this).attr("menuid");
//                    if ($(this).is(":checked")) {
//                        $('input:checkbox[parentid="' + modelidch + '"][ menuid="' + menuidch + '"]').attr("checked", true);
//                    } else {
//                        $('input:checkbox[parentid="' + modelidch + '"][ menuid="' + menuidch + '"]').attr("checked", false);
//                    }
//
//                });
                //$("#table_list_alert").jqGrid().trigger("reloadGrid"); //重载JQGrid
                 $.ajax({
                     type: "POST",
                     url: "../sys_roles/selectRoleModelMenus.do",
                     data: {"id":id},
                     dataType: "json",
                     success: function (data) {
                    	 $('input:checkbox[datatype="modelmenus"]').removeAttr("checked")
                    	 for(var i=0;i<data.data.length;i++){
 	                    	var one=data.data[i];
 	                    	console.log($('input:checkbox[datatype="modelmenus"][modelid="'+one.modelid+'"][menuid="'+one.menuid+'"]'));
 	                    	$('input:checkbox[datatype="modelmenus"][modelid="'+one.modelid+'"][menuid="'+one.menuid+'"]').prop("checked","checked");	
                     	}
                     }
                 });
            }
        });
    });
}


function setbutton(id) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , anim: 5
            , isOutAnim: false
            , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            , id: "divShowLayer" //防止重复弹出
            , content: $("#model_showwin"),
            area: [700 + 'px', 500 + 'px'],
            btn: ['确定', '取消'],
            btnAlign: 'c' //按钮居中
            , shade: 0 //不显示遮罩
            , yes: function () {
                //先保存数据，
                var ids = $('#table_list_alert').jqGrid('getGridParam', 'selarrrow');
                var modelid = $('#table_list_2').jqGrid('getGridParam', 'selrow');
                var rowDatas = $('#table_list_2').jqGrid('getRowData', modelid);
                var datas = [];
                for (var i = 0; i < ids.length; i++) {
                    var rowData = $("#table_list_alert").jqGrid('getRowData', ids[i]);
                    var rowone = {};
                    rowone.modelid = rowData.modelid;
                    rowone.roleid = rowDatas.roleid;
                    rowone.rolemodelid = "";
                    datas.push(rowone);
                }
                $.ajax({
                    type: "POST",
                    url: "../sys_roles/updateRoleModels.do",
                    data: JSON.stringify({
                        "sys_rolemodels": datas, "roleid": rowDatas.roleid
                    }),
                    dataType: "json",
                    contentType: 'application/json',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    success: function (data) {
                        messageHelper(data, reloadGrid());
                        //然后关闭
                        layer.closeAll();
                    }
                });
            },
            btn2: function (index, layero) {
                layer.closeAll();
            },
            success: function (layero, index) {
                $("#table_list_alert").jqGrid().trigger("reloadGrid"); //重载JQGrid
                //id
                $.ajax({
                    type: "POST",
                    url: "../sys_roles/selectRoleModels.do",
                    data: {"id":id},
                    dataType: "json",
                    success: function (data) {
                    	setTimeout(function(){
                    		var RowData=$("#table_list_alert").getRowData();
                        	for(var i=0;i<data.data.length;i++){
    	                    		var one=data.data[i];
    	                    		for(var j=0;j<RowData.length;j++){
    	                    			if(RowData[j].modelid==one.modelid){
    		                    			$("#table_list_alert").jqGrid('setSelection',j+1);
    	                    			}
    	                    		}
                        	}
                    	},500)
                    }
                });
            }
        });
    });
}

function showStatue(cellvalue, options, rowObject) {
    if (cellvalue == 0) {
        return '<span class="label label-primary">启用</span>';
    } else {
        return '<span class="label label-warning">禁用</span>';
    }
}
/**
删除
 */
function deldata(id) {
    swal({
        title: "您确定要删除这条信息吗",
        text: "删除后将无法恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除",
        closeOnConfirm: false
    }, function () {
        $.ajax({
            type: "GET",
            url: "../sys_roles/delmenu.do",
            data: { id: id },
            dataType: "json",
            success: function (data) {
                messageHelper(data, reloadGrid());
            }
        });
    });
}
/**
修改
 */
function editdata(id) {
    //alert(id);
    $("#frameView").attr("src",
        "../sys_roles/edit_view.do?id=" + id);
    $('#addmodal').modal({
        keyboard: true
    });
}
/**
新增
 */
function adddata() {
    $("#frameView").attr("src", "../sys_roles/add_view.do");
    $('#addmodal').modal({
        keyboard: true
    });
}
//刷新grid
function reloadGrid() {
    //alert(1);
    var page = $("#table_list_2").jqGrid("getGridParam", "page");
    $("#table_list_2").jqGrid().trigger("reloadGrid", [{
        page: page
    }]); //重载JQGrid
}
function messageHelper(data, func) {
    if (data.mst == 0) {
        swal.close();
        toastr.success(data.msg);
        if (func) {
            func();
        }
    } else {
        toastr.error(data.msg);
    }
}

/*
 * 方法权限设置
 * */
function fnSetFuncAu() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.roleid;
        setbuttonfuncs(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function showfuncicon(cellvalue, options, rowObject){
	return '<i class="' + rowObject.icon + '"></i>&nbsp;&nbsp;&nbsp;&nbsp;'+cellvalue;
}
function showfuncgroup(cellvalue, options, rowObject){
	if(cellvalue && cellvalue!='' ){
		return cellvalue;
	}else{
		return "首页功能";
	}
}
function setbuttonfuncs(id) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , anim: 5
            , isOutAnim: false
            , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            , id: "divShowLayerfunc" //防止重复弹出
            , content: $("#menu_funcs"),
            area: [700 + 'px', 500 + 'px'],
            btn: ['确定', '取消'],
            btnAlign: 'c' //按钮居中
            , shade: 0 //不显示遮罩
            , yes: function () {
                //先保存数据，
                var ids = $('#table_list_alert_funcs').jqGrid('getGridParam', 'selarrrow');
                var modelid = $('#table_list_2').jqGrid('getGridParam', 'selrow');
                var rowDatas = $('#table_list_2').jqGrid('getRowData', modelid);
                var datas = [];
                for (var i = 0; i < ids.length; i++) {
                    var rowData = $("#table_list_alert_funcs").jqGrid('getRowData', ids[i]);
                    var rowone = {};
                    rowone.modelfuncid = rowData.funcid;
                    rowone.roleid = rowDatas.roleid;
                    rowone.rolemodelfuncid = "";
                    datas.push(rowone);
                }
                console.log(datas);
                $.ajax({
                    type: "POST",
                    url: "../sys_roles/updateRoleModelfuncs.do",
                    data: JSON.stringify({
                        "sys_rolemodelfuncs": datas, "roleid": rowDatas.roleid
                    }),
                    dataType: "json",
                    contentType: 'application/json',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    success: function (data) {
                        messageHelper(data, reloadGrid());
                        //然后关闭
                        layer.closeAll();
                    }
                });
            },
            btn2: function (index, layero) {
                layer.closeAll();
            },
            success: function (layero, index) {
            	$("#table_list_alert_funcs").jqGrid().trigger("reloadGrid"); //重载JQGrid
                $.ajax({
                    type: "POST",
                    url: "../sys_roles/selectRoleModelfuncs.do",
                    data: {"id":id},
                    dataType: "json",
                    success: function (data) {
                    	setTimeout(function(){
                    		var RowData=$("#table_list_alert_funcs").getRowData();
                        	for(var i=0;i<data.data.length;i++){
    	                    		var one=data.data[i];
    	                    		for(var j=0;j<RowData.length;j++){
    	                    			if(RowData[j].funcid==one.modelfuncid){
    		                    			$("#table_list_alert_funcs").jqGrid('setSelection',j+1);
    	                    			}
    	                    		}
                        	}
                    	},100)
                    }
                });
            }
        });
    });
}


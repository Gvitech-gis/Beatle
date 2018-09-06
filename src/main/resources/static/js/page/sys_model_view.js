$(document).ready(
    function () {
        $.jgrid.defaults.styleUI = "Bootstrap";
        $("#table_list_2").jqGrid(
            {
                url: "../sys_models/serch.do",
                datatype: "json",
                height: 460,
                autowidth: true,
                shrinkToFit: true,
                rowNum: 10,
                rowList: [10, 20, 30],
                colNames: ["id", "parentid", "模块名称",
                    "是否可见", "是否启用", "图标", "描述", "创建人",
                    "最后修改人"],
                colModel: [{
                    name: "modelid",
                    index: "modelid",
                    editable: true,
                    width: 60,
                    search: false,
                    hidden: true
                }, {
                    name: "parentid",
                    index: "parentid",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "modelname",
                    index: "modelname",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "isview",
                    index: "isview",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "isused",
                    index: "isused",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "icon",
                    index: "icon",
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
                add: true,
                edit: true,
                addtext: "Add",
                edittext: "Edit",
                hidegrid: false,
                multiselect: true,
            });
        $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
            edit: false,
            add: false,
            del: false,
            search: true,
            editfunc: function (id) {
                var idStr = "#" + id;
                var $currRow = $("#table_list_2").find(idStr);
                var menu_id = $currRow.find("td:eq(1)").text();
                console.log(menu_id);
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

        $("#table_list_alert").jqGrid({
            url: "../sys_menus/serch.do",
            datatype: "json",
            height: "auto",
            width: "100%",
            autowidth: true,
            shrinkToFit: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            colNames: ["id", "checked", "菜单名称", "描述", "url", "js方法"],
            colModel: [{
                name: "menuid",
                index: "menuid",
                editable: false,
                width: 60,
                search: false,
                hidden: true
            }, {
                name: "checked",
                index: "checked",
                editable: false,
                width: 60,
                search: false,
                hidden: true
            }, {
                name: "menuname",
                index: "menuname",
                editable: false,
                width: 100,
                search: true,
                formatter: showicon
            }, {
                name: "description",
                index: "description",
                editable: false,
                width: 200,
                search: true
            }, {
                name: "action",
                index: "action",
                editable: true,
                width: 150,
                search: true,
                editable: true
            }, {
                name: "jsfunc",
                index: "jsfunc",
                editable: true,
                width: 150,
                search: true,
                editable: true
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
            beforeSelectRow: function (id) {
                if (id && id !== lastSel) {
                    $('#table_list_alert').jqGrid('saveRow', lastSel);
                    lastSel = id;
                }
                $('#table_list_alert').editRow(id, true);
            },//加上grid代码处。  
            gridComplete: function () {
                var RowData = $("#table_list_alert").getDataIDs();
                if (RowData) {
                    for (var i = 0; i < RowData.length; i++) {
                        var data = $("#table_list_alert").getRowData(RowData[i]);
                        if (data && data["checked"] == "true") {
                            $("#table_list_alert").jqGrid('setSelection', RowData[i]);
                        }
                    }
                }
            }
        });

        $(window).bind("resize", function () {
            var width = $(".jqGrid_wrapper").width();
            $("#table_list_2").setGridWidth(width);
        });
    });
function fnAdd() {
    adddata();
    return false;
}
function fnEdit() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.modelid;
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
        var modelid = rowData.modelid;
        deldata(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function fnSetBtn() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.modelid;
        setbutton(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function setbutton(id) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
        , anim: 5
        , isOutAnim: false
        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        , id: "divShowLayer"  //防止重复弹出
        , content: $("#menu_showwin")
        , area: [700 + 'px', 500 + 'px']
        , btn: ['确定', '取消']
        , btnAlign: 'c' //按钮居中
        , shade: 0 //不显示遮罩
        , yes: function () {
            //先保存数据，
            //$('#table_list_alert').jqGrid('getGridParam','selarrrow');
            //$('#table_list_alert').saveRow();
            $('#table_list_alert').jqGrid('saveRow', lastSel);//写在保存方法里  
            var ids = $('#table_list_alert').jqGrid('getGridParam', 'selarrrow');
            var modelid = $('#table_list_2').jqGrid('getGridParam', 'selrow');
            var rowDatas = $('#table_list_2').jqGrid('getRowData', modelid);
            var datas = [];
            for (var i = 0; i < ids.length; i++) {
                var rowData = $("#table_list_alert").jqGrid('getRowData', ids[i]);
                var rowone = {};
                rowone.action = rowData.action;
                rowone.jsfunc = rowData.jsfunc;
                rowone.menuid = rowData.menuid;
                rowone.modelid = rowDatas.modelid;
                rowone.modelmenuid = "";
                datas.push(rowone);
            }
            $.ajax({
                type: "POST",
                url: "../sys_models/updateMenus.do",
                data: JSON.stringify({ "sys_modelmenuss": datas, "modelid": rowDatas.modelid }),
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
                var modelid = $('#table_list_2').jqGrid('getGridParam', 'selrow');
                var rowDatas = $('#table_list_2').jqGrid('getRowData', modelid);
                $("#table_list_alert").setGridParam( //G,P要大写
                    {
                        url: "../sys_menus/serchwithmodel.do",
                        postData: { modelid: rowDatas.modelid }
                    }
                ).trigger("reloadGrid");
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
	Tool.delTipWin("删除数据后将无法恢复，确认删除？",function(){
    	$.ajax({
            type: "GET",
            url: "../sys_models/delmenu.do",
            data: { id: id },
            dataType: "json",
            success: function (data) {
            	Tool.layer.closeAll();
            	Tool.messageHelper(data, reloadGrid());
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
        "../sys_models/edit_view.do?id=" + id);
    $('#addmodal').modal({
        keyboard: true
    });
}
/**
新增
*/
function adddata() {
    $("#frameView").attr("src", "../sys_models/add_view.do");
    $('#addmodal').modal({
        keyboard: true
    });
}
//刷新grid
function reloadGrid() {
    var page = $("#table_list_2").jqGrid("getGridParam", "page");
    $("#table_list_2").jqGrid().trigger("reloadGrid", [{
        page: page
    }]); //重载JQGrid
}
function messageHelper(data, func) {
    if (data.mst == 0) {
        swal.close();
        toastr.success(data.msg);
        //swal("删除成功！", "您已经永久删除了这条信息。", "success");
        //reloadGrid();
        if (func) {
            func();
        }
    } else {
        toastr.error(data.msg);
        //swal("删除失败！", "请联系管理员", "error");
    }
}

/*
设置方法
*/
function fnSetFuncBtn() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.modelid;
        setfunc(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function setfunc(id) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
        , anim: 5
        , isOutAnim: false
        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        , id: "divShowLayer"  //防止重复弹出
        , content: $("#menu_showwin")
        , area: [700 + 'px', 500 + 'px']
        , btn: ['确定', '取消']
        , btnAlign: 'c' //按钮居中
        , shade: 0 //不显示遮罩
        , yes: function () {
            //先保存数据，
            //$('#table_list_alert').jqGrid('getGridParam','selarrrow');
            //$('#table_list_alert').saveRow();
            $('#table_list_alert').jqGrid('saveRow', lastSel);//写在保存方法里  
            var ids = $('#table_list_alert').jqGrid('getGridParam', 'selarrrow');
            var modelid = $('#table_list_2').jqGrid('getGridParam', 'selrow');
            var rowDatas = $('#table_list_2').jqGrid('getRowData', modelid);
            var datas = [];
            for (var i = 0; i < ids.length; i++) {
                var rowData = $("#table_list_alert").jqGrid('getRowData', ids[i]);
                var rowone = {};
                rowone.action = rowData.action;
                rowone.menuid = rowData.menuid;
                rowone.modelid = rowDatas.modelid;
                rowone.modelmenuid = "";
                datas.push(rowone);
            }
            //console.log(datas);
            $.ajax({
                type: "POST",
                url: "../sys_models/updateMenus.do",
                data: JSON.stringify({ "sys_modelmenuss": datas, "modelid": rowDatas.modelid }),
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

            }
        });
    });
}

var lastSel;//在顶部定义  
$(document).ready(function () {
    layui.use(['form', 'layedit'], function () {
        var form = layui.form, layer = layui.layer;
    });
    $("#using_json").jstree({
        "core": {
            "data": {
                'url': '../sys_models/models_Tree.do',
                'dataType': 'json',
            }
        }
    });

});

function showicon(cellvalue, options, rowObject) {
    return '<i class="' + rowObject.icon + '"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + cellvalue;
}
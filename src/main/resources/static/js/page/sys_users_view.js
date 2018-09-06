$(document).ready(
    function () {
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

        $("#table_list_2").jqGrid(
            {
                url: "../sys_users/serch.do",
                datatype: "json",
                height: 460,
                autowidth: true,
                shrinkToFit: true,
                rowNum: 10,
                rowList: [10, 20, 30],
                colNames: ["id", "名称", "姓名", "手机号", "email", "身份证号", "角色", "状态"],

                colModel: [{
                    name: "userid",
                    index: "userid",
                    editable: true,
                    width: 60,
                    search: false,
                    hidden: true
                }, {
                    name: "username",
                    index: "username",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "realname",
                    index: "realname",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "mobile",
                    index: "mobile",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "email",
                    index: "email",
                    editable: true,
                    width: 60,
                    search: true
                }, {
                    name: "card",
                    index: "card",
                    editable: true,
                    width: 60,
                    sorttype: "int",
                    search: true
                }, {
                    name: "roleid",
                    index: "roleid",
                    editable: true,
                    width: 90
                }, {
                    name: "status",
                    index: "status",
                    editable: true,
                    width: 60,
                    sorttype: "int",
                    search: true,
                    formatter: showStatue
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
        var modelid = rowData.userid;
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
/*
 * 分配角色
 * */
function fnSetBtn() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.userid;
        //console.log(rowData);
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
                var datas = [];
                for (var i = 0; i < ids.length; i++) {
                    var rowData = $("#table_list_alert").jqGrid('getRowData', ids[i]);
                    var rowone = {};
                    rowone.roleid = rowData.roleid;
                    rowone.userid = id;
                    datas.push(rowone);
                }
                $.ajax({
                    type: "POST",
                    url: "../sys_users/updateroles.do",
                    data: JSON.stringify({ "lstsys_userrole": datas, "userid": id }),
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
/*
 * 分配部门
 * */
function fnSetbumen() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.userid;
        setbuttonbumen(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function setbuttonbumen(id) {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , anim: 5
            , isOutAnim: false
            , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            , id: "divShowLayerdepart"  //防止重复弹出
            , content: $("#menu_showwin_depart")
            , area: [700 + 'px', 500 + 'px']
            , btn: ['确定', '取消']
            , btnAlign: 'c' //按钮居中
            , shade: 0 //不显示遮罩
            , yes: function () {
                //先保存数据，
                //$('#table_list_alert').jqGrid('getGridParam','selarrrow');
                //$('#table_list_alert').saveRow();
                $('#table_list_departs').jqGrid('saveRow', lastSel);//写在保存方法里  
                var ids = $('#table_list_departs').jqGrid('getGridParam', 'selarrrow');
                var datas = [];
                for (var i = 0; i < ids.length; i++) {
                    var rowData = $("#table_list_departs").jqGrid('getRowData', ids[i]);
                    var rowone = {};
                    rowone.departid = rowData.departid;
                    rowone.departuserid = "";
                    rowone.userid = id;
                    datas.push(rowone);
                }
                $.ajax({
                    type: "POST",
                    url: "../sys_users/updateuserdeparts.do",
                    data: JSON.stringify({ "lstsys_userdepart": datas, "userid": id }),
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
            url: "../sys_users/delmenu.do",
            data: {
                id: id
            },
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
        "../sys_users/edit_view.do?id=" + id);
    $('#addmodal').modal({
        keyboard: true
    });
}
/**
新增
*/
function adddata() {
    $("#frameView").attr("src", "../sys_users/add_view.do");
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
var lastSel;//在顶部定义
$(document).ready(function () {
    layui.use(['form', 'layedit'], function () {
        var form = layui.form, layer = layui.layer;
    });
    $("#using_json").jstree({
        "core": {
            "data": {
                'url': '../sys_roles/roles_Tree.do',
                'dataType': 'json',
            }
        }
    });
    $("#table_list_alert").jqGrid({
        url: "../sys_roles/serch.do",
        datatype: "json",
        height: "auto",
        width: "100%",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ["id", "菜单名称", "描述"],
        colModel: [{
            name: "roleid",
            index: "roleid",
            editable: false,
            width: 60,
            search: false,
            hidden: true
        }, {
            name: "rolename",
            index: "rolename",
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
        //multiboxonly:true,
        cellEdit: true,
        cellsubmit: 'clientArray',
        beforeSelectRow: function (id) {
            if (id && id !== lastSel) {
                $('#table_list_alert').jqGrid('saveRow', lastSel);
                lastSel = id;
            }
            $('#table_list_alert').editRow(id, true);
        }//加上grid代码处。
    });
    //
    $("#table_list_departs").jqGrid({
        url: "../sys_departs/serch.do",
        datatype: "json",
        height: "auto",
        width: "100%",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ["id", "部门名称", "描述"],
        colModel: [{
            name: "departid",
            index: "departid",
            editable: false,
            width: 60,
            search: false,
            hidden: true
        }, {
            name: "departname",
            index: "departname",
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
        //multiboxonly:true,
        cellEdit: true,
        cellsubmit: 'clientArray',
        beforeSelectRow: function (id) {
            if (id && id !== lastSel) {
                $('#table_list_departs').jqGrid('saveRow', lastSel);
                lastSel = id;
            }
            $('#table_list_departs').editRow(id, true);
        }//加上grid代码处。
    });
});

function showicon(cellvalue, options, rowObject) {
    return '<i class="' + rowObject.icon + '"></i>&nbsp;&nbsp;&nbsp;&nbsp;' + cellvalue;
}
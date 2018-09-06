$(document).ready(function () {
    $.jgrid.defaults.styleUI = "Bootstrap";
    $("#table_list_2").jqGrid({
        url: "../sys_menus/serch.do",
        datatype: "json",
        height: 350,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ["id", "菜单名称", "图标", "描述", "创建人", "最后修改人"],
        colModel: [{
            name: "menuid",
            index: "menuid",
            editable: true,
            width: 60,
            search: false,
            hidden: true
        }, {
            name: "menuname",
            index: "menuname",
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
        //$("#table_list_1").setGridWidth(width);
        $("#table_list_2").setGridWidth(width);
    });
   
});
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
function del() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.menuid;
        deldata(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function deldata(id) {
	Tool.delTipWin("删除数据后将无法恢复，确认删除？",function(){
    	$.ajax({
            type: "GET",
            url: "../sys_menus/delmenu.do",
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
function edit() {
    var ids = $("#table_list_2").jqGrid("getGridParam", "selarrrow");
    if (ids.length == 1) {
        var rowid = $("#table_list_2").jqGrid("getGridParam", "selrow");
        var rowData = $("#table_list_2").jqGrid('getRowData', rowid);
        var modelid = rowData.menuid;
        editdata(modelid);
    } else {
        toastr.error("你没有选取或者选取为多行数据");
    }
    return false;
}
function editdata(id) {
    $("#frameView").attr("src", "../sys_menus/edit_view.do?id=" + id);
    $('#addmodal').modal({
        keyboard: true
    });
}
/**
新增
 */
function add(){
	adddata();
}
function adddata() {
    $("#frameView").attr("src", "../sys_menus/add_view.do");
    $('#addmodal').modal({
        keyboard: true
    });
}
//刷新grid
function reloadGrid() {
    var page = $("#table_list_2").jqGrid("getGridParam", "page");
    $("#table_list_2").jqGrid().trigger("reloadGrid", [{ page: page }]);  //重载JQGrid
}
/**
 * Created by Administrator on 2016/8/4.
 */
var zTree, rMenu = $("#rMenu"), rUI = $("#rMenu ul"),roleFlag = true, nodes,ROLEID;
function Roleload() {
    change();
    $('#table').bootstrapTable({
        method: "post",
        striped: true,
        singleSelect: false,
        dataType: "json",
        pagination: true, //分页
        pageSize: 10,
        pageNumber: 1,
        search: false, //显示搜索框
        contentType: "application/x-www-form-urlencoded",
        queryParams: null,
        columns: [
            {
                checkbox: "true",
                field: 'check',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '角色id',
                field: 'id',
                align: 'center',
                valign: 'middle',
                visible:false,
            },
            {
                title: '角色名称',
                field: 'name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    /*var e = '<button button="#" mce_href="#" onclick="editRole(\'' + row.id + '\')">权限修改</button> '*/
                    var d = '<button button="#" mce_href="#" onclick="delRole(\'' + row.id + '\')">删除</button> ';
                    return d ;
                }
            }
        ]
    });
    getRoleTableData();
}
function getRoleTableData() {
    var name;
    if (roleFlag) {
        name = "";
        roleFlag = false;
    } else {
        name = $("#name").val();
    }

    $.ajax({
        type: "Post",
        url: "/role/getRolePage",
        dataType: "json",
        success: function (result) {
            if (result) {
                $('#table').bootstrapTable("load", result);
            }
        }
    })
}
function change() {
    var height01 = $(window).height();
    $(".right").css('height', height01 - 35+"px");
}
//权限修改
function editRole(id) {
      $.ajax({
        type: "GET",
        url: "../RoleManage/SearchPermissionById?id=" + id,
        dataType: "json",
        success: function (result) {
            if (result.data) {
                var RoleData = result.data;
                ROLEID = RoleData.ID;
                $("#ROLE").val(RoleData.NAME);
                $("#ROLENAME").val(RoleData.DESCRIBE);
                var str = RoleData.VALUE;
                var c = str.split(",");
                Tree(c);
        }
        }
    }) 
}

//删除角色
function delRole(id) {

    var ridArr = [];
    /**
     * 获取选中行
     */
    var row = $.map($("#table").bootstrapTable('getSelections'),function(row){
        return row ;
    });



    for(var i=0;i<row.length;i++){
        ridArr[i] = row[i].id;
    }

    if (id != null && id > 0)
        ridArr.push(id);

    if (ridArr.length === 0){
        layer.msg(
            "请选择要删除项",{icon:5});
        return;
    }

    $.ajax({
        type: "post",
        url: "/role/delRole",
        dataType: "json",
        data:{ridArr:ridArr},
        traditional:true,
        success: function (result) {
            if (result) {
                /*getRoleTableData();*/
                alert("删除成功");
                window.location.reload();
            }

        }
    })
}

//DOM树初始化setting设置
var setting = {
    check: {
        enable: true
    },
    data: {
        key: { name: "MODULENAME" },
        simpleData: {
            enable: true,
            idKey: "MODULEID",
            pIdKey: "pIdKey",
            rootPId: null
        }
    },
    callback: {
        onCheck: onCheck
    }
};

//加载DOM树数据方法
function Tree(c) {
    var number = c;
    $.ajax({
        type: "POST",
        url: '../RoleManage/GetFunctionModuleList',
        dataType: "json",
        success: function (result) {
            if (result) {
                var nodes = result.data;
                for (var i = 0; i < number.length; i++) {
                    nodes[number[i]].checked = true
                }
                var item = { "MODULENAME": "权限目录", "pIdKey": "-1",checked:true};
                    nodes.push(item);
                $.fn.zTree.init($("#treeDemo"), setting, nodes);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandAll(true);
            }
        }
    });
}
function onCheck(e, treeId, treeNode) {
    nodes = zTree.getCheckedNodes()
};

//保存
function onClick() {
    var ROLE = $("#ROLE").val();
    var ROLENAMES = $("#ROLENAME").val()
var v = "";
for (var i = 1; i < nodes.length; i++) {
    if (i == nodes.length - 1) {
        v += nodes[i].MODULEID;
    } else {
        v += nodes[i].MODULEID + ",";
    }
}
if (ROLE.length >0) {
    $.ajax({
        type: "POST",
        url: '../RoleManage/UpdatePermission',
        dataType: "json",
        data: { ID: ROLEID, NAME: ROLE, VALUE: v, DESCRIBE: ROLENAMES },
        success: function (result) {
            if (result) {
                alert("修改成功")
                getRoleTableData();
                $("#ROLE").val("");
                $("#ROLENAME").val("");
                checkCancel();
            }
        }
    });
} else {
    alert(权限修改失败)
}
}

//取消
function checkCancel() {
    $("#ROLE").val("");
    $("#ROLENAME").val("");
    zTree.checkAllNodes(false);
}

function openlayer() {
    layer.open({
        type: 2,
        title: '角色信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: '/role/toRole_Tail'

    });
    
}




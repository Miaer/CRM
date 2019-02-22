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
                title: "客户类型id",
                field: 'id',
                align: 'center',
                valign: 'middle',
                visible:false,
            },
            {
                title: '客户类型名称',
                field: 'name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    var d = '<button button="#" mce_href="#" onclick="delRole(\'' + row.id + '\')">删除</button>';
                    return d ;
                }
            }
        ]
    });
    getRoleTableData();
}
function getRoleTableData() {
  /*  var name;
    if (roleFlag) {
        name = "";
        roleFlag = false;
    } else {
        name = $("#name").val();
    }*/

    $.ajax({
        type: "Post",
        url: "/customerType/getCustomerType",
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

//删除
function delRole(id) {

    var cuTypeArr = [];
    /**
     * 获取选中行
     */
    var row = $.map($("#table").bootstrapTable('getSelections'),function(row){
        return row ;
    });

    for(var i=0;i<row.length;i++){
        cuTypeArr[i] = row[i].id;
    }

    if (id != null && id > 0)
        cuTypeArr.push(id);

    if (cuTypeArr.length === 0){
        layer.msg(
            "请选择要删除项",{icon:5});
        return;
    }

    $.ajax({
        type: "post",
        url: "/customerType/delCustomer",
        dataType: "json",
        data:{cuTypeArr:cuTypeArr},
        traditional:true,
        success: function (result) {
            if (result) {
                layer.msg('删除成功', {
                    icon: 1,
                    time: 800
                });
                getRoleTableData();
            }else {
                layer.open({
                    anim:2,
                    icon:5,
                    title: '删除信息',
                    closeBtn:2,
                    content: '删除失败，请保证无客户引用客户类型',
                    yes:function(){             //确定按钮回调方法
                        window.location.reload();
                    }
                });
            }

        }
    })
}

//取消
function checkCancel() {
    $("#ROLE").val("");
    $("#ROLENAME").val("");
    zTree.checkAllNodes(false);
}
//prompt层
function addCustomerType() {

    /**
     * 要想使用layer.prompt 必须先加载extend/layer.ext.js。此js   layer默认不加载
     */
    layer.use('extend/layer.ext.js', function(){
            //例子1
            layer.prompt({title:'请输入客户类型，并确认',formType: 0},function(value, index){

                $.ajax({
                    url:"/customerType/addType?type="+value,
                    success:function (result) {
                        if (result){
                            layer.msg('添加成功', {
                                icon: 1,
                                time: 800
                            });
                            getRoleTableData();
                        }
                    }
                });
                layer.close(index);
            });
    });
}

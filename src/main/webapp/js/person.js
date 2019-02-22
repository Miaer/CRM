/**
 * Created by Administrator on 2016/8/4.
 */

var user, role, currentID, flag = true;
function Personload() {
    $('#table').bootstrapTable({
        method: "get",
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
                checkbox:"true"
            },
            /*{
                title: "用户id",
                field: 'uid',
                align: 'center',
            },*/
            {
                title: "用户名",
                field: 'name',
                align: 'center'
            },
            {
                title: '角色',
                field: 'rname',
                align: 'center'
            },
            {
                title: '创建日期',
                field: 'createDate',
                align: 'center',
                formatter:function (value,row,index) {
                    return changeDateFormat(value);
                }
            },
            {
                title: '操作',
                field: 'uid',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="del(\'' + row.uid + '\')">删除</button> '
                    var d = '<button button="#" mce_href="#" onclick="edit(\'' + row.uid + '\')">编辑</button> ';
                    return e + d;
                }
            }
        ]
    });
    getData();
}
function getData() {
    if (flag) {
        user = "";
        role = "";
        flag = false;
    } else {
        user = $("#user").val();
        role = $("#role").val();
    }
    $.ajax({
        type:"POST",
        url: "/customer/getCustomer?user="+user+"&role="+role,
        dataType: "json",
        success: function (result) {
            if (result.length > 0) {
                var TableData = result;
                $('#table').bootstrapTable("load", TableData);
            }
        }
    })
}
function add(id) {
    openlayer(id);
    currentID = "";
}
function edit(id) {
    currentID = id;
    update(id);
}
function del(id) {
    var uidArr = [];
    /**
     * 获取选中行
     */
    var row = $.map($("#table").bootstrapTable('getSelections'),function(row){
        return row ;
    });


    for(var i=0;i<row.length;i++){
        uidArr[i] = row[i].uid;
    }

    if (id != null && id > 0)
        uidArr.push(id);

    if (uidArr.length === 0){
        layer.msg(
            "请选择要删除项",{icon:5});
        return;
    }

    $.ajax({
        url: '/customer/delCustomer',
        type: 'post',
        //contentType:"application/json",
        data:{uidArr : uidArr},                //传数组
        traditional:true,           //阻止其深度序列化数组对象
        dataType: 'json',
        success: function (data) {
            if (data) {
                layer.open({
                    anim:1,
                    title: '删除信息',
                    closeBtn:1,
                    content: '删除成功',
                    yes:function(){             //确定按钮回调方法
                        window.location.reload();
                    }
                });
            } else {
                alert("删除失败")
            }
        },
        error: function (err) {
        }
    });
}
function getCurrentID() {
    return currentID;
}
function openlayer(id){
    layer.open({
        type: 2,
        title: '添加用户',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: '/customer/toAdd'
        //iframe的url
    });
}

function update(id){
    layer.open({
        type: 2,
        title: '更新信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: '/customer/toUpdate?id='+id
        //iframe的url
    });
}






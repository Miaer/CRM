/**
 * Created by Administrator on 2016/8/4.
 */

var tit, person, currentID, time, flag = true;
function Mesload() {
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
                checkbox:"true",
            },
            {
                title: "用户id",
                field: 'id',
                align: 'center',
                visible:false,
            },
            {
                title: "客户名称",
                field: 'company_name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '客户联系人',
                field: 'person_name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '联系电话',
                field: 'person_phone',
                align: 'center'
            },
            {
                title: '地址',
                field: 'address',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '客户类型',
                field: 'name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="del(\'' + row.id + '\')">删除</button> ';
                    var j = '<button button="#" mce_href="#" onclick="edit(\'' + row.id + '\')">修改</button> ';
                    var d = '<button button="#" mce_href="#" onclick="schedule(\'' + row.id + '\')">预约</button> ';
                    return e +j + d ;
                }
            }
        ]
    });
    getData();
}
function getData() {
    if (flag) {
        customName= "";
        time = "";
        flag = false;
    } else {
        customName = $("#customName").val();
        time = $("#time").val();
    }
    $.ajax({
        type: "post",
        url: "/customer/getCustomerAll?customName="+customName+"&time="+time,
        dataType: "json",
        success: function (data) {
            if (data.length > 0) {
                layer.msg("正在加载",{
                    icon:4,
                    time: 800 //1.5秒关闭（如果不配置，默认是3秒）
                },function () {
                    $('#table').bootstrapTable("load", data);
                });
            }else {
                layer.msg("哦~No 没有找到你要的数据！");
            }
            $("#customName").val("");
            $("#time").val("");
        }
    })
}
//初始化状态下拉菜单
function getType() {
    $.ajax({
        url: '../Common/GetPhaseList',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var PHASEValue = data.data;
            if (PHASEValue.length > 0) {
                $("#part").html("");
                for (var i = 0; i < PHASEValue.length; i++) {
                    if (TASKPHASE == PHASEValue[i].ID) {
                        var html = "<Option value = '" + PHASEValue[i].ID + "'  selected = 'true'>" + PHASEValue[i].NAME + "</Option>";
                    } else {
                        var html = "<Option value = '" + PHASEValue[i].ID + "'>" + PHASEValue[i].NAME + "</Option>";
                    };
                    $("#part").append(html);
                }
            }


        },
        error: function (err) {
        }

    })
}
function add() {
    openlayer();
    currentID = "";

}
function edit(id) {
    openlayer();
    currentID = id;
}

/**
 * 预约该客户
 */
function schedule(id){
    openSchedulelayer(id);
}
function del(id) {
    var cusArr = [];
    /**
     * 获取选中行
     */
    var row = $.map($("#table").bootstrapTable('getSelections'),function(row){
        return row ;
    });



    for(var i=0;i<row.length;i++){
        cusArr[i] = row[i].id;
    }

    if (id != null && id > 0)
        cusArr.push(id);

    if (cusArr.length === 0){
        layer.msg(
            "请选择要删除项",{icon:5});
        return;
    }

    $.ajax({
        url: '/customer/deleteCustomer',
        type: 'post',
        data:{cusArr:cusArr},
        traditional:true,           //阻止其深度序列化数组对象
        dataType: 'json',
        success: function (data) {
            if (data) {
                layer.open({
                    anim:1,
                    title: '删除信息',
                    closeBtn:1,
                    content: '删除成功',
                    yes:function(index, layero){
                    //do something
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
function openlayer(){
    layer.open({
        type: 2,
        title: '添加信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:1,
        area: ['98%', '90%'],
        shadeClose: true,
        closeBtn: 1,
        anim: 1,
        content: '/customer/toAddCustomerPage'
    });
}

function openSchedulelayer(id){
    currentID = id;
    layer.open({
        type: 2,
        title: '预约客户',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:1,
        area: ['98%', '90%'],
        shadeClose: true,
        closeBtn: 1,
        anim: 1,
        content: '/view/customer/schedule.jsp?id='+ id
        //iframe的url
    });
}






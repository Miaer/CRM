/**
 * Created by Administrator on 2016/8/4.
 */

var projectId, person, currentID, time, flag = true;
function Recodeload() {
    $(function () {
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
                     title: '投资客户',
                     field: 'company_name',
                     align: 'center',
                     valign: 'middle'
                 },
                {
                    title: "投资额",
                    field: 'invest_amount',
                    align: 'center'
                },
                {
                     title: '认购日期',
                     field: 'invest_date',
                     align: 'center',
                    formatter:function (value) {
                        return changeDateFormat(value);
                    }
                 },
                {
                    title: '认购费',
                    field: 'invest_fee',
                    align: 'center',

                },
                {
                    title: '联系电话',
                    field: 'person_phone',
                    align: 'center',
                },
                {
                    title: '个人可投资产量级',
                    field: 'assert_volumn',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'id',
                    align: 'center',
                    formatter: function (value, row) {
                        // row.id 客户id
                        var c = '<button button="#" mce_href="#" onclick="delRecode(\'' + row.id + '\')">删除</button> ';
                        /*
                        * 修改功能，其思路和增加一样，和增加用同一页面，区别是：增加显示所有客户，修改 只显示当前要修改的客户的信息。
                        * */
                        var d = '<button button="#" mce_href="#" onclick="editRecode(\'' + row.id + '\')">修改</button> ';
                        return c + d;
                    }
                }
            ]
        });
    });
    getRecodeTableData();
}
function getRecodeTableData() {
    var currentID = parent.getCurrentID();
    $.ajax({
        type: "get",
        url: "/project/initProject?projectid="+currentID,
        dataType: "json",
        success: function (result) {
            if (result) {
                $('#table').bootstrapTable("load", result);
            }
        }
    })
}
function addRecode() {
    currentID = parent.getCurrentID();
    openlayer();
}

var customer;
function editRecode(id) {
    // 客户id
    customer = id;
    // 项目id
    currentID = parent.getCurrentID();
    updateProjectInfo(customer,projectId);
}

function getCustomerId() {
    return customer;
}

function delRecode(id) {
    var proId = parent.getCurrentID(); //项目id
    var RecodeId = id;
    alert(id);                              //147客户id
    $.ajax({
        url: '/project/deleteProjectInvestByProId?id=' + RecodeId+"&proId="+proId,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data) {
                layer.msg('删除成功', {
                    icon: 1,
                    time: 800
                }, function(){
                    getRecodeTableData();
                });

            } else {
                layer.msg('删除失败', {
                    icon: 4,
                    time: 800,
                    content:"正在返回项目管理页面"
                }, function(){
                    getRecodeTableData();
                });
            }
        },
    });
}
function getCurrentID() {
    return currentID;
}
function openlayer() {
    layer.open({
        type: 2,
        title: '投资人信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:'/project/toAddProjectCutomerPage?customer='+customer+'&projectId='+projectId
    });

}
function updateProjectInfo(customer,projectId) {
    layer.open({
        type: 2,
        title: '项目信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:"/project/updateProjectInfo?customerId="+customer+"&projectId="+projectId
    });

}


function changeDateFormat(cellval) {
    if (cellval != null) {
        var date = new Date(cellval).toJSON();
        return new Date(+new Date(date)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
    }
}





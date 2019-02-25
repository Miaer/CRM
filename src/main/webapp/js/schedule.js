/**
 * Created by Administrator on 2016/8/4.
 */

var workTitle, Publisher,CurrentID, visit_id, workTime, flag = true;
function scheduleload() {
    $(function () {
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
                    title: "预约客户id",
                    field: 'id',
                    align: 'center',
                    visible:false,
                },
                {
                    title: "预约id",
                    field: 'visit_id',
                    align: 'center',
                    visible:false,
                },
                {
                    title: "客户名",
                    field: 'company_name',
                    align: 'center',
                },
                {
                    title: '联系人',
                    field: 'person_name',
                    align: 'center',
                },
                {
                    title: '联系电话',
                    field: 'person_phone',
                    align: 'center',
                },
                {
                    title: '地址',
                    field: 'address',
                    align: 'center',
                },
                {
                    title: '预约时间',
                    field: 'visit_time',
                    align: 'center',
                    formatter:function (value) {
                        return changeDateFormat(value);
                    }
                },
                {
                    title: '洽谈事宜',
                    field: 'visit_matters',
                    align: 'center',
                },
                {
                    title: '备注',
                    field: 'visit_memo',
                    align: 'center',
                },
                {
                    title: '操作',
                    /*field: 'id',*/
                    align: 'center',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="delSchedule(\'' + row.visit_id + '\')">删除</button>';
                        var f = '<button button="#" mce_href="#" onclick="editSchedule(\'' + row.visit_id + '\')">更改</button>';
                        var g = '<button button="#" mce_href="#" onclick="scheduleComplete(\'' + row.visit_id + '\')">预约完成</button>';
                        return e + f + g;
                    }
                }
            ]
        });
    });
    getScheduleTableData();
}
function getScheduleTableData() {
    if (flag) {
        customName= "";
        scheduleTime = "";
        flag = false;
    } else {
        customName = $("#tit").val();
        scheduleTime = $("#demo").val();
    }
    $.ajax({
        type: "post",
        url: "/visit/findVisitStateIsChedule?scheduleTime="+scheduleTime + "&customName="+customName,
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
            $("#demo").val("");
        }
    })
}
function addSchedule(id) {
    if (id === ""){
        openlayer(id);
    }else {
        openlayerToAdd();
    }
    visit_id = "";
}
function editSchedule(id) {
    visit_id = id;
    openlayer(id);
}
function delSchedule(id) {
    var visitArr = [];
    /**
     * 获取选中行
     */
    var row = $.map($("#table").bootstrapTable('getSelections'),function(row){
        return row ;
    });



    for(var i=0;i<row.length;i++){
        visitArr[i] = row[i].visit_id;
    }

    if (id != null && id > 0)
        visitArr.push(id);

    if (visitArr.length === 0){
        layer.msg(
            "请选择要删除项",{icon:5});
        return;
    }

    $.ajax({
        url: '/visit/delVisitByIds',
        type: 'post',
        data:{visitArr:visitArr},
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
    return visit_id;
}
function openlayer(id) {
    layer.open({
        type: 2,
        title: '',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:'/visit/toUpdataSchedule?visitId='+id

    });
    
}

function openlayerToAdd() {
    layer.open({
        type: 2,
        title: '',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        area: ['98%', '98%'],
        closeBtn: 2,
        content:'/visit/toSchedule_tail'
    });
}

function layerClose(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}

function scheduleComplete(id) {
    $.ajax({
        url:"/visit/scheduleComplete?id="+id,
        success:function (data) {
            if (data){
                layer.open({
                    content: '操作成功!',
                    yes: function(index, layero){
                        window.location.reload();
                    }
                });
            }else {
                layer.msg("系统内部出错，请再试一次");
            }
        }
    });
}
function changeDateFormat(cellval) {
    if (cellval != null) {
        var date = new Date(cellval).toJSON();
        return new Date(+new Date(date)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
    }
}






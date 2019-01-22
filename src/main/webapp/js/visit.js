/**
 * Created by Administrator on 2016/8/4.
 */

var recodeTitle, Publisher, currentID, recodeTime, flag = true;
function Recodeload() {
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
            uniqueId:"id",
            columns: [
                {
                    checkbox:"false",
                },
                {
                    title: "客户id",
                    field: 'id',
                    align: 'center',
                    visible:false,
                },
                {
                    title: "预约记录id",
                    field: 'visit_id',
                    align: 'center',
                    visible:false,
                },
                {
                    title: "拜访完成日期",
                    field: 'visit_complete_time',
                    align: 'center',
                    formatter:function (value) {
                        return changeDateFormat(value);
                    }
                },
                {
                    title: '拜访客户',
                    field: 'company_name',
                    align: 'center',
                },
                {
                    title: '联系人',
                    field: 'person_name',
                    align: 'center'
                },
                {
                    title: '所属类别',
                    field: 'name',
                    align: 'center',
                },
                {
                    title: '职务',
                    field: 'person_positoin',
                    align: 'center',
                },
                {
                    title: '地址',
                    field: 'address',
                    align: 'center'
                },
                {
                    title: '联系电话',
                    field: 'person_phone',
                    align: 'center'
                },
                {
                    title: '拜访事宜',
                    field: 'visit_matters',
                    align: 'center'
                },
                {
                    title: '备注',
                    field: 'visit_memo',
                    align: 'center'
                },
                /*{
                    title: '操作',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="editRecode(\'' + row.id + '\')">查看</button> ';
                        return e;
                    }
                }*/
            ]
        });
    });
   getRecodeTableData();
}
function getRecodeTableData() {
    if (flag) {
        companyName = "";
        completeTime = "";
        flag = false;
    } else {
        companyName = $("#company_name").val();
        completeTime  = $("#visit_complete_time").val();
    }
    $.ajax({
        type: "post",
        url: "/visit/findVisitAll?completeTime="+completeTime+"&companyName="+companyName,
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
            $("#company_name").val("");
            $("#visit_complete_time").val("");
        }
    })
}

function editRecode(id) {
    openlayer();
    currentID = id;
}
function outRecode(id) {
    alert(id)
    var RecodeId = id;
    $.ajax({
        url: '../WorkRecord/DeleteWork?workId=' + RecodeId,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.data) {
                alert("下载成功！")
                // getRecodeTableData();
            } else {
                alert("下载失败")
            }
        },
        error: function (err) {
        }
    });
}
function getCurrentID() {
    return currentID;
}
function openlayer() {
    layer.open({
        type: 2,
        title: '拜访客户',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:"visit_tail.jsp"
    });
    
}

function delVisit() {
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





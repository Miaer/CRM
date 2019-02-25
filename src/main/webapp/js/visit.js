
/*
*   visit:
*   [{customer_type=2, person_phone2=null, memo=, person_positoin=, visit_matters=3否认43呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱古古怪怪呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱古古怪怪呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱古古怪怪呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱古古怪怪呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱古古怪怪钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃额          呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃, major_business=null, visit_memo=呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃, identification=null, home_address=null, company_alias=null, prejudice=null, id=163, person_company=null, visit_complete_time=2019-01-28 16:55:55.0, area=null, address=, create_time=2019-01-28 10:05:56.0, visit_time=2019-01-25 00:00:00.0, person_phone=, person_name=, user_id=103, company_name=marry, name=个人渠道, assert_volumn=null, visit_id=70, customer_id=163, chedule_state=1}, {customer_type=2, person_phone2=null, memo=, person_positoin=, visit_matters=是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶
    是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶
    是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶
    是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶
    是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶
    是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶
    是法国顶顶顶顶顶d顶顶顶顶顶顶顶顶顶顶顶顶, major_business=null, visit_memo=, identification=null, home_address=null, company_alias=null, prejudice=null, id=163, person_company=null, visit_complete_time=2019-01-28 10:05:56.0, area=null, address=, create_time=2019-01-28 10:05:56.0, visit_time=2019-01-17 00:00:00.0, person_phone=, person_name=, user_id=103, company_name=marry, name=个人渠道, assert_volumn=null, visit_id=68, customer_id=163, chedule_state=1}, {customer_type=2, person_phone2=null, memo=, person_positoin=, visit_matters=啦啦啦, major_business=null, visit_memo=急急急, identification=null, home_address=null, company_alias=null, prejudice=null, id=163, person_company=null, visit_complete_time=2019-01-28 10:05:56.0, area=null, address=, create_time=2019-01-28 10:05:56.0, visit_time=2019-01-09 00:00:00.0, person_phone=, person_name=, user_id=103, company_name=marry, name=个人渠道, assert_volumn=null, visit_id=67, customer_id=163, chedule_state=1}]

*
*
* */
var visit, currentID,flag = true;
/**
 * 查看拜访客户的信息
 * @param id
 */
function detail(id) {
    currentID = id;
    //墨绿深蓝风
    var index =  layer.open({
        type: 2,
        title: '拜访记录信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['80%', '80%'],
        /*content: '/view/work/detail.jsp'*/
        content:'/visit/initLookSchedule?visitId='+id
    });
}


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
            uniqueId:"id",      // 表明每行唯一的标识符
            columns: [
                {
                    checkbox:"false"
                },
                {
                    title: "客户id",
                    field: 'id',
                    align: 'center',
                    visible:false
                },
                {
                    title: "预约记录id",
                    field: 'visit_id',
                    align: 'center',
                    visible:false

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
                    align: 'center'
                },
                {
                    title: '联系人',
                    field: 'person_name',
                    align: 'center'
                },
                {
                    title: '所属类别',
                    field: 'name',
                    align: 'center'
                },
                {
                    title: '职务',
                    field: 'person_positoin',
                    align: 'center'
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
                {
                    title: '操作',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="detail(\'' + row.visit_id + '\')">查看</button> ';
                        return e;
                    }
                }
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
            visit = data;
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



function  getVisit() {
    return visit;
}

/**
 * 添加拜访记录
 */
function addVisit() {
    openAddlayer();
}
function getCurrentID() {
    return currentID;
}
function openAddlayer() {
    layer.open({
        type: 2,
        title: '拜访客户',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        content:"/view/work/visit_tail.jsp"
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





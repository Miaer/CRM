

function change() {
    var height01 = $(window).height();
    $(".top").css('height', height01 - 35+"px");
}

$.validator.setDefaults({
    submitHandler: function() {
        RecodeSave();
    }
});
$().ready(function() {
    $("#form_demo").validate();
});

var PROJECTID, customerID;

function init() {
    customerID = $("#customerid").val();
    $.ajax({
        url: '/customer/findCustomerById?id=' + customerID,
        type: 'POST',
        dataType: 'json',
        success: function (result) {
            $("#person_positoin").val(result.person_positoin);
            $("#identification").val(result.identification);
            $("#home_address").val(result.home_address);
            $("#person_phone").val(result.person_phone);
            $("#person_phone2").val(result.person_phone2);
            $("#assert_volumn").val(result.assert_volumn);
            $("#person_company").val(result.person_company);
        }});
}

/**
 * 初始化项目信息   用来填充当前投资人的信息
 */
function initPro() {
    customerID = $("#customerid").val();
    PROJECTID = parent.getCurrentID();
    if (PROJECTID != null){
        $.ajax({
            url: "/project/findProjecatInvestByProId?proId=" +PROJECTID+"&cuid="+customerID,
            type: 'POST',
            success: function (result) {
                if (result !== ""){
                    $("#invest_date").val(result.invest_date);
                    $("#invest_amount").val(result.invest_amount);
                    $("#invest_fee").val(result.invest_fee);
                    $("#collect").val(result.collect);
                    $("#cumemo").val(result.memo);
                }else {
                    $("#invest_date").val("");
                    $("#invest_amount").val("");
                    $("#invest_fee").val("");
                    $("#collect").val("");
                    $("#cumemo").val("");
                }
            }});
    }
}

function initUser1(){
    var userName = $("#user1Name option:selected").text();
    var customerId = $("#customerid option:selected").val();
    PROJECTID = parent.getCurrentID();
    $.ajax({
        url:"/project/findProjecatInvestUser1NameByUserId?userName="+userName+"&proId="+PROJECTID+"&customerId="+customerId,
        success: function (result) {
            $("#user_fee1").val(result.user_fee1);
            $("#user_fee2").val(result.user_fee2);
        }
    });
    initPro()
}

function initUser2(){
    var userName = $("#user2Name option:selected").text();
    var customerId = $("#customerid option:selected").val();
    PROJECTID = parent.getCurrentID();
    $.ajax({
        url:"/project/findProjecatInvestUser1NameByUserId?userName="+userName+"&proId="+PROJECTID+"&customerId="+customerId,
        success: function (result) {
            $("#user2_fee1").val(result.user2_fee1);
            $("#user2_fee2").val(result.user2_fee2);
        }
    });
    initPro()
}


function RecodeSave() {
    $.ajax({
        type: "POST",
        url: "/project/updateProject",
        traditional:true,
        data: {

            projectId:PROJECTID,                            //项目id
            customerId : $("#customerid").val(),            //客户id

            //projectname : $("#projectName").val(),          //项目名称
            investDate : $("#invest_date").val(),           //认购日期


            investAmount : $("#invest_amount").val(),       //认购金额


            investFee : $("#invest_fee").val(),             //  认购服务费

            collect : $("#collect").val(),                  //汇总

            projectMemo : $("#cumemo").val(),               //理财师备注 将{#cumemo}的值给到project_invest 表里的memo字段

            //------------------客户信息------------------------------------------------------------------

            identification : $("#identification").val(),    //身份证号

            personPhone : $("#person_phone").val(),         //联系电话
            personPhone2 : $("#person_phone2").val(),       //其他联系方式

            homeAddress : $("#home_address").val(),         //家庭地址

            assertVolumn : $("#assert_volumn").val(),       //可投资等级

            personCompany : $("#person_company").val(),     //所在公司

            personPositoin : $("#person_positoin").val(),   //职务

            user1name : $("#user1Name option:selected").text(),              //理财师1姓名

            user2name : $("#user2Name option:selected").text(),              //理财师2姓名

            userFee1 : $("#user_fee1").val(),               //前端费用
            userFee2 : $("#user_fee2").val(),               //后端费用
            user2Fee1 : $("#user2_fee1").val(),             // 前端费用
            user2Fee2 : $("#user2_fee2").val(),             // 后端费用
            // cumemo : $("#cumemo").val()
        },
        dataType: "json",
        success: function (result) {
            if (result) {
                layer.msg('保存成功', {
                    icon: 1,
                    time: 800
                }, function(){
                    parent.getRecodeTableData();
                    TaskCancel();
                });
            } else {
                alert("保存失败！！！")
            }
        }
    })
}
function TaskCancel() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}




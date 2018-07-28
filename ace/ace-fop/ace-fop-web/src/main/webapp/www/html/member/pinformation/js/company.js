var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    $("#establishDate").datetimepicker({
        format: "yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
        startView: 2,
        minView: 2
    });
    $("#birthDate").datetimepicker({
        format: "yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
        startView: 2,
        minView: 2
    });
    $("#recruitmentDate").datetimepicker({
        format: "yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
        startView: 2,
        minView: 2
    });

    $("#dzz_establishDate").datetimepicker({
        format: "yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
        startView: 2,
        minView: 2
    });
    $("#gh_establishDate").datetimepicker({
        format: "yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
        startView: 2,
        minView: 2
    });

    /**
     * 初始化查询企业信息
     */
    var companyId = "";
    var personId = "";
    $.ajax({
        url: "/fop/www/selectCompanyInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                // if(result.data.status == '2'){
                //     layer.alert("已经审核过的信息不能修改！", {
                //         icon: 1,
                //         skin: 'myskin',
                //         offset:'400px'
                //     });
                //     $('input,select,textarea,button').attr('readonly',true);
                //     $('input,select,textarea,button').attr('disabled',true);
                // }


                $scope.companyInfo = dataFormat(result.data, 'establishDate');
                $scope.person = dataFormat(result.data.person, 'birthDate');
                var clistObj= result.data.clist;
                for(var i=0; i< clistObj.length; i++){
                    if(clistObj[i].itemCode == "1"){ //安排下岗职工再就业
                        $scope.work = clistObj[i];
                    }
                    if(clistObj[i].itemCode == "2"){ //助学兴教
                        $scope.edu = clistObj[i];
                    }
                    if(clistObj[i].itemCode == "3"){//帮困扶贫
                        $scope.poverty = clistObj[i];
                    }
                    if(clistObj[i].itemCode == "4"){
                        $scope.others = clistObj[i];
                    }
                }
                var orgObj = result.data.olist;
                for(var i=0; i<orgObj.length; i++){
                    if(orgObj[i].orgType == "1"){ //党组织
                        $scope.dang = orgObj[i];
                    }
                    if(orgObj[i].orgType == "2"){//工会组织
                        $scope.gonghui = orgObj[i];
                    }
                }
                companyId = result.data.id;
                personId = result.data.personId;

                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
                });
            }
        },
        error:function(){
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin',
                offset:'400px'
            });
        }
    });
    /**
     * 查询民族信息
     */
    $.post("/portal/dict/findListByCategoryId.do?categoryId=09",{},function(result){
        console.log(result);
        $scope.nationData = result;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    });

    /**
     * 查询学历
     */
    $.post("/portal/dict/findListByCategoryId.do?categoryId=10",{},function(result){
        console.log(result);
        $scope.education = result;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    });
    /**
     * 完善企业信息
     */
    $scope.insertInformation = function(){
        /*基本信息*/
        var fullName = $("input[name='fullName']").val();
        var realName = $("input[name='realName']").val();
        var companyType = $("#companyType option:checked").val();
        var establishDate = $("input[name='establishDate']").val();
        var crewSize = $("input[name='crewSize']").val();
        var registeredCapital = $("input[name='registeredCapital']").val();
        var fixedAssets = $("input[name='fixedAssets']").val();
        var workingCapital = $("input[name='workingCapital']").val();
        var address = $("input[name='address']").val();
        var latitude = $("#latitude").val();
        var longitude = $("#longitude").val();
        var belongTo = $("#belongTo option:checked").val();

        /*法人代表*/
        var sex = $("#sex option:checked").val();
        var birthDate = $("input[name='birthDate']").val();
        var nativePlace = $("input[name='nativePlace']").val();
        var nationality = $("#nationality option:checked").val();
        var political = $("input[name='political']").val();
        var recruitmentDate = $("input[name='recruitmentDate']").val();
        var education = $("#education option:checked").val();
        var skillJobTitle = $("input[name='skillJobTitle']").val();
        var deptPost = $("input[name='deptPost']").val();
        var societyPost = $("input[name='societyPost']").val();
        var postcode = $("input[name='postcode']").val();
        var fax = $("input[name='fax']").val();
        var email = $("input[name='email']").val();
        var resume = $("textarea[name='resume']").val();
        var achievement = $("textarea[name='achievement']").val();

        /*企业生产经营信息*/
        var ownSpace = $("input[name='ownSpace']").val();
        var tenancySpace = $("input[name='tenancySpace']").val();
        var majorVariety = $("textarea[name='majorVariety']").val();

        /*党组织信息*/
        var companyOrgType = $("input[name='companyOrgType']").val();
        var peopleNum = $("input[name='peopleNum']").val();
        var dzz_establishDate = $("input[name='dzz_establishDate']").val();
        var dutyPersonName = $("input[name='dutyPersonName']").val();    //党组织负责人
        var dutyPersonPhone = $("input[name='dutyPersonPhone']").val();  //负责人联系电话

        /*工会组织信息*/
        var gh_establishDate = $("input[name='gh_establishDate']").val();//工会组织建立时间
        var gh_dutyPersonName = $("input[name='gh_dutyPersonName']").val();//工会组织负责人
        var gh_dutyPersonPhone = $("input[name='gh_dutyPersonPhone']").val();//工会联系电话

        /*构建和谐劳动关系*/
        var laborContractNum = $("input[name='laborContractNum']").val();
        var thirdLaborRelation = $("#thirdLaborRelation option:checked").val();
        var socialInsuranceAddr = $("input[name='socialInsuranceAddr']").val();
        var socialInsuranceNum = $("input[name='socialInsuranceNum']").val();

        /*对社会公益事业做过何种贡献*/
        var xg_work = $("textarea[name='xg_work']").val();     //安排下岗职工再就业
        var education_text = $("textarea[name='education']").val(); //助学兴教
        var poverty = $("textarea[name='poverty']").val();     //帮困扶贫
        var others = $("textarea[name='others']").val();       //其他

        /*纳税情况*/
        var accTaxAmount = $("input[name='accTaxAmount']").val();
        var yearTaxAmount = $("input[name='yearTaxAmount']").val();
        var identityCard = $("input[name='identityCard']").val();

        if(fullName)
        var json = {
            "basicInfo":{
                "id":companyId,
                "personId": personId,
                "fullName": fullName,
                "companyProperty": companyType,
                "establishDate": establishDate,
                "crewSize": crewSize,
                "registeredCapital": registeredCapital,
                "fixedAssets": fixedAssets,
                "workingCapital": workingCapital,
                "address": address,
                "ownSpace":ownSpace,
                "tenancySpace":tenancySpace,
                "majorVariety":majorVariety,
                "laborContractNum":laborContractNum,
                "thirdLaborRelation":thirdLaborRelation,
                "socialInsuranceAddr":socialInsuranceAddr,
                "socialInsuranceNum":socialInsuranceNum,
                "accTaxAmount":accTaxAmount,
                "yearTaxAmount":yearTaxAmount,
                "latitude": latitude,
                "longitude": longitude,
                "realName": realName,
                "belongTo": belongTo
            },
            "legalPerson":{
                "identityCard":identityCard,
                "sex": sex,
                "birthDate": birthDate,
                "nativePlace": nativePlace,
                "nationality": nationality,
                "political": political,
                "recruitmentDate": recruitmentDate,
                "education": education,
                "skillJobTitle": skillJobTitle,
                "deptPost": deptPost,
                "societyPost":societyPost,
                "postcode": postcode,
                "fax":fax,
                "email":email,
                "resume":resume,
                "achievement":achievement,
                "realName": realName,
            },
            "org":[
                {
                    "orgType":"1",    //1表示党组织
                    "companyOrgType":companyOrgType,
                    "peopleNum":peopleNum,
                    "establishDate":dzz_establishDate,
                    "dutyPersonName":dutyPersonName,
                    "dutyPersonPhone":dutyPersonPhone
                },
                {
                    "orgType":"2",    //2代表工会组织
                    "establishDate":gh_establishDate,
                    "dutyPersonName":gh_dutyPersonName,
                    "dutyPersonPhone":gh_dutyPersonPhone
                }
            ],
            "contribute":[
                {
                    "itemCode": "1",
                    "itemName":"安排下岗职工再就业",
                    "itemValue":xg_work
                },
                {
                    "itemCode": "2",
                    "itemName":"助学兴教",
                    "itemValue":education_text
                },
                {
                    "itemCode": "3",
                    "itemName":"帮困扶贫",
                    "itemValue":poverty
                },
                {
                    "itemCode": "4",
                    "itemName":"其他",
                    "itemValue":others
                }
            ]
        }
        $.ajax({
            url: "/fop/www/insertCompanyInfo",
            type:"post",
            async:false,
            dataType:"json",
            data:{json:JSON.stringify(json)},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    layer.alert(result.info, {
                        icon: 1,
                        skin: 'myskin',
                        offset:'400px'
                    });
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    layer.alert(result.detail, {
                        icon: 5,
                        skin: 'myskin',
                        offset:'400px'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
                });
            }
        });
    }
});
/**
 * 2、自动填写地址
 * @param latitude
 */
function latitude(latitude) {
    $("#latitude").val(latitude);
}
function longitude(longitude) {
    $("#longitude").val(longitude);
}
function addr(addr) {
    $("#address").val(addr);
}
/** 2、 end */
function divtoggle(div, obj){
    var spread = $('#'+div);
    var up = "收起<i class='glyphicon glyphicon-chevron-up'></i>";
    var down ="展开<i class='glyphicon glyphicon-chevron-down'></i>";
    obj.innerHTML = spread.is(":hidden") ? up : down;
    spread.slideToggle();
}

function dataFormat(data, str) {
    var d = data[str];
    if (d) {
        data[str] = d.substring(0, 10);
    }
    return data
}
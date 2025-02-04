<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"  />
    <meta name="format-detection" content="telephone=no"/>
    <title>申请筹款</title>
    <script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
    <script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
    <style>
        .box{
            width: 100%;
        }
        .content{
            width: 90%;
            margin: auto;
        }
        h3{
            text-align: center;
            color: #333333;
            font-size: 0.45rem;
            font-weight: bold;
        }
        p{
            text-indent: 2em;
            color: #333333;
            font-size: 0.3rem;
        }
        .title{
            color: #333333;
            font-size: 0.4rem;
            font-weight: bold;
        }
        .confirm{
            width: 3rem;
            height: 0.8rem;
            border: none;
            font-size: 0.4rem;
            font-weight: bold;
        }
        .active{
            background: #EA4436;
            color: #FFFFFF;
        }
        .unactive{
            background:rgba(234,68,54,0.1);
            color: #999999;
        }
        .opt{
            font-size: 0.35rem;
            color: #333333;
            font-weight: bold;
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $("#checkbox").click(function(){
                if($(this).prop("checked")){
                    $("#confirm").removeClass("unactive").addClass("active");
                    $("#confirm").attr("disabled",false);
                }else{
                    $("#confirm").removeClass("active").addClass("unactive");
                    $("#confirm").attr("disabled",true);
                }
            })
        });

    </script>
</head>
<body>
    <div class="box">
        <div class="content">
            <h3>常德市慈善总会“救急难”项目实施办法</h3>
            <p><span class="title">第一条</span>  为使“救急难”项目管理规范、公正、透明，为社会弱势群体解决燃眉之急发挥积极作用，根据《中华人民共和国慈善法》、《慈善组织公开募捐管理办法》（民政部令第59号）和《常德市慈善总会章程》，制定本实施办法。</p>
            <p><span class="title">第二条  求助对象范围</span></p>
            <p>一、求助对象原则上限定于本市辖区内突患重特大疾病和突遇意外变故（如火灾、事故等），自身无力承担巨额医疗和重建费用的低保户、建档立卡贫困户家庭和孤儿；以及一般家庭中突患重特大疾病和突遇意外变故（如火灾、事故等）的18岁以下在校就读学生。</p>
            <p>二、下列情况不予发布求助信息：</p>
            <p>1、服刑人员、吸毒人员；</p>
            <p>2、由他方承担费用或赔偿的；</p>
            <p>3、赡养人、抚养人或扶养人有经济实力的；</p>
            <p>4、在本平台发布过求助信息的；</p>
            <p>5、拒绝市慈善总会办公室调查，隐瞒或不提供家庭真实收入、出具虚假证明的。</p>
            <p><span class="title">第三条  求助申请与审核</span></p>
            <p>一、求助对象在“常德慈善”微信平台进行申请，填写基本信息，并准备以下材料：</p>
            <p>1、求助申请书；</p>
            <p>2、求助对象户口簿、居民身份证、低保证（贫困证明）原件及复印件；</p>
            <p>3、病患对象需提交县级以上医院出具的疾病诊断证明书，医疗费用发票（未结帐时预交款收据）及其他有关疾病的病历资料，卫生、医保部门提供的医疗费用报销情况证明以及政府其他医疗救助和社会互助帮困的情况证明等原件及复印件；</p>
            <p>4、重残对象的残疾证；</p>
            <p>5、家庭突发变故的相关证明材料；</p>
            <p>6、其它认为需要提供的依据。</p>
            <p>二、市慈善总会办公室受理申请后，在5个工作日内进行调查审核，对符合求助条件的，整理好相关资料，以便发布求助信息，对不符合求助条件的，明确告知求助人，并说明理由。</p>
            <p><span class="title">第四条  发布求助信息筹款</span></p>
            <p>一、市慈善总会办公室负责对符合条件的求助对象进行审核后，在3个工作日内提出筹款意见并报本会执行会长和秘书长审批，在“常德慈善”官方微信平台发布筹款信息。</p>
            <p>二、筹款额度最低5万元，最高不超过50万元。</p>
            <p>三、筹款时间最长不超过15天。</p>
            <p>四、求助对象在筹款期间接受其他渠道大额捐赠或意外身故的，市慈善办重新审核筹款目标，在平台予以公布和调整；实际筹款已超过目标的，停止筹款。</p>
            <p>五、市慈善总会办公室对发布信息的真实性负责。</p>
            <p><span class="title">第五条  资金监管与信息公开</span></p>
            <p>一、市慈善办分别为求助对象在总会帐户下设置筹款子帐户，对筹措资金统一监管。</p>
            <p>二、筹款期满或筹款额度已满结束筹款活动时，本会及时向求助对象拨付所筹款项；对于筹款期间急需用款的，本会办公室根据实际情况及已筹款数额予以办理。</p>
            <p>三、所筹款项出现结余时（求助对象在筹款期间接受其他渠道大额捐赠或意外身故），余额由本会支配，用于慈善救助。</p>
            <P>四、筹款项目结束后，本会办公室在常德慈善官网和微信平台上进行公示，接受社会监督。</P>
            <p><span class="title">第六条  附则</span></p>
            <p>一、本会承诺：不向求助对象收取与此项目相关的任何费用，并对每名公开发布求助信息的对象实施救助。</p>
            <p>二、本办法由市慈善总会办公室负责解释。</p>
        </div>
        <div class="opt">
            <input type="checkbox" name="checkbox" id="checkbox"/>我已经阅读实施办法<br>
        </div>
        <div style="width: 90%;margin: 0.2rem auto;text-align: center;">
            <button id="confirm" class="confirm unactive" disabled onclick="window.location.href='apply.jsp'">确认</button>
        </div>
    </div>
</body>
</html>
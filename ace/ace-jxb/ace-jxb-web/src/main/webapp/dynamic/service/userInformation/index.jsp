<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>

    <jsp:include page="../../common/header.jsp"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/city-select.css">
    <link rel="stylesheet" href="${portalPath}/content/common/simditor/styles/simditor.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">




</head>

<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

                                        <div class="portlet light">

                                            <div class="portlet-body">
                                                <div class="form-panel">
                                                    <!--具体界面元素开始-->
                                                    <form class="form-horizontal" id="fm-edit" role="form">

                                                    </form>
                                                </div>

                                            </div>
                                        </div>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<div class="modal fade bs-example-modal-lg" id="modal-doc" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content doc-content">
            <div class="content">


                <h3>咨询师入驻协议</h3>
                <p>心阳光联盟对入驻的咨询师有着严格的职业要求及审核标准，请在开始申请入驻之前，仔细阅读下方的协议说明</p>
                <h4>一、提示条款</h4>
                <p>
                    在接受本协议之前，应当认真阅读本协议。请咨询师务必审慎阅读、充分理解各条款内容，特别是免除或限制责任的条款、法律适用、和争议解决条款。免除或限制责任的条款将以粗体下划线标识，应重点阅读。如咨询师对协议有任何疑问，请不要进行后续操作，可及时联系心阳光联盟。
                    心理咨询平台：指心阳光联盟的PC网站、微信公众号微网页的合称。
                    心理咨询师：指同意并承诺遵守本协议规定使用心阳光联盟的心理咨询平台向心阳光联盟平台用户提供心理咨询服务的拥有相应资质的咨询师。
                    心理咨询预约服务：指心理咨询师通过本协议服务平台发布咨询信息，向服务平台用户提供心理咨询和科学心理学传播等。
                </p>
                <h4>二、定义</h4>
                <p>心阳光联盟：指常德近心帮心理健康咨询有限公司所运营的心阳光联盟网站、微信公众号微网页。</p>
                <p>咨询师：指同意并承诺遵守本协议规定向网站用户提供心理咨询服务的拥有相应资质的咨询师。</p>
                <p>心理咨询预约网站：指心阳光联盟的PC网站、微信公众号微网页的合称。</p>
                <p>心理咨询预约服务：指咨询师通过本协议网站发布咨询信息，向网站用户提供心理咨询和科学心理学传播等。</p>
                <p>
                    心理咨询：指咨询师与用户双方通过面谈、网络视频、语音和图文为媒介，共同探讨引起心理问题的原因，分析问题症结，进而帮助用户寻求摆脱困境解决问题的条件和对策，以便恢复心理平衡、提高对环境的适应能力，增进身心健康。</p>
                <h4>三、协议生效和适用范围</h4>
                <p>（一）咨询师通过网络页面点击确认或以其他方式选择接受本协议，表示咨询师同意接受本协议的全部内容。</p>
                <p>（二）咨询师签署或在线接受本协议并不导致本协议立即生效，经过心阳光联盟审核通过并向咨询师发出服务开通通知，咨询师首次登录时本协议成立，并在咨询师和心阳光联盟之间产生法律效力。</p>
                <h4>四、入驻方式</h4>
                <p>（一）咨询师同意并确认其可以通过直接入驻心阳光联盟网站的方式为用户提供咨询服务</p>
                <h4>五、协议内容</h4>
                <p>
                    （一）本协议内容包括协议正文以及所有本网站已经发布的或将来可能发布的各类规则、通知、公告等。所有规则为协议不可分割的组成部分，与协议正文具有同等法律效力。心阳光联盟将就网站制定特别规则（下称"特别规则"），如特别规则与网站其他规则存在冲突，则以特别规则为准。</p>
                <p>
                    （二）咨询师在使用本网站提供的各项服务的同时，承诺接受并遵守各项相关规则的规定。心阳光联盟有权根据需要不时地制定、修改本协议和/或各类规则，如有任何变更，心阳光联盟将在网站、微信公众号微网页等以公示的方式进行公告，不再单独通知每个咨询师。变更后的协议和规则一经公布即自动生效，成为本协议的一部分。如咨询师不同意相关变更，应当立即以书面通知的方式终止本协议；任何修订和新规则一经在网站上公布即自动生效，成为本协议的一部分。如咨询师登录或继续使用服务的，则视为对修改后的协议和规则不持异议并同意遵守。除另行明确声明外，任何使服务范围扩大或功能增强的新内容均受本协议约束。</p>
                <p>
                    （三）心阳光联盟保留在任何时候自行决定对服务及其相关功能、应用软件变更、升级的权利。心阳光联盟进一步保留在服务中开发新的模块、功能和软件或其它语种服务的权利。上述所有新的模块、功能、软件服务的提供，除非心阳光联盟另有说明，否则仍适用本协议。服务随时可能因心阳光联盟的单方判断而被增加或修改，或因定期、不定期的维护而暂缓提供，咨询师将会得到相应的变更通知。咨询师在此同意心阳光联盟在任何情况下都无需向其或第三方在使用服务时对其在传输或联络中的迟延、不准确、错误或疏漏及因此而致使的损害负责。</p>
                <h4>六、入驻资格</h4>
                <p>(一) 咨询师确认，在完成注册程序或以心阳光联盟网站允许的方式实际使用服务或产品时，您应当具备至少以下资格：</p>
                <p>1、咨询师是具备完全民事权利能力和所从事的民事行为相适应的自然人。</p>
                <p>2、咨询师欲使用本协议下服务，必须向心阳光联盟提交其申请服务时提供信息的相关证明。具体证明信息内容包括</p>
                <p>1)真实姓名</p>
                <p>2)联系方式（电话、地址）</p>
                <p>3)本人形象照</p>
                <p>4)身份证号（正反面、手持身份证）</p>
                <p>5)从业资格证证号</p>
                <p>6)从业资格证书</p>
                <p>7)个案时长</p>
                <p>8)个案人数</p>
                <p>9)其他心阳光联盟认为需查验的信息。</p>
                <p>
                    咨询师注册应同意：确保提供真实、正确、最新及完整的资料。若提供任何错误、不实、过时或不完整或具误导性的资料，或者本网站有理由怀疑前述资料为错误、不实、过时或不完整或具误导性的，本网站有权终止您的帐号，并拒绝您于现在和未来使用本网站所提供服务之全部或任何部分，并有权要求咨询师赔偿由此造成的损失。</p>
                <p>(二) 若咨询师不具备前述主体资格，则咨询师应承担由此产生的一切后果，且心阳光联盟有权终止咨询师的咨询账号，由此造成的损失，心阳光联盟有权向咨询师索赔。</p>
                <p>(三) 协议期内，上述资格信息或相关证明文件发生任何变更，咨询师应及时书面通知心阳光联盟，如上述资格信息或相关证明文件使得咨询师不再具备履行本协议的情形出现时，心阳光联盟有权立即终止或中止本协议。
                    咨询师同意为未及时的通知或更新其信息承担全部责任，咨询师保证其以任何形式向心阳光联盟提供的全部证明文件真实、准确且不存在超过时效问题（即保证所有证明文件在整个合同履行期间都处于有效期内）如因上述原因发生纠纷或被相关国家主管机关处罚，咨询师应当独立承担全部责任，如给心阳光联盟（包括其合作伙伴、代理人、职员等）造成损失的，咨询师同意赔偿其全部损失。</p>
                <h4>七、账户说明</h4>
                <p>(一)
                    当您按照本网站的流程完成全部注册程序并签署本协议后，心阳光联盟会向咨询师提供唯一编号的咨询账户。咨询师可以设置或修改账户密码。咨询师应对自己的咨询账户和密码的安全负责。除非有法律规定或司法裁定，且征得心阳光联盟的同意，否则，账户不得以任何方式转让、赠与或继承。如果发现任何人不当使用咨询师账户或有任何其他可能危及咨询师账户安全的情形时，咨询师应当立即以有效方式通知心阳光联盟，要求心阳光联盟暂停相关服务。咨询师向心阳光联盟提供所需注册资料、阅读并同意本协议、完成全部注册程序后，即成为心阳光联盟注册咨询师。在后续的使用过程中，咨询师应当及时更新自己的资料，以使之真实、及时，完整和准确。</p>
                <p>(二)
                    心阳光联盟网站只允许每位咨询师使用一个账户。如有证据证明或本网站有理由相信咨询师存在不当注册或不当使用多个账户的情形，本网站可采取注销账户并拒绝提供服务措施，如给本网站及其相关方造成损失的，咨询师还应承担赔偿责任。</p>
                <h4>八、入驻规则</h4>
                <h5>（一）合法性</h5>
                <p>
                    咨询师在使用本网站服务时，务必遵守中华人民共和国相关法律法规及本网站的各项规则和要求，不违背社会公共利益或公共道德，不损害他人的合法权益，不违反本协议及相关规则，不得把本网站提供的服务用于任何违法或不正当或不道德的活动，包括但不限于以下行为：</p>
                <p>
                    1.违反宪法或法律法规规定的；危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；损害国家荣誉和利益的，损害公共利益的；煽动民族仇恨、民族歧视，破坏民族团结的；破坏国家宗教政策，宣扬邪教和封建迷信的；散布谣言，扰乱社会秩序，破坏社会稳定的；散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；侮辱或者诽谤他人，侵害他人合法权益的；含有法律、行政法规禁止的其他内容的</p>
                <p>2.以虚构或歪曲事实的方式不当评价其他心阳光联盟的咨询师，采取不正当方式制造或提高自身的评价度及信用度，采取不正当方式制造或提高（降低）其他壹咨询师的评价度及信用度。</p>
                <p>3.以任何非法目的而是用网络服务系统。</p>
                <p>4.其他危害计算机信息网络安全的行为。</p>
                <h5>（二）排名颁奖</h5>
                <p>咨询师同意：心阳光联盟可以根据心理咨询预约PC网站、手机网站及APP所开展的业务，对咨询师回答问题质量的评价以及其他专业、学术指标将咨询师与其他咨询师进行排名。
                    咨询师同意：心阳光联盟可以设定颁奖活动规则，对排名靠前的咨询师进行颁奖。咨询师亦同意遵守网站的颁奖活动规则和奖项设置规则，理解并接受不是每一个咨询师都符合获奖资格。</p>
                <h5>(三) 禁止私自交易</h5>
                <p>
                    咨询师不得与用户私下交易。私下交易包括但不限于：咨询师与用户通过网站互相认识/曾经发生过交易，但是，双方之后约定不通过心阳光联盟网站支付/收取咨询费，由咨询师直接收取用户咨询费/咨询师指定其它第三方机构收取用户咨询费而产生的咨询服务交易。</p>
                <p>咨询师同意私自交易属于严重违规情形，心阳光联盟网站如有证据证明或本网站有理由相信咨询师存在私下交易的情形，本网站有权直接注销该咨询师的账户，如给本网站及其相关方造成损失的，咨询师还应承担赔偿责任。
                    若咨询师与用户私下交易并发生纠纷的，请自行解决双方纠纷，与心阳光联盟网站无关，心阳光联盟没有收取咨询费亦无协助解决的义务。</p>
                <h4>九、提供服务</h4>
                <p>（一）咨询师在从事心理咨询时，应遵守职业道德，遵守国家有关的法律法规，在咨询工作中建立并执行严格的道德准则。</p>
                <p>（二）咨询师应遵从心阳光联盟的安排，本着负责的态度尽心尽力向心阳光联盟指定的用户提供优质的咨询服务，遵守预约时间，如有特殊情况提前告知用户。</p>
                <p>（三）咨询师在从事咨询服务过程中，不得利用用户对自己的信任和依赖谋取私权，不得与用户私下交易。</p>
                <p>（四）咨询师在心阳光联盟网站上仅从事心理咨询活动，不得行使处方权，不得对用户进行心理治疗或精神障碍的诊断、治疗等。如发现接受咨询的用户可能患有精神障碍的，应当建议到符合规定的医疗机构就诊。</p>
                <p>（五）在咨询过程中，用户因自身原因而突发疾病或意外死亡，或因自身原因造成情绪失控而发生自伤、自残、自杀等后果，咨询师尽可能采取措施控制事态恶化，必要时与相关部门取得联系。</p>
                <h4>十、隐私保护</h4>
                <p>（一）网站将采取合理的安全手段保护您已存储的个人信息，除下述信息披露条款外，在未得到您许可之前，网站不会将您的任何个人信息提供给任何无关的第三方（包括公司个人）。</p>
                <p>1.经您事先同意，向第三方披露</p>
                <p>2.只有通过使用用户的保密信息，才能向用户提供所要求的产品服务</p>
                <p>3.根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；</p>
                <p>4.如您出现违反中国有关法律、法规或者网站法律声明、服务协议或相关规则的情况，需要向第三方披露；</p>
                <p>5.维护网站的合法权益；</p>
                <p>6.根据本协议相关规定或者网站认为必要的其他情况</p>
                <p>（二）网站亦不允许任何第三方以任何手段收集、编辑、出售或者无偿传播您的个人信息。</p>
                <h4>十一、权利声明</h4>
                <p>
                    （一）心阳光联盟所属公司拥有本网站内的所有产品、技术、程序、资料、信息内容（包括但不限于文字、图片、图像、照片、音频、视频、图表、色彩、版面设计、电子文档）的所有权利（包括但不限于版权、商标权、专利权、商业秘密及其他所有相关权利）。未经权利人许可，任何人不得擅自使用。</p>
                <p>
                    （二）咨询师接受本协议即视为您主动将您在本网站发表的任何形式的信息的著作权，包括但不限于复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及《著作权法》规定的由著作权人享有的其他著作财产权利无偿独家转让给本网站所有，若发生第三人侵权，本网站有权单独提起诉讼并获得全部赔偿。</p>
                <p>
                    （三）咨询师同意本网站后服务市场的全部运营数据，包括但不限于用户信息、用户列表、用户关系、用户的使用数据、交易数据、订单数据等，其全部的权利均归属于本网站。未经本网站事先书面同意，服务商均不得利用用户列表及用户关系、向用户发送信息，不得为任何目的擅自保存、备份、泄露、使用或授权他人使用前述运营数据。本协议自然终止或提前解除后，咨询师无权要求备份前述运营数据。</p>
                <p>（四）咨询师通过注册所得的本网站账户（即本网站用户ID）所有权归属于本网站公司，咨询师仅对该账户拥有使用权。如您以任何方式转让的，本网站有权追究您的违约责任，由此产生的一切责任由您自行承担。</p>
                <p>（五）对不遵守本协议或其他违法、未经许可或者恶意使用本网站内容者，本网站所属公司保留追究其法律责任的权利。</p>
                <h4> 十二、第三人主张权利</h4>
                <p>因网站的特殊性，心阳光联盟没有义务对所有咨询师的服务行为以及与服务有关的其它事项进行事先审查，但如存在下列情况：</p>
                <p>1.第三方通知心阳光联盟，认为某个具体咨询师或具体服务事项可能存在重大问题。</p>
                <p>2.咨询师或第三方向心阳光联盟告知网站上有违法或不当行为的；
                    心阳光联盟以普通非专业人员的知识水平标准对相关内容进行判别，可以认为这些内容或行为具有违法或不当性质的，心阳光联盟有权根据不同情况选择删除相关信息或停止对该咨询师提供网站，并追究相关法律责任。</p>
                <h4>十三、反贿赂</h4>
                <p>咨询师不得向心阳光联盟及其关联企业之员工、顾问提供任何形式的不正当利益。如有，无论后是否实际获得利益，本网站均有权立即解除本合同，并由咨询师按提供任何形式的不正当利益的总金额向本网站赔偿。</p>
                <h4>十四、保密</h4>
                <p>
                    （一）咨询师保证在使用本网站过程中所获悉的属于本网站及他方的且无法自公开渠道获得的商业秘密、专有信息、双方共有的客户信息及其他保密性质的所有信息均为保密信息（包括但不限于商业秘密、公司计划、运营活动、财务信息、技术信息、经营信息及其他商业秘密）。未经该信息的原享有方同意，不应以任何形式向第三方披露或泄露保密信息，否则，应承担赔偿损失责任。</p>
                <p>（二）不论本服务协议是否变更、解除或终止，本条款均由法律效力。</p>
                <h4>十五、免费及有限责任</h4>
                <p>
                    （一）心阳光联盟不保证网络服务的及时性、安全性、准确性。不论在何种情况下，均不对由于Internet连接故障，电脑，通讯或其他系统的故障，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，不可抗力，战争，政府行为，国际、国内法院的命令或第三方的不作为而造成的不能服务或延迟服务承担责任。请咨询师务必及时保存自己的需求，否则因网络服务中断，需要维修、检修、维护等导致损失的，本网站不承担损失赔偿的责任。</p>
                <p>
                    （二）在任何情况下，对于因使用心阳光联盟网站内容或无法进入本网站而导致的任何直接的、间接的、附带的、给第三人造成的损失（包括但不限于利润损失、信息数据丢失、财产毁坏等损失），心阳光联盟均无须承担法律责任，不论是采用合同之诉、侵权之诉或其他诉讼理由。</p>
                <p>（三）未经授权的存取或修改数或数据的传输，本网站不承担赔偿因此造成损失的责任。</p>
                <p>（四）第三方通过服务所作的陈述或行为，本网站不承担赔偿因此造成损失的责任。</p>
                <p>（五）其他与服务相关事件，包括疏忽等所造成的损害，本网站不承担赔偿因此造成损失的责任。</p>
                <h4>十六、违约责任</h4>
                <p>（一）如咨询师在约定好的咨询时间内迟到，应补齐原定咨询时间，并退还迟到时段所对应的咨询费用。</p>
                <p>
                    （二）咨询师同意赔偿由于使用服务（包括但不限于将咨询师资料展示在网站上）或违反本协议而给心阳光联盟造成的任何损失（包括但不限于诉讼费、律师费、公证费、差旅费等）。咨询师同意心阳光联盟不对任何其张贴的资料，包括诽谤性的，攻击性的或非法的资料，承担任何责任；由于此类材料对其它用户造成的损失由咨询师自行全部承担。</p>
                <p>
                    （三）除本协议及网站规则另有约定之外，如一方发生违约行为，守约方可以书面通知方式要求违约方在指定的时限内停止违约行为，并就违约行为造成的损失进行索赔，如违约方未能按时停止违约行为，则守约方有权立即终止本协议。</p>
                <p>（四）如咨询师的行为使心阳光联盟及所属公司遭受第三人主张权利，心阳光联盟及所属公司可在对第三人承担金钱给付义务后就全部损失向您追偿。</p>
                <p>（五）心阳光联盟因违约或侵权依法对咨询师赔偿损失的，赔偿额以心阳光联盟就该咨询师最终实际收取用户的费用总额为限。</p>
                <h4>十七、协议终止</h4>
                <p>（一）通知解除：协议任何一方均可以提前三天通知终止本协议。</p>
                <p>（二）心阳光联盟单方解除权：</p>
                <p>
                    1.如咨询师违反心阳光联盟网的任何规则或本协议中的任何承诺或保证，包括但不限于本协议项下的任何约定，心阳光联盟都有权立刻终止本协议，注销咨询师的账号，因注销账号造成咨询师损失的，由咨询师自行承担，网站不承担赔偿损失责任。</p>
                <p>
                    2.若咨询师屡次违反本协议或相关规定，经心阳光联盟提醒仍不改正的；或因咨询师的言行对心阳光联盟造成人民币1000元以上损失的，心阳光联盟有权终止与咨询师的协议，注销咨询师的账号，因注销账号造成咨询师损失的，由咨询师自行承担，网站不承担赔偿损失责任。</p>
                <p>
                    （三）若双方无法达成一致，心阳光联盟无权强制咨询师使用心理咨询网站。咨询师可根据自己意愿，随时、随地终止心理咨询网站的使用。咨询师完全终止心理咨询网站使用后一年或咨询师通过电子邮件形式告知心阳光联盟，本协议将自动终止。在协议终止前、账号注销前，咨询师均可随时、随地自行再次开通心理咨询网站的使用。本协议终止后咨询师的心阳光联盟咨询账号将被自动注销，之后心阳光联盟无权使用、展示您的肖像、资料等信息。</p>
                <p>（四）本协议规定的其他协议终止条件发生或实现，导致本协议终止。</p>
                <p>（五）协议终止后有关事项的处理：</p>
                <p>1.协议终止后，心阳光联盟没有义务为咨询师保留账户中或与之相关的任何信息，或转发任何未曾阅读或发送的信息给咨询师或第三方。亦不就终止协议而对咨询师或任何第三者承担任何责任。</p>
                <p>2.无论本协议因何原因终止，在协议终止前的行为所导致的任何赔偿和责任，咨询师必须完全且独立地承担。</p>
                <p>3.协议终止后，心阳光联盟有权保留该咨询师的注册数据及以前的服务行为记录。如咨询师在协议终止前在网站上存在违法行为或违反协议的行为，心阳光联盟仍可行使本协议所规定的权利。</p>
                <h4>十八、协议期限</h4>
                <p>本协议经咨询师在线接受且经过心阳光联盟审核通过后（或书面签署后）即告生效，除非本协议规定的终止条件发生，本协议将持续有效。双方另有约定的除外。</p>
                <h4>十九、有效通知</h4>
                <p>
                    （一）咨询师应当准确填写并及时更新提供给心阳光联盟的联系电话、联系地址等联系方式，保证联系方式的有效性及安全性，以便心阳光联盟及其他用户可以通过上述联系方式与自己进行有效联系。如因通过上述联系方式无法与咨询师取得联系，导致本网站产生任何损失或增加费用的，应由咨询师完全独立承担。</p>
                <p>（二）本协议下所规定的通知有权要求以书面形式通过以下邮址递交收悉，通知的到达以心阳光联盟收悉为准。</p>
                <p>心阳光联盟地址：常德市武陵区沙港东路荷花社区三楼</p>
                <p>邮编：415100 ； 收件人：心阳光联盟心理咨询组 （收）</p>
                <p>心阳光联盟可自jinxinbang123@163.com邮址向咨询师在心阳光联盟网注册时提供的电子邮件地址发出通知。通知的送达以邮件发出为准。</p>
                <h4>二十、争议解决</h4>
                <p>（一）本协议之解释与适用，以及与本协议有关的争议，均应依照中华人民共和国法律予以处理，并以常德市武陵区人民法院为管辖法院。</p>
                <p>（二）如本协议的任何条款被视作无效或无法执行，则上述条款可被分离，其余部份则仍具有法律效力。</p>
                <p>（三）心阳光联盟于咨询师过失或违约时放弃本协议规定的权利的，不得视为其对咨询师的其他或以后同类之过失或违约行为弃权。</p>
                <h5>本协议于2018年8月20日发布并执行。</h5>
            </div>
        </div>
    </div>
</div>

</body>


<style>
    html, body {
        height: 100%;
    }

    .hc-checkbox {
        width: 11em;
    }

    .error_message {
        color: red;
    }

    .idCardBoxs > div, .certificateBoxs > div {
        width: 192px;
        float: left;
        margin-right: 30px;
        margin-bottom: 15px;
    }

    .idCardBoxs > div > p, .certificateBoxs > div > p {
        text-align: center;
        line-height: 1.05rem;
    }

    .idCardBox, .certificateBox {
        height: 128px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .idCardBox img, .certificateBox img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .headimgbox {
        width: 100px;
        height: 100px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .headimgbox img{
        width: 100%;
        height: 100%;
    }

    .input_style {
        border-bottom: 1px solid #c2cad8 !important;
    }

    .AreaS {
        background-color: #05920a !important;
        color: #fff !important;
    }

    .portlet-body {
        background-color: #fff !important;
        padding: 14px 98px 45px 20px !important;
    }

    .doc-content {
        height: 700px;
        padding: 50px;
    }

    .doc-content .content {
        height: 600px;
        overflow: auto;
    }

   
}

</style>


<script id="tpl-fm" type="text/template">

    <div class="form-group ">
        <label class="col-md-2 control-label">
            姓名
        </label>
        <div class="col-md-4">
            <input type="text" name="name" value="\${data.name}" class="form-control" placeholder="" \${data.regAuditRst==1?'readonly':''}>
        </div>
        <div class="col-md-6">

        </div>
    </div>


    <div class="form-group">
        <label class="col-md-2 control-label">
            性别
        </label>
        <div class="col-md-10">
            <div class="row">
                <div style="float:left;width:60px;padding:2px">
                    <input type="radio" \${data.sex==1?'checked':''}  id="sex1" \${data.regAuditRst==1?'readonly':''}
                           name="sex" value="1"
                           >
                    <label for="sex1">
                        男</label>
                </div>

                <div style="float:left;width:60px;padding:2px">
                    <input type="radio" \${data.sex==2?'checked':''} id="sex2" \${data.regAuditRst==1?'readonly':''}
                           name="sex"
                           value="2"
                          >
                    <label for="sex2">
                       女
                    </label>
                </div>

            </div>
        </div>
    </div>


    <div class="form-group ">
        <label class="col-md-2 control-label">所在城市
        </label>
        <div class="col-md-10 city-select">
            <span id="cityCode"></span>
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label">个人简介
        </label>
        <div class="col-md-10">
        <textarea class="form-control"  name="profile" rows="5">\${data.profile}</textarea>
        </div>
    </div>


    <div class="form-group ">
        <label class="col-md-2 control-label">手机号码
        </label>
        <div class="col-md-4">
            <input type="text" class="form-control" name="mobile" value="\${data.mobile}" readonly="readonly">
        </div>
        <div class="col-md-6">
            <div class="input-icon right">


            </div>
        </div>
    </div>


    <div class="form-group ">
        <label class="control-label col-md-2">形象照

        </label>
        <div class="col-md-10">
            <div>
                <div class="headimgbox"  id="headimg">
                    <img style="max-width:400px;cursor:pointer;background-color: "
                         id="imagePhotoUrl"
                         data-toggle="modal"
                         data-xsize="400" data-ysize="400"
                         data-cover="imagePhotoUrl"
                         data-target="#img-uploader"
                         src="{@if data.imagePhotoUrl} \${data.imagePhotoUrl} {@else}${pageContext.request.contextPath}/content/service/information/img/head.png?v=${cfg.version}{@/if}">
                    <input name="imagePhotoUrl" type="hidden" value="\${data.imagePhotoUrl}">
                </div>
            </div>
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label">身份证号码

        </label>
        <div class="col-md-4">
            <input \${data.regAuditRst==1?'readonly':''} type="text" class="form-control" placeholder="" name="idCard" value="\${data.idCard}">

        </div>
        <div class="col-md-6">
            <div class="input-icon right">


            </div>
        </div>
    </div>


    <div class="form-group ">
        <label class="control-label col-md-2">身份证

        </label>
        <div class="col-md-10 idCardBoxs">
            <div>
                <div class="idCardBox">
                    <input name="idCardImgUrl" type="hidden" value="\${data.idCardImgUrl}">
                    <img
                         id="idCardImgUrl" style="max-height:500px;cursor:pointer"
                         data-cover="idCardImgUrl" data-toggle=\${data.regAuditRst==1?'':'modal'} data-target="#img-uploader"
                         data-xsize="400" data-ysize="300"
                         src="{@if data.idCardImgUrl!=''} \${data.idCardImgUrl} {@else}${pageContext.request.contextPath}/content/service/information/img/idcardz.png?v=${cfg.version}{@/if}">

                </div>
                <p>身份证正面</p>
            </div>

            <div>
                <div class="idCardBox">
                    <input name="idCardSideImgUrl" type="hidden" value="\${data.idCardSideImgUrl}">
                    <img
                         data-cover="idCardSideImgUrl" style="max-height:500px;cursor:pointer"
                         id="idCardSideImgUrl"
                         data-xsize="400" data-ysize="300" data-toggle=\${data.regAuditRst==1?'':'modal'} data-target="#img-uploader"
                         src="{@if data.idCardSideImgUrl!=''} \${data.idCardSideImgUrl} {@else}${pageContext.request.contextPath}/content/service/information/img/idcardf.png?v=${cfg.version}{@/if}">
                </div>
                <p>身份证反面</p>
            </div>


            <div>
                <div class="idCardBox">
                    <input name="evidenceImgUrl" type="hidden" value="\${data.evidenceImgUrl}">
                    <img
                         id="evidenceImgUrl" style="max-height:500px;cursor:pointer"
                         data-cover="evidenceImgUrl"
                         data-xsize="400" data-ysize="300"
                         data-toggle=\${data.regAuditRst==1?'':'modal'} data-target="#img-uploader"
                         src="{@if data.evidenceImgUrl!=''} \${data.evidenceImgUrl} {@else}${pageContext.request.contextPath}/content/service/information/img/idcardsc.png?v=${cfg.version}{@/if}">
                </div>
                <p>手持身份证</p>
            </div>
        </div>
    </div>


    <div class="form-group ">
        <label class="col-md-2 control-label">职业名称</label>
        <div class="col-md-10">
            <div class="row">
                <div  style="float:left;width:130px;padding:2px">
                    <input type="radio" id="radios_1" \${data.regAuditRst==1?'disabled':''} name="certification"
                           value="国家二级咨询师"  \${data.certification=='国家二级咨询师'?'checked':''}>
                    <label
                            for="radios_1"><span class="inc"></span>
                        <span class="check"></span>
                        <span class="box"></span>国家二级咨询师</label>
                </div>

                <div  style="float:left;width:130px;padding:2px">

                    <input type="radio" id="radios_2" \${data.regAuditRst==1?'disabled':''}
                                             name="certification"
                                             value="国家三级咨询师"
                                              \${data.certification=='国家三级咨询师'?'checked':''}>
                    <label
                            for="radios_2">国家三级咨询师</label>
                </div>
            </div>
        </div>
    </div>


    <div class="form-group ">
        <label class="col-md-2 control-label">从业资格证证号

        </label>
        <div class="col-md-4">
            <input type="text" class="form-control" \${data.regAuditRst==1?'readonly':''}
                   placeholder="" value="\${data.certificateNo}"
                   name="certificateNo">

        </div>
        <div class="col-md-6">
            <div class="input-icon right">


            </div>
        </div>
    </div>


    <div class="form-group ">
        <label class="control-label col-md-2">从业资格证书

        </label>
        <div class="col-md-10 certificateBoxs">
            <div>
                <div class="certificateBox">
                    <input name="certificateImgUrl" type="hidden" value="\${data.certificateImgUrl}">
                    <img style="max-height:400px;cursor:pointer"
                         id="certificateImgUrl"
                         data-toggle="\${data.regAuditRst==1?'':'modal'}"
                         data-cover="certificateImgUrl"
                         data-xsize="400" data-ysize="300"
                         data-target="#img-uploader"
                         src="{@if data.certificateImgUrl!=''} \${data.certificateImgUrl} {@else}${pageContext.request.contextPath}/content/service/information/img/certificateimg.png?v=${cfg.version}{@/if}">
                </div>
                <p>从业资格证书</p>
            </div>
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label">个案时长

        </label>
        <div class="col-md-4">
            <div class="input-icon right">
                <input type="text" class="form-control" value="\${data.duration}"
                       name="duration"
                       placeholder="一共从事了多少小时">

            </div>
        </div>
        <div class="col-md-6">
            <div class="input-icon right">
                (单位：小时）

            </div>
        </div>
    </div>
    <div class="form-group ">
        <label class="col-md-2 control-label">个案人数

        </label>
        <div class="col-md-4">
            <div class="input-icon right">
                <input type="text" class="form-control"
                       value="\${data.peopleNum}"
                       name="peopleNum"
                       placeholder="一共辅导了多少人">

            </div>
        </div>
        <div class="col-md-6">
            <div class="input-icon right">
                (单位：人)

            </div>
        </div>
    </div>
    <div class="form-group ">
        <div class="col-md-10 col-md-offset-3">
            <input class="protocol"  \${data.regAuditRst==1?'checked':''} type="checkbox"/>
            <span>
        同意
        <span class="primary-link"
              data-target="#modal-doc"
              data-toggle="modal">咨询师入驻协议</span>
        </span>
        </div>
    </div>
    <div class="form-actions">
        <div class="row">
            <div class="col-md-offset-3 col-md-7">
                <input type="hidden" name="regAuditRst" value="\${data.regAuditRst}">
                <button class="btn  green" type="submit" style="width:30%">提交</button>
            </div>
        </div>
    </div>

</script>

<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/module.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/hotkeys.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/uploader.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/simditor.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/plupload/plupload.full.min.js?v=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/upload.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/citySelect/js/citydata.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/citySelect/js/citySelect-1.0.3.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">
    <div class="portlet-body">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="tab-content" id="app">
                            <div class="tab-pane fade" :class="{'active in':type1.type==1}" id="test1">
                                <div class="col-md-offset-3 col-md-6">
                                    <div class="test1 test">
                                        <div class="title">
                                            <textarea placeholder="题目" v-model="type1.content" @input="autoHeight"
                                                      name="content" rows="1">{{type1.content}}</textarea>
                                        </div>
                                        <template v-for="(item,index) in type1.topicOptList">
                                            <div class="option" v-if="index<2">
                                                <input class="pointer" type="radio" onclick="check(this);" :checked="item.answer==1" name="type1" :value="index"/>
                                                <textarea rows="1" type="text" class="option-text option-margin"
                                                          @input="autoHeight" v-model="item.content"
                                                          :placeholder="'选项'+(index+1)">{{item.content}}</textarea>
                                            </div>
                                            <div class="option" v-else>
                                                <input class="pointer" onclick="check(this);" :checked="item.answer==1" type="radio" name="type1" :value="index"/>
                                                <textarea rows="1" type="text" @input="autoHeight" class="option-text"
                                                          v-model="item.content"
                                                          :placeholder="'选项'+(index+1)">{{item.content}}</textarea>
                                                <div class="remove pointer" @click="removeOption('type1',index)">×</div>
                                            </div>
                                        </template>
                                        <div class="tool">
                                            <ul>
                                                <li @click="addOption('type1')">添加选项</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <button @click="submit1('type1')" class="btn btn-default">提交</button>

                                </div>
                            </div>
                            <div class="tab-pane fade" :class="{'active in':type2.type==2}" id="test2">
                                <div class="col-md-offset-3 col-md-6">
                                    <div class="test2 test">
                                        <div class="title">
                                            <textarea placeholder="题目" v-model="type2.content" @input="autoHeight"
                                                      name="content" rows="1">{{type2.content}}</textarea>
                                        </div>
                                        <template v-for="(item,index) in type2.topicOptList">
                                            <div class="option" v-if="index<2">
                                                <input class="pointer" :checked="item.answer==1" type="checkbox" name="type2" :value="index"/>
                                                <textarea rows="1" type="text" class="option-text option-margin"
                                                          @input="autoHeight" v-model="item.content"
                                                          :placeholder="'选项'+(index+1)">{{item.content}}</textarea>
                                            </div>
                                            <div class="option" v-else>
                                                <input class="pointer" :checked="item.answer==1" type="checkbox" name="type2" :value="index"/>
                                                <textarea rows="1" type="text" @input="autoHeight" class="option-text"
                                                          v-model="item.content"
                                                          :placeholder="'选项'+(index+1)">{{item.content}}</textarea>
                                                <div class="remove pointer" @click="removeOption('type2',index)">×</div>
                                            </div>
                                        </template>
                                        <div class="tool">
                                            <ul>
                                                <li @click="addOption('type2')">添加选项</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <button @click="submit2('type2')" class="btn btn-default">提交</button>
                                </div>
                            </div>
                            <div class="tab-pane fade" :class="{'active in':type3.type==3}" id="test3">

                                <div class="col-md-offset-3 col-md-6">
                                    <div class="test3 test">
                                        <div class="title">
                                            <textarea placeholder="题目" v-model="type3.content" @input="autoHeight"
                                                      name="content" rows="1">{{type3.content}}</textarea>
                                        </div>
                                        <template v-for="(item,index) in type3.topicOptList">
                                            <div class="option" v-if="index<2">
                                                <input class="pointer" :checked="item.answer==1" type="radio" name="type3" :value="index"/>
                                                <div>{{item.content}}</div>
                                            </div>
                                        </template>
                                    </div>
                                    <button @click="submit1('type3')" class="btn btn-default">提交</button>
                                </div>

                            </div>
                            <div class="tab-pane fade" :class="{'active in':type4.type==4}" id="test4">
                                <div class="col-md-offset-3 col-md-6">
                                    <div class="test4 test">
                                        <div class="title">
                                            <textarea placeholder="题目" v-model="type4.content" @input="autoHeight"
                                                      name="content" rows="1">{{type4.content}}</textarea>
                                        </div>
                                    </div>
                                    <button @click="submit1('type4')" class="btn btn-default">提交</button>

                                </div>
                            </div>
                            <div class="tab-pane fade" :class="{'active in':type5.type==5}" id="test5">
                                <div class="col-md-offset-3 col-md-6">
                                    <div class="test5 test">
                                        <div class="title">
                                            <textarea placeholder="题目" v-model="type5.content" @input="autoHeight"
                                                      name="content" rows="1">{{type5.content}}</textarea>
                                        </div>
                                    </div>
                                    <button @click="submit1('type5')" class="btn btn-default">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<script src="js/vue.js?v=${cfg.version}"></script>
</html>

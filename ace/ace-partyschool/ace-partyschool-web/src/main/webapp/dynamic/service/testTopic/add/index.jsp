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
                        <ul class="nav nav-pills">
                            <li class="active"><a href="#test1" data-toggle="tab">单选题</a>
                            </li>
                            <li class=""><a href="#test2" data-toggle="tab">多选题</a>
                            </li>
                            <li class=""><a href="#test3" data-toggle="tab">判断题</a>
                            </li>
                            <li class=""><a href="#test4" data-toggle="tab">问答题</a>
                            </li>
                            <li class=""><a href="#test5" data-toggle="tab">打分题</a>
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="test1">
                                <h4>创建单选题</h4>
                                <div class="col-md-offset-3 col-md-6">
                                    <form role="form">
                                        <div class="test1 test">
                                            <div class="title">
                                                <textarea placeholder="题目" name="name" rows="1"></textarea>
                                            </div>
                                            <div class="option">
                                                <input class="pointer" type="radio" name="option" value=""/>
                                                <textarea rows="1" type="text" class="option-text option-margin" placeholder="选项1"></textarea>
                                            </div>
                                            <div class="option">
                                                <input class="pointer" type="radio" name="option" value=""/>
                                                <textarea rows="1" type="text" class="option-text" placeholder="选项1"></textarea>
                                                <div class="remove pointer">×</div>
                                            </div>
                                            <div class="tool">
                                                <ul>
                                                    <li>添加选项</li>
                                                    <li>删除</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-default">Submit Button</button>
                                        <button type="reset" class="btn btn-default">Reset Button</button>
                                    </form>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="test2">
                                <h4>多选题 Tab</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                    irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                                    deserunt mollit anim id est laborum.</p>
                            </div>
                            <div class="tab-pane fade" id="test3">
                                <h4>判断题 Tab</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                    irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                                    deserunt mollit anim id est laborum.</p>
                            </div>
                            <div class="tab-pane fade" id="test4">
                                <h4>问答题 Tab</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                    irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                                    deserunt mollit anim id est laborum.</p>
                            </div>
                            <div class="tab-pane fade" id="test5">
                                <h4>打分题 Tab</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                                    irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                                    deserunt mollit anim id est laborum.</p>
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
</html>

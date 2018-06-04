<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

    <!DOCTYPE html>
    <!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
    <!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
    <!--[if !IE]><!-->
    <html lang="en">
    <!--<![endif]-->

    <head>
        <meta charset="utf-8" />
        <title></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1,user-scalable=no" name="viewport" />
        <meta content="" name="description" />
        <jsp:include page="../../../dynamic/common/base.jsp" />
        <script src="js/index.js?v=${cfg.version}"></script>
        <script src="js/loader.js?version=${cfg.version}"></script>
        <script type="text/javascript">
            var pageId = '${param.pageId}';
        </script>
    </head>

    <body>
        <div class="page">
            <!--图片轮播栏-->
            <div id="myCarousel" class="carousel slide">
               
            </div>
            <div class="navigation">
               
            </div>
            <div id="navitem">
                
            </div>

        </div>
        <script id="tpl-carousel" type="text/template">
           
                
                 <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators" style="padding-bottom: 0.02rem; padding-top: 0.02rem;">
										{@each data.data.covers as item,index}
											{@if index==0}
												<li data-target="#myCarousel" data-slide-to="\${index}" class="active"></li>
											{@else}
												<li data-target="#myCarousel" data-slide-to="\${index}"></li>
											{@/if}
										{@/each}
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner" id="sliceImgContainer">
									{@each data.data.covers as item,index}
											{@if index==0}
											
										<div class="item active">
												<a href="{@if item.hrefUrl!=null}\${item.hrefUrl}{@else}preview.jsp?id=\${item.id}{@/if}">
													<img src="\${item.cover}" alt="\${item.title}" style="height: 4rem; width: 100%; object-fit: cover;" />
												</a>
												<div class="carousel-caption">\${item.title}</div>
                    </div>
											{@else}
												<div class="item">
													<a href="{@if item.hrefUrl!=null}\${item.hrefUrl}{@else}preview.jsp?id=\${item.id}{@/if}">
														<img src="\${item.cover}" alt="\${item.title}" style="height: 4rem; width: 100%; object-fit: cover;" />
													</a>
												</div>
											{@/if}
                  {@/each}
                </div>
                
           
        </script>
				
				<script id="tpl-navigation" type="text/template">
                 <div class="news-title">
                    <ul class="news-module clear">
											{@each data.data.categorys as item,index}
													{@if index==0}
														<li class="active" data-id="\${item.id}" >\${item.name}</li>
													{@else}
														<li data-id="\${item.id}" >\${item.name}</li>
													{@/if}
											{@/each}
                    </ul>
                    <div class="news-slider"></div>
                </div>
        </script>
				<script id="tpl-navitem" type="text/template">
											{@each data.data.categorys as item,index}
													{@if index==0}
														<div class="navitem" data-id="\${item.id}" style="display: block;">
													{@else}
														<div class="navitem" data-id="\${item.id}" style="display: none;">
													{@/if}
														{@each item.articles as o}
															<a class="list_item" href="{@if o.hrefUrl!=null}\${o.hrefUrl}{@else}preview.jsp?id=\${o.id}{@/if}">
															<h2 class="title">\${o.title}</h2>
															<div class="cover">
																	<img class="img js_img" src="\${o.cover}">
															</div>
															<div class="cont">
																	<p class="desc">\${o.remark}</p>
															</div>
															</a>
														{@/each}
													</div>
											{@/each}
                  
        </script>
    </body>

    </html>

/**
 * cvi_busy_lib.js 1.21 (20-Mar-2009) (c) by Christian Effenberger
 * All Rights Reserved. Source: busy.netzgesta.de
 * Distributed under Netzgestade Software License Agreement.
 * This license permits free of charge use on non-commercial
 * and private web sites only under special conditions.
 * Read more at... http://www.netzgesta.de/cvi/LICENSE.txt
 * syntax:

 Add:
	OBJECT = getBusyOverlay(parent[,overlay[,busy]]);

	parent		== element to add the overlay (e.g. document.getElementById(id) or 'viewport')

	overlay 	== OBJECT e.g. {color: 'black', opacity: 0.5, ...}
		color	== STR 'black' or '#000000' or 'rgb(0,0,0)' Default: 'white'
		opacity	== FLOAT 0.0 - 1.0  Default: 0.0
		text	== STR e.g. "loading" Default: ''
		style	== STR e.g. "color: black;" or "my_text_class" Default: ''

	busy		== OBJECT e.g. {color: '#fff', size: 48, ...}
		color	== STR '#000000' - '#ffffff' or '#000' - '#fff' Default: '#000'
		size	== INT 16 - 512 (pixel) Default: 32
		type	== STR 'circle|oval|polygon|rectangle|tube' or 'c|o|p|r|t' Default: 'tube'
		iradius	== INT 6 - 254 (pixel) Default: 8
		weight	== INT 1 - 254 (pixel) Default: 3
		count	== INT 5 - 36 (rays) Default: 12
		speed	== INT 30 - 1000 (millisec) Default: 96
		minopac	== FLOAT 0.0 - 0.5  Default: 0.25

 Remove:
	OBJECT.remove();

 Set Overlay text:
	OBJECT.settext(string);

 *
**/

function onIEWinResize(event) {
	function parseWidth(val) {return (isNaN(parseInt(val,10))?0:parseInt(val,10));}
	if(!event) {event=window.event;} var i,cs,parent=this, div=parent.getElementsByTagName("div");
	if(div.length>0) {if(parent.currentStyle){cs=parent.currentStyle;}else if(document.defaultView&&document.defaultView.getComputedStyle){cs=document.defaultView.getComputedStyle(parent,"");}else{cs=parent.style;}
		for(i=0; i<div.length; i++) {if(div[i].className=='buzy_ele') {
				div[i].style.height=(parent.offsetHeight-parseWidth(cs.borderBottomWidth)-parseWidth(cs.borderTopWidth));
				div[i].style.width=(parent.offsetWidth-parseWidth(cs.borderLeftWidth)-parseWidth(cs.borderRightWidth));
				div[i].firstChild.style.height=div[i].style.height; div[i].firstChild.style.width=div[i].style.width;
				break;
			}
		}
	}
}
function onIEVPResize(event) {
	if(!event) {event=window.event;} var vp=document.getElementById('viewport'); if(vp) {
		if(typeof document.documentElement!='undefined') {vp.style.width=document.documentElement.clientWidth+'px'; vp.style.height=document.documentElement.clientHeight+'px';}
		else {vp.style.width=document.getElementsByTagName('body')[0].clientWidth+'px'; vp.style.height=document.getElementsByTagName('body')[0].clientHeight+'px';}
	}
}
function onIEVPScroll(event) {
	if(!event) {event=window.event;} var vp=document.getElementById('viewport'); if(vp) {
		if(typeof document.documentElement!='undefined') {vp.style.left=document.documentElement.scrollLeft+'px'; vp.style.top=document.documentElement.scrollTop+'px';}
		else {vp.style.left=document.getElementsByTagName('body')[0].scrollLeft+'px'; vp.style.top=document.getElementsByTagName('body')[0].scrollTop+'px';}
	}
}
function getBusyOverlay(parent,overlay,busy) {
	if((typeof(parent)==='object' || parent=='viewport') && document.getElementsByTagName) {
		function parseWidth(val) {return (isNaN(parseInt(val,10))?0:parseInt(val,10));}
		var isIE,isVL,isCV,isWK,isGE,i,b,o,lt,rt,lb,rb,cz,cs,size,viewport,inner,outer,string,canvas,context,ctrl,opacity,color,text,styles,waiting=true;
		if(parent=='viewport') {viewport=document.getElementById('viewport');
			if(!viewport) {viewport=document.createElement('div'); viewport.id='viewport'; cz=viewport.style;
				cz.backgroundColor='transparent'; cz.position='fixed'; cz.overflow='hidden';
				cz.display='block'; cz.zIndex=999999; cz.left='0px'; cz.top='0px'; cz.zoom=1;
				cz.width='100%'; cz.height='100%'; cz.margin='0px'; cz.padding='0px';
				if(document.all&&!window.opera&&!window.XMLHttpRequest) {cz.position='absolute';
					if(typeof document.documentElement!='undefined') {cz.width=document.documentElement.clientWidth+'px'; cz.height=document.documentElement.clientHeight+'px';}
					else {cz.width=document.getElementsByTagName('body')[0].clientWidth+'px'; cz.height=document.getElementsByTagName('body')[0].clientHeight+'px';}
				}document.getElementsByTagName("body")[0].appendChild(viewport);
			}else {viewport.style.display='block';
				if(document.all&&!window.opera&&!window.XMLHttpRequest) {
					if(typeof document.documentElement!='undefined') {viewport.style.width=document.documentElement.clientWidth+'px';	viewport.style.height=document.documentElement.clientHeight+'px';}
					else {viewport.style.width=document.getElementsByTagName('body')[0].clientWidth+'px';	viewport.style.height=document.getElementsByTagName('body')[0].clientHeight+'px';}
				}
			}parent=viewport;
		}
		if(parent.currentStyle){cs=parent.currentStyle;}else if(document.defaultView&&document.defaultView.getComputedStyle){cs=document.defaultView.getComputedStyle(parent,"");}else{cs=parent.style;}
		while(cs.display.search(/block|inline-block|table|inline-table|list-item/i)<0) {parent=parent.parentNode; if(parent.currentStyle){cs=parent.currentStyle;}else if(document.defaultView&&document.defaultView.getComputedStyle){cs=document.defaultView.getComputedStyle(parent,"");}else{cs=parent.style;} if(parent.tagName.toUpperCase()=='BODY') {parent="";}}
		if(typeof(parent)==='object') {
			if(!overlay) {overlay=new Object(); overlay['opacity']=0; overlay['color']='white'; overlay['text']=''; overlay['style']='';}
			if(!busy) {busy=new Object(); busy['size']=32; busy['color']='#000'; busy['type']='tube'; busy['iradius']=8; busy['weight']=3; busy['count']=12; busy['speed']=96; busy['minopac']=.25;}
			opacity=Math.max(0.0,Math.min(1.0,(typeof overlay['opacity']==='number'?overlay['opacity']:0)||0)); color=(typeof overlay['color']==='string'?overlay['color']:'white');
			text=(typeof overlay['text']==='string'?overlay['text']:''); styles=(typeof overlay['style']==='string'?overlay['style']:'');
			canvas=document.createElement("canvas"); isCV=canvas.getContext?1:0;
			isWK=navigator.userAgent.indexOf('WebKit')>-1?1:0; isGE=navigator.userAgent.indexOf('Gecko')>-1&&window.updateCommands?1:0;
			isIE=navigator.appName=='Microsoft Internet Explorer'&&window.navigator.systemLanguage&&!window.opera?1:0;
			isVL=document.all&&document.namespaces?1:0; outer=document.createElement('div'); parent.style.position=(cs.position=='static'?'relative':cs.position);
			cz=parent.style.zIndex>=0?(parent.style.zIndex-0+2):2; if(isIE && !cs.hasLayout) {parent.style.zoom=1;}
			outer.style.position='absolute'; outer.style.overflow='hidden'; outer.style.display='block'; outer.style.zIndex=cz;
			outer.style.left=0+'px'; outer.style.top=0+'px'; outer.style.width='100%'; outer.style.height='100%';
			if(isIE) {outer.className='buzy_ele'; outer.style.zoom=1; outer.style.margin='0px'; outer.style.padding='0px'; outer.style.height=(parent.offsetHeight-parseWidth(cs.borderBottomWidth)-parseWidth(cs.borderTopWidth)); outer.style.width=(parent.offsetWidth-parseWidth(cs.borderLeftWidth)-parseWidth(cs.borderRightWidth));}
			if(typeof(cs.borderRadius)=="undefined"){
				if(typeof(cs.MozBorderRadius)!="undefined"){
					lt=parseFloat(cs.MozBorderRadiusTopleft)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderTopWidth));
					rt=parseFloat(cs.MozBorderRadiusTopright)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderTopWidth));
					lb=parseFloat(cs.MozBorderRadiusBottomleft)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderBottomWidth));
					rb=parseFloat(cs.MozBorderRadiusBottomright)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderBottomWidth));
					outer.style.MozBorderRadiusTopleft=lt+"px"; outer.style.MozBorderRadiusTopright=rt+"px"; outer.style.MozBorderRadiusBottomleft=lb+"px"; outer.style.MozBorderRadiusBottomright=rb+"px";
				}else if(typeof(cs.WebkitBorderRadius)!="undefined"){
					lt=parseFloat(cs.WebkitBorderTopLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderTopWidth));
					rt=parseFloat(cs.WebkitBorderTopRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderTopWidth));
					lb=parseFloat(cs.WebkitBorderBottomLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderBottomWidth));
					rb=parseFloat(cs.WebkitBorderBottomRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderBottomWidth));
					outer.style.WebkitBorderTopLeftRadius=lt+"px"; outer.style.WebkitBorderTopRightRadius=rt+"px"; outer.style.WebkitBorderBottomLeftRadius=lb+"px"; outer.style.WebkitBorderBottomRightRadius=rb+"px";
				}
			}else {
				lt=parseFloat(cs.borderTopLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderTopWidth));
				rt=parseFloat(cs.borderTopRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderTopWidth));
				lb=parseFloat(cs.borderBottomLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderBottomWidth));
				rb=parseFloat(cs.borderBottomRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderBottomWidth));
				outer.style.borderTopLeftRadius=lt+"px"; outer.style.borderTopRightRadius=rt+"px"; outer.style.borderBottomLeftRadius=lb+"px"; outer.style.borderBottomRightRadius=rb+"px";
			}
			parent.appendChild(outer); inner=document.createElement('div');
			inner.style.position='absolute'; inner.style.cursor='progress'; inner.style.display='block';
			inner.style.zIndex=(cz-1); inner.style.left=0+'px'; inner.style.top=0+'px';
			inner.style.width=100+'%'; inner.style.height=100+'%'; inner.style.backgroundColor=color;
			if(isIE) {inner.style.zoom=1; inner.style.margin='0px'; inner.style.padding='0px'; inner.style.height=outer.style.height; inner.style.width=outer.style.width; }
			if(typeof(cs.borderRadius)=="undefined"){
				if(typeof(cs.MozBorderRadius)!="undefined"){inner.style.MozBorderRadiusTopleft=lt+"px"; inner.style.MozBorderRadiusTopright=rt+"px"; inner.style.MozBorderRadiusBottomleft=lb+"px"; inner.style.MozBorderRadiusBottomright=rb+"px";}else
				if(typeof(cs.WebkitBorderRadius)!="undefined"){inner.style.WebkitBorderTopLeftRadius=lt+"px"; inner.style.WebkitBorderTopRightRadius=rt+"px"; inner.style.WebkitBorderBottomLeftRadius=lb+"px"; inner.style.WebkitBorderBottomRightRadius=rb+"px";}
			}else {inner.style.borderTopLeftRadius=lt+"px"; inner.style.borderTopRightRadius=rt+"px"; inner.style.borderBottomLeftRadius=lb+"px"; inner.style.borderBottomRightRadius=rb+"px";}
			if(isIE) {inner.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+parseInt(opacity*100)+")";}else {inner.style.opacity=opacity;}
			outer.appendChild(inner); size=Math.max(16,Math.min(512,(typeof busy['size']==='number'?(busy['size']==0?32:busy['size']):32)));
			if(isVL){
				if(document.namespaces['v']==null) {
					var e=["shape","shapetype","group","background","path","formulas","handles","fill","stroke","shadow","textbox","textpath","imagedata","line","polyline","curve","roundrect","oval","rect","arc","image"],s=document.createStyleSheet();
					for(i=0; i<e.length; i++) {s.addRule("v\\:"+e[i],"behavior: url(#default#VML);");} document.namespaces.add("v","urn:schemas-microsoft-com:vml");
				}
			}
			if(!isCV){canvas=document.createElement("div");}
			canvas.style.position='absolute'; canvas.style.cursor='progress'; canvas.style.zIndex=(cz-0+1);
			canvas.style.top='50%'; canvas.style.left='50%'; canvas.style.marginTop='-'+(size/2)+'px'; canvas.style.marginLeft='-'+(size/2)+'px';
			canvas.width=size; canvas.height=size; canvas.style.width=size+"px"; canvas.style.height=size+"px";
			outer.appendChild(canvas);
			if(text!=""){
				string=document.createElement('div'); string.style.position='absolute'; string.style.overflow='hidden';
				string.style.cursor='progress'; string.style.zIndex=(cz-0+1); string.style.top='50%'; string.style.left='0px';
				string.style.marginTop=2+(size/2)+'px'; string.style.textAlign='center'; string.style.width=100+'%'; string.style.height='auto';
				if(styles!="") {string.innerHTML='<span '+(styles.match(/:/i)?'style':'class')+'="'+styles+'">'+text+'</span>';}
				else {string.innerHTML='<span>'+text+'</span>';} outer.appendChild(string);
			}
			if(isGE){outer.style.MozUserSelect="none"; inner.style.MozUserSelect="none"; canvas.style.MozUserSelect="none";}else
			if(isWK){outer.style.KhtmlUserSelect="none"; inner.style.KhtmlUserSelect="none"; canvas.style.KhtmlUserSelect="none";}else
			if(isIE){outer.unselectable="on"; inner.unselectable="on"; canvas.unselectable="on";}
			if(isVL){ctrl=getBusyVL(canvas,busy['color'],busy['size'],busy['type'],busy['iradius'],busy['weight'],busy['count'],busy['speed'],busy['minopac']); ctrl.start();}else
			if(isCV){ctrl=getBusyCV(canvas.getContext("2d"),busy['color'],busy['size'],busy['type'],busy['iradius'],busy['weight'],busy['count'],busy['speed'],busy['minopac']); ctrl.start();}
			else {ctrl=getBusy(canvas,busy['color'],busy['size'],busy['type'],busy['iradius'],busy['weight'],busy['count'],busy['speed'],busy['minopac']); ctrl.start();}
			if(isIE) {parent.onresize=onIEWinResize; if(parent.id=='viewport'&&!window.XMLHttpRequest) {window.onresize=onIEVPResize; window.onscroll=onIEVPScroll;}}
			return {
				remove: function (){if(waiting){waiting=false; ctrl.stop(); delete ctrl; parent.removeChild(outer); if(parent.id=='viewport') {parent.style.display='none';}}},
				settext: function (v){if(string&&typeof(v)=='string') {string.firstChild.innerHTML=v; return false;}}
			};
		}
	}
}
function getBusy(obj,cl,sz,tp,ir,w,ct,sp,mo) {
	function getHEX(v) {var col=v||'#000000';
		if(!col.match(/^#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]$/i)) {
			if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f]$/i)) {col='#'+v.substr(1,1)+v.substr(1,1)+v.substr(2,1)+v.substr(2,1)+v.substr(3,1)+v.substr(3,1);}
		}return col;
	}
	var running=false,i=0,os=0,al=0,f=100,c,h,p,t,x,y,v,hp,ph,sh,ele=new Array();
	c=getHEX(cl); tp=tp||"t"; t=(tp.match(/^[coprt]/i)?tp.substr(0,1).toLowerCase():'t');
	ct=Math.max(5,Math.min(36,ct||12)); sp=Math.max(30,Math.min(1000,sp||96));
	sz=Math.max(16,Math.min(512,sz||32)); ir=Math.max(1,Math.min((sz/2)-2,ir||sz/4));
	w=Math.max(1,Math.min((sz/2)-ir,w||sz/10)); mo=Math.max(0,Math.min(0.5,mo||0.25));
	al=360/ct; hp=(Math.PI/2)*-1; ph=Math.PI/180; w=(t!='c'?parseInt((w/2)*3):w); v=parseInt((sz/2)-(w/2));
	for(i=0;i<ct;i++) {
		sh=document.createElement('div'); x=Math.round(v+v*Math.cos(hp+(i+1)*al*ph)); y=Math.round(v+v*Math.sin(hp+(i+1)*al*ph));
		sh.style.position='absolute'; sh.style.margin='0px'; sh.style.width=w+'px'; sh.style.height=w+'px';
		sh.style.lineHeight='1px'; sh.style.fontSize='0px'; sh.style.top=y+'px'; sh.style.left=x+'px'; sh.style.backgroundColor=c;
		if(document.all&&!window.opera) {sh.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+parseInt(Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))))*100)+")";
		}else {sh.style.opacity=Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1)))); }
		obj.appendChild(sh); ele[i]=sh;
	}
	function nextLoop(){
		if(!running) {return;} os=(os+1)%ct;
		if(document.all&&!window.opera) {for(i=0;i<ct;i++){al=((os+i)%ct); ele[al].style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+parseInt(Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))))*100)+")";}}
		else {for(i=0;i<ct;i++){al=((os+i)%ct); ele[al].style.opacity=Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))));}}
		setTimeout(nextLoop,sp);
	}
	nextLoop(0);
	return {
		start: function (){if(!running){running=true; nextLoop(0);}},
		stop: function (){running=false; for(i=0;i<ct;i++) {if(document.all&&!window.opera) {ele[i].style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity=0)";}else {ele[i].setAttribute('opacity',0);}}},
		pause: function (){running=false; }
	};
}
function getBusyVL(obj,cl,sz,tp,ir,w,ct,sp,mo) {
	function getHEX(v) {var col=v||'#000000';
		if(!col.match(/^#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]$/i)) {
			if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f]$/i)) {col='#'+v.substr(1,1)+v.substr(1,1)+v.substr(2,1)+v.substr(2,1)+v.substr(3,1)+v.substr(3,1);}
		}return col;
	}
	var running=false,os=0,al=0,f=100,c,i,h,p,t,x,y,hs,qs,hw,qw,rp,sh,fl,ele=new Array();
	c=getHEX(cl); tp=tp||"t"; t=(tp.match(/^[coprt]/i)?tp.substr(0,1).toLowerCase():'t');
	ct=Math.max(5,Math.min(36,ct||12)); sp=Math.max(30,Math.min(1000,sp||96));
	sz=Math.max(16,Math.min(512,sz||32)); ir=Math.max(1,Math.min((sz/2)-2,ir||sz/4));
	w=Math.max(1,Math.min((sz/2)-ir,w||sz/10)); mo=Math.max(0,Math.min(0.5,mo||0.25));
	h=(sz/2)-ir; x=sz/2; y=x; al=360/ct; hs=parseInt((sz/2)*f); qs=parseInt(hs/2);
	hw=parseInt((w/2)*f); qw=parseInt(hw/2); rp=hs-parseInt(ir*f);
	switch(t) {
		case "c": p='m '+hs+','+(rp-hw)+' ar '+(hs-hw)+','+(rp-hw-hw)+','+(hs+hw)+','+rp+','+(hs-hw)+','+(rp-hw-hw)+','+(hs-hw)+','+(rp-hw-hw)+' e'; break;
		case "p": p='m '+(hs-qw)+',0 l '+(hs-hw)+','+rp+','+(hs+hw)+','+rp+','+(hs+qw)+',0 x e'; break;
		case "o": p='m '+hs+','+(rp-qs)+' ar '+(hs-hw)+',0,'+(hs+hw)+','+rp+','+(hs-hw)+',0,'+(hs-hw)+',0 e'; break;
		case "t": p='m '+(hs-hw)+','+rp+' l '+(hs-hw)+','+hw+' qy '+hs+',0 qx '+(hs+hw)+','+hw+' l '+(hs+hw)+','+rp+' x e'; break;
		default: p='m '+(hs-hw)+',0 l '+(hs-hw)+','+rp+','+(hs+hw)+','+rp+','+(hs+hw)+',0 x e'; break;
	}
	for(i=0;i<ct;i++) {
		sh=document.createElement('v:shape'); sh.setAttribute('filled','t'); sh.setAttribute('stroked','f');
		sh.setAttribute('coordorigin','0,0'); sh.setAttribute('coordsize',(sz*f)+','+(sz*f));
		sh.setAttribute('path',p); sh.style.rotation=(i*al); sh.style.position='absolute'; sh.style.margin='0px';
		sh.style.width=sz+'px'; sh.style.height=sz+'px'; sh.style.top='-1px'; sh.style.left='-1px';
		obj.appendChild(sh); fl=document.createElement('v:fill');
		fl.setAttribute('opacity',Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1)))));
		fl.setAttribute('color',c); sh.appendChild(fl); ele[i]=fl;
	}
	function nextLoop(){
		if(!running) {return;} os=(os+1)%ct;
		if(document.documentMode==8) {
			for(i=0;i<ct;i++) {al=((os+i)%ct); ele[al].opacity=Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))));}
		}else {
			for(i=0;i<ct;i++) {al=((os+i)%ct); ele[al].setAttribute('opacity',Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1)))));}
		}
		setTimeout(nextLoop,sp);
	}
	nextLoop(0);
	return {
		start: function (){if(!running){running=true; nextLoop(0);}},
		stop: function (){running=false; for(i=0;i<ct;i++) {ele[i].setAttribute('opacity',0);}},
		pause: function (){running=false; }
	};
}
function getBusyCV(ctx,cl,sz,tp,ir,w,ct,sp,mo) {
	function getRGB(v){
		function hex2dec(h){return(Math.max(0,Math.min(parseInt(h,16),255)));}
		var r=0,g=0,b=0; v=v||'#000'; if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f]$/i)) {
			r=hex2dec(v.substr(1,1)+v.substr(1,1)),g=hex2dec(v.substr(2,1)+v.substr(2,1)),b=hex2dec(v.substr(3,1)+v.substr(3,1));
		}else if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]$/i)) {
			r=hex2dec(v.substr(1,2)),g=hex2dec(v.substr(3,2)),b=hex2dec(v.substr(5,2));
		} return r+','+g+','+b;
	}
	function drawOval(ctx,w,h){ctx.beginPath(); ctx.moveTo(-w/2,h/2); ctx.quadraticCurveTo(-w/2,0,0,0); ctx.quadraticCurveTo(w/2,0,w/2,h/2); ctx.quadraticCurveTo(w/2,h,0,h); ctx.quadraticCurveTo(-w/2,h,-w/2,h/2); ctx.fill();}
	function drawTube(ctx,w,h){ctx.beginPath(); ctx.moveTo(w/2,0); ctx.lineTo(-w/2,0); ctx.lineTo(-w/2,h-(w/2)); ctx.quadraticCurveTo(-w/2,h,0,h); ctx.quadraticCurveTo(w/2,h,w/2,h-(w/2)); ctx.fill();}
	function drawPoly(ctx,w,h){ctx.beginPath(); ctx.moveTo(w/2,0); ctx.lineTo(-w/2,0); ctx.lineTo(-w/4,h); ctx.lineTo(w/4,h); ctx.fill();}
	function drawCirc(ctx,r,z){ctx.beginPath(); ctx.arc(r,r,r,0,Math.PI*2,false); ctx.fill();}
	var running=false,os=0,al=0,c,i,h,t,x,y;
	c=getRGB(cl); tp=tp||"t"; t=(tp.match(/^[coprt]/i)?tp.substr(0,1).toLowerCase():'t');
	ct=Math.max(5,Math.min(36,ct||12)); sp=Math.max(30,Math.min(1000,sp||96));
	sz=Math.max(16,Math.min(512,sz||32)); ir=Math.max(1,Math.min((sz/2)-2,ir||sz/4));
	w=Math.max(1,Math.min((sz/2)-ir,w||sz/10)); mo=Math.max(0,Math.min(0.5,mo||0.25));
	h=(sz/2)-ir; x=sz/2; y=x;
	function nextLoop(){
		if(!running) {return;} os=(os+1)%ct; ctx.clearRect(0,0,sz,sz); ctx.save(); ctx.translate(x,y);
		for(i=0;i<ct;i++) {al=2*((os+i)%ct)*Math.PI/ct;
			ctx.save(); ctx.translate(ir*Math.sin(-al),ir*Math.cos(-al)); ctx.rotate(al);
			ctx.fillStyle='rgba('+c+','+Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))))+')';
			switch(t) {case "c": drawCirc(ctx,w/2,h); break; case "o": drawOval(ctx,w,h); break; case "p": drawPoly(ctx,w,h); break; case "t": drawTube(ctx,w,h); break; default: ctx.fillRect(-w/2,0,w,h); break;} ctx.restore();
		} ctx.restore();
		setTimeout(nextLoop,sp);
	}
	nextLoop(0);
	return {
		start: function (){if(!running){running=true; nextLoop(0);}},
		stop: function (){running=false; ctx.clearRect(0,0,sz,sz); },
		pause: function (){running=false; }
	};
}

function startLoading(){
    return getBusyOverlay('viewport',{color:'#eff3f8', opacity:0.75, text:'viewport: loading...', style:'font-size:14px;color:#000000'},{color:'#00000', size:80, type:'o'});
}
function startLoad(){
 try {loading = startLoading();} catch (e) {};
 if (loading) {loading.settext("玩命请求中，请耐心等待......");}
}
function stopLoad(){
  if (loading) { loading.remove();}
}

 function getQueryString() {
          var qs = location.search.substr(1),
            args = {},
            items = qs.length ? qs.split("&") : [],
            item = null,
            len = items.length;

          for(var i = 0; i < len; i++) {
            item = items[i].split("=");
            var name = decodeURIComponent(item[0]),
              value = decodeURIComponent(item[1]);
            if(name) {
              args[name] = value;
            }
          }
          return args;
}
var  urlParams =urlParams=getQueryString();
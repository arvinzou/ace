bridge3d = function(containerId,width,height,objurl){

	if(null == document.getElementById(containerId)){
		alert('id閿欒锛屾棤娉曞垱寤�3D妯″瀷');
		return;
	}

	this.container = document.getElementById(containerId);

	var WIDTH,HEIGHT;

	if(width =='auto'){

		whauto();

		window.onresize=function(){

			whauto();

			camera.aspect = WIDTH / HEIGHT;
			camera.updateProjectionMatrix();

			renderer.setSize(WIDTH, HEIGHT);
		}
	}else{

		WIDTH = (parseInt(width)>0)?parseInt(width):972;
		HEIGHT = (parseInt(height)>0)?parseInt(height):740;

	}

	this.objurl = objurl;

	var scene;
	var	renderer;
	var	camera;
	var dLight;
	var water
	var stats;
	var textmesh = new Array;
	//var sensors = new Array;
	var sensors = [];
	var sensorsActive = [];
	var webcam = [];
	var vedio = [];
	//var vedioPlane;
	//var webcamMesh

	var obj;
	var carModel;
	var car;

	var controls;
	var timea;
	var initPosition = new Object();
	var initType = {'init':'cylinder'};

	var material = ['red','green'];
	var red = new THREE.MeshPhongMaterial({ color:0xFF0000, ambient: 0x552811, specular: 0x333333, shininess: 25,bumpScale: 19, metal: true });
	var green = new THREE.MeshPhongMaterial({ color:0x00FF00, ambient: 0x552811, specular: 0x333333, shininess: 25,bumpScale: 19, metal: true });

	var geometry = ['cylinder'];
	var cylinder = new THREE.CylinderGeometry( 0.5, 0.2, 1, 32 );//radiusTop, radiusBottom, height, radiusSegments, heightSegments, openEnded

	var clock = new THREE.Clock();

	var isCarRun = false;
	var isPersonRun = false;
	var isVideoRun = false;

	this.setPo = function(id,x,y,z){

		if(!initPosition.hasOwnProperty(id)){alert('璇D浼犳劅鍣ㄦ湭鍒濆鍖�');return;}

		x = parseFloat(x);

		y = parseFloat(y);

		z = parseFloat(z);

		sensors[id].position.set(x,y,z);
		textmesh[id].position.set(x,y,z);
		renderer.render( scene, camera );
	}


	this.init = function () {

		initScene();
		initCamera();
		initRenderer(this.container);
		initLight();
		initEnviroment();
		initObj(this.objurl);
		initCar();
		initControls();
		//initStats(this.container);
		animate();
		//initWebcam();
	}

	this.initWeather = function(url){
		document.getElementById("weather").innerHTML='姝ｅ湪鏇存柊锛岃绋嶇瓑銆傘€傘€�';
		ajaxFunction( weather,url);
	}

	this.initSensors = function(sensorsData){

		for(id in sensorsData){

			if(initPosition.hasOwnProperty(id)){alert('ID涓衡€�'+id+'鈥濈殑浼犳劅鍣ㄥ凡缁忓瓨鍦紒');continue;}

			initPosition[id] = intPo(sensorsData[id]['po']);
			initType[id] = inArray(sensorsData[id]['type'],geometry)?sensorsData[id]['type']:initType['init'];

			var po = initPosition[id];
			sensors[id] = new THREE.Mesh( eval(initType[id]), red );
			sensors[id].position.set(po.x,po.y,po.z);

			//[text],{width,height,auto,fontsize,borderColor,backgroundColor}
			textmesh[id] = new THREE.Sprite(makeTextSprite(["鏈兘鑾峰彇鍒版湇鍔″櫒鐨勬暟鎹�","璇风‘璁よ緭鍏ラ摼璺甯�"]));
			textmesh[id].scale.set(24,12,24);
			textmesh[id].position.set(po.x,po.y,po.z);
			textmesh[id].visible = true;
			scene.add( textmesh[id] );
			scene.add( sensors[id] );

		}

	}

	this.hideshow = function(id){

		if(!initPosition.hasOwnProperty(id)){alert('璇D浼犳劅鍣ㄦ湭鍒濆鍖�');return;}

		textmesh[id].visible = !textmesh[id].visible;
		//sensors[id].visible = !sensors[id].visible;

	}

	this.hideall = function(){
		for(id in initPosition){
			if(textmesh[id].visible){
			this.hideshow(id);}
		}
	}

	this.showall = function(){
		for(id in initPosition){
			if(!textmesh[id].visible){
			this.hideshow(id);}
		}

	}

	this.remove = function(id){

		if(!initPosition.hasOwnProperty(id)){alert('璇D浼犳劅鍣ㄦ湭鍒濆鍖�');return;}

		scene.remove( textmesh[id] );
		scene.remove( sensors[id] );
		delete initPosition[id];

	}

	this.removeall = function(){
		for(id in initPosition){
			this.remove(id);
		}
	}

	this.setSensors = function(data){

		//alert(null == initPosition);

		//鍒ゆ柇鏄惁鏉ヨ嚜url
		if(typeof data=="string"){
			var currData = eval("("+data+")");
		}else{
			var currData = data;
		}
		//閬嶅巻鎵ц
		for(id in currData){

			if(!initPosition.hasOwnProperty(id)){continue;}
			setSensor(id,currData[id]);
		}

	}

	this.update = function(url){

		ajaxFunction(this.setSensors,url);

	}

	this.resetvisio = function(parameters){

		controls.resetCamera(parameters);
	}

	this.resetMode = function(mode){

		controls.setMode(mode);
	}

	this.showVideo = function(){

		//isVideoRun = true;
		//vedioPlane.visible = true;
	}

	this.hideVideo = function(){

		//isVideoRun = false;
		//vedioPlane.visible = false;
	}

	this.carRunOn = function(){
		//isCarRun = true;
	}

	this.carRunOff = function(){
		//isCarRun = false;
	}

	this.personRunOn = function(){
//		controls.enabled = false;
//		isPersonRun = true;
	}

	this.personRunOff = function(){
//		controls.enabled = true;
//		isPersonRun = false;
	}

	function initScene(){

		scene = new THREE.Scene();
		scene.fog = new THREE.Fog( 0xffffff, 1000, 10000 );

	}

	function initCamera(){

		var VIEW_ANGLE = 105,
			ASPECT = WIDTH / HEIGHT,
			NEAR = 1,
			FAR = 10000;

		camera = new THREE.PerspectiveCamera(VIEW_ANGLE, ASPECT, NEAR, FAR);
		camera.position.set(0, 100, 200);
		camera.lookAt(new THREE.Vector3(0,0,0));
		scene.add(camera);
	}

	function initRenderer(container){

		if ( Detector.webgl )
			renderer = new THREE.WebGLRenderer( {antialias:true} );
		else{
			renderer = new THREE.CanvasRenderer();
		}

		renderer.setSize(WIDTH , HEIGHT);
		renderer.setClearColor(scene.fog.color, 1);
		renderer.domElement.style.position = "relative";
		container.appendChild( renderer.domElement );

		renderer.gammaInput = true;
		renderer.gammaOutput = true;
	}

	function initLight(){

		dLight = new THREE.DirectionalLight( 0xffffff, 1.475 );
		dLight.position.set( 100, 100, -100 ).normalize();
		scene.add( dLight );

		aLight = new THREE.HemisphereLight( 0xffffff, 0xffffff, 1.25 );
		aLight.color.setHSL( 0.6, 1, 0.6 );
		aLight.groundColor.setHSL( 0.1, 0.8, 0.7 );
		aLight.position.y = 500;
		scene.add( aLight );

	}

	function initEnviroment(){

		var vertexShader = 'varying vec3 vWorldPosition;void main() {vec4 worldPosition = modelMatrix * vec4( position, 1.0 );vWorldPosition = worldPosition.xyz;gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );}';

		var fragmentShader = 'uniform vec3 topColor;uniform vec3 bottomColor;uniform float offset;uniform float exponent;varying vec3 vWorldPosition;void main() {float h = normalize( vWorldPosition + offset ).y;gl_FragColor = vec4( mix( bottomColor, topColor, max( pow( max( h, 0.0 ), exponent ), 0.0 ) ), 1.0 );}';

		var uniforms = {
			topColor: 	 { type: "c", value: new THREE.Color( 0x0077ff ) },
			bottomColor: { type: "c", value: new THREE.Color( 0xffffff ) },
			offset:		 { type: "f", value: 400 },
			exponent:	 { type: "f", value: 0.6 }
		}
		uniforms.topColor.value.copy( aLight.color );

		scene.fog.color.copy( uniforms.bottomColor.value );

		var skyGeo = new THREE.SphereGeometry( 5000, 32, 15 );
		var skyMat = new THREE.ShaderMaterial( { vertexShader: vertexShader, fragmentShader: fragmentShader, uniforms: uniforms, side: THREE.BackSide } );

		var sky = new THREE.Mesh( skyGeo, skyMat );
		sky.position.set(0,0,0);
		scene.add( sky );

		//// 鑽夊湴

//		var groundTexture = THREE.ImageUtils.loadTexture( "./image/grass.jpg" );
//		groundTexture.wrapS = groundTexture.wrapT = THREE.RepeatWrapping;
//		groundTexture.repeat.set( 100, 100 );
//		groundTexture.anisotropy = 16;
//
//		var groundMaterial = new THREE.MeshPhongMaterial( { color: 0xffffff, specular: 0x111111, map: groundTexture } );
//
//		var mesh = new THREE.Mesh( new THREE.PlaneGeometry( 20000, 20000 ), groundMaterial );
//		mesh.position.y = -20;
//		mesh.rotation.x = - Math.PI / 2;
//		mesh.receiveShadow = true;
//		scene.add( mesh );


		//娴锋磱
		var waterNormals = new THREE.ImageUtils.loadTexture('./image/water.jpg');
		waterNormals.wrapS = waterNormals.wrapT = THREE.RepeatWrapping;

		// Create the water effect
		water = new THREE.Water(renderer, camera, scene, {
			textureWidth: 256,
			textureHeight: 256,
			waterNormals: waterNormals,
			alpha: 	1.0,
			sunDirection: dLight.position.normalize(),
			sunColor: 0xffffff,
			waterColor: 0x94C9EC,
			betaVersion: 0,
			//side: THREE.DoubleSide
			side:THREE.FrontSide
		});
		var waterMirror = new THREE.Mesh( new THREE.PlaneGeometry(20000, 20000, 100, 100) , water.material );
		waterMirror.add(water);
		waterMirror.rotation.x = - Math.PI * 0.5;
		waterMirror.position.y = -20;
		//waterMirror.receiveShadow = true;

		scene.add(waterMirror);

	}

	function initObj(url){
		//var material = new THREE.MeshPhongMaterial({ ambient: 0x552811, specular: 0x333333, shininess: 25,bumpScale: 19, metal: true });
		var loader = new THREE.JSONLoader(true);

		loader.load(url, function ( geometry,materials ) {
			var material = new THREE.MeshFaceMaterial( materials);
			obj = new THREE.Mesh(geometry, material);
			//obj.scale.set(40,40,40);
			obj.position.set(0,0,0);
			obj.visible = true;
			scene.add(obj);
			renderer.render(scene, camera);
		});
	}

	function initCar(){

		carModel = new THREE.Car();

		var scale = 0.012;

		carModel.modelScale = scale;
		carModel.backWheelOffset = 0.27;

		carModel.MAX_SPEED *= scale;
		carModel.MAX_REVERSE_SPEED *= scale;
		carModel.FRONT_ACCELERATION *= scale;
		carModel.BACK_ACCELERATION *= scale;
		carModel.FRONT_DECCELERATION  *= scale;
		carModel.STEERING_RADIUS_RATIO /= scale*2;

		carModel.carOrientation = - Math.PI /2;

		carModel.callback = function( object ) {

			car = object.root;

			car.position.set( 150, 22,  -4 );

			car.rotation.y = - Math.PI /2;

			car.visible = true;

			scene.add( car );

			controls.hadCar = true;

		};

		//carModel.loadPartsBinary( "./obj/veyron/parts/veyron_body_bin.js", "./obj/veyron/parts/veyron_wheel_bin.js" );
		carModel.loadPartsBinary( "./obj/gallardo/parts/gallardo_body_bin.js", "./obj/gallardo/parts/gallardo_wheel_bin.js" );
	}

	function initWebcam(){

		image = document.createElement( 'canvas' );

		var videoWidth = 1920, videoHeight = 1080;
		image.width = videoWidth;
		image.height = videoHeight;

		imageContext = image.getContext( '2d' );
		imageContext.fillStyle = '#000000';
		imageContext.fillRect( 0, 0, videoWidth, videoHeight );

		texture = new THREE.Texture( image );
		texture.minFilter = THREE.LinearFilter;
		texture.magFilter = THREE.LinearFilter;

		var material = new THREE.MeshBasicMaterial( { side: THREE.DoubleSide, map: texture, overdraw: 0.5 } );

		var plane = new THREE.PlaneGeometry( videoWidth, videoHeight, 4, 4 );

		var vedioPlane = new THREE.Mesh( plane, material );

		vedioPlane.position.set(-34,45,0);

		vedioPlane.rotation.y = Math.PI /2;

		vedioPlane.scale.x = vedioPlane.scale.y = vedioPlane.scale.z = 0.012;

		vedioPlane.visible = false;

		vedioPlane.indexId = 0;

		isVideoRun = false;

		scene.add(vedioPlane);

		vedio.push(vedioPlane);

		var loader = new THREE.JSONLoader(true);

		loader.load('./obj/sc.js', function ( geometry ) {
			//var material = new THREE.MeshFaceMaterial( materials);
			//var material = new THREE.MeshPhongMaterial({ ambient: 0x552811, specular: 0xFFFFFF, shininess: 25,bumpScale: 19, metal: true });
			var material = new THREE.MeshPhongMaterial( { color: 0x333333, specular: 0x000000, emissive: 0x000000, ambient: 0x000000, shininess: 10, shading: THREE.SmoothShading, opacity: 0.9, transparent: true } ) ;
			var webcamMesh = new THREE.Mesh(geometry, material);
			webcamMesh.scale.set(0.03,0.03,0.03);
			webcamMesh.position.set(-36,56,0);
			//webcamMesh.rotation.z = Math.PI /4;
			webcamMesh.rotation.set(0,0,0);
			webcamMesh.indexId = 0;
			webcamMesh.visible = true;
			scene.add(webcamMesh);
			webcam.push(webcamMesh);

			renderer.render(scene, camera);

		});


	}

	this.initMouseDown =function(){

		for( id in sensors){

			sensorsActive.push( sensors[id] );
		}

		/*document.removeEventListener( 'mousedown' );*/
		document.addEventListener( 'mousedown', onMouseDown, false );
	}


	function onMouseDown( event ) {

		var projector = new THREE.Projector();

		event.preventDefault();
		event.stopPropagation();

		var vector = new THREE.Vector3( ( event.clientX / window.innerWidth ) * 2 - 1, - ( event.clientY / window.innerHeight ) * 2 + 1, 0.5 );

		projector.unprojectVector( vector, camera );

		var raycaster = new THREE.Raycaster( camera.position, vector.sub( camera.position ).normalize() );

		var intersects = raycaster.intersectObjects( sensorsActive );

		if ( intersects.length > 0 ) {

			intersects[ 0 ].object.material.color.setHex( Math.random() * 0xffffff );

		}

		intersects = raycaster.intersectObjects( vedio );

		if ( intersects.length > 0 ) {

			//alert('ooooo');
			//alert(intersects[0].object.id);
			isVideoRun = !isVideoRun;
			playVideo(isVideoRun);

		}

		intersects = raycaster.intersectObjects( webcam );

		if ( intersects.length > 0 ) {

			vedio[intersects[ 0 ].object.indexId].visible = !vedio[intersects[ 0 ].object.indexId].visible;
			isVideoRun = vedio[intersects[ 0 ].object.indexId].visible;
			playVideo(vedio[intersects[ 0 ].object.indexId].visible);

		}

		/*
		// Parse all the faces
		for ( var i in intersects ) {

			intersects[ i ].face.material[ 0 ].color.setHex( Math.random() * 0xffffff | 0x80000000 );

		}
		*/
	}

	function initControls(){

		//浜猴細1.5m/s,4.5m/s   555-1084m

		controls = new THREE.TrackballControls( camera , renderer.domElement , carModel,false);

		controls.keymoveSpeed = 0.04;
		controls.keyRollSpeed = Math.PI / 360;
		controls.autoForward = false;
		controls.maxDistance = 1000;

		//controls.resetCamera();

	}

	function render() {

		var delta = clock.getDelta();
		controls.update( delta );

		water.render();
		water.material.uniforms.time.value += 1.0 / 60.0;

		if(isVideoRun === true){

			if ( video.readyState === video.HAVE_ENOUGH_DATA ) {

				imageContext.drawImage( video, 0, 0 );
				if ( texture ) texture.needsUpdate = true;

			}
		}

		renderer.render( scene, camera );

	}

	function weather(message){
		//alert(message);
		document.getElementById("weather").innerHTML=message;

	}

	function initStats(container){
		stats = new Stats();
		container.appendChild( stats.domElement );
	}

	function setSensor(id,singleData){

		sensors[id].material = eval(singleData["color"]);
		textmesh[id].material = makeTextSprite(singleData["value"]);
		renderer.render( scene, camera );

	}

	function animate() {

		requestAnimationFrame( animate, renderer.domElement );

		//stats.update();

		render();
	}

	function makeTextSprite( message, parameters ){

		if ( parameters === undefined ) parameters = {};

		var auto = parameters.hasOwnProperty("auto") ? parameters["auto"] : false;
		var backgroundWidth = parameters.hasOwnProperty("width") ? parameters["width"] : 145;
		var backgroundHeight = parameters.hasOwnProperty("height") ? parameters["height"] : 36;
		var fontface = parameters.hasOwnProperty("fontface") ? parameters["fontface"] : "Arial";
		var fontsize = parameters.hasOwnProperty("fontsize") ? parameters["fontsize"] : 12;
		var borderThickness = parameters.hasOwnProperty("borderThickness") ? parameters["borderThickness"] : 4;
		var borderColor = parameters.hasOwnProperty("borderColor") ?parameters["borderColor"] : { r:96, g:131, b:203, a:1.0 };
		var backgroundColor = parameters.hasOwnProperty("backgroundColor") ?parameters["backgroundColor"] : { r:255, g:255, b:255, a:1.0 };
		var spriteAlignment =new THREE.Vector2( 1, -1 );

		var canvas2d = document.createElement('canvas');

		var context = canvas2d.getContext('2d');
		context.font = "bold " + fontsize + "px " + fontface;

		if(auto){
			backgroundWidth = 0;
			var metrics = new Array;
			for(i=0;i<message.length;i++){
				metrics[i] = context.measureText( message[i] );
				backgroundWidth = metrics[i].width>backgroundWidth?metrics[i].width:backgroundWidth;
			}
		}

		backgroundHeight=(auto)?((fontsize + borderThickness)*(message.length+0.2)):backgroundHeight;

		context.fillStyle   = "rgba(" + backgroundColor.r + "," + backgroundColor.g + ","+ backgroundColor.b + "," + backgroundColor.a + ")";
		context.strokeStyle = "rgba(" + borderColor.r + "," + borderColor.g + ","+ borderColor.b + "," + borderColor.a + ")";
		context.lineWidth = borderThickness;
		roundRect(context, borderThickness/2, borderThickness/2, backgroundWidth + borderThickness*3, backgroundHeight + borderThickness*0.5, 6);

		context.fillStyle = "rgba(0, 0, 0, 1.0)";
		for(i=0;i<message.length;i++){
			context.fillText( message[i], borderThickness*2, (fontsize + borderThickness)*(i+1));
		}

		var texture = new THREE.Texture(canvas2d)
		texture.needsUpdate = true;

		var spriteMaterial = new THREE.SpriteMaterial({ map: texture, useScreenCoordinates: false, alignment: spriteAlignment } );

		return spriteMaterial;
	}

	function roundRect(ctx, x, y, w, h, r) {

		ctx.beginPath();
		ctx.moveTo(x+r, y);

		ctx.lineTo(x+w-r, y);
		ctx.quadraticCurveTo(x+w, y, x+w, y+r);

		ctx.lineTo(x+w, y+h-r);
		ctx.quadraticCurveTo(x+w, y+h, x+w-r, y+h);

		ctx.lineTo(x+r, y+h);
		ctx.quadraticCurveTo(x, y+h, x, y+h-r);

		ctx.lineTo(x, y+r);
		ctx.quadraticCurveTo(x, y, x+r, y);

		ctx.closePath();
		ctx.fill();
		ctx.stroke();

	}

	function inArray(needle,array){

		if(typeof needle=="string"||typeof needle=="number"){
			for(var i in array){
				if(needle===array[i]){
					return true;
				}
			}
			return false;
		}

	}

	function intPo(po){

		po['x']=isNaN(parseFloat(po['x']))?0:parseFloat(po['x']);
		po['y']=isNaN(parseFloat(po['y']))?0:parseFloat(po['y']);
		po['z']=isNaN(parseFloat(po['z']))?0:parseFloat(po['z']);

		return po;
	}

	function ajaxFunction( func,url ){
		var xmlHttp;
		try{
		// Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();    // 瀹炰緥鍖栧璞�
		}
		catch( e ){
		 // Internet Explorer
			try{
				xmlHttp = new ActiveXObject( "Msxml2.XMLHTTP" );
			}
			catch ( e ){
				try{
					xmlHttp = new ActiveXObject( "Microsoft.XMLHTTP" );
				}
				catch( e ){
					alert("鎮ㄧ殑娴忚鍣ㄤ笉鏀寔AJAX锛�");
					return false;
				}
			}
		}
		xmlHttp.onreadystatechange=function(){
			if (xmlHttp.readyState==4 && xmlHttp.status==200){
				func(xmlHttp.responseText);
			}
		}
		xmlHttp.open("GET",url,true);
		xmlHttp.send();
	}

	function whauto(){

		WIDTH = window.innerWidth;
		HEIGHT = window.innerHeight;

	}


};
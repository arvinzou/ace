/**
 * @author Eberhard Graether / http://egraether.com/
 * @author Mark Lundin 	/ http://mark-lundin.com
 */

THREE.TrackballControls = function ( object, domElement, carModel , hot) {

	var _this = this;
	var STATE = { NONE: -1, ROTATE: 0, ZOOM: 1, PAN: 2, TOUCH_ROTATE: 3, TOUCH_ZOOM_PAN: 4 };

	this.object = object;
	this.domElement = ( domElement !== undefined ) ? domElement : document;
	//susanadd
	this.hadCar = false;
	this.carModel = ( carModel !== undefined ) ? carModel : null;
	this.controlsCar = {moveForward: false,	moveBackward: false,moveLeft: false,moveRight: false};
	this.cameraFollowCar = true;
	this.hot = ( hot !== undefined ) ? hot : false;

	//end
	// API

	this.enabled = true;

	var MODE = { defaultEnabled: true, beeEnabled: false, personEnabled: false , carEnabled:false}

	this.screen = { left: 0, top: 0, width: 0, height: 0 };

	this.rotateSpeed = 1.0;
	this.zoomSpeed = 1.2;
	this.panSpeed = 0.3;

	this.noRotate = false;
	this.noZoom = false;
	this.noPan = false;
	this.noRoll = false;

	this.staticMoving = false;
	this.dynamicDampingFactor = 0.2;

	this.minDistance = 0;
	this.maxDistance = Infinity;

	//susanadd
	//this.keymoveSpeed = 1.0;
	this.keymoveSpeed = 0.005;
	this.keyRollSpeed = 0.005;

	this.keymoveSpeedMultiplier = 1.0;

	this.autoForward = false;

	var personPosition = new THREE.Vector3();
	//end

	//this.keys = [ 65 /*A*/, 83 /*S*/, 68 /*D*/ ];

	// internals

	this.target = new THREE.Vector3();

	var EPS = 0.000001;

	var lastPosition = new THREE.Vector3();

	var _state = STATE.NONE,
	_prevState = STATE.NONE,

	_eye = new THREE.Vector3(),

	_rotateStart = new THREE.Vector3(),
	_rotateEnd = new THREE.Vector3(),

	_zoomStart = new THREE.Vector2(),
	_zoomEnd = new THREE.Vector2(),

	_touchZoomDistanceStart = 0,
	_touchZoomDistanceEnd = 0,

	_panStart = new THREE.Vector2(),
	_panEnd = new THREE.Vector2();

	//susanadd
	this.tmpQuaternion = new THREE.Quaternion();

	this.moveState = { up: 0, down: 0, left: 0, right: 0, forward: 0, back: 0, pitchUp: 0, pitchDown: 0, yawLeft: 0, yawRight: 0, rollLeft: 0, rollRight: 0 };
	this.moveVector = new THREE.Vector3( 0, 0, 0 );
	this.rotationVector = new THREE.Vector3( 0, 0, 0 );
	//end

	// for reset

	this.target0 = this.target.clone();
	this.position0 = this.object.position.clone();
	this.up0 = this.object.up.clone();

	// events

	var changeEvent = { type: 'change' };
	var startEvent = { type: 'start'};
	var endEvent = { type: 'end'};


	// methods

	this.handleResize = function () {

		if ( this.domElement === document ) {

			this.screen.left = 0;
			this.screen.top = 0;
			this.screen.width = window.innerWidth;
			this.screen.height = window.innerHeight;

		} else {

			var box = this.domElement.getBoundingClientRect();
			// adjustments come from similar code in the jquery offset() function
			var d = this.domElement.ownerDocument.documentElement;
			this.screen.left = box.left + window.pageXOffset - d.clientLeft;
			this.screen.top = box.top + window.pageYOffset - d.clientTop;
			this.screen.width = box.width;
			this.screen.height = box.height;

		}

	};

	this.handleEvent = function ( event ) {

		if ( typeof this[ event.type ] == 'function' ) {

			this[ event.type ]( event );

		}

	};

	var getMouseOnScreen = ( function () {

		var vector = new THREE.Vector2();

		return function ( pageX, pageY ) {

			vector.set(
				( pageX - _this.screen.left ) / _this.screen.width,
				( pageY - _this.screen.top ) / _this.screen.height
			);

			return vector;

		};

	}() );

	var getMouseProjectionOnBall = ( function () {

		var vector = new THREE.Vector3();
		var objectUp = new THREE.Vector3();
		var mouseOnBall = new THREE.Vector3();

		return function ( pageX, pageY ) {

			mouseOnBall.set(
				( pageX - _this.screen.width * 0.5 - _this.screen.left ) / (_this.screen.width*.5),
				( _this.screen.height * 0.5 + _this.screen.top - pageY ) / (_this.screen.height*.5),
				0.0
			);

			var length = mouseOnBall.length();

			if ( _this.noRoll ) {

				if ( length < Math.SQRT1_2 ) {

					mouseOnBall.z = Math.sqrt( 1.0 - length*length );

				} else {

					mouseOnBall.z = .5 / length;

				}

			} else if ( length > 1.0 ) {

				mouseOnBall.normalize();

			} else {

				mouseOnBall.z = Math.sqrt( 1.0 - length * length );

			}

			_eye.copy( _this.object.position ).sub( _this.target );

			vector.copy( _this.object.up ).setLength( mouseOnBall.y )
			vector.add( objectUp.copy( _this.object.up ).cross( _eye ).setLength( mouseOnBall.x ) );
			vector.add( _eye.setLength( mouseOnBall.z ) );

			return vector;

		};

	}() );

	this.rotateCamera = (function(){

		var axis = new THREE.Vector3(),
			quaternion = new THREE.Quaternion();


		return function () {

			var angle = Math.acos( _rotateStart.dot( _rotateEnd ) / _rotateStart.length() / _rotateEnd.length() );

			if ( angle ) {



				axis.crossVectors( _rotateStart, _rotateEnd ).normalize();

				angle *= _this.rotateSpeed;

				quaternion.setFromAxisAngle( axis, -angle );

				_eye.applyQuaternion( quaternion );
				_this.object.up.applyQuaternion( quaternion );

				_rotateEnd.applyQuaternion( quaternion );

				if ( _this.staticMoving ) {

					_rotateStart.copy( _rotateEnd );

				} else {

					quaternion.setFromAxisAngle( axis, angle * ( _this.dynamicDampingFactor - 1.0 ) );
					_rotateStart.applyQuaternion( quaternion );

				}

			}
		}

	}());

	this.zoomCamera = function () {

		if ( _state === STATE.TOUCH_ZOOM_PAN ) {

			var factor = _touchZoomDistanceStart / _touchZoomDistanceEnd;
			_touchZoomDistanceStart = _touchZoomDistanceEnd;
			_eye.multiplyScalar( factor );

		} else {

			var factor = 1.0 + ( _zoomEnd.y - _zoomStart.y ) * _this.zoomSpeed;

			if ( factor !== 1.0 && factor > 0.0 ) {

				_eye.multiplyScalar( factor );

				if ( _this.staticMoving ) {

					_zoomStart.copy( _zoomEnd );

				} else {

					_zoomStart.y += ( _zoomEnd.y - _zoomStart.y ) * this.dynamicDampingFactor;

				}

			}

		}

	};

	this.panCamera = (function(){

		var mouseChange = new THREE.Vector2(),
			objectUp = new THREE.Vector3(),
			pan = new THREE.Vector3();

		return function () {

			mouseChange.copy( _panEnd ).sub( _panStart );

			if ( mouseChange.lengthSq() ) {

				mouseChange.multiplyScalar( _eye.length() * _this.panSpeed );

				pan.copy( _eye ).cross( _this.object.up ).setLength( mouseChange.x );
				pan.add( objectUp.copy( _this.object.up ).setLength( mouseChange.y ) );

				_this.object.position.add( pan );
				_this.target.add( pan );

				if ( _this.staticMoving ) {

					_panStart.copy( _panEnd );

				} else {

					_panStart.add( mouseChange.subVectors( _panEnd, _panStart ).multiplyScalar( _this.dynamicDampingFactor ) );

				}

			}
		}

	}());

	this.checkDistances = function () {

		if ( !_this.noZoom || !_this.noPan ) {

			if ( _eye.lengthSq() > _this.maxDistance * _this.maxDistance ) {

				_this.object.position.addVectors( _this.target, _eye.setLength( _this.maxDistance ) );

			}

			if ( _eye.lengthSq() < _this.minDistance * _this.minDistance ) {

				_this.object.position.addVectors( _this.target, _eye.setLength( _this.minDistance ) );

			}

		}

	};

	//susanadd
	this.updateMovementVector = function() {

		if ( MODE.beeEnabled === false && MODE.personEnabled === false ) return;

		var forward = ( this.moveState.forward || ( this.autoForward && !this.moveState.back ) ) ? 1 : 0;

		this.moveVector.x = ( -this.moveState.left    + this.moveState.right );
		this.moveVector.y = ( -this.moveState.down    + this.moveState.up );
		this.moveVector.z = ( -forward + this.moveState.back );

		//console.log( 'move:', [ this.moveVector.x, this.moveVector.y, this.moveVector.z ] );

	};

	this.updateRotationVector = function() {

		this.rotationVector.x = ( -this.moveState.pitchDown + this.moveState.pitchUp );
		this.rotationVector.y = ( -this.moveState.yawRight  + this.moveState.yawLeft );
		this.rotationVector.z = ( -this.moveState.rollRight + this.moveState.rollLeft );

		//console.log( 'rotate:', [ this.rotationVector.x, this.rotationVector.y, this.rotationVector.z ] );

	};

	this.getContainerDimensions = function() {

		if ( this.domElement != document ) {

			return {
				size	: [ this.domElement.offsetWidth, this.domElement.offsetHeight ],
				offset	: [ this.domElement.offsetLeft,  this.domElement.offsetTop ]
			};

		} else {

			return {
				size	: [ window.innerWidth, window.innerHeight ],
				offset	: [ 0, 0 ]
			};

		}

	};

	function bind( scope, fn ) {

		return function () {

			fn.apply( scope, arguments );

		};

	};
	//end

	this.update = function (delta) {

		//susanadd

		if(this.hadCar ===true && MODE.carEnabled === true){

				this.carModel.updateCarModel( delta, this.controlsCar );


				var point = verticalPoint( this.carModel.root.position.x , this.carModel.root.position.y , this.carModel.root.position.z );

				this.carModel.root.position.x = point.x;
				this.carModel.root.position.y = point.y;
				this.carModel.root.position.z = point.z;

				//console.log('x:'+this.carModel.root.position.x+' y:'+this.carModel.root.position.y+' z:'+this.carModel.root.position.z +' f:'+this.controlsCar.moveForward);
			}

		if( MODE.personEnabled === true || MODE.beeEnabled === true ){

			var moveMult = this.keymoveSpeedMultiplier * this.keymoveSpeed;
			var rotMult = this.keyRollSpeed;

			//personPosition.y = _this.object.position.y;

			this.object.translateX( this.moveVector.x * moveMult );
			this.object.translateY( this.moveVector.y * moveMult );
			this.object.translateZ( this.moveVector.z * moveMult );

			if(MODE.personEnabled === true){

				var point = verticalPoint( _this.object.position.x , _this.object.position.y , _this.object.position.z );

				_this.object.position.x = point.x;
				_this.object.position.y = point.y+2;
				_this.object.position.z = point.z;

			}

			//_this.object.position.y = MODE.personEnabled === true? personPosition.y:_this.object.position.y;

			this.tmpQuaternion.set( this.rotationVector.x * rotMult, this.rotationVector.y * rotMult, this.rotationVector.z * rotMult, 1 ).normalize();
			this.object.quaternion.multiply( this.tmpQuaternion );

			// expose the rotation vector for convenience
			this.object.rotation.setFromQuaternion( this.object.quaternion, this.object.rotation.order );
			//end
		}

		_eye.subVectors( _this.object.position, _this.target );

		if ( !_this.noRotate ) {

			_this.rotateCamera();

		}

		if ( !_this.noZoom ) {

			_this.zoomCamera();

		}

		if ( !_this.noPan ) {

			_this.panCamera();

		}

		_this.object.position.addVectors( _this.target, _eye );

		_this.checkDistances();

		if(MODE.defaultEnabled === true){_this.object.lookAt( _this.target );}

		if ( lastPosition.distanceToSquared( _this.object.position ) > EPS ) {

			_this.dispatchEvent( changeEvent );

			lastPosition.copy( _this.object.position );

		}

		//debugger;

		//console.log('x:'+_this.object.position.x+' y:'+_this.object.position.y+' z:'+_this.object.position.z+' x:'+_this.target.x+' y:'+_this.target.y+' z:'+_this.target.z);

	};

//	this.reset = function () {
//
//		debugger;
//
//		_state = STATE.NONE;
//		_prevState = STATE.NONE;
//
//		_this.target.copy( _this.target0 );
//		_this.object.position.copy( _this.position0 );
//		_this.object.up.copy( _this.up0 );
//
//		_eye.subVectors( _this.object.position, _this.target );
//
//		_this.object.lookAt( _this.target );
//
//		_this.dispatchEvent( changeEvent );
//
//		lastPosition.copy( _this.object.position );
//
//	};
	//susanadd
	this.resetCamera = function (parameters) {

		_state = STATE.NONE;
		_prevState = STATE.NONE;

		if ( parameters === undefined ) parameters = {};
		_this.object.position.x = parameters.hasOwnProperty("camera_x") ? parameters["camera_x"] : _this.position0.x;
		_this.object.position.y = parameters.hasOwnProperty("camera_y") ? parameters["camera_y"] : _this.position0.y;
		_this.object.position.z = parameters.hasOwnProperty("camera_z") ? parameters["camera_z"] : _this.position0.z;
		_this.target.x = parameters.hasOwnProperty("target_x") ? parameters["target_x"] : _this.target0.x;
		_this.target.y = parameters.hasOwnProperty("target_y") ? parameters["target_y"] : _this.target0.y;
		_this.target.z = parameters.hasOwnProperty("target_z") ? parameters["target_z"] : _this.target0.z;
		_this.object.up.x = parameters.hasOwnProperty("up_x") ? parameters["up_x"] : _this.up0.x;
		_this.object.up.y = parameters.hasOwnProperty("up_y") ? parameters["up_y"] : _this.up0.y;
		_this.object.up.z = parameters.hasOwnProperty("up_z") ? parameters["up_z"] : _this.up0.z;

		//_this.target.copy( _this.target0 );
		//_this.object.position.copy( _this.position0 );
		//_this.object.up.copy( _this.up0 );

		_eye.subVectors( _this.object.position, _this.target );

		_this.object.lookAt( _this.target );

		_this.dispatchEvent( changeEvent );

		lastPosition.copy( _this.object.position );

	};
	//end

	this.setMode = function(mode){

		if(!MODE.hasOwnProperty(mode)){mode = 'defaultEnabled'}

		MODE = { defaultEnabled: false, beeEnabled: false, personEnabled: false  , carEnabled:false }
		MODE[mode] = true;

		var point = verticalPoint( _this.carModel.root.position.x , _this.carModel.root.position.y , _this.carModel.root.position.z );
		//var point = verticalPoint( _this.object.position.x , _this.object.position.y , _this.object.position.z );

		switch(mode){

			case 'defaultEnabled': 	this.carModel.root.remove( _this.object );this.resetCamera();/*document.removeEventListener( 'mousedown');*/if(this.hot === true){document.addEventListener( 'mousedown', mousedown, false );} $('.dropdownLi').removeClass('activeLi');$('.dropdownLiBridge').addClass('activeLi'); break;
			case 'beeEnabled': 	this.carModel.root.remove( _this.object );this.resetCamera({"camera_x":point.x,"camera_y":point.y+20,"camera_z":point.z,"target_x":0,"target_y":point.y+20,"target_z":point.z});if(this.hot === true){initmouse();} $('.dropdownLi').removeClass('activeLi');$('.dropdownLiHiking').addClass('activeLi'); break;
			case 'personEnabled': 	this.carModel.root.remove( _this.object );this.resetCamera({"camera_x":point.x,"camera_y":point.y+1,"camera_z":point.z,"target_x":0,"target_y":point.y+1,"target_z":point.z}); if(this.hot === true){initmouse();} $('.dropdownLi').removeClass('activeLi');$('.dropdownLiHiking').addClass('activeLi'); break;
			case 'carEnabled': 	this.resetCamera({"camera_x":0,"camera_y":1,"camera_z":-3.6});this.carModel.root.add( _this.object ); $('.dropdownLi').removeClass('activeLi');$('.dropdownLiHiking').addClass('activeLi'); break;

		}

	}

	// listeners

//	function keydown( event ) {
//
//		if ( _this.enabled === false ) return;
//
//		window.removeEventListener( 'keydown', keydown );
//
//		_prevState = _state;
//
//		if ( _state !== STATE.NONE ) {
//
//			return;
//
//		} else if ( event.keyCode === _this.keys[ STATE.ROTATE ] && !_this.noRotate ) {
//
//			_state = STATE.ROTATE;
//
//		} else if ( event.keyCode === _this.keys[ STATE.ZOOM ] && !_this.noZoom ) {
//
//			_state = STATE.ZOOM;
//
//		} else if ( event.keyCode === _this.keys[ STATE.PAN ] && !_this.noPan ) {
//
//			_state = STATE.PAN;
//
//		}
//
//	}

//	function keyup( event ) {
//
//		if ( _this.enabled === false ) return;
//
//		_state = _prevState;
//
//		window.addEventListener( 'keydown', keydown, false );
//
//	}

	function mousedown( event ) {

		if ( MODE.defaultEnabled === false || _this.enabled === false) return;

		event.preventDefault();
		event.stopPropagation();

		if ( _state === STATE.NONE ) {

			_state = event.button;

		}

		if ( _state === STATE.ROTATE && !_this.noRotate ) {

			_rotateStart.copy( getMouseProjectionOnBall( event.pageX, event.pageY ) );
			_rotateEnd.copy( _rotateStart );

		} else if ( _state === STATE.ZOOM && !_this.noZoom ) {

			_zoomStart.copy( getMouseOnScreen( event.pageX, event.pageY ) );
			_zoomEnd.copy(_zoomStart);

		} else if ( _state === STATE.PAN && !_this.noPan ) {

			_panStart.copy( getMouseOnScreen( event.pageX, event.pageY ) );
			_panEnd.copy(_panStart)

		}

		document.addEventListener( 'mousemove', mousemove, false );
		document.addEventListener( 'mouseup', mouseup, false );

		_this.dispatchEvent( startEvent );

	}

	function mousemove( event ) {

		if ( MODE.defaultEnabled === false || _this.enabled === false ) return;

		event.preventDefault();
		event.stopPropagation();

		if ( _state === STATE.ROTATE && !_this.noRotate ) {

			_rotateEnd.copy( getMouseProjectionOnBall( event.pageX, event.pageY ) );

		} else if ( _state === STATE.ZOOM && !_this.noZoom ) {

			_zoomEnd.copy( getMouseOnScreen( event.pageX, event.pageY ) );

		} else if ( _state === STATE.PAN && !_this.noPan ) {

			_panEnd.copy( getMouseOnScreen( event.pageX, event.pageY ) );

		}

	}

	function mouseup( event ) {

		if (  MODE.defaultEnabled === false || _this.enabled === false ) return;

		event.preventDefault();
		event.stopPropagation();

		_state = STATE.NONE;

		document.removeEventListener( 'mousemove', mousemove );
		document.removeEventListener( 'mouseup', mouseup );
		_this.dispatchEvent( endEvent );

	}

	function mousewheel( event ) {

		if (  MODE.defaultEnabled === false || _this.enabled === false ) return;

		event.preventDefault();
		event.stopPropagation();

		var delta = 0;

		if ( event.wheelDelta ) { // WebKit / Opera / Explorer 9

			delta = event.wheelDelta / 40;

		} else if ( event.detail ) { // Firefox

			delta = - event.detail / 3;

		}

		_zoomStart.y += delta * 0.01;
		_this.dispatchEvent( startEvent );
		_this.dispatchEvent( endEvent );

	}

	//susanadd
	function keydown( event ) {

		if ( _this.enabled === false) return;

		if ( event.altKey ) {

			return;

		}

		event.preventDefault();

		if(MODE.beeEnabled === true){
			switch ( event.keyCode ) {

				case 16: /* shift */ _this.keymoveSpeedMultiplier = 10; break;

				case 87: /*W*/ _this.moveState.forward = 1; break;
				case 83: /*S*/ _this.moveState.back = 1; break;

				case 65: /*A*/ _this.moveState.left = 1; break;
				case 68: /*D*/ _this.moveState.right = 1; break;

				case 82: /*R*/ _this.moveState.up = 1; break;
				case 70: /*F*/ _this.moveState.down = 1; break;

				case 38: /*up*/ _this.moveState.pitchUp = 1; break;
				case 40: /*down*/ _this.moveState.pitchDown = 1; break;

				case 37: /*left*/ _this.moveState.yawLeft = 1; break;
				case 39: /*right*/ _this.moveState.yawRight = 1; break;

				case 81: /*Q*/ _this.moveState.rollLeft = 1; break;
				case 69: /*E*/ _this.moveState.rollRight = 1; break;

			}
		}

		if(MODE.personEnabled === true){
			switch ( event.keyCode ) {

				case 16: /* shift */ _this.keymoveSpeedMultiplier = 3; break;

				case 87: /*W*/ _this.moveState.forward = 1; break;
				case 83: /*S*/ _this.moveState.back = 1; break;

				case 65: /*A*/ _this.moveState.left = 1; break;
				case 68: /*D*/ _this.moveState.right = 1; break;

				case 38: /*up*/ _this.moveState.pitchUp = 1; break;
				case 40: /*down*/ _this.moveState.pitchDown = 1; break;

				case 37: /*left*/ _this.moveState.yawLeft = 1; break;
				case 39: /*right*/ _this.moveState.yawRight = 1; break;

			}
		}

		if( _this.hadCar ===true && MODE.carEnabled === true){
			switch( event.keyCode ) {

				case 87: /*W*/ 	_this.controlsCar.moveForward = true; break;

				case 83: /*S*/ 	 _this.controlsCar.moveBackward = true; break;

				case 65: /*A*/   _this.controlsCar.moveLeft = true; break;

				case 68: /*D*/    _this.controlsCar.moveRight = true; break;

			}
		}

		_this.updateMovementVector();
		_this.updateRotationVector();

	};

	function keyup(event ) {

		if ( _this.enabled === false) return;

		switch ( event.keyCode ) {

			case 112: /*F1*/ _this.setMode('defaultEnabled'); break;
			case 113: /*F2*/ _this.setMode('personEnabled');  break;
			case 114: /*F3*/ _this.setMode('beeEnabled');  break;
			case 115: /*F4*/ _this.setMode('carEnabled');  break;

		}

		if(MODE.beeEnabled === true){
			switch( event.keyCode ) {

				case 16: /* shift */ _this.keymoveSpeedMultiplier = 1; break;

				case 87: /*W*/ _this.moveState.forward = 0; break;
				case 83: /*S*/ _this.moveState.back = 0; break;

				case 65: /*A*/ _this.moveState.left = 0; break;
				case 68: /*D*/ _this.moveState.right = 0; break;

				case 82: /*R*/ _this.moveState.up = 0; break;
				case 70: /*F*/ _this.moveState.down = 0; break;

				case 38: /*up*/ _this.moveState.pitchUp = 0; break;
				case 40: /*down*/ _this.moveState.pitchDown = 0; break;

				case 37: /*left*/ _this.moveState.yawLeft = 0; break;
				case 39: /*right*/ _this.moveState.yawRight = 0; break;

				case 81: /*Q*/ _this.moveState.rollLeft = 0; break;
				case 69: /*E*/ _this.moveState.rollRight = 0; break;

			}
		}

		if(MODE.personEnabled === true){
			switch( event.keyCode ) {

				case 16: /* shift */ _this.keymoveSpeedMultiplier = 1; break;

				case 87: /*W*/ _this.moveState.forward = 0; break;
				case 83: /*S*/ _this.moveState.back = 0; break;

				case 65: /*A*/ _this.moveState.left = 0; break;
				case 68: /*D*/ _this.moveState.right = 0; break;

				case 38: /*up*/ _this.moveState.pitchUp = 0; break;
				case 40: /*down*/ _this.moveState.pitchDown = 0; break;

				case 37: /*left*/ _this.moveState.yawLeft = 0; break;
				case 39: /*right*/ _this.moveState.yawRight = 0; break;

			}
		}

		if(_this.hadCar ===true && MODE.carEnabled === true){
			switch( event.keyCode ) {

				case 87: /*W*/ _this.controlsCar.moveForward = false; break;

				case 83: /*S*/ 	_this.controlsCar.moveBackward = false; break;

				case 65: /*A*/ 	_this.controlsCar.moveLeft = false; break;

				case 68: /*D*/ 	_this.controlsCar.moveRight = false; break;

			}
		}

		_this.updateMovementVector();
		_this.updateRotationVector();


	};
	//end

	function touchstart( event ) {

		if (  MODE.defaultEnabled === false || _this.enabled === false ) return;

		switch ( event.touches.length ) {

			case 1:
				_state = STATE.TOUCH_ROTATE;
				_rotateStart.copy( getMouseProjectionOnBall( event.touches[ 0 ].pageX, event.touches[ 0 ].pageY ) );
				_rotateEnd.copy( _rotateStart );
				break;

			case 2:
				_state = STATE.TOUCH_ZOOM_PAN;
				var dx = event.touches[ 0 ].pageX - event.touches[ 1 ].pageX;
				var dy = event.touches[ 0 ].pageY - event.touches[ 1 ].pageY;
				_touchZoomDistanceEnd = _touchZoomDistanceStart = Math.sqrt( dx * dx + dy * dy );

				var x = ( event.touches[ 0 ].pageX + event.touches[ 1 ].pageX ) / 2;
				var y = ( event.touches[ 0 ].pageY + event.touches[ 1 ].pageY ) / 2;
				_panStart.copy( getMouseOnScreen( x, y ) );
				_panEnd.copy( _panStart );
				break;

			default:
				_state = STATE.NONE;

		}
		_this.dispatchEvent( startEvent );


	}

	function touchmove( event ) {

		if ( MODE.defaultEnabled === false || _this.enabled === false ) return;

		event.preventDefault();
		event.stopPropagation();

		switch ( event.touches.length ) {

			case 1:
				_rotateEnd.copy( getMouseProjectionOnBall( event.touches[ 0 ].pageX, event.touches[ 0 ].pageY ) );
				break;

			case 2:
				var dx = event.touches[ 0 ].pageX - event.touches[ 1 ].pageX;
				var dy = event.touches[ 0 ].pageY - event.touches[ 1 ].pageY;
				_touchZoomDistanceEnd = Math.sqrt( dx * dx + dy * dy );

				var x = ( event.touches[ 0 ].pageX + event.touches[ 1 ].pageX ) / 2;
				var y = ( event.touches[ 0 ].pageY + event.touches[ 1 ].pageY ) / 2;
				_panEnd.copy( getMouseOnScreen( x, y ) );
				break;

			default:
				_state = STATE.NONE;

		}

	}

	function touchend( event ) {

		if (  MODE.defaultEnabled === false || _this.enabled === false ) return;

		switch ( event.touches.length ) {

			case 1:
				_rotateEnd.copy( getMouseProjectionOnBall( event.touches[ 0 ].pageX, event.touches[ 0 ].pageY ) );
				_rotateStart.copy( _rotateEnd );
				break;

			case 2:
				_touchZoomDistanceStart = _touchZoomDistanceEnd = 0;

				var x = ( event.touches[ 0 ].pageX + event.touches[ 1 ].pageX ) / 2;
				var y = ( event.touches[ 0 ].pageY + event.touches[ 1 ].pageY ) / 2;
				_panEnd.copy( getMouseOnScreen( x, y ) );
				_panStart.copy( _panEnd );
				break;

		}

		_state = STATE.NONE;
		_this.dispatchEvent( endEvent );

	}

//	this.domElement.addEventListener( 'contextmenu', function ( event ) { event.preventDefault(); }, false );
//
//	this.domElement.addEventListener( 'mousedown', mousedown, false );
//
//	this.domElement.addEventListener( 'mousewheel', mousewheel, false );
//	this.domElement.addEventListener( 'DOMMouseScroll', mousewheel, false ); // firefox
//
//	this.domElement.addEventListener( 'touchstart', touchstart, false );
//	this.domElement.addEventListener( 'touchend', touchend, false );
//	this.domElement.addEventListener( 'touchmove', touchmove, false );

	this.domElement.addEventListener( 'contextmenu', function ( event ) { event.preventDefault(); }, false );

	document.addEventListener( 'mousedown', mousedown, false );

	this.domElement.addEventListener( 'mousewheel', mousewheel, false );
	this.domElement.addEventListener( 'DOMMouseScroll', mousewheel, false ); // firefox

	this.domElement.addEventListener( 'touchstart', touchstart, false );
	this.domElement.addEventListener( 'touchend', touchend, false );
	this.domElement.addEventListener( 'touchmove', touchmove, false );



//	window.addEventListener( 'keydown', keydown, false );
//	window.addEventListener( 'keyup', keyup, false );

	//susanadd
	window.addEventListener( 'keydown', keydown, false );
	window.addEventListener( 'keyup', keyup, false );

	this.updateMovementVector();
	this.updateRotationVector();
	//end

	this.handleResize();

	// force an update at start

	this.update();

	//susanadd
	this.resetCamera();

};

THREE.TrackballControls.prototype = Object.create( THREE.EventDispatcher.prototype );
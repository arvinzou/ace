var faceset_token="c159ecda6f1e7fac4de5d5e85fe42948";
var api_key="pR2lqv_5hDyvmQSNsZlnFl8DfPDT-MjJ";
var api_secret="Xb9cAcjc23_p_Igj0we-Al0u4eE6cRer";
var gender={"Male":"男","Female":"女"};
var glass={"None":"不佩戴眼镜","Dark":"佩戴墨镜","Normal":"佩戴普通眼镜"};
var headpose={"pitch_angle":"抬头","roll_angle":"旋转（平面旋转）","yaw_angle":"摇头"};
var emotion={"anger":"愤怒","disgust":"厌恶","fear":"恐惧","happiness":"高兴","neutral":"平静","sadness":"伤心","surprise":"惊讶"};
var ethnicity={"Asian":"亚洲人","White":"白人","Black":"黑人"};
function detect(id,image_url){
    console.log(image_url);
	$.ajax({
		type : "post",
		url : 'https://api-cn.faceplusplus.com/facepp/v3/detect',
		data : {
			api_key : api_key,
			api_secret:api_secret,
			image_url:image_url,
			return_attributes:"gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus"
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    console.log(rst);
		    if(rst["error_message"]){
		        alert(rst["error_message"]);
		        return;
		    }
		    var face=rst.faces[0];
		    var faceToken=face.face_token;
		    var attributes=JSON.stringify(face.attributes);
		    updatePersonFaceTokenAttributes(id,faceToken,attributes);

		},
		error : function(rst) {
			alert("请求限制，请再尝试。");
		}
	});
}

function removeface(face_token){
    console.log(face_token);
	$.ajax({
		type : "post",
		url : 'https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface',
		data : {
			api_key : api_key,
			api_secret:api_secret,
			faceset_token:faceset_token,
			face_tokens:face_token
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    console.log(rst);
		    if(rst["error_message"]){
		        alert(rst["error_message"]);
		        return;
		    }
		    addface(face_token);
		},
		error : function(rst) {
			alert("请求限制，请再尝试。");
		}
	});
}
function addface(face_token){
    console.log(face_token);
	$.ajax({
		type : "post",
		url : 'https://api-cn.faceplusplus.com/facepp/v3/faceset/addface',
		data : {
			api_key : api_key,
			api_secret:api_secret,
			faceset_token:faceset_token,
			face_tokens:face_token
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    console.log(rst);
		    if(rst["error_message"]){
		        alert(rst["error_message"]);
		        return;
		    }
		    alert("添加成功，目前数量："+rst["face_count"]);
		},
		error : function(rst) {
			alert("请求限制，请再尝试。");
		}
	});
}
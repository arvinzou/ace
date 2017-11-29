ps -ef | grep com.huacainfo.ace.face.service.FaceServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

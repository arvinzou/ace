ps -ef | grep com.huacainfo.ace.operana.service.OperanaServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

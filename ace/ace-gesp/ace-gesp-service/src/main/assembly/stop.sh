ps -ef | grep com.huacainfo.ace.gesp.service.GespServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

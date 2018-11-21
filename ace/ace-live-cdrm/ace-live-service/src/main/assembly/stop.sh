ps -ef | grep com.huacainfo.ace.live.service.LiveServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

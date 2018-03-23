ps -ef | grep com.huacainfo.ace.jxb.service.LiveServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

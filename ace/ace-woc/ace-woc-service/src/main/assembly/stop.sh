ps -ef | grep com.huacainfo.ace.woc.service.WocServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

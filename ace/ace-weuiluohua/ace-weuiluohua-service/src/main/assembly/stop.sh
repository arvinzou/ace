ps -ef | grep com.huacainfo.ace.weuiluohua.service.WeuiluohuaServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

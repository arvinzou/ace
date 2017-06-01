ps -ef | grep com.huacainfo.ace.luohua.service.LuohuaServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

ps -ef | grep com.huacainfo.ace.pwl.service.IopServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

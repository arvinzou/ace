ps -ef | grep com.huacainfo.ace.fop.service.IopServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

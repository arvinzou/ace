ps -ef | grep com.huacainfo.ace.iop.service.IopServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

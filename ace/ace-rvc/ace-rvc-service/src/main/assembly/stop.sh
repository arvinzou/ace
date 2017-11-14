ps -ef | grep com.huacainfo.ace.rvc.service.RVCServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

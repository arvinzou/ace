ps -ef | grep com.huacainfo.ace.weui.service.KernelServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

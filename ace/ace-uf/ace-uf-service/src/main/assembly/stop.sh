ps -ef | grep com.huacainfo.ace.uf.service.UfServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

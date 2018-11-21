ps -ef | grep com.huacainfo.ace.society.service.SocietyServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

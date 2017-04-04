ps -ef | grep com.huacainfo.ace.portal.service.PortalServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

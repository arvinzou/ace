ps -ef | grep com.huacainfo.ace.workflow.service.WorkflowServiceApp | grep -v grep | awk '{print $2}' | xargs kill -9

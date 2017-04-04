ps -ef | grep KernelBackendApp | grep -v grep | awk '{print $2}' | xargs kill -9

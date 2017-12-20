export CLASSPATH=$CLASSPATH:./config:./lib/*:./tlib/*
export JAVA_OPTS="-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=18802 -server -Xmn128m -Xms256m -Xmx1024m -Xss512K -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m"
export DUBBO_OPTS="-Ddubbo.registry.file=.dubbocache"
nohup java ${JAVA_OPTS} ${DUBBO_OPTS} com.huacainfo.ace.live.service.LiveServiceApp &

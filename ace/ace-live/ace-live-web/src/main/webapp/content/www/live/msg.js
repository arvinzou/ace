    var websocketMsg = null;
        /*var wxuser={
           headimgurl:"https://wx.qlogo.cn/mmopen/vi_32/Ch0hmaNn9SVq7Skvq4TIkIzVBlib23IsllMSwbRpRV8ue8CpV4T4erxULbAMp8da7PXVHyowOu4hLfKZSiarNlHg/0",
           nickname:"王昭",
           openid:"oCjYM0d0x60MkCh5LWt7bPPxUq_I"
       };*/
      //判断当前浏览器是否支持WebSocket
      if('WebSocket' in window){
          var url="ws://"+cfg.websocketurl+"/live/websocket/"+lvsCmd.urlParams.id+"/"+wxuser.openid+"/msg";
          websocketMsg = new ReconnectingWebSocket(url);
      }
      else{
          alert('Not support websocketMsg');
      }

      //连接发生错误的回调方法
      websocketMsg.onerror = function(){
            alert("onerror");
          //setMessageInnerHTML("error");
      };

      //连接成功建立的回调方法
      websocketMsg.onopen = function(event){
          //setMessageInnerHTML("open");
         // alert("onopen ok");
      };

      //接收到消息的回调方法
      websocketMsg.onmessage = function(){
          var data=JSON.parse(event.data);
            console.log(data);
          if(data.header.type==1){
             var tpl = document.getElementById('tpl-msg').innerHTML;
             var html = juicer(tpl, data);
             $("#chatContent").append(html);
            // var chatlist = document.getElementById('chatlist');
             //chatlist.scrollTop = chatlist.scrollHeight;
          }
          if(data.header.type==2){
                console.log(data.header.type);
               var tpl = document.getElementById('tpl-cmt').innerHTML;
               var html = juicer(tpl, data);
               var el="#cmtlist"+data.id;
               $(el).append(html);
                $(el).removeClass("fn-hide");
                var els="#j-remark-"+data.id+" em";
                $(els).removeClass("fn-hide");
               //var cmtlist = document.getElementById(el);
               //cmtlist.scrollTop = cmtlist.scrollHeight;
           }
           if(data.header.type==3){
              load=false;
              getReport(reportPage);
          }
          //alert(data.header.type);

      };

      //连接关闭的回调方法
      websocketMsg.onclose = function(){
          //setMessageInnerHTML("close");
      };

      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocketMsg连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function(){
          websocketMsg.close();
      };

      //将消息显示在网页上
      function setMessageInnerHTML(innerHTML){
                $("#chatContent").append(innerHTML);
             var chatlist = document.getElementById('chatContent');
             chatlist.scrollTop = chatlist.scrollHeight;
      }

      //关闭连接
      function closeWebSocket(){
          websocketMsg.close();
      }

      //发送消息
      function send(){
          var message = document.getElementById('text').value;
          websocketMsg.send(message);
      }


      function submitMsg(){

         var message={};
         message.header={
            type:1,
            wxuser:wxuser
         };
         message.content=$("#j-remarkform").find("input[name=content]").val();
         message.createTime=new Date().pattern("hh:mm:ss");
         websocketMsg.send(JSON.stringify(message));
        return false;
      }
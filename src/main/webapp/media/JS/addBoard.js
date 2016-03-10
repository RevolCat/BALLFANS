$(document).ready(function(){
    $("#tianjiaboard").validate({
        rules:{
            board_name:{
                required:true,
                minlength: 2,
                maxlength:45
            },
            board_des:{

            }
        },
        message:{
          board_name:{
              required:"ûд��",
              minlength:"�������ֶ�"
          },
            board_des:{
                required:"ûд��"
            }
        },
        submitHandler:function(form){
            $.ajax({
                url:"/topic/checkTopicUser",
                type:"post",
                dataType:"json",
                success:function(data){
                    if(data.result==0){
                        alert("please login");
                    }else if(data.result==1){
                        //alert("1");
                        var form=document.forms[0];
                        form.action="/m/addboard";
                        form.method="post";
                        form.submit();
                    }
                },
                error:function(){
                    alert("load failed");
                }
            });
        }
    });
});
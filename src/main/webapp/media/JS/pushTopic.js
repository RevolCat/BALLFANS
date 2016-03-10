$(document).ready(function(){
    $("#fabiao").validate({
        rules:{
            topic_title:{
                required:true,
                minlength: 5,
                maxlength:45
            },
           topic_text:{
                required:true,
                minlength: 5
            }
        },
        messages: {
           topic_title: {
                required:"标题不少于5个字符哦",
                minlength:"要5个字符",
               maxlength:"不要超过45个字符哦"
            },
            topic_text: {
                required: "内容还没写呢",
                minlength:"至少要5个字符哟"
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
                        form.action="/topic/pushTopic";
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




//发表主题

/*
* function pushTopic(){
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
 form.action="/topic/pushTopic";
 form.method="post";
 form.submit();
 }
 },
 error:function(){
 alert("load failed");
 }
 });


 }

 * */


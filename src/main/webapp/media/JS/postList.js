$(document).ready(function(){
    $("#pushPost").validate({
        rules:{
            post_text:{
                required:true,
                minlength: 5
            }
        },
        messages: {
            post_text: {
                required: "回复的内容还没写呢",
                minlength:"至少要5个字符哦"
            }

        },
        submitHandler:function(form){
            $.ajax({
                url:"/post/checkUser",
                type:"post",
                dataType:"json",
                success:function(data){
                    if(data.result==0){
                        alert("please login");
                    }else if(data.result==1){
                        //alert("1");
                        var form=document.forms[0];
                        form.action="/post/pushPost";
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

function deletePost(post_id){
    var r1=confirm("删除此楼？");
    if(r1==true){
        window.location.href="/post/m/deletePost?post_id="+post_id;
       alert("删除回复成功。。");
    }
    else{
        return false;
    }
}

function setDigest(topic_id){
    var r1=confirm("此主题帖设为精华？");
    if(r1==true){
        window.location.href="/post/m/setDigest?topic_id="+topic_id;
        // alert("dosnafndsa");
    }
    else{
        return false;
    }
}

function offDigest(topic_id){
    var r2=confirm("取消此精华？");
    if(r2==true){
        window.location.href="/post/m/offDigest?topic_id="+topic_id;
        // alert("dosnafndsa");
    }
    else{
        return false;
    }
}
/*
* function pushPost(){
 $.ajax({
 url:"/post/checkUser",
 type:"post",
 dataType:"json",
 success:function(data){
 if(data.result==0){
 alert("please login");
 }else if(data.result==1){
 //alert("1");
 var form=document.forms[0];
 form.action="/post/pushPost";
 form.method="post";
 form.submit();
 }
 },
 error:function(){
 alert("load failed");
 }
 });

 }
*
* */

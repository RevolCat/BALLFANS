$(document).ready(function(){

    checkUserType();
    window.setTimeout(checkUserType(),3000);
});

//检测超级管理的权限
function checkUserType(){
    $.ajax({
        url:"/user/checkUserType",
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.result==100){
                alert("无权限访问！即将返回主页......");
                window.location.href="/";
            }
            else if(data.result==101){
              //  alert("yes");
            }
        },
        error:function(){
            alert("load failed");
        }
    });
}


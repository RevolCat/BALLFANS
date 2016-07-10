$(document).ready(function(){
    checkManagerPower();
    //window.setTimeout(checkManagerPower(),3000);
});
//板块管理权限
function checkManagerPower(){
    $.ajax({
        url:"/user/checkUserTypeTopic",
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.result==202){
                alert("请先登录");
                window.location.href="/";
            }
            else if(data.result==200){
                alert("无权限访问，即将返回首页......");
                window.location.href="/";
            }
            else if(data.result==201){
                //  alert("yes");
            }
        },
        error:function(){
            alert("load failed");
        }
    });
}
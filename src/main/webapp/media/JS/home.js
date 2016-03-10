/**
 * Created by Revol on 2015/11/8.
 */
$(document).ready(function(){
checkPower();
 // window.setTimeout(checkPower(),90000);
});
function checkPower(){
    $.ajax({
        url:"/user/checkPower",
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.result==200){
            //    alert("无权限访问，即将返回首页......");
               // window.location.href="/";
            }
            else if(data.result==202){
                //alert("dfsaf");
                window.location.href="/managerhome";
            }
            else if(data.result==203){
                window.location.href="/roothome";
            }
        },
        error:function(){
          //  alert("load failed");
        }
    });
}

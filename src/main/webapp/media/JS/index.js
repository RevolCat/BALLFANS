/**
 * Created by Revol on 2015/11/7.
 */
// ����ղ�
function addFavorite(){
    var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd': 'CTRL';
    if (document.all) {
        window.external.addFavorite('http://localhost:63342/JRs%E8%AE%BE%E8%AE%A1/page/index.html#', 'JRs������̳');
    } else if (window.sidebar) {
        window.sidebar.addPanel('JRs������̳', 'http://localhost:63342/JRs%E8%AE%BE%E8%AE%A1/page/index.html#', "");
    } else {
        alert('可以使用组合键' + ctrl + ' + D 进行收藏');
    }
}


//login
function login(){
    var account=$("#user_account").val();
    var pwd= $.md5($("#password").val());
    //console.log(pwd);
    $.ajax({
        type:"post",
        url:"/user/login",
        data:{"user_account":account,"password":pwd},
        dataType:"json",
        success:function(data){
            if(data.result==5){
                alert("账号错误或不存在");
            }else if(data.result==6){
                alert("密码错误");
            }
            else if(data.result==7){
                alert("账号锁定了，请联系管理员");
            }
            else if(data.result==8){
                alert("登录成功");
                //返回到当前页面
                window.location.reload();
            }
            else if(data.result==9){
                alert("登录成功");
                window.location.href="/managerhome";
            }
            else if(data.result==10){
                alert("登录成功");
                window.location.href="/roothome";
            }
        }
    });
}


$(document).ready(function(){
    $.ajax({
        url:'/user/getCredit',
        type:'POST',
        dataType:'json',
        success: function (data) {
            console.log(data);

            $("#usercredit").html(data);
        },
        error:function(err){
            console.log(err);
        }
    });
});
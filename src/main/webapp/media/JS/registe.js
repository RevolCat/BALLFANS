/**
 * Created by Revol on 2015/12/18.
 */
$(document).ready(function(){

    $("#regform").validate({
        rules:{
            user_account:{
                required:true,
                minlength: 4
            },
            user_name:{
                required:true,
                minlength: 3
            },
            password:{
                required:true,
                minlength: 6
            },
            password2:{
                required:true,
                minlength:6,
                equalTo:"#password"
            }
        },
        messages: {
           user_account: {
               required:"输入4位以上的英文或数字哦",
               minlength:"要4位哦"
           },
            user_name: {
                required:"请输入用户名",
                minlength:"要3位以上哦"
            },
            password: {
                required: "请输入密码",
                minlength: "麻麻说密码不少于6位才安全哦"
            },
            password2: {
                required: "请输入密码",
                minlength: "麻麻说密码不少于6位才安全哦",
                equalTo: "和上面的不一样诶"
            }
        },
        submitHandler:function(form){
            var jg1=checkspace1();
            var jg2=checkspace2();
            if(jg1==false){
                alert("账号不能有空格哦");
            }
            else if(jg2==false) {
                alert("用户名不能有空格哦");
            }
            else{
                var md5password=$("#password").val();
            document.getElementById('password').value= $.md5(md5password);

                    form.action = "/user/register";
                    form.method = "post";
                    form.submit();

                }
            }
        });
});

//检测含有空格吗
function checkspace1(){
    var x1=$("#user_account").val();
    if(x1.indexOf(" ")>=0){
        return false;
    }
}
function checkspace2(){
    var x2=$("#user_name").val();
    if(x2.indexOf(" ")>=0){
        return false;
    }
}

//账号账号检测是否存在
function checkAccount(){
    var account=$("#user_account").val();
    $.ajax({
        type:"POST",
        url:"/user/checkAccount",
        data:{"user_account":account},
        dataType:"json",
        success:function(data){
            if(data.result==1){
               // alert("the name can be used");
                $("#spaName").html("<h6 class='p1' style='color:green' >可使用</h6>&nbsp;&nbsp;");
                   // $("#subbutton").removeAttr('disabled');
                setButtonStatus();
            }
            else if(data.result==2){
                //alert("the name cannot be used");
                $("#spaName").html("<p class='p2' style='color:red' >账号已存在</p>&nbsp;&nbsp;");
                $("#subbutton").attr('disabled',true);
            }
        },
        error:function(){
            alert("loading  fail");
        }
    });
}

//检测用户名是否重复
function checkName(){
    var name=$("#user_name").val();
    $.ajax({
        type:"POST",
        url:"/user/checkName",
        data:{"user_name":name},
        dataType:"json",
        success:function(data){
            if(data.result==3){
                 //alert("the name can be used");
                $("#spaName2").html("<h6 class='p1' style='color:green' >可使用&nbsp;&nbsp;</h6>&nbsp;&nbsp;");
                   // $("#subbutton").removeAttr('disabled');
                setButtonStatus();
                      }
            else if(data.result==4){
                //alert("the name cannot be used");
                $("#spaName2").html("<p  class='p2' style='color:red' >用户名已存在&nbsp;&nbsp;</p>&nbsp;&nbsp;");
                $("#subbutton").attr('disabled',true);
            }
        },
        error:function(){
            alert("loading  fail");

        }
    });
}

//account&name都可使用才能点击按钮
function setButtonStatus(){
if($("#spaName").find('h6').length>0&&$("#spaName2").find('h6').length>0){

        $("#subbutton").removeAttr('disabled');

}
}
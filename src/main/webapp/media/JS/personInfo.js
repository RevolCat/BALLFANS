/**
 * Created by Revol on 2015/12/22.
 */
$(document).ready(function(){
$("#xiugaimima").validate({
    rules:{
        currentpassword:{
            required:true
        },
        editpassword:{
            required:true,
            minlength: 6
        },
        confirmpassword:{
            required:true,
            minlength: 6,
            equalTo:"#editpassword"
        }
    },
    messages: {
        currentpassword: {
            required:"这儿没写呢",
        },
        editpassword: {
            required: "这儿还没填呢",
            minlength:"至少要6位"
        },
        confirmpassword:{
            required: "这儿还没填呢",
            minlength:"至少要6位",
            equalTo:"和上面不一样诶"
        }
    },
    submitHandler:function(){
        var password=$('#cofirmpassword').val();
        $.ajax({
            url:"/user/updatepwd",
            type:"post",
            data:{"password":password},
            dataType:"json",
            success:function(data){
                if(data.result=9999){
                    alert("修改成功");
                    window.location.href="/user/personInfo";
                }

            },
            error:function(){
                alert("load failed");
            }
        });
    }
});
});

function changeNav1(){
    $(".rightNavInfo").css('display','block');
    $(".rightNavSecurity").css('display','none');

}
function changeNav2(){
    $(".rightNavSecurity").css('display','block');
    $(".rightNavInfo").css('display','none');
}

//检测旧密码是否正确
function checkPwd(){
    var  currentpassword=$("#currentpassword").val();
    $.ajax({
        url:"/user/pwd",
        type:"POST",
        data:{"currentpassword": currentpassword},
        dataType:"json",
        success:function(data){
            if(data.result==6){
               // alert("ERRORRRRRRRRRRRRRRRRR");
                $("#spaN").html("<h5 class='p1' style='color:red' >当前密码错误</h5>");
                $("#subbutton").attr('disabled',true);
            }
            else{
                $("#spaN").html("<h4 class='p1' style='color:red' ></h4>");
              //   $("#subbutton").removeAttr('disabled');
                setButtonStatus();
            }
        },
        error:function(){
            alert("load failed");
        }
    });
}

//更新密码ajax
function updatePwd(){
  var password=$('#cofirmpassword').val();
    $.ajax({
        url:"/user/updatepwd",
        type:"post",
        data:{"password":password},
        dataType:"json",
        success:function(data){
            if(data.result=9999){
                alert("修改成功");
                window.location.href="/user/personInfo";
            }

        },
        error:function(){
            alert("load failed");
        }
    });
}

//检测长度
function checkLength(){
    if($("#editpassword").val().length<6){
        $("#spaN2").html("<h5 class='p1' style='color:red' >至少6位</h5>");
        $("#subbutton").attr('disabled',true);
    }
    else if($("#editpassword").val().length>=6){
        $("#spaN2").html("<h4 class='p1' style='color:red' ></h4>");
        //$("#subbutton").removeAttr('disabled');
        setButtonStatus();
    }
}

//再次确认
function checkConfirm(){
    var fstpassword=$("#editpassword").val();
    var sndpassword=$("#cofirmpassword").val();
    if(fstpassword!=sndpassword){
        $("#spaN3").html("<h5 class='p1' style='color:red' >两次不一样哦</h5>");
        $("#subbutton").attr('disabled',true);
    }else{
        $("#spaN3").html("<h4 class='p1' style='color:red' ></h4>");
        //$("#subbutton").removeAttr('disabled');
        setButtonStatus();
    }
}

//account&name都可使用才能点击按钮
function setButtonStatus(){
    if($("#spaN").find('h4').length>0&&$("#spaN2").find('h4').length>0&&$("#spaN3").find('h4').length>0){

        $("#subbutton").removeAttr('disabled');

    }
}
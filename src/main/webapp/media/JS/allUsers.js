

//锁定用户

function lockUser(){
    /*var r1=confirm("确定锁定此用户？");
     if(r1==true){

     }
     else{
     return false;
     }
    * */
    alert("已锁定此用户");

}


//解锁用户
/* */
function UnlockUser(){
   /*var r2=confirm("解锁此用户？");
    if(r2==true){
    }
    else{
    return false;
    }
   * */
    alert("已解锁");
}


//设置管理员
function setManager(user_id){
    var r1=confirm("设置此用户为管理员？");
    if(r1==true){
        window.location.href="/user/m/setManager?user_id="+user_id;
    }
    else{
        return false;
    }
}



// 取消管理员
function offManager(user_id){
    var r2=confirm("取消管理权限？");
    if(r2==true){
        window.location.href="/user/m/offManager?user_id="+user_id;
    }
    else{
        return false;
    }
}
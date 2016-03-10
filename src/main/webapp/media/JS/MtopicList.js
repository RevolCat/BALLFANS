function deleteTopic(topic_id){
    var r1=confirm("删除此主题帖？");
    if(r1==true){
       window.location.href=" /topic/m/deleteTopic?topic_id="+topic_id;
        alert("删除成功。。");
    }
    else{
        return false;
    }

}

function stickyTopic(topic_id){
    var r1=confirm("此主题帖置顶？");
    if(r1==true){
        window.location.href="/topic/m/stickyTopic?topic_id="+topic_id;
       // alert("dosnafndsa");
    }
    else{
        return false;
    }
}


function unstickyTopic(topic_id){
    var r1=confirm("是否取消置顶？");
    if(r1==true){
        window.location.href="/topic/m/unstickyTopic?topic_id="+topic_id;
        // alert("dosnafndsa");
    }
    else{
        return false;
    }
}
/**
 *
 * var r1=confirm("删除此主题帖？");
 if(r1==true){
        $.ajax({
            url:"/topic/m/deleteTopic?topic_id=${topicList.topic_id}",
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.result=301){
                    alert("删除成功。。。");
                }
                else{

                }

            },
            error:function(){
                alert("load failed");
            }
    });
    }
 else{
        return false;
    }

 * */
$(document).ready(function(){
 lineHighLight();

});


function toAddnewpost(){
window.location.href="/topic/push";
}

function lineHighLight(){
    $("#board_table tr").mouseover(function(){
        $(this).addClass("linehigh").siblings().removeClass("linehigh");
});
}
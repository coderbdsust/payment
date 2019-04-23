
$( document ).ready(function() {
    var url      = window.location.href;
    $('.nav li.active').removeClass('active');
    var sidebarUL = $(".sidebar-wrapper ul>li");
   $.each(sidebarUL, function(i, li){
       var a = li.children[0];
       var href = a.href;
       if(href===url){
           li.classList.add("active");
           return ;
       }
   });
});
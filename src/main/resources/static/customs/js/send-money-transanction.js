

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

$( document ).ready(function() {
    var contextPath = getContextPath();
    $("#sendMoneyBtn").click(function(){
        $("#sendMoneyForm").submit(function(e){
            e.preventDefault();
            var form = $(this);
            var  data = form.serialize();
            var url = contextPath+'/account/transanction/rest/send-money/confirm';
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                success: function(data)
                {
                    console.log(data);
                    showToaster('Tnx successfully done, Tnx ID: '+data.id,'SUCCESS');
                },
                error: function(e){
                    console.log(e);
                    showToaster('Please give a valid information','ERROR');
                }
            });
        });
    });
});
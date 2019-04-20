$.extend({
    getUrlVars: function(){
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    },
    getUrlVar: function(name){
        return $.getUrlVars()[name];
    }
});



function buildToasterDiv(message, messageType){
    var div = $("<div>");
    var btn = $("<button>");
    var it = $("<i>");
    var span = $("<span>");
    btn.addClass("close");
    it.addClass("material-icons");
    btn.append(it);
    span.text(message);
    div.append(btn);
    div.append(span);
    div.addClass("alert");
    if(messageType==='ERROR'){
        div.addClass("alert-danger");
    }else if(messageType==='SUCCESS'){
        div.addClass("alert-success");
    }else if(messageType==='WARNING'){
        div.addClass("alert-warning");
    }else if(messageType==='INFO'){
        div.addClass("alert-info");
    }else if(messageType==='DARNGER'){
        div.addClass("alert-danger");
    }else{
        div.addClass("alert-primary");
    }
    return div;
}

$( document ).ready(function() {
    var alertDiv = '<div class="alert">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
        '<i class="material-icons">close</i>' +
        '</button>' +
        '<span id="messageAlertDiv"></span>' +
        '</div>';
    var messageType = $.getUrlVar('messageType');
    var message = $.getUrlVar('message');
    if(messageType && message) {
        message=decodeURIComponent(message);
        message = message.replace(/\+/g, " ");
        var div = buildToasterDiv(message, messageType);
        var alertShowBlock = $('#alertshowblock');
        alertShowBlock.append(div);
        alertShowBlock.delay(5000).fadeOut();
    }
});
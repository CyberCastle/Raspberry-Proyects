var stompClient = null;

function setConnected(connected) {
    $('#connect').prop("disabled", connected);
    $('#disconnect').prop("disabled", !connected);
    $('#container').toggle();
    $('#response').html("");
}

function connect() {
    $.get("video/processing/start", function (data) {
        //$(".result").html(data);
        //alert("Load was performed.");
    });

    var socket = new SockJS('/realtime.fallback');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/realtime', function (message) {
            deployMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    $.get("video/processing/stop", function (data) {
        //$(".result").html(data);
        //alert("Load was performed.");
    });
    
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

/*function sendName() {
 var name = document.getElementById('name').value;
 stompClient.send("/app/hello", {}, JSON.stringify({'name': name}));
 }*/

function deployMessage(message) {
    $('#type span').html(message.type);
    $('#title span').html(message.title);
    $('#text span').html(message.text);
}
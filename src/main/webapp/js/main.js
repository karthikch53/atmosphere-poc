var path = "atmos/feed";
$("document").ready(function(){
	$("#feedsTable").hide();
	$("#noFeedsWarning").show();
	$("#btnSubscribe").bind("click", function(){
		var topic = $("#slist").val();
		if(topic=="default")
		{
			alert('Please select at least one topic.');
		}
		else
		{
			subscribe(topic);
			$("#noFeedsWarning").hide();
		}
	});
	$("#btnUnSubscribe").bind("click", function(){
		var topic = $("#slist").val();
		if(topic=="default")
		{
			alert('Please select at least one topic to unsubscribe.');
		}
		else
		{
			unsubscribeUrl(topic);
		}
	});
	
});

function unsubscribeUrl(topic) {
	var location = path + "/" + topic;
	unsubscribeAtmosphere(location);
}

function unsubscribeAtmosphere(location) {
	try
	{
		$.atmosphere.unsubscribeUrl(location);
	}
	catch(error)
	{
		
	}
	
}

function subscribe(topic)
{
	var socket = $.atmosphere;
    var subSocket;
    // when specified the framework tries to establish a connection with
    //the server through this means. if the server doesn't support the
    // transport protocol, the framework tries to connect using
    // the specified fallback transport.
    var transport = 'websocket';
    var websocketUrl = path +"/"+topic;  
    var request = {

            url: websocketUrl,
            contentType : "application/json",
            logLevel : 'debug',
            dataType: 'json',
            // when set to true, the connections are shared between the
            // tabs   
            shared: true,
            // headers: you can pass auth tokens or any identity tokens
            // to be handled in the server side.
            headers: {
                'token': 'sampletoken'
            },
            transport : transport ,
            trackMessageLength : true,
            enableProtocol : true,
            reconnectInterval : 0,
            maxReconnectOnClose : 3,
            dropAtmosphereHeaders : false,
            // timeout value for the request.
            timeout : 5 * 60 * 1000,
            //timeout : 5 * 60 * 1000,
            fallbackTransport: 'long-polling',
            connectTimeout: -1,
            // handles the broadcast from the server.
            onMessage: updateResponseFromServer,
            onOpen: function(response) {
                    console.log('onOpen '+ response);                       
            },
            onReconnect: function (request, response) {
                    console.log('onReconnect ' + request);
                    console.log('onReconnect ' +  response);
            },
            onClose: function(response) {
            	var rb = response.responseBody;
            	console.dir(rb);
            },
            onError: function(response) {
            	 alert('onError');
            }
    };
    subSocket = socket.subscribe(request);
}



function updateResponseFromServer(response)
{
	var jsonData;
	var data;
	if (response.status == 200) 
    {
       $("#noFeedsWarning").hide();
       $("#feedsTable").show();
	   var res = response.responseBody;
	   var index = res.indexOf("|"); // when the callback receives multiple messages, they get delimited by a '|'
       if(index != -1)
       {
           var resArr = res.split("|");
           jsonData = resArr[resArr.length - 1];
           data = $.parseJSON(jsonData);
       }
       else
       {
           data = $.parseJSON(res);
       }
       updateFeedsTable(data);
    }
}

function updateFeedsTable(data)
{
	var row = $("<tr>");
	row.append( $("<td>").text(data.message));
	row.append( $("<td>").text(data.postedBy) );
	row.append( $("<td>").text(data.postedDate) );
	$("#feedsTable").append(row);
}

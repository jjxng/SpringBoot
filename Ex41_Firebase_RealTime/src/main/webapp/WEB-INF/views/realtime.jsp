<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<title>Firebase Realtime</title>
<script type="module">
import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js'
import { getDatabase, ref, onValue, set, child, push, onChildAdded, query, limitToLast }
 from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-database.js'

// Your web app's Firebase configuration
 	 const firebaseConfig = {
    apiKey: "AIzaSyCNu_aNJT5mNnz8CoEVbaId4nRDcNU3-UE",
    authDomain: "pro4-24097.firebaseapp.com",
    databaseURL: "https://pro4-24097-default-rtdb.firebaseio.com",
    projectId: "pro4-24097",
    storageBucket: "pro4-24097.appspot.com",
    messagingSenderId: "768421008038",
    appId: "1:768421008038:web:830630add098a360e99a67"
  };

var app = initializeApp(firebaseConfig);
var db = getDatabase(app);

var roomName;
var userName;

function connect() {
	roomName = $("#roomName").val();
	userName = $("#userName").val();

	var dbRef = ref(db, 'chat/' + roomName);
	var lastDbRef = query(dbRef, limitToLast(1));
/*

	// 데이터 변경시 모든 데이터 조회됨
	onValue(dbRef, (snapshot) => {
		snapshot.forEach((childSnapshot) => {
		// var key = childSnapshot.key;
		var data = childSnapshot.val();

		var name = data.userName;
		var msg = data.message;

		console.log("[2] " + name + ":" + msg);
		appendMessage(name + ":" + msg);
		});
	});
*/
	// dbRef : 처음에는 기존 데이터 모두 조회됨. 이후 추가된 데이터만 조회됨.
	// lastDbRef : 마지막 데이터 하나 조회
	onChildAdded(lastDbRef, (data) => {
		var name = data.val().userName;
		var msg = data.val().message;
	
		console.log("[3] " + name + ":" + msg);
		appendMessage(name + ":" + msg);
	});
}

function writeNewPost(roomId, name, msg) {
	var postData = {
		userName: name,
		message: msg
	};

	// Get a key for a new Post
	var newPostKey = push(child(ref(db), 'chat/' + roomId)).key;
	var newRef = ref(db, 'chat/' + roomId + '/' + newPostKey);

	set(newRef, postData);
}

function send() {
	var msg = $('#message').val();
	writeNewPost(roomName, userName, msg);
}

function appendMessage(msg) {
	$('#chatMessageArea').append(msg + "<br>");
	var chatAreaHeight = $('#chatArea').height();
	var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
	$('#chatArea').scrollTop(maxScroll);
}

$(document).ready(function() {
	console.log(111);
	$('#sendBtn').click(function() { send(); });
	$('#enterBtn').click(function() { connect(); });
});
</script>
<style>
#chatArea{
	width: 200px; height: 100px; overflow-y: auto; border: 1px solid black; 
}
</style>

</head>
<body>
	Room Name : <input type="text" id="roomName"> <br>
	User Name : <input type="text" id="userName"> <br>
	<input type="button" id="enterBtn" value="입장">
	<h1>대화 영역</h1>
	<div id="chatArea"><div id="chatMessageArea"></div></div>
	<br>
	
	<input type="text" id="message">
	<input type="button" id="sendBtn" value="전송">
</body>
</html>
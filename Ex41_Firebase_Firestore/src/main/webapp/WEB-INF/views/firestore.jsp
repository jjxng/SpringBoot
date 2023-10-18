<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery.js"></script>
<title>Firebase_Firestore</title>
<script type="module">
  import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js';
  import { getFirestore, collection, getDocs, setDoc, doc } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-firestore.js';

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
  var db = getFirestore(app);

  async function writeDoc() {
    var email = $('#email').val();
    var pwd = $('#pwd').val();

    var postData = {
      email: email,
      pw: pwd
    };
    var timeElapsed = Date.now();
    var newRef = doc(db, 'members','member'+timeElapsed);

    await setDoc(newRef, postData);
  }

  async function selectDoc(){
    $('#chatMessageArea').empty();

    const snapshot = await getDocs(collection(db,"members"));
    snapshot.forEach((doc) => {
      var doc_id = doc.id;
      var data = doc.data();
      console.log(doc_id + " - " + data.email + " : " + data.pw);
      appendMessage(data.email + " : " + data.pw);
    });
  }

  function appendMessage(msg) {
    $('#chatMessageArea').append(msg + "<br>");
    var chatAreaHeight = $('#chatArea').height();
    var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
    $('#chatArea').scrollTop(maxScroll);
  }

  $(document).ready(function() {
    console.log(111);
    $('#select').click(function() { selectDoc(); });
    $('#write').click(function() { writeDoc(); });
  });
</script>
<style>
  #chatArea{
    width: 200px; height: 100px; overflow-y: auto; border: 1px solid black; 
  }
</style>
</head>
<body>
  <input type="button" id="select" value="조회">
  <div id="chatArea"><div id="chatMessageArea"></div></div>
  <br>
  
  <input type="text" id="email">
  <input type="text" id="pwd">
  <input type="button" id="write" value="작성">
</body>
</html>

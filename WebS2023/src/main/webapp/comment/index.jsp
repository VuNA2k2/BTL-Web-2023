<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đánh giá sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-storage.js"></script>
    <script>
        var firebaseConfig = {
            apiKey: "AIzaSyDwwT0-a3QmZUFWdHmcxVguvHxNF_hnc6M",
            authDomain: "image-storage-bdbae.firebaseapp.com",
            projectId: "image-storage-bdbae",
            storageBucket: "image-storage-bdbae.appspot.com",
            messagingSenderId: "659858191609",
            appId: "1:659858191609:web:e64cda24c4b14cabfb9e28",
            measurementId: "G-MPR5X9E15K"
        };
        firebase.initializeApp(firebaseConfig);
    </script>
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<h1>Đánh giá sản phẩm</h1>
<form id="review-form" onsubmit="event.preventDefault();">
    <h3>Đánh giá của bạn</h3>
    <div class="comment-form">
        <label for="comment" class="text-comment">Bình luận:</label>
        <input type="text" id="comment" placeholder="Nhập bình luận của bạn tại đây.">
    </div>
    <div class="form-rating">
        <label for="star" class="text-rating">Đánh giá:</label>
        <div class="rating">
            <input type="radio" id="star5" name="rating" value="5">
            <label for="star5" title="5 sao"></label>
            <input type="radio" id="star4" name="rating" value="4">
            <label for="star4" title="4 sao"></label>
            <input type="radio" id="star3" name="rating" value="3">
            <label for="star3" title="3 sao"></label>
            <input type="radio" id="star2" name="rating" value="2">
            <label for="star2" title="2 sao"></label>
            <input type="radio" id="star1" name="rating" value="1">
            <label for="star1" title="1 sao"></label>
        </div>
    </div>
    <div class="image-form">
        <label for="image" class="text-image">Hình ảnh kèm theo(nếu có):</label>
        <input type="file" id="image" multiple accept="image/*">
    </div>
    <button id="btnSubmit" type="submit" class="submit-comment">Gửi đánh giá</button>
</form>
<%@ include file="../layout/footer.jsp" %>

<script src="script.js"></script>
</body>
</html>

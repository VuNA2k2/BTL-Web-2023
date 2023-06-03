<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đánh giá sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Đánh giá sản phẩm</h1>
<div id="product-info">
    <p>Trung bình: <span id="avg-rates"></span><span class="star-fill"></span></p>
    <p>Số lượt đánh giá: <span id="count-rates"></span></p>
</div>
<ul id="review-list"></ul>
<form id="review-form">
    <input type="hidden" id="product-id-hidden" value="12">
    <div>
        <label for="comment">Bình luận:</label>
        <input type="text" id="comment">
    </div>
    <div>
        <label for="star">Đánh giá:</label>
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
    <button type="submit" onclick="submitReview()">Gửi đánh giá</button>
</form>
<script src="script.js"></script>
</body>
</html>

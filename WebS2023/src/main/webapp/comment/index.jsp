<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đánh giá sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<h1>Đánh giá sản phẩm</h1>
<div id="product-info">
    <p>Trung bình: <span id="avg-rates"></span><span class="star-fill"></span></p>
    <p>Số lượt đánh giá: <span id="count-rates"></span></p>
</div>
<ul id="review-list"></ul>
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
<%--    <div class="image-form">--%>
<%--        <label for="image" class="text-image">Hình ảnh kèm theo(nếu có):</label>--%>
<%--        <input type="file" id="image" multiple accept="image/*">--%>
<%--    </div>--%>
    <button id="btnSubmit" type="submit" class="submit-comment">Gửi đánh giá</button>
</form>
<%@ include file="../layout/footer.jsp" %>

</body>
<script src="script.js"></script>
</html>

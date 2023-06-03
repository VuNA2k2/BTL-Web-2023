<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Reviews</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Product Reviews</h1>
<div id="product-info">
    <p>Average Rating: <span id="avg-rates"></span></p>
    <p>Total Reviews: <span id="count-rates"></span></p>
</div>
<ul id="review-list"></ul>
<form id="review-form">
    <input type="hidden" id="product-id-hidden" value="12">
    <div>
        <label for="comment">Comment:</label>
<%--        <textarea id="comment" rows="4" cols="50"></textarea>--%>
        <input type="text" id="comment"></div>
    </div>
    <div>
        <label for="star">Rating:</label>
        <select id="star">
            <option value="1">1 Star</option>
            <option value="2">2 Stars</option>
            <option value="3">3 Stars</option>
            <option value="4">4 Stars</option>
            <option value="5">5 Stars</option>
        </select>
    </div>
    <button type="submit" onclick="submitReview()">Submit Review</button>
</form>
<script src="script.js"></script>
</body>
</html>

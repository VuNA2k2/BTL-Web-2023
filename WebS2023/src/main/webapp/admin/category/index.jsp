<%@page contentType="text/html" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Phân loại</title>
		<link rel="stylesheet" href="style.css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
		<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
	</head>

	<body>
		<%@include file="../../layout/header.jsp" %>
			<div id="container">
				<h1>Phân loại</h1>

				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--12-col">
						<table id="users-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
							<thead>
								<tr>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: start; color: white;">ID</th>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: start; color: white;">Tên</th>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: start; color: white;">Mô tả</th>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: start; color: white;">Chi tiết</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>


				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--12-col">
						<button type="button" id="show-create-dialog-btn"
							class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Tạo thể loại mới</button>
					</div>
				</div>

				<dialog id="create-dialog" class="mdl-dialog">
					<h4 class="mdl-dialog__title">Tạo phân loại mới</h4>
					<div class="mdl-dialog__content mdl-grid">
						<form id="create-form" class="mdl-cell mdl-cell--12-col">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" type="text" id="create-name-input">
								<label class="mdl-textfield__label" for="create-name-input">Tên</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" type="text" id="create-description-input">
								<label class="mdl-textfield__label" for="create-description-input">Mô tả</label>
							</div>
						</form>
					</div>
					<div class="mdl-dialog__actions">
						<button type="button"
							class="mdl-button mdl-js-button mdl-button--raised close green-bg">Đóng</button>
						<button type="button" id="save-btn"
							class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Lưu</button>
					</div>
				</dialog>

				<dialog id="edit-dialog" class="mdl-dialog">
					<h4 class="mdl-dialog__title" id="edit-dialog-title"></h4>
					<div class="mdl-dialog__content mdl-grid">
						<form id="edit-form" class="mdl-cell mdl-cell--12-col">
							<input type="hidden" id="edit-id-input">
							<div
								class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
								<input class="mdl-textfield__input" type="text" id="edit-name-input">
								<label class="mdl-textfield__label" for="edit-name-input">Tên</label>
							</div>
							<div
								class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
								<input class="mdl-textfield__input" type="text" id="edit-description-input">
								<label class="mdl-textfield__label" for="edit-description-input">Mô tả</label>
							</div>
						</form>
					</div>
					<div class="mdl-dialog__actions">
						<button type="button" class="mdl-button mdl-js-button mdl-button--raised close">Đóng</button>
						<button type="button" id="update-btn"
							class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Cập nhật</button>
						<button type="button" id="delete-btn"
							class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Xóa</button>
					</div>
				</dialog>

				<dialog id="result-dialog" class="mdl-dialog">
					<div class="mdl-dialog__content">
						<p id="result-message"></p>
					</div>
				</dialog>
			</div>
			<%@include file="../../layout/footer.jsp" %>

				<script src="script.js"></script>
	</body>

	</html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Dashboard</title>
		<link rel="stylesheet" href="style.css">
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
		<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
	</head>

	<body>
		<%@include file="../../layout/header.jsp" %>
			<div id="container">
				<h1>Dashboard</h1>
				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--6-col">
						<h4 class="mdl-color-text--grey-700">Tổng doanh thu</h4>
					</div>
					<div class="mdl-cell mdl-cell--6-col">
						<div class="filter-container">
							<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
								<select id="userFilterSelect" class="mdl-textfield__input">
									<option value="income">Doanh thu theo sản phẩm</option>
									<option value="loyal customers">Khách hàng trung thành</option>
									<option value="quality products">Chất lượng sản phẩm</option>
								</select>
								<label class="mdl-textfield__label" for="userFilterSelect">Filter by</label>
							</div>
						</div>
					</div>
				</div>

				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--12-col">
						<table id="users-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
							<thead>
								<tr>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: end; color: white;">ID</th>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: end; color: white;">Tên sản phẩm</th>
									<th class="mdl-data-table__cell--non-numeric" style="text-align: end; color: white">
										Giá sản phẩm</th>
									<th class="mdl-data-table__cell--non-numeric"
										style="text-align: end; color: white;">Chi tiết</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>

				<div class="chart-container">
					<div class="canvas-container">
						<canvas id="pieChart"></canvas>
					</div>
				</div>


			</div>
			<dialog class="mdl-dialog">
				<h4 class="mdl-dialog__title" id="dialog-title"></h4>
				<div class="mdl-dialog__content">
					<p id="dialog-description"></p>
					<p id="dialog-price"></p>
				</div>
				<div class="mdl-dialog__actions">
					<button type="button" class="mdl-button mdl-js-button mdl-button--raised close">Close</button>
					<button type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored detail">Detail</button>
				</div>
			</dialog>

			<%@include file="../../layout/footer.jsp" %>

				<script src="script.js"></script>
	</body>

	</html>
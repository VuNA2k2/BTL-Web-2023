const url = "https://localhost:443/WebS2023_war/api/dashboard?action=PRODUCT_REVENUE"
const productUrl = "https://localhost/WebS2023_war/api/products?id="
const customerRevnueUrl = "https://localhost/WebS2023_war/api/dashboard?action=CUSTOMER_REVENUE"
const pieChartCanvas = document.getElementById('pieChart');
var selectElement = document.getElementById('userFilterSelect');
var filterButton = document.getElementById('filterButton');


const chartData = {
  labels: [],
  datasets: [{
    data: [],
    backgroundColor: [],
  }],
};

selectElement.addEventListener('change', function () {
  const usersContainer = document.querySelector('tbody');
  usersContainer.innerHTML = '';
  chartData.labels = [];
  chartData.datasets[0].data = [];
  chartData.datasets[0].backgroundColor = [];
  var selectedValue = selectElement.value;
  if (selectedValue == 'income') {
    const tableHeaders = document.querySelectorAll('.mdl-data-table th');
    tableHeaders[0].textContent = 'ID';
    tableHeaders[1].textContent = 'Tên sản phẩm';
    tableHeaders[2].textContent = 'Doanh thu sản phẩm';
    tableHeaders[3].textContent = 'Chi tiết';
    generateIncome();
  }
  else if (selectedValue == 'loyal customers') {
    const tableHeaders = document.querySelectorAll('.mdl-data-table th');
    tableHeaders[0].textContent = 'ID';
    tableHeaders[1].textContent = 'Tên khách hàng';
    tableHeaders[2].textContent = 'Email';
    tableHeaders[3].textContent = 'Số tiền đã tiêu';
    generateLoyalCustomersHTML();
  }
  else {
    const tableHeaders = document.querySelectorAll('.mdl-data-table th');
    tableHeaders[0].textContent = 'ID';
    tableHeaders[1].textContent = 'Tên sản phẩm';
    tableHeaders[2].textContent = 'Giá';
    tableHeaders[3].textContent = 'Đánh giá';
    generateProductHTML();
  }
  generatePieChart();
});


function getTokenFromCookie() {
  const cookie = document.cookie.split(';');
  const token = cookie[0].substring("token=".length, cookie[0].length);
  return token;
}

pricelist = [];
var totalRevenue = 0;
userList = [
  {
    id: 1,
    name: 'John Doe',
    email: 'johndoe@example.com',
    moneySpent: 100
  },
  {
    id: 2,
    name: 'Jane Smith',
    email: 'janesmith@example.com',
    moneySpent: 200
  },
  {
    id: 3,
    name: 'Bob Johnson',
    email: 'bobjohnson@example.com',
    moneySpent: 50
  }
];

const productList = [
  {
    id: 1,
    name: 'Product 1',
    price: 10.99,
    rating: 4.5,
  },
  {
    id: 2,
    name: 'Product 2',
    price: 19.99,
    rating: 3.8,
  },
  {
    id: 3,
    name: 'Product 3',
    price: 5.99,
    rating: 4.2,
  },
];



fetch(url, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + getTokenFromCookie()
  }
}).then(response => response.json())
  .then(data => {
    pricelist = data.data.productRevenues;
    totalRevenue = data.data.totalRevenue;
    generateIncome(data.data.totalRevenue);
    generatePieChart();
  })
  .catch((error) => {
    alert('Error: ' + error);
  });

userRevenueList = [];

fetch(customerRevnueUrl, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + getTokenFromCookie()
  }
}).then(response => response.json())
  .then(data => {
    console.log(data);
    userRevenueList = data.data.customerRevenues;
  })
  .catch((error) => {
    alert('Error: ' + error);
  });






function generateIncome() {
  pricelist.forEach(product => {

    const row = document.createElement('tr');

    const idCell = document.createElement('td');
    idCell.textContent = product.id;
    row.appendChild(idCell);

    const nameCell = document.createElement('td');
    nameCell.textContent = product.name;
    row.appendChild(nameCell);

    const priceCell = document.createElement('td');
    priceCell.textContent = product.revenue;
    row.appendChild(priceCell);

    const detailsCell = document.createElement('td');
    const totalRevenueText = document.querySelector('.mdl-color-text--grey-700');
    totalRevenueText.innerHTML = '<strong style="font-size: 20px;">Tổng doanh thu: </strong>' + '<strong style="color: green; font-size: 20px;">' + totalRevenue.toLocaleString() + ' <span style="font-weight: bold;">VND</span></strong>';
    detailsCell.textContent = 'Xem chi tiết';
    detailsCell.classList.add('mdl-button', 'mdl-js-button', 'mdl-button--raised', 'mdl-button--colored');
    detailsCell.style.backgroundColor = 'limegreen';
    detailsCell.style.color = 'white';
    detailsCell.style.borderRadius = '4px';
    detailsCell.style.padding = '16px';
    detailsCell.style.textAlign = 'center';
    detailsCell.style.display = 'flex';
    detailsCell.style.alignItems = 'center';
    detailsCell.style.justifyContent = 'center';
    detailsCell.addEventListener('click', function () {
      showProductDialog(product.id);
    });
    row.appendChild(detailsCell);
    const usersContainer = document.querySelector('tbody');
    usersContainer.appendChild(row);
    chartData.labels.push(product.name);
    chartData.datasets[0].data.push(product.revenue);
    const randomColor = getRandomColor();
    chartData.datasets[0].backgroundColor.push(randomColor);
  })
}
function showProductDialog(id) {
  fetch(productUrl + id, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + getTokenFromCookie()
    }
  }).then(response => response.json()
    .then(data => {
      const dialog = document.querySelector('dialog');
      const dialogTitle = document.querySelector('.mdl-dialog__title');
      const dialogContent = document.querySelector('.mdl-dialog__content');
      const dialogActions = document.querySelector('.mdl-dialog__actions');
      dialogTitle.textContent = data.data.name;
      dialogContent.innerHTML = `
    <p>Giá: ${data.data.price}</p>
    <p>Mô tả: ${data.data.description}</p>
    `;
      dialogActions.innerHTML = `
    <button type="button" class="mdl-button close">Đóng</button>
    <button type="button" class="mdl-button detail">Chi tiết</button>
    `;
      dialog.showModal();
      const closeButtons = document.querySelectorAll('.close');
      closeButtons.forEach(closeButton => {
        closeButton.addEventListener('click', function () {
          dialog.close();
        });
      });
      const detailButton = document.querySelector('.detail');
      detailButton.addEventListener('click', function () {
            window.location.href =  '/WebS2023_war/product-detail/?productId=' + data.data.id;
          });
    })
    .catch((error) => {
      alert('Error: ' + error);
    }));
  }

  function generateLoyalCustomersHTML() {
    userRevenueList.forEach(user => {
      const row = document.createElement('tr');

      const idCell = document.createElement('td');
      idCell.textContent = user.customer.id;
      row.appendChild(idCell);

      const nameCell = document.createElement('td');
      nameCell.textContent = user.customer.username + user.customer.fullName;
      row.appendChild(nameCell);

      const emailCell = document.createElement('td');
      emailCell.textContent = user.customer.email;
      row.appendChild(emailCell);

      const moneySpentCell = document.createElement('td');
      moneySpentCell.textContent = user.revenue;
      row.appendChild(moneySpentCell);
      const usersContainer = document.querySelector('tbody');


      usersContainer.appendChild(row);

      chartData.labels.push(user.customer.username);
      chartData.datasets[0].data.push(user.revenue);
      const randomColor = getRandomColor();
      chartData.datasets[0].backgroundColor.push(randomColor);
    });
  }

  function generateProductHTML() {
    productList.forEach(product => {
      const row = document.createElement('tr');

      const idCell = document.createElement('td');
      idCell.textContent = product.id;
      row.appendChild(idCell);

      const nameCell = document.createElement('td');
      nameCell.textContent = product.name;
      row.appendChild(nameCell);

      const priceCell = document.createElement('td');
      priceCell.textContent = product.price;
      row.appendChild(priceCell);

      const ratingCell = document.createElement('td');
      ratingCell.textContent = product.rating;
      row.appendChild(ratingCell);
      const usersContainer = document.querySelector('tbody');
      usersContainer.appendChild(row);
      chartData.labels.push(product.name);
      chartData.datasets[0].data.push(product.rating);
      const randomColor = getRandomColor();
      chartData.datasets[0].backgroundColor.push(randomColor);
    });
  }

  function generatePieChart() {
    const pieChart = new Chart(pieChartCanvas, {
      type: 'pie',
      data: chartData,
    });
  }

  function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }


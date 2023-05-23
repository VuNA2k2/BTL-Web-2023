<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
        <style>
            body {
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }

            footer {
                background-color: black;
                color: white;
                padding: 10px 50px;
                margin-top: auto;
            }

            .footer-content {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }

            .footer-section {
                flex: 1;
                margin-right: 40px;
            }

            .footer-section h2 {
                font-size: 18px;
                margin-bottom: 20px;
            }

            .footer-section a {
                color: white;
                text-decoration: none;
                transition: color 0.3s;
            }

            .footer-section a:hover {
                color: gray;
            }
            .footer-section ul {
                list-style-type: none;
                padding: 0;
                margin: 0;
            }


            .contact span {
                display: block;
                margin-bottom: 10px;
            }

            .socials a {
                display: inline-block;
                margin-right: 10px;
                font-size: 24px;
                color: white;
                transition: transform 0.3s;
            }

            .socials a:hover {
                transform: translateY(-5px);
            }
            .footer-bottom {
                background: #111;
                color: white;
                padding: 10px 0;
                text-align: center;
                font-size: 14px;
            }
        </style>
    </head>
    <body>
        <footer class="footer">
            <div class="footer-content">
                <div class="footer-section">
                    <h2 class="logo-text">TOPZONE</h2>
                    <div class="contact">
                        <span><i class="fa-solid fa-phone"></i> <a href="">0123456789</a></span>
                        <span><i class="fa-solid fa-envelope"></i> <a href="">info@company.com</a></span>
                    </div>
                    <div class="socials">
                        <a href="#"><i class="fab fa-facebook"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                <div class="footer-section">
                    <h2>Hệ thống cửa hàng</h2>
                    <ul>
                        <li><a href="#">Xem 100 cửa hàng</a></li>
                        <li><a href="#">Nội quy cửa hàng</a></li>
                        <li><a href="#">Chất lượng phục vụ</a></li>
                        <li><a href="#">Chính sách bảo hành & đổi trả</a></li>
                    </ul>
                </div>
                <div class="footer-section">
                    <h2>Hỗ trợ khách hàng</h2>
                    <ul>
                        <li><a href="#">Điều kiện giao dịch chung </a></li>
                        <li><a href="#">Hướng dẫn mua hàng online </a></li>
                        <li><a href="#">Chính sách giao hàng </a></li>
                        <li><a href="#">Hướng dẫn thanh toán </a></li>
                    </ul>
                </div>
                <div class="footer-section">
                    <h2>Về thương hiệu TopZone</h2>
                    <ul>
                        <li><a href="#">Tích điểm Quà tặng VIP</a></li>
                        <li><a href="#">Giới thiệu TopZone</a></li>
                        <li><a href="#">Bán hàng doanh nghiệp</a></li>
                        <li><a href="#">Chính sách bảo mật thông tin</a></li>
                    </ul>
                </div>
                <div class="footer-section">
                    <h2>Trung tâm bảo hành</h2>
                    <ul>
                        <li><a href="#">Giới thiệu TopCare</a></li>
                    </ul>
                </div>
            </div>
            <div class="footer-bottom">
                <div>&copy; © 2018. Công ty cổ phần Thế Giới Di Động. GPDKKD: 0303217354 do sở KH & ĐT TP.HCM cấp ngày
                    02/01/2007.</div>
                <div>Địa chỉ: 128 Trần Quang Khải, P. Tân Định, Q.1, TP. Hồ Chí Minh. Điện thoại: 028 38125960.</div>
            </div>
        </footer>
    </body>
</html>

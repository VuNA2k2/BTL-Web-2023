package dal;

import model.Customer;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO extends DBContext {

    public Customer getAccount(String user, String pass) {
        String sql = "SELECT * FROM customers WHERE username=? AND password=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Customer(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("amount"),
                        user, pass);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Item> getItemsByCartId(int cartId) {
        List<Item> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM items WHERE cid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setCid(resultSet.getInt("cid"));
                item.setPid(resultSet.getInt("pid"));
                item.setPname(resultSet.getString("pname"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getDouble("price"));
                items.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return items;
    }

    public Cart getCartByCustomerId(int cusId) {
        Cart cart = null;

        try {
            String query = "SELECT * FROM carts WHERE cusId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cusId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cart = new Cart();
                cart.setId(resultSet.getInt("id"));
                cart.setCusId(resultSet.getInt("cusId"));
                cart.setTotalMoney(resultSet.getDouble("totalMoney"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return cart;
    }

    public Item getItemByCartAndProductId(int cartId, int productId) {
        Item item = null;
        try {
            String sql = "SELECT * FROM items WHERE cid = ? AND pid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.setInt(2, productId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                int cid = resultSet.getInt("cid");
                int pid = resultSet.getInt("pid");
                String pname = resultSet.getString("pname");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                item = new Item(cid, pid, pname, quantity, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void addOrder(Customer c, Cart cart) {
        try {
            String sql = "INSERT INTO orders (cusid, totalmoney, status) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, c.getId());
            st.setDouble(2, cart.getTotalMoney());
            st.setString(3, "preparing");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCartById(int cartId) {
        String sql = "DELETE FROM items WHERE cid = ?";
        String sql1 = "DELETE FROM carts WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.executeUpdate();

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, cartId);
            st1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderDetail(List<Item> itemList) {
        try {
            String sql1 = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();

            if (rs.next()) {
                int orderId = rs.getInt("id");
                for (Item item : itemList) {
                    String sql2 = "INSERT INTO order_ref_product VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, orderId);
                    st2.setInt(2, item.getPid());
                    st2.setString(3, item.getPname());
                    st2.setInt(4, item.getQuantity());
                    st2.setDouble(5, item.getPrice());
                    st2.executeUpdate();

                    // Update product quantity in the products table
                    String updateProductSql = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
                    PreparedStatement updateProductStmt = connection.prepareStatement(updateProductSql);
                    updateProductStmt.setInt(1, item.getQuantity());
                    updateProductStmt.setInt(2, item.getPid());
                    updateProductStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCart(Cart cart, List<Item> items) {
        try {

            // Update total money in cart
            double totalMoney = calculateTotalMoney(cart.getId());
            cart.setTotalMoney(totalMoney);
            String sql = "UPDATE carts SET totalmoney = ? WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, totalMoney);
            st.setInt(2, cart.getId());
            st.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private double calculateTotalMoney(int cartId) {
        double total = 0;
        try {
            String sql = "SELECT SUM(price * quantity) AS totalmoney FROM items WHERE cid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getDouble("totalmoney");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Other methods...
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY CASE status "
                + "WHEN 'preparing' THEN 1 "
                + "WHEN 'delivering' THEN 2 "
                + "WHEN 'finished' THEN 3 "
                + "ELSE 4 END";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int cusid = rs.getInt("cusid");
                double totalmoney = rs.getDouble("totalmoney");
                String status = rs.getString("status");

                Order order = new Order(id, cusid, totalmoney, status);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderById(int orderId) {
        Order order = new Order();
        String sql = "SELECT * FROM orders WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int cusid = rs.getInt("cusid");
                double totalmoney = rs.getDouble("totalmoney");
                String status = rs.getString("status");

                order = new Order(id, cusid, totalmoney, status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, orderId);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM order_ref_product WHERE oid = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                OrderDetail orderDetail = new OrderDetail(orderId, pid, pname, quantity, price);
                orderDetails.add(orderDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        System.out.println(d.getAll().get(0).getName());
    }

    public void addItemToCart(int productId, int cartId) {
        try {
            String sql = "UPDATE items SET quantity = quantity + 1 WHERE cid = ? AND pid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItemFromCart(int productId, int cartId) {
        try {
            String sql = "UPDATE items SET quantity = quantity - 1 WHERE cid = ? AND pid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int productId, int cartId) {
        try {
            String sql = "DELETE FROM items WHERE cid = ? AND pid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.setInt(2, productId);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

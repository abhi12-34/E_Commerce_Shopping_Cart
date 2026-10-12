E-Commerce Shopping Cart

Create a console-based shopping cart application.
Create:
Product
CartItem
ShoppingCart
Customer

Product should contain:
productId
name
category
price
stock

Program must allow the customer to:
1. View Products
2. Add Product to Cart
3. Remove Product from Cart
4. Update Quantity
5. View Cart
6. Calculate Total
7. Checkout

Rules:
- Product stock se zyada quantity cart mein add nahi ho sakti.
- Quantity 0 ya negative nahi ho sakti.
- Same product dobara add karne par new CartItem create nahi hona chahiye; existing quantity increase honi chahiye.
- Product unavailable hone par ProductOutOfStockException throw karo.
- Checkout ke baad stock update hona chahiye.

# Add products to the cart

```
curl -i -XPOST -H 'Content-Type: application/json' -d '{"productCd": "P003", "amount": 1}'  http://localhost:8080/cart/items
```

# Show cart items

```
curl -i http://localhost:8080/cart
```

# Checkout

```
curl -i -XPUT http://localhost:8080/cart/checkout
```

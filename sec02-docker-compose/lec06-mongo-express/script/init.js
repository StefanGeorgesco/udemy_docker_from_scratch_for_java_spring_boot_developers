db = db.getSiblingDB("product-service");
db.createCollection("products");
db.products.insertMany([
  { name: "iPhone", price: 1200 },
  { name: "iPad", price: 800 },
  { name: "macBook", price: 3000 },
]);

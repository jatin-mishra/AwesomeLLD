

# Problem Statement
Design an online shopping system like amazon


# Requirements

## Must to have
- user profile management
- merchant profile management
- add user, merchant, warehouse and product
- browse products
    - product name
    - merchant
    - category / sub category
- place orders
- provide search functionalities
- inventory management and product availability

- view order history
- multiple product categories


## Good to have
- track orders
- serviceability
- add them to the shopping cart
- multiple payment methods, and ensure secure transactions


## Out of scope
- products hierarchy


# Non Functional
- concurrency
- data consistency
- large number of products and users
- idempotent payments


# Edge cases and Error Handling
- order gets oos, then throw error?
- none match then return empty list?
- show only available ones?
- request from inactive user?
- tried adding same product in same category undere same merchant?


# Entity and Relations
Merchant:
- id
- name
- status
+ markActive()
+ markInactive()

MerchantRepository:
- Map<String, Merchant> merchantStore
+ addMerchant(merchant)
+ getMerchant(merchantId) -> Optional<Merchant>

Warehouse:
- id
- merchantId
- status
+ markActive()
+ markInactive()

WarehouseRepository:
// warehouse, merchant, warehouse
- Map<String, Map<String, Warehouse>>
+ addWarehouse(warehouse)
+ getWarehouse(merchantId, wareHouseId) -> Optional<Warehouse>

User:
- id
- name
- status
+ markActive()
+ markInactive()

UserRepository:
- Map<String, User> userStore
+ addUser(user)
+ getUser(userId) -> Optional<User>

Address:
- id
- entityId.  | userid, warehouseid
- entityType | user, warehouse
- lat
- long
- geoHash
- line1
- line2
- pincode
- landmark

AddressRepository:
- Map<String, Address>
+ addAddress(Address)
+ getAddress(entityId, entityType) -> Optional<Address>

ProductCategory(Enum):
- APARREL(size, color) dimensions
- ELECTRONICS(color ...)

Product:
- id
- name
- category
- description
- merchantId

Sku:
- id
- productId
- name
- description
- dimension_data[]

CatalogService:
- Map<String, Product> products
- Map<String, Sku> skus
- EventBus
+ addProduct(Product)
+ addSku(Sku)
+ getProduct(productId)
+ getProductBySkuId(skuId)
+ getSku(skuId)


EventBus:
- Map<EventType, Listener>
- Map<EventType, Class>
+ onEvent(EventType, Object)

ProductIndex extends Listener:
+ index(Product)
+ onEvent(Product)

SkuIndex extends Listener:
+ index(Sku)
+ onEvent(Sku)


ProductNameIndex implements ProductIndex:
- Map<String, Product>

ProductCategoryIndex implements ProductIndex:
- Map<Category, Product>

ProductSkuIndex implements SkuIndex:
- Map<Product, Sku>


Inventory:
- id
- skuId
- warehouseId
- total
- confirmed
- blocked


InventoryService:
- Map<SkuId, Map<WarehouseId, Inventory>>
- set<String> idempotency // order ids
+ addInventory(Inventory)
+ blockInventory(BlockInventoryContext)
+ confirmInventory(ConfirmInventoryContext)
+ revertInventory(RevertInventoryContext)
+ checkAvailability(skuId)
+ checkAvailability(skuId, warehouseId)


SearchService:
- indices and search


CartService:
- Map<userId, Cart>


OrderService:
- Order
- OrderItem


PaymentService:
- processPayment(type, double)

PaymentStrategy;
- ...




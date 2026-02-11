CAR RENTAL SYSTEM

# Primary Capabilities
- browse and reserve available cars for specific dates
- car details
  - make
  - model
  - license
  - plate number
  - rental price per day 
- should be able to search for cars based on various criteria
  - car type
  - price range
  - availability
- handle reservations (create, modify and cancel reservation)
- keep track of availability of cars and update their status accordingly
- customer info (name, contact info, driver license number)
- payment processing for reservation
- concurrent reservation and ensure data consistency


# Error Handling
- if cars not available
- if multiple people try to book same car
- if payment fails

# Entity and Relation
CarType(Enum)
- Sedan
- SUV

Car
- id
- model
- type
- plate_number
- price_per_day
- km_limit_per_day (can be created as strategies but skipping it for ease)


User
- id
- name
- contact info
- license number


CarPrice
- carId
- price_type
- price
- metadata


CarPriceType
- base_price
- extraPricePerKM
- weekend_charges

Inventory
- car_id
- car_type
- state (reserved/available)
- geoHash

Payment
- id
- booking_id
- type (pre_book, post_book)
- price_paid
- user_id
- state (confirmed / refunded)
- audit details

FilterQuery
- type
- minPrice
- maxPrice
- from
- to

Booking
- id
- user_id
- from
- to
- price
- state (CREATED/CONFIRMED/CANCELLED/REFUNDED) 

BookingItem
- id
- booking_id
- user_id
- from_date
- to_date
- car_id
- car_type
- price
- final_price
- state (CONFIRMED/CANCELLED/REFUNDED/PICKED/DROPPED)
- pricing_details
    - base_price
    - surcharges
      - weekend etc
    - extra_charge
      - extra_km
      - price_per_km


BookingService:
- Map<String, Booking>
- Map<String, BookingItem>
- InventoryService
- PaymentService
- Filter
+ browse(query) -> List<Car>
  + inventory service.getAvailability
  + return filter.filter([]cars, query)


+ reserve(from, to, user, carId) -> Optional<BookingTicket>
  + try with lock on inventory
  + take lock in all the inventories for given dates (deadlock issue) (sort by date and then take)
  + mark them booked (delete inventory from set for now) and release the lock
  + create booking and booking item
  + calculate pricing
  + initiate the payment and return nonce
  
+ modifyIntent(bookingId) -> ModificationInfo
  + get already booked item
  + if adding dates
    + reserve(from, to, user, carId)
  + else if removing
    + get cancellation charges 
    + return cost_to_refund - cancellation charges along with info, token 

+ modify(modificationInfo, signature)
  + paymentservice.initiate refund
  + return response


+ cancelIntent(BookingId) -> CancellationInfo
+ cancel(CancellationInfo, Signature)


InventoryService -> Singleton
- Map<Date, Set<Inventory>> dateWiseInventory
+ getAvailability(query) -> List<Car>
+ reserveInventory(car, from, to) -> Optional<Inventory>
+ releaseInventory(car, from, to) -> Optional<Inventory>


Filter
+ static getFilterPipeline() -> Optional<Filter>
  + returns CatalogServiceInstance


CatalogService extends Filter -> Singleton
- nextFilter (PricingService)
- Map<CarId, Car>
+ filter([]CarIds, query) -> List<Car>
  - check if query has type filter
  - either return next or input


EventBus
- Map<EventType, List<Listeners>>


PricingService extends Filter -> Singleton
- nextFilter
- Map<CarId, Map<PriceType, CarPrice>>
+ getTotalPrice(carId)
+ filter([]CarIds, query) -> List<Car>
  - check if query has pricing range query
  - either return next or input


PaymentService
- Map<String, Payment>
- PaymentGateWay
+ intent(PaymentOrder, nonce) -> return token and endpoint
+ handleCompletionEvent(nonce)

# problem statement
Design hotel management system

- handle availability and reservation status
- book rooms (multiple days)
- check in and check out
- different type of rooms
  - single
  - double
  - deluxe
  - suite
- assign room (of selected type) and billing
- manage guest information
- payment methods (cash, credit card, online payment)
  - pre booking
  - at the reception

## Good to have
- reporting, analytics
- search nearby hotels in an given area


# Entity and Relation
HotelRating:
- hotelId
- userId
- rating(double)
- reviews
- createdAt
- checkInTime
- checkOutTime

Hotel: 
- id
- name
- address{state, city, more...}
- rating


User:
- id
- name


HotelInventory:
- hotelId
- roomType
- roomId
- inventoryStatus (Available/Booked)
- date
- RoomStatus (Active/Inactive)


InventoryCreator extends Runnable:
- Config
- HotelManagementService
+ scheduled{every day}createInventory()


Reservations:
- userid
- fromId
- id
- ....


HotelManagementSystem:
- Map<String, Hotel>
- Map<String, HotelRating>
+ addHotel(Hotel)
+ findAvailability(from, to, roomType)
+ book(hotelId, from, to) -> nonce for price



PricingService:
+ getPrice(HotelId, RoomType) -> double (not in req)



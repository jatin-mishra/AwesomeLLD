
# Problem Statement
Design a movie booking system like BookMyShow

## Requirement

### Must have
- register in app (theatres, audies, seating plans, seat charges - specific to audy and theatre)
- different type of seats (normal, premium) and pricing
- add, update, remove movies, shows : theaters, audies, time slot
- add movie ticket price at theater
- list of movies in different theaters
    - time slots, seats, audi and more
- select movie, then theatre, timings
- display seating arrangement
- choose seat, pay[dummy] and get ticket (generate QR).
- on entering, mark QR used.

### Non functional
- concurrent booking
- real time seat availability updates
- scale : large number of concurrent users and bookings

### Good to have
- payment stack
    - make payment and confirm booking

### Out of scope
- changing seat arrangement
- real QR generation (we would use unique numbers)
- dynamic seat charges
- geo search
- flexible pricing system

### Rules and Error Handling
- don't allow same movie in same slot in different audi
- keep slot at theatre level (same for all audi)


# Entity and Relation
Slot
- name (start_end_type)
- type (morning, noon, evening, night)
- startingAt
- endingAt

Theatre:
- id
- name
- status (open / closed)
- opens_at
- closes_at
- holidays{date: {time_range}}
- ruleId[]
- location
- Map<id, Audi>
- Set<SlotName>
- slot[]

SeatType:
- Normal
- Premium

Audi:
- id
- theatreId
- number
- floor
- ScreenType (4D, 3D, 2D, INOX)
- seat_plan[][]
- Map<[][], String(Id)> seatIds
- Map<SeatType, Integer> seatCounts
- Map<SeatType, Charge> seatCharges
- Map<SlotType, Charge> slotCharges
- basePrice
- baseSlotPrice


CatalogService:
- Map<String, Theatre> theatres
+ registerTheatre(Theatre)
+ removeTheatre(TheatreId)
+ addAudi(TheatreId, Audi)
+ removeAudi(Theatre, AudiId)
+ getTheatres()
+ getSlots(TheatreId, AudiId) -> []Slot

Movie:
- id
- title
- actors[] // name
- rating | double
- released_at
- metadata{}
- cost

MovieService:
- Map<title, Movie>
- TreeMap<Date, Map<theatre, Map<audi, Map<slot, Movie>>>>
+ addMovie(Movie, theatreId, slotName ...)
+ removeMovie(MovieId, theatreId, slotName...)

EventBus:
Listener:
Index:
SearachFactory:
SearchQuery:
SearchByMovieName -> [{theatreId}]
SearchByTheatreName -> [{theatreId}]
SearchByMovieAndAudiType -> [{theatreId}]

Booking
- bookingId
- userId
- movieId
- theatreId
- audiId
- numberOfPeople
- slotId
- Status

BookingItem
- itemId
- bookingId
- theatreId
- audi
- slot
- seatId
- Status

BookingService:
- CatalogService
- SearchFactory -> theatres, audies, movies, slots and seat arrangement
- PaymentService
+ search(movieName)
+ searchTheatre(theatrename)
+ searchByMovieAndAudiType(movieName, audiType)
+ book(theatre, audi, seatId, date,  slot) -> Optional<Ticket, nonce for payment>
+ pay(nonce, ...)
+ enter(bookingId)
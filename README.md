# PaoTaxidi
# The app to organize your trip including flights and hotels

#Endpoints generated

### Manage bookings
GET /my-bookings/{{userId}}

###
POST /my-bookings/save-flight

###
POST /my-bookings/save-hotel

###
PATCH /my-bookings/{{bookingId}}

###
DELETE /my-bookings

### Flight search
GET /flight-search/inspire

### Hotel search
GET /hotel-search

### User management
GET /user-management/users

###
POST /user-management

###
PUT /user-management/{{id}}

###
PUT /user-management/{{id}}

###
PATCH /user-management/update-user-{{userId}}

###
DELETE /user-management/{{id}}

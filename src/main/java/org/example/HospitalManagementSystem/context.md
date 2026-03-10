# Problem Statement
Hospital Management System

# Requirements

## Must have
- patient registration
- assign priority to patients
- maintain queue of patients
- assign doctor based on specialization and availability
- track treatment records
- mark consultation complete
- retrieve patient history


# Entity and Relation
Patient:
- id
- name
- bornIn
- mobileNumber

Registration:
- id
- patient-id
- age
- problem description
- disease_category (HEART_ISSUE, KNEE_ISSUE, ....)
- severity
- assignedDoctor
- doctorToken
- doctorTime
- price
- price_details{base_price, []charges}
- state (CREATED, PAYMENT_DONE, DOCTOR_ASSIGNED, VISITED, SKIPPED)

RegistrationManager:
- map<id, Registration>

Hospital:
- RegistrationManager
- tokenNumber
- currentTurn: tokenNumber
+ queue(patient) -> number
- announce() -> token Number
+ register(problem, disease category, severity, assignment_strategy)
  + doctor: assignmentservice.assign()
  + enrich registration 
  + get price

DiseaseCategory:
Heart_issue(map[]{1: heart surgeon, 2: physiologist, 3: neuro, 4: nurse)
Knee_issue(map[]{physiologist, ...})
...

DoctorType(Enum):
- surgeon
- physiologist
- physician
- ...

DoctorManager:
- Map<doctor_type, []doctors>
- SlotService
+ addDoctor
+ removeDoctor
+ markDoctorUnAvailable

AssignmentService:
+ assign(registration) 
+ manually_assign(registration) -> doctor

EarliestAvailableDoctorAssignment implements AssignmentService:
- doctor repository
- SlotService
+ assign(registration) -> doctor

CheapestAvailableDoctorAssignment implements AssignmentService:
- doctor repository
- SlotService
+ assign(registration) -> doctor

SlotService:
- all indices (doctor_type, state and sort by time/price)
    doctorSlots:
    - doctorId
    - slot_date
    - slot_time
    - state
    - doctor_type
+ search(doctor_type)
+ markBooked(doctor_type, slot_time)
+ addAvailability(doctorId, date, []slot)

PriceService: 
- for all indices, keep map for in mem implementation
    entity_prices:
    - doctorId
    - service_type
    - price_type (default, price)
    - price
    
    charges:
    - doctor-id
    - date
    - chargeType -> mapped with schema
    - chargeData{}

+ getPrice(doctor_id, slot_time) -> {price, metadata{}}

  
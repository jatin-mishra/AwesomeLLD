# Problem Statement
- create and update profile / account
- create group
  - add user to group
  - add expense to the group
    - Amount
    - Description
    - Participants
- Split expenses among participants based on their share
  - different split methods
    - equal
    - percentage
    - exact amount
- user can see payment to be done to other users
  - and should be able to settle 
- transaction history of user (total)
  - when paid in which group
  - when settled in which group
- group expenses

# Entity and Relations
User:
- id
- name
- age 

Group:
- name
- description
- createdAt
- createdBy
+ addParticipant(User)

GroupUser
- groupId
- userId
- status

Split:
- participant
- amountToBePaid

Expense:
- groupId
- description
- paidBy
- SplitType (Equal, Percentage, Exact_Amount)
- {}FinalSplit
- []{participant, share}

SplitStrategy:
+ split([]{participant, share}, totalAmount) -> {}Split

GroupService:
- Map<String, Group> group
- Map<GroupId, Set<UserId>> groupUser 
- Map<String, List<Expense>> groupExpense
- SettlementService
+ createGroup(Group)
+ addUserToGroup(groupId, userId)
+ addExpenseToGroup(Expense)
  // {}Split = SplitStrategy([]{participant, share})
  // add user2 -> user1 amount to be paid
+ getGroupExpense(groupId)

Balance:
- id
- payTo
- payer
- amount
- description
- status (TODO, DONE)

BalanceSheetManager:
- Map<String, Settlement>
- Map<Id, Map<GroupId, List<Settlement>>> payerToSettlement
- Map<Id, Map<GroupId, List<Settlement>>> receiverToSettlement
+ createSettlement(Settlement)
+ getTransaction(userId)
+ getTransaction(userId, groupId)




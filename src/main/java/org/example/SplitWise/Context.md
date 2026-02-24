# Problem Statement
Design SplitWise

# Requirements
- create Account and Manage profile
- create groups and add users to groups
- add expense (group, amount, description, participants={default=all})
- split expenses among participants
- users should be able to see and settle balances to other
- support different split methods
    - equal
    - percentage
    - exact amount
- transaction history and group expenses

# Good to have
- normalize and reduce total number of transactions
    - Example: remove cycle transactions


# Error Handling and Corner cases
- absolute number? or double?
- number smaller than group already? (Don't allow)


# Entity and Relation
User:
- id
- name
- metadata{}


Group:
- id
- name
- []users
- []Expense
- Map<user1, Map<user2, amount>> splits
- Map<user1, Map<user2, amount>> toReceive
+ Group(name)
+ addUser(user)
+ addExpense(Expense)
+ paid(user1, map[user, amount])
+ remove(user1, user2)
+ update(user1, user2, amount)
+ userDetails(userId) -> map<user2, amount>


SplitStrategy:
+ split(amount, debtors, breakupConfig)


EqualSplit:
PercentageSplit:
ExactSplit:


Expense:
- amount
- payor
- []debtors
- Split Type (Equal, Percentage, Exact)
- map<debtor, value> breakupConfig (for percentage or exact)



SplitWise:
- UserTransactionService
- SettlementService
- GroupService
+ addUser(user)
+ removeUser(user)
+ createGroup()
+ addUserToGroup()
+ addExpense(expense)
  // groupservice.addExpense which does split
  // prepare and add transaction and settlements global level
+ paid(group, user1, pay{user2, amount})
  // inform transactions and setttlement service as well


GroupService
- Map<GroupName, Group>
- SplitStrategy
+ createGroup()
+ addUserToGroup([]users, groupname)
+ addExpenseToGroup(group, expense)
  = calculate split
  = add to group
  = send group settlement data
+ settlePayment(group, user1, pay{user2, amount})


UserService:
- Map<id, User>
+ createUser(user) -> User
+ removeUser(userId) -> boolean


Transaction:
- user1
- user2
- type: Expense/Settle
- amount
- created_at


SettlementService:
- Map<user, Map<user, amount>> toGive
- Map<user, Map<user, amount>> toTake
+ addExpense(group, Expense)
+ settle(group, user1, map[user2, amount])


LedgerService:
- Map<userId, []Transaction>
+ addTRansaction(userId, Transaction)


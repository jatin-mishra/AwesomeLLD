# Points to keep in mind

1. Don't overthink
2. discuss requirements properly and separate requirements into (must to have, good to have and out of scope)
3. discuss edge cases and error handling before hand
4. discuss solutioning (entities and relations)
5. Trickiest part if not applying design principles but it is to decide right level of abstraction
   Example:
   while building Message Queue, should topic be another class? should entities be like Producer, Broker, Topic? or Broker can have Map<String, List<Message>> ?
   Finding out the right entities and their relation with right level of abstraction is the where most of the people make mistake.
   Secondary things are about design principles and patterns.
6. [MOST IMPORTANT] Solutions you read online won't work, stick to requirements you wrote, sometimes requirement changes are small but design changes a lot.
7. ALWAYS PREFER SIMPLICITY WHICH MIGHT REQUIRE TO BREAK SOME PRINCIPLES (COMMUNICATE THAT "I know this principle is breaking but i am keeping it simple to avoid complexity")
  Example:
  You don't always need to create separate class and strategies for everything, even though it might make sense as future requirements.
  Sometimes multiple principles contradict each other.



# Steps

1. Discuss requirements (even if it is clean, keep it in documented format)
   - must to have
   - good to have
   - out of scope
   - edge cases and error handling

2. Create Entities and Relation
   
    Example:
     Car
     - model
     - id
     - kmLimitPerDay
     + changeLimit(limit)
      
3. Start Coding and comment everything you leave for later (Example: seeing race condition? possibility of deadlock? but don't want to break continuity of implementation? -> add comment and communicate to cover later)
4. Once you have working code, show edge case handling, concurrency handling, patterns used, and extensibility of code.
5. And you are done!
 

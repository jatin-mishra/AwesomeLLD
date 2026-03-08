# Problem
Design a rate limiter

# Requirements

## Must have
- configurable rules
  - entity_type
  - limit 
  - window_size 
- Algorithm is configurable
- multiple rules
  - all must pass
- check if eligible
- multi tenant (service level rules)

## Good to have
- proper headers
  - X-Refreshing-at
  - X-Remaining-rate

## out of scope
- if rules composition possible
  - and or

# Rules and Error Handling
- if any configured rule fails then don't allow
- if rule changes and it's okay to start fresh

# Solution
Algorithm(Enum):
- TokenBucket
- SlidingWindow

Rule:
- id 
- entity_type (USER_ID, REQUEST_ID, SERVICE_NAME)
- limit 
- time_window_in_seconds 
- Algorithm

// can handle defaults also
ConfigurationService:
- Map<ServiceName, Map<String, Rule>> 
+ addRule(serviceName, Rule) -> Optional<RuleId>
+ removeRule(serviceName, ruleId) -> Optional<Rule>

RequestContext(BuilderPattern):
- user-id
- request-id
- service_name (must to have)
- timestamp

RateLimiter:
+ isAllowed(RequestContext, rules)

SlidingWindowRateLimiter:
- Map<ServiceName, Map<EntityType, Deque<Timestamp>>
- isAllowed(RequestContext, Rule)
  // takes lock on service name, Entity Type
  // keep removing from timestamp limit based on config
  // if not allowed then add timestamp and return not allowed
  // or bulk function will try for next rule

TokenBucketCounter:
- bucketSize: integer
- tokens: double
- lastRefreshedAt

TokenBucketRateLimiter:
- Map<ServiceName, Map<EntityType, TokenBucketCounter>>
- isAllowed(RequestContext, Rule)
  // take lock
  // refresh based on Rule and current time vs last RefreshedAt
  // check if possible to allow
  // decrement and return proper response

RateLimiterFactory:
- Map<Algo, RateLimiter>
+ RateLimiterFactory()
+ getInstance(Algo)


RateLimitingApplication:
- ConfigurationService
- RateLimiterFactory
+ onBoardService(serviceName)
+ addRule(serviceName, Rule)
+ removeRule(serviceName, RuleId)
+ isAllowed(RequestContext)
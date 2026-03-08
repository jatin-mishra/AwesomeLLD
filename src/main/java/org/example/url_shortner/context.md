# Problem
Design a url shortner 

# Requirements

## Must have
- able to generate short url for given long url
- should be able to get long url for given short url
- refresh and increment expiry time

## Good to have
- expiry of urls


# Rules and Error handling
- if expired, refresh will generate new url
- limit of long url 

# Solutioning

RedisService:
- expiry
+ put(key, value, expiry) // tenant counter: (global, counter), shortUrl -> longUrl
+ refresh(key, expiry)
+ remove(key)
+ checkIfAlreadyExists(longUrl) // maintain set

IdGenerator:
+ generate()

@Primary
SequenceWiseGenerator:
- RedisService
+ generate() -> string

Shortener:
- RedisService 
- IdGenerator
+ shorten(longUrl)
  + idgenerator.generate()
  + add expiry and long to short in redis
  + return
+ remove(shortUrl)
+ refresh(shortUrl)


// can do with rdbms with unique index on shorturl and long url
// cache with redis
// or just use redis for LLD round explaining mysql could have been used.




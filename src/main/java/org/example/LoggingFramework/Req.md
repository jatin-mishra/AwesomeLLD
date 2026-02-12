Problem: https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/logging-framework.md

Design a logging framework


# Requirements

## Must have 
- support different log levels
- log with timestamp, log level and message content
- multiple output destination (console file or database)
- log level and output destination - must be configurable
- handle concurrent logging from multiple threads
- extensible to new log level and destination

## Good to have
- file destination
- variable args (with substitution)
- pretty json printer

## Out of scope
- real implementation of all destinations (file or database) (Will show extensibility)
- rule based encryption before logging
- distributed logging implementation of sending logs to batches and maintaining correctness 
  - or implementing some agent to push later when network destination is down.
  - retry implementation

# Error Handing and Edge cases
- wrong config
  - log level -> don't let the service up
  - destination type -> don't let the service up
  - destination connection -> don't let the service up (ping and check)
- changing level and destination at runtime -> don't allow


# Entity and Relation

LogLevel(enum)
- levels and priorities
+ isEnabled(configured LogLevel) -> boolean
  -> checks the priority

AppenderType
- Console
- File
- Database

MDC
- threadLocal<Mao<String, String>>
+ add(K, V)
+ remove(K)
+ copy() -> Map<String, String>
+ clear()

LogConfig
- globalLevel
- loggers (package overrides)
  - packageName: logLevel
- appenders
  - type: console/db/file
    timeZone: IST
    layout_type: json
    config:
      - timestampFormat
        prettyJson
        includes
        includeMDCKeys
        flattenMDC
        exception:
          rootFirst: true
          depth: full


LayoutConfig
- timestampformat
- includes
- includeMDCKeys
- flattenMDC
- rootFirst
- depth


JsonLayoutConfig extends LayoutConfig
- prettyJson

AppenderFactory

Appender
+ append(String formattedLog)

ConsoleAppender implements Appender
+ append(String formattedLog)


Layout:
+ format(Map<String, Object> event, LayoutConfig config)
+ buildErrorString(rootFirst, depth)
+ addMDC(includeMDCKeys, flattenMDC)


JsonLayout implements Layout
+ format(Map<String, Object> event, JsonLayoutConfig config) -> String
+ printJson(pretty, Map<String, Object>, includes[])


Logger
- packageName
- LoggerConfig

+ info(String content)
+ error(String content)
+ error(String content, Throwable th)

- log(level, content, throwable th)
  - validate the level
  - prepare map
    - content
    - level
    - th
  - append in all appenders, get using factory
    - for layouts in config
      - appender.append(layout.format(map, config))~~

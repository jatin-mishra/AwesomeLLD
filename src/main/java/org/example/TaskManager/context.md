

# Problem
Design Task Management System

## Requirements

### Must have
- create, update and delete tasks
- task (title, description, duedate, priority, status)
- assign tasks
- search tasks
    - priority, assigned user, due date, title, description(inverted index)
- task history of user

### Good to have
- generic state creation
- set reminders

### Out of scope
- permissions, who can assign. (scrum master permission)

### Edge cases and Error handling
- update/delete -> task doesn't exist : throw error
- assign task (already assigned) : override
- search (no match) : empty
- reminder (keep idempotent)


# Entity and Relation
Task:
- id
- title
- description
- priority
- due date
- status (TODO, IN-PROGRESS, DONE)
- List<Comment>
- List<Tag>
+ setTitle(title)
+ setDescription(description)
+ setPriority(prio)
+ setStatus(status)
+ addComment(Comment)
+ addReply(commentId, Comment reply)
+ getComments(depth)
+ getAllReplies(commentId)


Board:
- id
- name
- Map<id, Task>
+ addTask(task)
+ getTask(taskId)


BoardManager:
- Map<String, Board>
- EventBus
- SearchService
- TaskHistoryManager
+ addTask(boardId, task)
+ getTask(boardId, taskId)
+ updateTask(boardId, taskId, Task)
+ search(SearchQuery)
+ getHistory(boardId, taskId) -> List<Log>


EventData:
- boardId
- taskId
- metadata{}

EventBus:
- Map<EventType, List<Listeners>>
+ onEvent(EventType, EventData)

Listener:
+ onEvent(EventData)

// maintain at board and task level
Index:
+ index(EventData)
  PriorityIndex implements Index, Listener
  DueDateIndex implements Index, Listener
  AssignedUserIndex implements Index, Listener
  TagIndex implements Index, Listener

SearchService:
- IndexFactory
+ search(SearchQuery) -> List<Task>

TaskHistoryManager implements Listener:
- Map<boardId, Map<taskId, List<Log>>>
+ onEvent(EventData)

Comment:
- id
- content
- userId
- timestamp
- Comment[] replies


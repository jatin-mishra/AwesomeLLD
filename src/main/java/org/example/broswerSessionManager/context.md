# Problem Statement
Design Session Manager

API:
POST /session
    {
        browser: chrome|firefox|safari,
        version: "v1",
        timeout : 120 // seconds
    }
    Response:
    {
        session_id: <uuid>,
        created_at: <timestamp>
    }

GET /session/:id
    Response:
    {
        browser: chrome|firefox|safari,
        version: "v1",
        timeout : 120 // seconds
    }

get by id
delete by id
get all by browser type

redis_storage_type:
    browser_type:
        session_id: {}





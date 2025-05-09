# JavaBackend
Java backend for angular project

## Database-overzicht

```mermaid
erDiagram
    USER {
        BIGINT  id PK
        VARCHAR firstName
        VARCHAR lastName
        VARCHAR email
        VARCHAR password
        VARCHAR phone
        ENUM    role  "USER | ADMIN | TEAMLEADER"
    }

    EVENT {
        BIGINT  id PK
        VARCHAR name
        ENUM    eventType "Sport | Tech | Social | Community"
        BIGINT  location_id FK
    }

    LOCATION {
        BIGINT  id PK
        VARCHAR name
        VARCHAR address
        DOUBLE  latitude
        DOUBLE  longitude
    }

    REGISTRATION {
        BIGINT id PK
        BIGINT user_id FK
        BIGINT event_id FK
    }

    USER ||--o{ REGISTRATION : has
    EVENT ||--o{ REGISTRATION : has
    LOCATION ||--o{ EVENT : hosts


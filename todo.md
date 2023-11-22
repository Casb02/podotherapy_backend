Endpoints
---
### Dashboard

#### Users
| Status              | Method                                          | Endpoint                          | Description                             |
|---------------------|-------------------------------------------------|-----------------------------------|-----------------------------------------|
| Done                | ![](https://img.shields.io/badge/POST-8A2BE2)   | `/users`                          | All (non-admin) users (admin only)      |
| Development started | ![](https://img.shields.io/badge/GET-47d147)    | `/users/{userId}`                 | Get info of 1 user (more detailed)      |
| Done                | ![](https://img.shields.io/badge/POST-8A2BE2)   | `/users/create`                   | Create patient                          |
| Done                | ![](https://img.shields.io/badge/POST-8A2BE2)   | `/users/{userId}/update`          | Update a user's info                    |
| Done                | ![](https://img.shields.io/badge/DELETE-f20d0d) | `/users/{userId}`                 | Delete a user by ID                     |

## History
| Status | Method                                       | Endpoint               | Description          |
|--------|----------------------------------------------|------------------------|----------------------|
| Soon   | ![](https://img.shields.io/badge/GET-47d147) | `/users/{id}/history/` | Get a user's history |

## HistoryDay
| Status | Method                                       | Endpoint                          | Description               |
|--------|----------------------------------------------|-----------------------------------|---------------------------|
| Soon   | ![](https://img.shields.io/badge/GET-47d147) | `/users/{id}/history/day/{dayID}` | Info about a specific day |

---

### (Game Engine)

#### User Endpoint
| Status     | Method                                        | Endpoint       | Description                           |
|------------|-----------------------------------------------|----------------|---------------------------------------|
| Soon       | ![](https://img.shields.io/badge/POST-8A2BE2) | `/history/day` | User sends 1 day to be stored         |
| Soon (TBD) | ![](https://img.shields.io/badge/POST-8A2BE2) | `/history`     | User sends array of days to be stored |

#### Gamesave Upload
| Status | Method                                        | Endpoint                 | Description                    |
|--------|-----------------------------------------------|--------------------------|--------------------------------|
| Done   | ![](https://img.shields.io/badge/POST-8A2BE2) | `/gamesave/upload`       | Upload to server               |
| Done   | ![](https://img.shields.io/badge/GET-47d147)  | `/gamesave/restore`      | Sends a download file          |

#### Login (applicable for both)
| Status | Method                                        | Endpoint             | Description                           |
|--------|-----------------------------------------------|----------------------|---------------------------------------|
| Done   | ![](https://img.shields.io/badge/POST-8A2BE2) | `/auth/authenticate` | Returns a user token with permissions |

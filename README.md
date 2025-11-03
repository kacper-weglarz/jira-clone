# 🎫 Simple Ticket System

> A ticket management system built with Spring Boot and Thymeleaf. Create, edit, and track tickets with real-time filtering and user assignment.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-blue)

---

## 📋 What It Does

**Simple Ticket System** is a web-based CRUD application for managing team tasks. Track bugs, features, and tasks with:

- ✅ Create and edit tickets
- 👤 Assign to users (auto-create if needed)
- 📊 Track status (NEW → IN_PROGRESS → DONE)
- 🏷️ Set priority levels (LOW, MEDIUM, HIGH)
- 🔍 Filter by status, user, date range
- 📄 REST API for programmatic access
- 💾 Auto-save completion timestamps

---

## 🏗️ Architecture

Spring Boot Application <br>
├── Controller Layer (MVC + REST)<br>
│ ├── ViewController (Thymeleaf UI)<br>
│ ├── TicketController (REST API)<br>
│ └── UserController (REST API)<br>
├── Service Layer (Business Logic)<br>
│ ├── TicketService<br>
│ └── UserService<br>
├── Repository Layer (JPA)<br>
│ ├── TicketRepository<br>
│ └── UserRepository<br>
└── Data Layer (H2)<br>
├── Ticket Entity<br>
└── User Entity<br>

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Backend** | Spring Boot, Spring Data JPA |
| **Frontend** | Thymeleaf, HTML5, CSS |
| **Database** | PostgreSQL, PgAdmin4 |
| **Build** | Maven |
| **Utilities** | Lombok, Jakarta Validation |

## ⚠️ Known Limitations

- No user authentication/authorization (everyone can access everything)
- No DTO pattern (REST API exposes entities directly)
- Circular reference potential (Ticket ↔ User)
- Missing global exception handler
- No HTTP status code standardization (201, 204, etc.)
- N+1 query problem with user relationships

---

## 📚 What I Learned

✅ Spring Boot & Spring Data JPA  
✅ Thymeleaf templating engine  
✅ REST API design with pagination  
✅ JPA entity relationships (@ManyToOne, @OneToMany)  
✅ Transactional operations (@Transactional)  
✅ Lifecycle callbacks (@PrePersist, @PreUpdate)  
✅ Custom entity setters for business logic  

---

## 🚀 Future Improvements

- [ ] Spring Security (login, roles)
- [ ] DTO pattern for API
- [ ] Global exception handler
- [ ] Proper HTTP status codes (201, 204, etc.)
- [ ] Unit tests (90%+ coverage)
- [ ] Swagger/OpenAPI docs
- [ ] Docker Compose setup
- [ ] Deploy to Railway/Render
- [ ] Comments on tickets
- [ ] Dashboard with stats


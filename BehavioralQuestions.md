# 🎯 Strategy Pattern

### 1) Payment Strategy Selection (very common)

**Requirement:**
An e-commerce system supports multiple payment methods: `CreditCard`, `UPI`, `Wallet`, `NetBanking`. At runtime, the user chooses how to pay. Payment behavior must be swappable without changing checkout logic.

**Implement:**

* `PaymentStrategy` interface → `pay(amount)`
* Concrete strategies for each payment method
* `CheckoutService` uses `PaymentStrategy`

**Acceptance:**

* Switching payment method does not change checkout code
* New payment methods can be added without modifying `CheckoutService`

**Follow-ups:**

* Strategy vs Factory (why both are often used together)
* How to inject strategy (constructor vs setter)
* Validation logic placement
* Logging / retry handling

---

### 2) Pricing / Discount Strategy Engine

**Requirement:**
Different users get different pricing rules:

* Regular → no discount
* Premium → flat 10%
* Festival → dynamic discount

Pricing logic must be configurable and runtime-switchable.

**Follow-ups:**

* Strategy vs if-else chain
* Combining strategies (stacked discounts)
* Strategy + Decorator discussion

---

# 👀 Observer Pattern

### 3) Order Status Notification System (classic but modern)

**Requirement:**
When an order status changes (`PLACED`, `SHIPPED`, `DELIVERED`), notify:

* Email service
* SMS service
* Push notification service

Observers can be added/removed dynamically.

**Implement:**

* `Order` as Subject
* `NotificationObserver` interface
* Concrete observers

**Acceptance:**

* Adding a new notification channel does NOT change `Order` logic
* Observers are notified automatically on status change

**Follow-ups:**

* Observer vs Pub-Sub
* Synchronous vs asynchronous observers
* Failure handling (one observer fails)
* Memory leaks (dangling observers)

---

### 4) Stock Price Monitoring System (very common)

**Requirement:**
A stock price changes frequently. Multiple consumers (charts, alerts, analytics) subscribe to price updates.

**Follow-ups:**

* Pull vs push model
* Thread safety
* Event ordering
* Why Observer instead of polling

---

# 🔁 Iterator Pattern

### 5) Custom Collection Traversal

**Requirement:**
You have a custom data structure (`Playlist`, `OrderHistory`, `Cache`). Provide traversal without exposing internal representation.

**Implement:**

* Custom `Iterator`
* Multiple traversal strategies (forward, reverse)

**Follow-ups:**

* Iterator vs exposing list
* Fail-fast vs fail-safe iterators
* External vs internal iterator

---

# 🧾 Command Pattern

### 6) Undo / Redo in Text Editor (VERY common)

**Requirement:**
Implement undo/redo for actions like:

* Insert text
* Delete text
* Format text

Each action must be reversible.

**Implement:**

* `Command` interface (`execute`, `undo`)
* Command history stack

**Acceptance:**

* Undo restores previous state
* Redo reapplies last undone command

**Follow-ups:**

* Command vs Memento
* Memory optimization
* Batch commands
* Logging & audit trails

---

### 7) Remote Control / Button Handler

**Requirement:**
Buttons trigger actions (TV on/off, volume up/down). Buttons should not know what device they control.

**Follow-ups:**

* Command vs Strategy
* Macro commands
* Queueing commands (async execution)

---

# 🧠 Mediator Pattern

### 8) Chat Room System

**Requirement:**
Users send messages to each other via a chat room. Users do not communicate directly.

**Implement:**

* `ChatMediator`
* `User` objects communicate via mediator

**Follow-ups:**

* Mediator vs Observer
* Scaling mediator
* Avoiding god-object mediator

---

### 9) Air Traffic Control System

**Requirement:**
Planes do not talk to each other directly. All coordination happens via ATC.

**Follow-ups:**

* Centralized control pros/cons
* Failure handling
* Distributed mediators

---

# 🔄 State Pattern

### 10) Order Lifecycle Management (extremely common)

**Requirement:**
An order transitions through states:
`New → Paid → Shipped → Delivered → Cancelled`

Each state has different allowed actions.

**Implement:**

* `OrderState` interface
* Concrete state classes

**Acceptance:**

* Invalid transitions are prevented
* Behavior depends on current state

**Follow-ups:**

* State vs if-else
* State explosion
* Persistence of state
* Workflow engines comparison

---

### 11) Vending Machine

**Requirement:**
Machine behaves differently based on state (NoCoin, HasCoin, Dispensing).

**Follow-ups:**

* Thread safety
* Handling partial failures
* State transitions testing

---

# 🧩 Template Method Pattern

### 12) Data Import Pipeline

**Requirement:**
Data import follows steps:

1. Read data
2. Validate
3. Transform
4. Save

Different data sources override only certain steps.

**Implement:**

* Abstract class with template method
* Hooks for optional steps

**Follow-ups:**

* Template vs Strategy
* Hook methods
* When inheritance becomes limiting

---

# 🔗 Chain of Responsibility

### 13) Request Validation Pipeline (VERY common)

**Requirement:**
An API request passes through:

* Authentication
* Authorization
* Rate limiting
* Validation

Each handler can stop the chain.

**Implement:**

* Handler interface
* Linked handlers

**Acceptance:**

* Order of handlers matters
* Easy to add/remove handlers

**Follow-ups:**

* Chain vs filters / middleware
* Short-circuiting
* Debugging complexity

---

### 14) Logging Framework

**Requirement:**
Log messages go through multiple loggers based on severity.

**Follow-ups:**

* Performance concerns
* Async logging
* Dynamic chain configuration

---

# 🧳 Visitor Pattern

### 15) Tax Calculation on Different Products

**Requirement:**
Different product types (`Food`, `Electronics`, `Luxury`) require different tax calculations.

**Implement:**

* Visitor for tax logic
* Product hierarchy

**Follow-ups:**

* Visitor vs double dispatch
* Adding new operations vs new elements
* Why Visitor is rarely used but powerful

---

# 🧠 Memento Pattern

### 16) Game Save / Restore System

**Requirement:**
Allow saving and restoring game state without exposing internals.

**Implement:**

* Memento object
* Caretaker

**Follow-ups:**

* Memento vs Command
* Memory optimization
* Snapshot frequency

---

# 🔥 Mixed / Realistic Interview Problems (IMPORTANT)

### 17) Event-Driven Notification Platform (Observer + Factory)

**Requirement:**
System emits events (OrderPlaced, PaymentFailed). Different notification channels are created via Factory and subscribed via Observer.

**Patterns Used:**

* Factory → create notifier
* Observer → subscribe to events

**Follow-ups:**

* Scaling observers
* Message queues vs Observer
* Retry & failure handling

---

### 18) Trading System (Strategy + Observer + Singleton)

**Requirement:**

* Strategy → trading algorithm
* Observer → price updates
* Singleton → market data feed

**Follow-ups:**

* Thread safety
* Latency concerns
* Strategy hot-swap

---

### 19) Workflow Engine (State + Observer + Chain)

**Requirement:**
Workflow transitions trigger observers and pass through validation chains.

**Follow-ups:**

* Transaction boundaries
* Rollbacks
* Audit trails

---

# 🧠 How interviewers evaluate you

They check if you can:

1. **Identify the pattern from requirements**
2. **Avoid over-engineering**
3. **Explain trade-offs**
4. **Combine patterns logically**
5. **Write clean, extensible code**

---

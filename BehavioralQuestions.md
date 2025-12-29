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

## 1️⃣ Notification Platform (Observer + Strategy + Factory)

**Asked in:** Flipkart, Swiggy, Amazon (variants)

### Requirement

Design a notification system where:

* Events: `OrderPlaced`, `OrderCancelled`, `PaymentFailed`
* Channels: Email, SMS, WhatsApp, Push
* Each user can **configure preferred channels**
* Each channel has **different retry logic**

### Patterns Used

* **Observer** → event subscription
* **Strategy** → delivery & retry strategy
* **Factory** → create notification channel objects

### Interview Focus

* Why Observer instead of direct calls?
* Why Strategy for retry?
* How to add Slack notification tomorrow?
* Async vs sync observers

---

## 2️⃣ Workflow Engine (State + Observer + Chain of Responsibility)

**Asked in:** Atlassian, Salesforce, ServiceNow

### Requirement

Design a workflow system where:

* Entity goes through states: `Draft → Review → Approved → Rejected`
* Each transition:

  * Triggers notifications
  * Passes through validation rules (role, SLA, permissions)

### Patterns Used

* **State** → behavior per state
* **Observer** → notify watchers
* **Chain of Responsibility** → validation pipeline

### Follow-ups

* Persisting state
* Rollback on failure
* Workflow versioning

---

## 3️⃣ Rate Limiter + Access Control (Chain + Strategy)

**Asked in:** Amazon, Uber

### Requirement

Incoming API request passes through:

1. Authentication
2. Authorization
3. Rate limiting
4. Request validation

Different APIs use **different rate-limit strategies**.

### Patterns Used

* **Chain of Responsibility** → request pipeline
* **Strategy** → fixed window, sliding window, token bucket

### Interview Twist

> “Tomorrow we want per-user + per-IP rate limits — how do you extend?”

---

## 4️⃣ Trading Platform (Observer + Strategy + Singleton)

**Asked in:** Bloomberg, Goldman Sachs, HFT startups

### Requirement

* Market price feed (single source)
* Multiple trading algorithms subscribe
* Algorithms can be switched at runtime
* Only one market feed instance allowed

### Patterns Used

* **Observer** → price updates
* **Strategy** → trading logic
* **Singleton** → market data feed

### Follow-ups

* Thread safety
* Latency guarantees
* Backpressure handling

---

## 5️⃣ Chat Application (Mediator + Observer)

**Asked in:** Microsoft, Meta (LLD rounds)

### Requirement

* Users send messages via chat rooms
* Users can subscribe/unsubscribe
* Message delivery events trigger notifications

### Patterns Used

* **Mediator** → message routing
* **Observer** → notifications, read receipts

### Interview Angle

* Mediator vs Observer difference
* Scaling chat rooms
* Avoiding god mediator

---

## 6️⃣ Undo / Redo System (Command + Memento)

**Asked in:** Adobe, Atlassian

### Requirement

* Support undo/redo
* Each action changes complex internal state
* State snapshot should not expose internals

### Patterns Used

* **Command** → actions
* **Memento** → state snapshots

### Follow-ups

* Memory optimization
* Partial undo
* Command batching

---

## 7️⃣ Rule Engine (Chain + Strategy + Interpreter-lite)

**Asked in:** Paytm, Razorpay

### Requirement

Rules like:

* If amount > 10k → extra verification
* If country != India → block
* If VIP → skip some rules

Rules should be:

* Configurable
* Order-sensitive

### Patterns Used

* **Chain of Responsibility** → rule execution
* **Strategy** → rule behavior
* (Optional) **Interpreter** for DSL

---

## 8️⃣ Logging & Audit System (Chain + Observer)

**Asked in:** Oracle, SAP

### Requirement

* Logs go through filters
* Certain logs trigger alerts
* Multiple destinations (file, DB, external service)

### Patterns Used

* **Chain** → filtering
* **Observer** → alerting system

### Interview Question

> “Why not just if-else logging?”

---

## 9️⃣ Scheduler System (Command + Singleton + Observer)

**Asked in:** Amazon (scheduler services)

### Requirement

* Schedule jobs
* Execute later
* Notify listeners on completion/failure

### Patterns Used

* **Command** → encapsulated jobs
* **Singleton** → scheduler
* **Observer** → job listeners

---

## 🔟 Form Validation Engine (Template + Strategy)

**Asked in:** Google, Frontend-heavy LLD rounds

### Requirement

Form submission steps:

1. Sanitize
2. Validate
3. Persist
4. Notify

Validation rules differ per form.

### Patterns Used

* **Template Method** → workflow
* **Strategy** → validation logic

---

## 1️⃣1️⃣ Document Processing Pipeline

**(Template + Chain + Observer)**
**Asked in:** Document management systems

### Requirement

* Process documents in steps
* Validation failures stop pipeline
* Completion triggers notifications

### Patterns Used

* Template → workflow
* Chain → validation
* Observer → events

---

## 1️⃣2️⃣ Multi-Level Approval System

**(State + Observer + Mediator)**
**Asked in:** Enterprise HR / ERP systems

### Requirement

* Approval passes through levels
* Transitions notify stakeholders
* No approver talks directly to another

### Patterns Used

* State → approval stages
* Mediator → coordination
* Observer → notifications

---

## 1️⃣3️⃣ API Gateway Design

**(Chain + Strategy + Observer)**
**Asked in:** Backend platform teams

### Requirement

* Request validation
* Rate limiting
* Routing
* Metrics publishing

### Patterns Used

* Chain → pipeline
* Strategy → routing logic
* Observer → metrics/events

---

## 1️⃣4️⃣ Recommendation System

**(Strategy + Observer)**
**Asked in:** Netflix-like systems

### Requirement

* Different recommendation algorithms
* Trigger recalculation on user activity

### Patterns Used

* Strategy → recommendation logic
* Observer → activity events

---

## 1️⃣5️⃣ Version Control Lite

**(Command + Memento + Observer)**
**Asked in:** Dev tools companies

### Requirement

* Track changes
* Rollback
* Notify collaborators

### Patterns Used

* Command → changes
* Memento → snapshots
* Observer → collaboration updates
---

# 🧠 How interviewers evaluate you

They check if you can:

1. **Identify the pattern from requirements**
2. **Avoid over-engineering**
3. **Explain trade-offs**
4. **Combine patterns logically**
5. **Write clean, extensible code**

---

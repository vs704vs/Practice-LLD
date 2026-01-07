# 🧱 STRUCTURAL DESIGN PATTERNS — INTERVIEW QUESTION BANK

---

## 🔌 Adapter Pattern

### 1️⃣ Third-Party Payment Gateway Adapter

**Asked in:** Amazon, Razorpay, Paytm

**Requirement:**
Your system expects a `PaymentProcessor.pay(amount)` interface.
You integrate third-party gateways:

* Razorpay (`makePayment`)
* Stripe (`charge`)
* PayPal (`sendMoney`)

You **cannot modify third-party code**.

**Implement:**

* Adapter classes wrapping each gateway
* Client depends only on `PaymentProcessor`

**Acceptance Criteria:**

* Switching gateway does not affect checkout logic
* New gateways can be added without touching client code

**Follow-ups:**

* Adapter vs Facade
* Object adapter vs class adapter
* Error mapping & retries
* When NOT to use adapter

---

### 2️⃣ Legacy System Integration Adapter

**Asked in:** Banks, enterprise systems

**Requirement:**
Legacy service returns data in XML; new system expects JSON DTOs.

**Follow-ups:**

* Data transformation responsibility
* Performance concerns
* Adapter vs anti-corruption layer

---

## 🌳 Composite Pattern

### 3️⃣ File System Representation

**Asked in:** Microsoft, Google

**Requirement:**
Represent:

* Files
* Directories (which can contain files/directories)

Client should treat both uniformly.

**Implement:**

* `FileSystemNode`
* `File`, `Directory`

**Acceptance:**

* `getSize()` works for both
* Recursive operations supported

**Follow-ups:**

* Composite vs tree data structure
* Traversal strategies
* Prevent cyclic references

---

### 4️⃣ Organization Hierarchy

**Asked in:** HR / ERP systems

**Requirement:**
Employee → Manager → Director → VP
Each can calculate total salary cost under them.

**Follow-ups:**

* Lazy vs eager computation
* Performance for deep trees
* Composite + Iterator

---

## 🎭 Facade Pattern

### 5️⃣ Order Placement Facade

**Asked in:** Flipkart, Amazon

**Requirement:**
Placing an order involves:

* Inventory service
* Payment service
* Shipping service
* Notification service

Expose **one simple API**: `placeOrder()`.

**Implement:**

* `OrderFacade`

**Acceptance:**

* Client does not interact with subsystems directly
* Internal complexity hidden

**Follow-ups:**

* Facade vs Service layer
* Error handling inside facade
* When facade becomes god-object

---

### 6️⃣ Video Upload Platform Facade

**Asked in:** Media platforms

**Requirement:**
Uploading video involves:

* Encoding
* Virus scan
* Thumbnail generation
* CDN upload

Expose a simple interface to UI.

---

## 🎀 Decorator Pattern

### 7️⃣ Coffee / Pricing Add-ons

**Asked in:** Starbucks-style problems (very common)

**Requirement:**
Base coffee + add-ons:

* Milk
* Sugar
* Whipped cream

Add-ons can stack dynamically.

**Implement:**

* Base component
* Decorators wrapping component

**Acceptance:**

* Cost accumulates correctly
* No class explosion

**Follow-ups:**

* Decorator vs inheritance
* Order of decorators
* Decorator vs Strategy

---

### 8️⃣ API Request Middleware

**Asked in:** Backend-heavy roles

**Requirement:**
HTTP request passes through:

* Authentication
* Logging
* Metrics
* Compression

Each concern should be **pluggable**.

**Patterns:**

* Decorator (wrapping request handling)

**Follow-ups:**

* Decorator vs Chain of Responsibility
* Execution order
* Async decorators

---

## 🌉 Bridge Pattern

### 9️⃣ Notification Sender (VERY common)

**Asked in:** Uber, Swiggy

**Requirement:**
Notifications:

* Types: Alert, Promotion, OTP
* Channels: Email, SMS, Push

Avoid class explosion like:
`EmailAlert`, `SMSAlert`, `PushAlert`, etc.

**Implement:**

* Abstraction: Notification
* Implementor: NotificationSender

**Acceptance:**

* Any notification type works with any channel

**Follow-ups:**

* Bridge vs Strategy
* When bridge is overkill
* Runtime switching

---

### 🔟 Device + Remote Control

**Asked in:** Embedded + system design

**Requirement:**
Remote controls operate:

* TV
* AC
* Speaker

Remote abstraction decoupled from device implementation.

---

## 🕵️ Proxy Pattern

### 1️⃣1️⃣ API Gateway / Access Proxy

**Asked in:** Platform teams

**Requirement:**
Proxy should:

* Authenticate
* Rate limit
* Log requests
* Forward to real service

**Implement:**

* Proxy implements same interface as real service

**Acceptance:**

* Client unaware of proxy
* Proxy controls access

**Follow-ups:**

* Proxy vs Decorator
* Remote proxy vs virtual proxy
* Performance impact

---

### 1️⃣2️⃣ Lazy Loading Images

**Asked in:** Frontend interviews

**Requirement:**
Large image should load only when accessed.

**Patterns:**

* Virtual Proxy

---

## 🧬 Flyweight Pattern

### 1️⃣3️⃣ Text Editor Character Rendering

**Asked in:** Microsoft, Adobe

**Requirement:**
Millions of characters rendered, but:

* Font, size, style shared
* Position differs

**Implement:**

* Flyweight factory caching intrinsic state

**Acceptance:**

* Memory usage minimized

**Follow-ups:**

* Intrinsic vs extrinsic state
* Cache eviction
* Flyweight vs Singleton

---

### 1️⃣4️⃣ Map / Game Object Rendering

**Asked in:** Game companies

**Requirement:**
Thousands of trees/buildings share same texture/model.

---

# 🔥 MIXED / REALISTIC INTERVIEW PROBLEMS

(Structural + Behavioral + Creational)

---

## 1️⃣ Notification Platform

**(Observer + Bridge + Factory)**

**Asked in:** Flipkart, Swiggy, Amazon, Uber

### Problem Statement

Design a notification system for an e-commerce platform where different system events trigger notifications that can be sent through multiple channels.

### Functional Requirements

* System generates events like:

  * `OrderPlaced`
  * `OrderCancelled`
  * `PaymentFailed`
* Each event can generate different **notification types**:

  * Alert
  * Promotional
  * Transactional
* Notifications can be sent via:

  * Email
  * SMS
  * Push
  * WhatsApp
* New notification channels must be added **without modifying existing code**
* Users can subscribe/unsubscribe from notification events

### Follow-ups

* Why Observer instead of direct method calls?
* Why Bridge instead of inheritance (`EmailAlert`, `SMSAlert`)?
* How will retries and failures be handled?
* How would you make this async (Kafka / MQ)?
* How to prevent notification storms?

---

## 2️⃣ E-commerce Checkout System

**(Facade + Adapter + Strategy)**

**Asked in:** Amazon, Walmart, Meesho

### Problem Statement

Design a checkout system that simplifies order placement while integrating multiple subsystems.

### Functional Requirements

* Checkout involves:

  * Inventory validation
  * Pricing & discount calculation
  * Payment processing
  * Order confirmation
* Multiple payment gateways:

  * Razorpay
  * Stripe
  * PayPal
* Pricing rules vary:

  * Festival discounts
  * Coupon-based discounts
  * User-specific pricing
* Client should call **only one API** to place an order

### Follow-ups

* Why Facade instead of a Service class?
* Adapter vs Strategy for payment gateways?
* How to add a new payment provider?
* Where will rollback happen if payment fails?
* How do you test this system?

---

## 3️⃣ Logging Framework

**(Facade + Decorator + Observer)**

**Asked in:** Oracle, SAP, Atlassian

### Problem Statement

Design a logging framework similar to Log4j with extensible features.

### Functional Requirements

* Support log levels:

  * INFO
  * DEBUG
  * ERROR
* Logs can be:

  * Filtered
  * Formatted
  * Enriched with metadata
* Logs can be sent to:

  * File
  * Database
  * External monitoring service
* ERROR logs should trigger alerts

### Follow-ups

* Decorator vs Chain of Responsibility
* How to avoid performance bottlenecks?
* How to make logging async?
* How to prevent recursive logging failures?

---

## 4️⃣ Cloud Storage SDK

**(Adapter + Proxy + Flyweight)**

**Asked in:** AWS teams, Google Cloud, Microsoft Azure

### Problem Statement

Design a unified cloud storage SDK that supports multiple cloud providers.

### Functional Requirements

* Support cloud providers:

  * AWS S3
  * Google Cloud Storage
  * Azure Blob Storage
* Expose a **single interface** for upload/download
* Add:

  * Authentication
  * Caching
  * Access control
* Metadata objects should be shared to reduce memory usage

### Follow-ups

* Adapter vs Facade for cloud SDK?
* What goes into Proxy vs Adapter?
* Intrinsic vs extrinsic state in Flyweight?
* How to handle token expiration?
* Thread safety concerns?

---

## 5️⃣ UI Component Library

**(Composite + Decorator + Observer)**

**Asked in:** Frontend LLD rounds, Meta, Google

### Problem Statement

Design a UI component framework similar to React/Angular component trees.

### Functional Requirements

* UI elements:

  * Button
  * TextField
  * Panel
* Components can be nested infinitely
* Styles and themes can be applied dynamically
* UI updates should propagate automatically on state changes

### Follow-ups

* Composite vs simple tree structure
* Decorator vs CSS-based styling
* Observer vs event bubbling
* Performance optimizations for deep trees

---

## 6️⃣ Enterprise Workflow Platform

**(Factory + Composite + Observer + Facade)**

**Asked in:** ServiceNow, Salesforce, SAP

### Problem Statement

Design a workflow engine that supports complex business workflows.

### Functional Requirements

* Workflow consists of multiple steps
* Steps can be:

  * Sequential
  * Parallel
  * Nested
* Workflow creation depends on workflow type
* State changes must notify interested parties
* Client interacts with workflow via a simple API

### Follow-ups

* How to persist workflow state?
* How to rollback on failure?
* Composite vs State pattern here?
* How to visualize workflow execution?

---

## 7️⃣ API Gateway

**(Proxy + Chain of Responsibility + Observer)**

**Asked in:** Uber, Netflix, Amazon

### Problem Statement

Design an API gateway that sits in front of microservices.

### Functional Requirements

* Incoming request must pass through:

  * Authentication
  * Authorization
  * Rate limiting
  * Routing
* Gateway should publish metrics and logs
* Gateway should not expose internal services directly

### Follow-ups

* Proxy vs Facade in gateway?
* Chain vs middleware pipeline?
* How to add new filters dynamically?
* Failure isolation strategies?

---

## 8️⃣ Document Processing System

**(Facade + Decorator + Observer)**

**Asked in:** Adobe, DocuSign

### Problem Statement

Design a document processing pipeline.

### Functional Requirements

* Upload document
* Apply:

  * Virus scan
  * OCR
  * Watermark
  * Digital signature
* Each processing step is optional
* Completion or failure should notify users

### Follow-ups

* Decorator vs Strategy for processing steps?
* How to handle large files?
* Parallel processing?
* Retry logic?

---

## 9️⃣ Multi-Tenant Configuration System

**(Flyweight + Proxy + Observer)**
🆕 **(Recent interview trend)**

**Asked in:** SaaS platform teams (2024–2025)

### Problem Statement

Design a configuration management system for a multi-tenant SaaS product.

### Functional Requirements

* Thousands of tenants
* Many share identical configuration values
* Config updates should notify dependent services
* Access must be controlled per tenant

### Follow-ups

* Flyweight memory savings
* Cache invalidation
* Event consistency
* Thread safety

---

## 🔟 Feature Toggle System

**(Proxy + Observer + Strategy)**
🆕 **(Very recent & practical)**

**Asked in:** Netflix, Uber (Platform teams)

### Problem Statement

Design a feature flag system that controls feature rollout.

### Functional Requirements

* Enable/disable features dynamically
* Support rollout strategies:

  * Percentage based
  * User based
  * Region based
* Feature access should be transparent to client code
* Changes should propagate immediately

### Follow-ups

* Proxy vs conditional logic
* Consistency across distributed systems
* Rollback safety
* Observability

---

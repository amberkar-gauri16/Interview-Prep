**System Design Cheat Sheet: Tools, Technologies, Tradeoffs**

---

### 🧱 Load Balancing

Load balancing distributes incoming traffic across multiple servers to ensure reliability and performance.

| Tool/Tech       | Use Case               | Pros                             | Cons                        | When to Use                                           |
| --------------- | ---------------------- | -------------------------------- | --------------------------- | ----------------------------------------------------- |
| NGINX / HAProxy | HTTP Load Balancer     | Simple, widely used, easy config | Limited app-layer awareness | When you need a quick reverse proxy or L7/L4 balancer |
| AWS ALB / ELB   | Managed Load Balancing | Scalable, integrated with AWS    | Cloud lock-in, cost         | AWS-hosted services or auto-scaling architectures     |
| Envoy           | Service mesh + LB      | Advanced routing, observability  | Steeper learning curve      | Use with microservices or gRPC                        |

**Important Notes:** Load balancers can be Layer 4 (TCP) or Layer 7 (HTTP). For advanced traffic routing, canary deployments, or retries, Envoy is often preferred.

---

### 🧩 API Gateways

API gateways sit between clients and services, handling routing, security, and monitoring.

| Tool/Tech        | Use Case                  | Pros                                           | Cons                        | When to Use                                             |
| ---------------- | ------------------------- | ---------------------------------------------- | --------------------------- | ------------------------------------------------------- |
| AWS API Gateway  | Managed API Gateway       | Scales easily, integrates with AWS, throttling | Cost at high volume         | Serverless or AWS-native APIs                           |
| Kong / Tyk       | Open-source API Gateway   | Extensible plugins, authentication             | Requires setup, ops effort  | Full control over routing, rate limits, security       |
| Apigee (Google)  | Enterprise API Management | Strong analytics, policy enforcement           | Expensive, overkill for SMB | Enterprises needing deep control over public APIs      |

**Important Notes:** API gateways are key in microservices architectures for enforcing rate limits, authentication, logging, and routing to versioned services. They often work alongside service meshes for east-west traffic control.

---

### 📡 REST APIs

REST APIs are a standard way to expose resources over HTTP using stateless, uniform interfaces.

| Feature           | Description                                                 |
| ----------------- | ----------------------------------------------------------- |
| Statelessness     | Each request contains all context, no server-side sessions |
| Uniform Interface | Uses standard HTTP methods (GET, POST, PUT, DELETE)        |
| Resource-Based    | URLs represent resources; responses are typically in JSON  |
| Cacheable         | Responses can be cached with proper headers                |

**Important Notes:** REST APIs are ideal for web and mobile apps, are widely supported, and work well with API gateways. For real-time or streaming use cases, consider alternatives like gRPC or WebSockets.

---

### 📦 Container Orchestration (Kubernetes)

Kubernetes (K8s) automates deployment, scaling, and management of containerized applications.

| Tool/Tech   | Use Case                          | Pros                                                  | Cons                                | When to Use                                           |
| ----------- | --------------------------------- | ----------------------------------------------------- | ----------------------------------- | ----------------------------------------------------- |
| Kubernetes  | Container orchestration platform  | Declarative deployments, auto-scaling, self-healing   | Complex setup and learning curve   | Managing microservices or large-scale container apps  |
| EKS/GKE/AKS | Managed Kubernetes services       | Simplifies infra, auto-upgrades, integrates with cloud| Less control, vendor lock-in       | Production-grade K8s with reduced ops burden          |

**Important Notes:** Kubernetes is ideal for running distributed systems or microservices. It works best when paired with service meshes (like Istio) and observability tools. It allows for blue-green/canary deployments, and facilitates resource-efficient scaling.

---

### 🗃 Caching

Caching helps reduce latency and load on backend systems by storing frequently accessed data.

| Tool/Tech                | Use Case                  | Pros                                     | Cons                                  | When to Use                                 |
| ------------------------ | ------------------------- | ---------------------------------------- | ------------------------------------- | ------------------------------------------- |
| Redis                    | In-memory key-value store | Extremely fast, supports data structures | Memory-bound, cost for large datasets | Session caching, leaderboard, rate limiting |
| Memcached                | Simple caching            | Very fast, less memory overhead          | No persistence, limited data types    | Transient caching without complex types     |
| CDN (Cloudflare, Akamai) | Static content delivery   | Global edge delivery, latency reduction  | Cache invalidation can be tricky      | Static assets, images, video content        |

**Important Notes:** Choose Redis for persistence and rich data types; Memcached for simple key-value. Always define TTLs and eviction policies clearly.

---

### 🛢 Databases

Database choice impacts consistency, scalability, and query capabilities.

| Tool/Tech  | Type              | Pros                              | Cons                              | When to Use                               |
| ---------- | ----------------- | --------------------------------- | --------------------------------- | ----------------------------------------- |
| PostgreSQL | Relational        | Strong consistency, rich querying | Harder to scale horizontally      | OLTP apps, analytics, joins required      |
| MySQL      | Relational        | Fast, simple, widely supported    | Weaker with complex queries       | LAMP stack, read-heavy workloads          |
| MongoDB    | Document (NoSQL)  | Flexible schema, JSON-like        | Weak consistency, joins are hard  | Fast-moving apps, unstructured data       |
| DynamoDB   | Key-Value (NoSQL) | Fully managed, scalable           | Harder to debug, limited querying | AWS-native apps needing high availability |
| Cassandra  | Wide Column Store | Write-optimized, horizontal scale | Complex setup and tuning          | Time-series data, large write-heavy apps  |

**Important Notes:** Use RDBMS for transactional consistency, NoSQL for horizontal scaling and flexibility. Understand CAP theorem tradeoffs.

---

### ☁️ Message Queues & Streaming

Messaging systems decouple services and help manage asynchronous communication.

| Tool/Tech   | Type                | Pros                               | Cons                                  | When to Use                                    |
| ----------- | ------------------- | ---------------------------------- | ------------------------------------- | ---------------------------------------------- |
| Kafka       | Log-based stream    | High throughput, persistent logs   | Operational overhead                  | Event-driven systems, real-time data pipelines |
| RabbitMQ    | Message queue       | Reliable delivery, plugins         | Not ideal for high-throughput pub-sub | Traditional pub-sub use cases                  |
| AWS SQS/SNS | Managed queue       | Scales automatically, simple setup | Latency, cloud lock-in                | Lightweight event-driven apps on AWS           |
| Pulsar      | Distributed pub-sub | Tiered storage, multi-tenancy      | Newer, less community support         | Kafka alternative with better multi-tenancy    |

**Important Notes:** Kafka is ideal for replayable logs; RabbitMQ for traditional task queues. SQS/SNS work well for small-scale, serverless use cases.

---

### 🔍 Observability

Observability provides insight into system health through logs, metrics, and traces.

| Tool/Tech            | Use Case                | Pros                            | Cons                     | When to Use                  |
| -------------------- | ----------------------- | ------------------------------- | ------------------------ | ---------------------------- |
| Prometheus + Grafana | Metrics                 | Open source, flexible           | Scaling requires effort  | Infra and app monitoring     |
| Datadog              | Full-stack monitoring   | Easy to set up, rich dashboards | Expensive at scale       | SaaS app teams needing speed |
| OpenTelemetry        | Tracing/logging/metrics | Vendor-neutral, open standard   | Requires instrumentation | Unified observability stack  |

**Important Notes:** Use Prometheus for infrastructure metrics, Datadog for out-of-the-box full observability, and OpenTelemetry for future-proof, vendor-agnostic instrumentation.

---

### 🔒 Authentication & Authorization

Authentication verifies identity; authorization determines access control.

| Tool/Tech     | Type                 | Pros                 | Cons                   | When to Use                     |
| ------------- | -------------------- | -------------------- | ---------------------- | ------------------------------- |
| OAuth2 + OIDC | AuthZ/AuthN protocol | Standardized, secure | Can be complex         | External integrations, SSO      |
| JWT           | Token format         | Stateless, portable  | Hard to revoke         | API authentication, mobile apps |
| Auth0 / Okta  | Managed auth         | Quick to integrate   | Costly, vendor lock-in | Fast auth setup for SaaS        |

**Important Notes:** Use OAuth2/OIDC for federated identity, JWTs for stateless sessions, and managed providers for quick deployment with security.

---

### ⚙️ Microservices vs Monolith

| Approach      | Pros                          | Cons                               | When to Use                       |
| ------------- | ----------------------------- | ---------------------------------- | --------------------------------- |
| Monolith      | Easier to develop, deploy     | Hard to scale, tightly coupled     | Small teams, MVPs                 |
| Microservices | Scalable, independent deploys | Complex ops, distributed debugging | Large teams, independent services |

**Important Notes:** Microservices promote modularity and scale but require robust CI/CD, observability, and DevOps maturity. Start with a modular monolith when in doubt.

---

Let me know if you'd like a cheat sheet focused on database scaling, consistency models, or microservice observability patterns!


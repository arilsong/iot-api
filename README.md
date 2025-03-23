# Real-Time Device Communication API

🚧 **Project Under Construction** 🚧

An API developed in **Java Spring Boot** to manage devices, topic subscriptions, and deliver real-time messages via WebSocket.

---

## 📋 Project Description
This API allows users to:
- Register devices linked to their account.
- Subscribe to specific topics to receive messages.
- Receive real-time notifications from devices through WebSocket.

The goal is to facilitate bidirectional communication between devices (IoT, applications, etc.) and users, ensuring low latency and scalability.

---

## ⚙️ Key Features

### 📱 Device Registration
- Device registration with a unique ID, name, and user information.
- Linking devices to a user for access control.

### 🔔 Topic Management
- Users can subscribe or unsubscribe to specific topics.
- Topics organize messages by categories (e.g., `temperature`, `status`, `alerts`).

### 🌐 Real-Time WebSocket
- Sending messages from devices to subscribed users via WebSocket.
- Persistent connection for instant updates (e.g., IoT sensors notifying changes).

### 👤 User Management
- Authentication for API access.
- Permission control for devices and topics.

---

## 🛠 Technologies Used
- **Java Spring Boot**: Main framework for building the API.
- **Spring WebSocket**: Implementation of the WebSocket protocol for real-time communication.
- **Spring Data JPA**: Data persistence for devices, users, and topics.
- **MySql**: In-memory database for testing (to be replaced by PostgreSQL in production).
- **Lombok**: Simplification of code for models and DTOs.

---

## 🔜 Next Steps
- Add message persistence to the database for history.
- Create an email/SMS notification system for critical messages.
- Develop an administrative interface for monitoring topics and devices.
- Add MQTT support for low-resource IoT devices.

---

## 👨💻 Contribution
Contributions are welcome! Send suggestions, issues, or pull requests to:  
**GitHub**: https://github.com/arilsong/iot-api

---

**Note**: This API is under active development. New features and adjustments will be added progressively.  
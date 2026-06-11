# 💬 Appi - Real-Time Chat Application

Appi is a lightweight, responsive, and real-time chat application built using **Spring Boot**, **WebSockets (STOMP)**, and **Thymeleaf**. It enables instant bi-directional messaging between users connected to the chat room, utilizing a simple and modern Bootstrap interface.

---

## 🚀 Features

- **Real-Time Communication:** Direct and instant messaging using WebSockets with fallback support via SockJS.
- **Subscribed Channels:** Employs STOMP protocol to publish and subscribe to chat messages on `/topic/messages`.
- **User-Friendly UI:** Modern, clean, and responsive UI built with Bootstrap 5.
- **Robust Tech Stack:** Leverages modern Java 21 features, Spring Boot auto-configuration, and Lombok for clean boilerplate-free code.

---

## 🛠️ Tech Stack & Dependencies

- **Java Version:** 21
- **Framework:** Spring Boot 4.0.6
- **Real-Time Protocol:** Spring WebSocket (STOMP & SockJS)
- **Templating Engine:** Thymeleaf
- **Styling:** Bootstrap 5.3.8 (via CDN)
- **Developer Utilities:** Project Lombok

---

## 📂 Project Architecture

Here is the structural overview of the key components in the application:

```
appi/
├── src/
│   ├── main/
│   │   ├── java/com/chat/appi/
│   │   │   ├── config/
│   │   │   │   └── WebSocketConfig.java    # Configures STOMP endpoints & message brokers
│   │   │   ├── controller/
│   │   │   │   └── ChatController.java     # Handles message routing & chat page navigation
│   │   │   ├── model/
│   │   │   │   └── ChatMessage.java        # Message data structure (Lombok-annotated)
│   │   │   └── AppiApplication.java        # Main Spring Boot application starter
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── chat.html               # Frontend chat room UI (Thymeleaf, SockJS, STOMP)
│   │       └── application.properties       # Spring Boot server configurations
```

---

## 🔌 How It Works

### 1. WebSocket Configuration (`WebSocketConfig.java`)
The application defines a WebSocket message broker to route messages starting with `/app` to destination handlers (e.g. `@MessageMapping`) and configures `/topic` as the simple message broker prefix to send messages back to the clients.
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
```

### 2. Message Controller (`ChatController.java`)
Listens to client payloads sent to `/app/sendmessage` and broadcasts the processed message to all subscribers at `/topic/messages`.
```java
@Controller
public class ChatController {
    @MessageMapping("/sendmessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }
}
```

---

## ⚙️ Setup and Installation

### Prerequisites
- [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/) (optional, Maven wrapper is included)

### Step 1: Clone the Repository
```bash
git clone https://github.com/AkarshYadav9411/Appi.git
cd Appi
```

### Step 2: Build the Application
Compile and package the project using the included Maven wrapper:
```bash
# Windows
mvnw.cmd clean install

# macOS/Linux
./mvnw clean install
```

### Step 3: Run the Application
Start the Spring Boot application server:
```bash
# Windows
mvnw.cmd spring-boot:run

# macOS/Linux
./mvnw spring-boot:run
```

---

## 💻 Usage

1. Open your web browser and navigate to:
   ```
   http://localhost:8080/chat
   ```
2. Enter your name in the **Type name...** field.
3. Type your message and click **Send**.
4. Open the URL in multiple tabs or different browsers to test the real-time chat sync across all sessions!

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/AkarshYadav9411/Appi/issues) if you want to contribute.

---

## 📄 License
This project is open-source. Please check the LICENSE file (if available) or customize it as you see fit.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

# Telegram Bot API

## Project Description

This project contains a project template using the Telegram Bot API and a Postman collection for testing the Telegram Bot API. The collection includes essential methods for working with Telegram bots and allows easy testing of functionality without writing code.

## Project Structure

```
TelegramBot/
├── .mvn
├── src/
│   ├── main/
│   │   │── java/org/example/bot/
│   │   │   ├── config/
│   │   │   │   └── BotConfig.java         # Bot configuration
│   │   │   ├── controller/
│   │   │   │   └── BotController.java     # Command and message handling
│   │   │   ├── model/
│   │   │   │   ├── Command.java           # Command model
│   │   │   │   └── Currency.java          # Currency model
│   │   │   ├── service/
│   │   │   │   ├── CommandService.java    # Command handling service
│   │   │   │   ├── CurrencyService.java   # Currency service
│   │   │   │   └── MessageService.java    # Message service
│   │   │   ├──Bot.java                    # Main bot component
│   │   │   └── BotApplication.java        # Entry point
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/org/example/bot/
│           └── BotApplicationTests.java
│
├── Telegram Bot Api Postman/
│   └── Telegram Bot API.postman_collection.json
│
├── .gitattributes
├── .gitignore
├── Help.md
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Technologies Used

- Java 21
- Maven
- Spring Boot
- Lombok

## Backend Overview (Spring Boot Telegram Bot)

### Core Components

* **[BotApplication.java](src/main/java/org/example/bot/BotApplication.java)** — entry point of the Spring Boot application.
* **[Bot.java](src/main/java/org/example/bot/Bot.java)** — main component that connects TelegramBot and listens to updates.
* **[BotConfig.java](src/main/java/org/example/bot/config/BotConfig.java)** — configuration for connecting to the Telegram API (token, name).
* **[BotController.java](src/main/java/org/example/bot/controller/BotController.java)** — handles incoming messages and delegates commands.
* **[Command.java](src/main/java/org/example/bot/model/Command.java)** — list of supported bot commands.
* **[CurrencyModel.java](src/main/java/org/example/bot/model/CurrencyModel.java)** — model for storing currency information.
* **[CommandService.java](src/main/java/org/example/bot/service/CommandService.java)** — logic for processing commands and buttons.
* **[MessageService.java](src/main/java/org/example/bot/service/MessageService.java)** — generating textual responses for the user.
* **[CurrencyService.java](src/main/java/org/example/bot/service/CurrencyService.java)** — retrieves exchange rates from the NBU API.

### How It Works

1. **Startup:**
   Launch the application via `BotApplication`. Once started, the bot connects to Telegram via the token.

2. **Message Handling:**
   Each new message is received by [`BotController`](src/main/java/org/example/bot/controller/BotController.java), where it determines whether it’s a command (starts with `/`) or regular text/button.

3. **Commands:**
   Supported commands:

   * `/start` — greeting
   * `/help` — list of commands
   * `/info` — bot information
   * `/currency` — request for exchange rate
   * `/something` — special command
   * unknown command — reply about unknown command

4. **Exchange Rates:**
   - Support for conversion of major currencies to UAH (USD, EUR, PLN, GBP, CHF)
   - Automatic update of rates through the NBU API
   - Validation of the entered currency code
   
5. **Keyboard:**
   For the `/help` command, the bot sends a keyboard with quick command options.

### Configuration

* The bot token and name are set in `application.properties` (file should be created in `src/main/resources/`):

```
spring.application.name=bot
bot.token=YOUR_BOT_TOKEN
bot.name=YOUR_BOT_NAME
```

### Features of Implementation
   - Use of the MVC pattern
   - Modular structure for easy expansion
   - Command handling through enum to avoid code duplication

## Telegram Bot API Postman Collection

### Collection Overview

The collection contains 11 ready-to-use requests for testing various Telegram Bot API methods:

#### 1. **getMe**

* **Method**: GET
* **Purpose**: Get bot information
* **URL**: `https://api.telegram.org/bot{{TOKEN}}/getMe`

#### 2. **getUpdates**

* **Method**: GET
* **Purpose**: Receive updates (messages) for the bot
* **URL**: `https://api.telegram.org/bot{{TOKEN}}/getUpdates`

#### 3. **sendMessage**

* **Method**: POST
* **Purpose**: Send a text message
* **Request Body**:

```json
{
  "chat_id": "{{CHAT_ID}}",
  "text": "Hello from Postman!"
}
```

#### 4. **sendMessage with buttons**

* **Method**: POST
* **Purpose**: Send a message with a keyboard
* **Request Body**:

```json
{
  "chat_id": "{{CHAT_ID}}",
  "text": "Choose an option:",
  "reply_markup": {
    "keyboard": [
      [
        "Option 1",
        "Option 2"
      ],
      [
        "Option 3"
      ]
    ],
    "resize_keyboard": true,
    "one_time_keyboard": true
  }
}
```

#### 5. **sendPhoto**

* **Method**: POST
* **Purpose**: Send a photo
* **Body Type**: form-data
* **Parameters**: chat\_id, photo (file)

#### 6. **sendDocument**

* **Method**: POST
* **Purpose**: Send a document
* **Body Type**: form-data
* **Parameters**: chat\_id, document (file)

#### 7. **sendLocation**

* **Method**: POST
* **Purpose**: Send location
* **Request Body**:

```json
{
  "chat_id": "{{CHAT_ID}}",
  "latitude": 50.4501,
  "longitude": 30.5234
}
```

#### 8. **sendVenue**

* **Method**: POST
* **Purpose**: Send venue information
* **Request Body**:

```json
{
  "chat_id": "{{CHAT_ID}}",
  "latitude": 50.4501,
  "longitude": 30.5234,
  "title": "Maidan Nezalezhnosti",
  "address": "Kyiv, Ukraine"
}
```

#### 9. **sendPoll**

* **Method**: POST
* **Purpose**: Create a poll
* **Request Body**:

```json
{
  "chat_id": "{{CHAT_ID}}",
  "question": "What is your favorite color?",
  "options": [
    "Red",
    "Blue",
    "Green"
  ],
  "is_anonymous": false
}
```

#### 10. **deleteMessage**

* **Method**: POST
* **Purpose**: Delete a message
* **Request Body**:

```json
{
  "chat_id": "{{CHAT_ID}}",
  "message_id": 1
}
```

#### 11. **getWebhookInfo**

* **Method**: GET
* **Purpose**: Get webhook information
* **URL**: `https://api.telegram.org/bot{{TOKEN}}/getWebhookInfo`

### Collection Variables

The collection uses two variables:

* `TOKEN`: Your Telegram bot token
* `CHAT_ID`: Chat ID for sending messages

## Detailed Guide to Creating and Using a Telegram Bot

### Step 1: Creating a Telegram Bot

1. **Find BotFather in Telegram**

   * Open Telegram
   * Search for `@BotFather`
   * Click "Start"

2. **Create a new bot**

   ```
   /newbot
   ```

3. **Enter the bot name**

   ```
   My Test Bot
   ```

4. **Enter the bot username (must end with 'bot')**

   ```
   my_test_bot
   ```

5. **Get the token**

   * BotFather will provide a token in the format: `123456789:ABCdefGHIjklMNOpqrsTUVwxyz`
   * **IMPORTANT**: Keep the token safe!

### Step 2: Getting Chat ID

1. **Add the bot to a chat or message it directly**

2. **Send a message to the bot**

3. **Use the getUpdates method**

   ```
   https://api.telegram.org/bot<YOUR_TOKEN>/getUpdates
   ```

4. **Find chat\_id in the response**

   ```json
    {
    	"ok": true,
    	"result": [
    		{
    			"update_id": "SomeID",
    			"message": {
    				"message_id": 1353,
    				"from": {
    					"id": "ChatID",
    					"is_bot": false,
    					"first_name": "YourFirstName",
    					"username": "YourUserName",
    					"language_code": "uk"
    				},
    				"chat": {
    					"id": "ChatID",
    					"first_name": "YourFirstName",
    					"username": "YourUserName",
    					"type": "private"
    				},
    				"date": 1751450617,
    				"text": "/start",
    				"entities": [
    					{
    						"offset": 0,
    						"length": 6,
    						"type": "bot_command"
    					}
    				]
    			}
    		}
    	]
    }
   ```

### Step 3: Postman Setup

1. **Import the collection**

   * Open Postman
   * File → Import
   * Select `Telegram Bot API.postman_collection.json`

2. **Set variables**

   * Go to the collection
   * "Variables" tab
   * Set values for `TOKEN` and `CHAT_ID`

3. **Test the methods**

   * Start with `getMe` to verify the token
   * Use `sendMessage` to send a test message

### Step 4: Using API Methods

#### Basic Testing

```bash
# Check bot
GET https://api.telegram.org/bot<TOKEN>/getMe

# Get updates
GET https://api.telegram.org/bot<TOKEN>/getUpdates

# Send message
POST https://api.telegram.org/bot<TOKEN>/sendMessage
Content-Type: application/json

{
  "chat_id": "<CHAT_ID>",
  "text": "Hello!"
}
```

#### Working with Files

For sending files use `multipart/form-data`:

```
POST https://api.telegram.org/bot<TOKEN>/sendPhoto
Content-Type: multipart/form-data

chat_id: <CHAT_ID>
photo: [file]
```

#### Creating a Keyboard

```json
{
  "chat_id": "<CHAT_ID>",
  "text": "Choose an option:",
  "reply_markup": {
    "keyboard": [
      [
        "Button 1",
        "Button 2"
      ],
      [
        "Button 3"
      ]
    ],
    "resize_keyboard": true,
    "one_time_keyboard": true
  }
}
```


## Quick Start
1. Clone: `git clone https://github.com/Maksym-Kovbasa/TelegramBot.git`
2. Configure: Create `application.properties` with bot token
3. Run: `mvn spring-boot:run`
4. Test: Send `/start` to your bot


## Security

⚠️ **IMPORTANT**:

* Never publish your bot token publicly
* Use environment variables to store tokens
* Regularly refresh tokens via BotFather

## Support

If you encounter questions or issues:

1. Verify the token using the `getMe` method
2. Ensure `CHAT_ID` is correct
3. Check request formats according to API documentation
4. 
## Useful Links

* [Official Telegram Bot API Documentation](https://core.telegram.org/bots/api)
* [Library from pengrad/java-telegram-bot-api](https://github.com/pengrad/java-telegram-bot-api)
* [BotFather](https://t.me/botfather)
* [Postman Documentation](https://learning.postman.com/)
* [The project that was taken as an example](https://habr.com/ru/articles/715384/)
## ATM Simulation System
A robust, console-based Java application that simulates real-world ATM functionalities, including user authentication, financial transactions, and an administrative dashboard.

### Project Structure
The project follows Object-Oriented Programming (OOP) principles by separating concerns into four primary classes:

- Main.java: The entry point. Handles the user interface, input scanning, and program flow.

-  ATM.java: The core engine. Contains the business logic for all banking operations.

- User.java: The data model for individual bank customers.

- Transaction.java: The data model for logging every action performed within the system.

### Method & Logic Breakdown
1. Core Banking Operations `_(ATM.java)_`
- This class manages the interaction between the user's account and the ATM's cash reserves.

`validateUser(int accNo, int pin):` Iterates through the totalUsers list to match credentials. Returns the User object if successful, otherwise null.

`withdraw(User user, double amt):`

- Validation: Checks if the amount is a multiple of 100 and ensures the ATM and User have sufficient funds.

- Updates: Deducts balance from both the User and the ATM's TotalAmountAvailable.

`fundtransfer(User user, int recAccNo, double amt):`

- Locates the recipient using `findUser().`

- Atomically deducts from the sender and adds to the receiver.

- Logs unique transaction types: FUND_TRANSFER_FROM and FUND_TRANSFER_TO.

- adminDashboard(): A protected view (triggered by account 99999) that provides high-level stats like total deposits and unique user counts using a HashSet.

2. Data Persistence `(User.java & Transaction.java)`
User: Stores personal details and an individual ArrayList<Transaction> for their mini-statement.

- Transaction: Captures a snapshot of an action (ID, Type, Amount, and User ID).

3. Execution Flow `(Main.java)`
- Initialization: Hardcodes initial users and ATM cash.

- Authentication Loop: Uses a do-while loop to ensure a user (or admin) is logged in before showing the menu.

- Menu System: A switch-case block inside a while loop that keeps the session active until the user chooses to exit.

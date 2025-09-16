# Liquibase Starter Project

A Spring Boot 3.5.0 application demonstrating database schema management with Liquibase, featuring a complete e-commerce domain model with Users, Products, Orders, and OrderItems.

## Features

- **Spring Boot 3.5.0** with Java 21
- **Liquibase** for database schema versioning and migrations
- **JPA/Hibernate** for object-relational mapping
- **H2 Database** for development and testing
- **RESTful API** endpoints for all entities
- **Validation** with Bean Validation annotations
- **Transaction Management** with Spring's @Transactional

## Project Structure

```
src/
├── main/
│   ├── java/com/example/liquibasestarter/
│   │   ├── LiquibaseStarterApplication.java    # Main application class
│   │   ├── config/
│   │   │   └── LiquibaseConfig.java           # Liquibase configuration
│   │   ├── controller/
│   │   │   ├── UserController.java             # User REST endpoints
│   │   │   └── ProductController.java          # Product REST endpoints
│   │   ├── entity/
│   │   │   ├── User.java                      # User entity
│   │   │   ├── Product.java                   # Product entity
│   │   │   ├── Order.java                     # Order entity
│   │   │   └── OrderItem.java                 # OrderItem entity
│   │   ├── repository/
│   │   │   ├── UserRepository.java            # User data access
│   │   │   ├── ProductRepository.java         # Product data access
│   │   │   └── OrderRepository.java           # Order data access
│   │   └── service/
│   │       ├── UserService.java               # User business logic
│   │       └── ProductService.java            # Product business logic
│   └── resources/
│       ├── application.yml                     # Application configuration
│       └── db/
│           └── changelog/
│               ├── db.changelog-master.xml    # Main changelog
│               └── changes/
│                   ├── 001-initial-schema.xml # Database schema creation
│                   └── 002-sample-data.xml    # Sample data insertion
```

## Database Schema

The application creates the following tables:

- **users**: User information (username, email, first/last name)
- **products**: Product catalog (name, description, price, category, stock)
- **orders**: Customer orders (user reference, status, total amount)
- **order_items**: Order line items (product, quantity, unit price)

## Liquibase Changelogs

### 001-initial-schema.xml
Creates the database schema with:
- All tables with proper constraints
- Foreign key relationships
- Timestamps for auditing

### 002-sample-data.xml
Inserts sample data:
- Two sample users (John Doe, Jane Smith)
- Three sample products (Laptop, Mouse, Keyboard)
- One sample order with items

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd liquibase-starter
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: `password`

## API Endpoints

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `GET /api/users/email/{email}` - Get user by email
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user
- `GET /api/users/search?q={term}` - Search users

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product
- `GET /api/products/category/{category}` - Get products by category
- `GET /api/products/price-range?minPrice={min}&maxPrice={max}` - Get products by price range
- `GET /api/products/available` - Get available products (in stock)
- `GET /api/products/search?q={term}` - Search products
- `PATCH /api/products/{id}/stock?quantity={qty}` - Update stock quantity

## Database Migrations

Liquibase automatically runs database migrations when the application starts. The changelog files are processed in order:

1. **001-initial-schema.xml** - Creates the database structure
2. **002-sample-data.xml** - Populates with sample data

To add new migrations:
1. Create a new changelog file in `src/main/resources/db/changelog/changes/`
2. Include it in `db.changelog-master.xml`
3. Restart the application

## Configuration

The application uses `application.yml` for configuration:

- **Database**: H2 in-memory database
- **JPA**: Hibernate with SQL logging enabled
- **Liquibase**: Enabled with debug logging
- **H2 Console**: Enabled for database inspection

## Development

### Adding New Entities
1. Create the entity class with JPA annotations
2. Create the repository interface
3. Create the service class with business logic
4. Create the controller with REST endpoints
5. Add database migration changelog if needed

### Testing
Run tests with:
```bash
mvn test
```

### Building
Create a JAR file with:
```bash
mvn clean package
```

## Troubleshooting

### Common Issues

1. **Port already in use**: Change the port in `application.yml`
2. **Database connection issues**: Check H2 console credentials
3. **Liquibase errors**: Check changelog XML syntax and database state

### Logs
Enable debug logging for Liquibase and database operations in `application.yml`.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.
